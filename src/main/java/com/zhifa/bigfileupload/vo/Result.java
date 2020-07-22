package com.zhifa.bigfileupload.vo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Result {
    private int code = 200;
    private boolean status = true;
    private String msg = "操作成功";
    private String url = "";
    private Object Data;

    public static Result success(){
        return Result.builder().code(200).status(true).msg("操作成功").build();
    }
    public static Result success(Object data){
        return Result.builder().code(200).status(true).msg("操作成功").Data(data).build();
    }
    public static Result success(Object data,String url){
        return Result.builder().code(200).status(true).msg("操作成功").Data(data).url(url).build();
    }

}
