package cn.jasonone.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * 电影演员关系表
 * @author 凌久熙
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
    private Integer fId;

    /**
     * 电影名
     */
    private String fName;

    /**
     * 演员id
     */
    private Integer playId;

    /**
     * 演员名字
     */
    private String playerName;

    private static final long serialVersionUID = 1L;
}