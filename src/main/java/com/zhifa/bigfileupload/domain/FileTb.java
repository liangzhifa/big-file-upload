package com.zhifa.bigfileupload.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "file_tb")
public class FileTb implements Serializable {
    public static final String COL_KEY = "key";
    public static final String COL_INDEX = "index";
    public static final String COL_TOTAL = "total";
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文件唯一标识
     */
    @TableField(value = "f_key")
    private String fKey;

    /**
     * 第几个分片
     */
    @TableField(value = "f_index")
    private Integer fIndex;

    /**
     * 共有几个分片
     */
    @TableField(value = "f_total")
    private Integer fTotal;

    /**
     * 文件名称，后面可以返回出去
     */
    @TableField(value = "f_name")
    private String fName;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_F_KEY = "f_key";

    public static final String COL_F_INDEX = "f_index";

    public static final String COL_F_TOTAL = "f_total";

    public static final String COL_F_NAME = "f_name";

    public static FileTbBuilder builder() {
        return new FileTbBuilder();
    }
}