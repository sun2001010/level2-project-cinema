package cn.jasonone.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * 评分表
 * @TableName score
 */
@Data
public class Score implements Serializable {
    /**
     * 评分id
     */
    private Integer sId;

    /**
     * 影片名
     */
    private String fName;

    /**
     * 评分的用户名
     */
    private String uName;

    /**
     * 评分
     */
    private Double sScore;

    /**
     * 评价内容
     */
    private String sContent;

    private static final long serialVersionUID = 1L;
}