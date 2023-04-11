package cn.jasonone.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * 演员表
 * @TableName player
 */
@Data
public class Player implements Serializable {
    /**
     * 演员id
     */
    private Integer playerid;

    /**
     * 演员名字
     */
    private String playername;

    /**
     * 演员图片
     */
    private String playerimg;

    private static final long serialVersionUID = 1L;
}