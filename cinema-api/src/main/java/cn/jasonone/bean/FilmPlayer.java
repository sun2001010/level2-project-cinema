package cn.jasonone.bean;

import java.io.Serializable;

/**
 * 影片演员关系表
 * @author 凌久渊
 */
public class FilmPlayer implements Serializable {
    /**
     * 影片演员关系id
     */
    private Integer fpId;
    /**
     * 影片id
     */
    private Integer fId;
    /**
     * 影片名
     */
    private String fName;
    /**
     * 演员id
     */
    private Integer pId;
    /**
     * 演员名
     */
    private String pName;
}
