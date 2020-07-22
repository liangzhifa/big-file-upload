package com.zhifa.bigfileupload.controller;

import com.zhifa.bigfileupload.constance.FileConstance;
import com.zhifa.bigfileupload.domain.FileTb;
import com.zhifa.bigfileupload.pojo.FilePojo;
import com.zhifa.bigfileupload.service.FileTbService;
import com.zhifa.bigfileupload.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@Slf4j
public class FileUploadController {
    @Autowired
    private FileTbService fileTbService;
    @PostMapping(value = "/upload")
    public Result upload(@RequestParam(value = "file") MultipartFile file,
                         FilePojo filePojo) throws Exception {
        File fullDir = new File(FileConstance.FILE_PATH);
        if (!fullDir.exists()) {
            fullDir.mkdir();
        }

        //uid 防止文件名重复,又可以作为文件的唯一标识
        String fullPath = FileConstance.FILE_PATH + filePojo.getKey() + "." + filePojo.getShardIndex();
        File dest = new File(fullPath);
        file.transferTo(dest);
        log.info("文件分片 {} 保存完成",filePojo.getShardIndex());

        //开始保存索引分片信息 bu不存在就新加 存在就修改索引分片
        FileTb fileTb = FileTb.builder()
                .fKey(filePojo.getKey())
                .fIndex(Math.toIntExact(filePojo.getShardIndex()))
                .fTotal(Math.toIntExact(filePojo.getShardTotal()))
                .fName(filePojo.getFileName())
                .build();
        if (fileTbService.isNotExist(filePojo.getKey())) {
            fileTbService.saveFile(fileTb);
        }else {
            fileTbService.UpdateFile(fileTb);
        }


        if (filePojo.getShardIndex() == filePojo.getShardTotal()) {
            //开始合并
            merge(filePojo);
            return Result.success(FileConstance.ACCESS_PATH + filePojo.getFileName());
        }
        return Result.success();
    }

    public void merge(FilePojo filePojo) throws Exception {
        Long shardTotal = filePojo.getShardTotal();
        File newFile = new File(FileConstance.FILE_PATH + filePojo.getFileName());
        if (newFile.exists()) {
            newFile.delete();
        }
        FileOutputStream outputStream = new FileOutputStream(newFile, true);//文件追加写入
        FileInputStream fileInputStream = null;//分片文件
        byte[] byt = new byte[10 * 1024 * 1024];
        int len;
        try {
            for (int i = 0; i < shardTotal; i++) {
                // 读取第i个分片
                fileInputStream = new FileInputStream(new File(FileConstance.FILE_PATH + filePojo.getKey() + "." + (i + 1))); //  course\6sfSqfOwzmik4A4icMYuUe.mp4.1
                while ((len = fileInputStream.read(byt)) != -1) {
                    outputStream.write(byt, 0, len);//一直追加到合并的新文件中
                }
            }
        } catch (IOException e) {
            log.error("分片合并异常", e);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                outputStream.close();
                log.info("IO流关闭");
                System.gc();
            } catch (Exception e) {
                log.error("IO流关闭", e);
            }
        }
        log.info("合并分片结束");
    }

    //文件上传之前判断是否已经上传过 -1就是没有
    @GetMapping("/check")
    public Result check(@RequestParam String key){
        FileTb fileTb = fileTbService.selectLatestIndex(key);
        log.info("检查分片：{}");
        return Result.success(fileTb);

    }

}
