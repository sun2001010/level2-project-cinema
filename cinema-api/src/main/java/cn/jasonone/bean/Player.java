package cn.jasonone.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * 演员表
 * @author 凌久熙
 * @TableName player
 */
@Data
public class Player implements Serializable {
    /**
     * 演员id
     */
    private Integer playerId;

    /**
     * 演员名字
     */
    private String playerName;

    /**
     * 演员图片
     */
    private String playerImg;

    private static final long serialVersionUID = 1L;
}