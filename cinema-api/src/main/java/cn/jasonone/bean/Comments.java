package cn.jasonone.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * 评论表
 * @TableName comments
 */
@Data
public class Comments implements Serializable {
    /**
     * 评论id
     */
    private Integer commId;

    /**
     * 电影名
     */
    private String fName;

    /**
     * 用户名
     */
    private String uName;

    /**
     * 评论
     */
    private String comments;

    private static final long serialVersionUID = 1L;
}