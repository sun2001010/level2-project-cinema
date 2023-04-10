package cn.jasonone.bean;

import java.io.Serializable;

/**
 * 影片表
 * @author 凌久熙
 */

public class Film implements Serializable {
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
    private String fTime;
    /**
     * 影片售价
     */
    private String fPrice;

}
