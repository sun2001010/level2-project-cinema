package cn.jasonone.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * 电影演员关系表
 * @TableName film_player
 */
@Data
public class FilmPlayer implements Serializable {
    /**
     * 
     */
    private Integer fPId;

    /**
     * 电影id
     */
    private Integer filmid;

    /**
     * 电影名
     */
    private String filmname;

    /**
     * 演员id
     */
    private Integer playerid;

    /**
     * 演员名字
     */
    private String playername;

    private static final long serialVersionUID = 1L;
}