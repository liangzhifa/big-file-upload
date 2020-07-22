package com.zhifa.bigfileupload.service;

import com.zhifa.bigfileupload.constance.FileConstance;
import com.zhifa.bigfileupload.domain.FileTb;
import com.zhifa.bigfileupload.mapper.FileTbMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class FileTbService {
    @Autowired
    private FileTbMapper fileTbMapper;

    public void saveFile(FileTb fileTb) {
        fileTbMapper.insert(fileTb);
    }

    public void UpdateFile(FileTb fileTb) {
        fileTbMapper.UpdateFile(fileTb);
    }

    public boolean isNotExist(String key){
        Integer id = fileTbMapper.isExist(key);
        if (ObjectUtils.isEmpty(id)) {
            return true;
        }
        return false;
    }
    public FileTb selectLatestIndex(String key) {
        FileTb fileTb = fileTbMapper.selectLatestIndex(key);
        if (ObjectUtils.isEmpty(fileTb)) {
            fileTb = FileTb.builder().fKey(key).fIndex(-1).fName("").build();
        }else {
            fileTb.setFName(FileConstance.ACCESS_PATH+fileTb.getFName());
        }
        return fileTb;
    }

}
