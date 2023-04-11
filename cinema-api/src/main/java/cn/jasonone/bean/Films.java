package cn.jasonone.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 影片表
 * @TableName films
 */
@Data
public class Films implements Serializable {
    /**
     * 影片id
     */
    private Integer fId;

    /**
     * 影片名
     */
    private String fName;

    /**
     * 影片图片
     */
    private String fImage;

    /**
     * 影片类型
     */
    private String oType;

    /**
     * 导演
     */
    private String director;

    /**
     * 导演图片
     */
    private String directorImage;

    /**
     * 影片简介
     */
    private String fContent;

    /**
     * 上映时间
     */
    private Date fTime;

    /**
     * 基础影片票价
     */
    private Double fPrice;

    private static final long serialVersionUID = 1L;
}