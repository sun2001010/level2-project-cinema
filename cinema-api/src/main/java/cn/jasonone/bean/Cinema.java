package cn.jasonone.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * 影院表
 * @TableName cinema
 */
@Data
public class Cinema implements Serializable {
    /**
     * 影院id
     */
    private Integer cId;

    /**
     * 影院名
     */
    private String cName;

    /**
     * 影院级别
     */
    private Integer cLevel;

    /**
     * 影院地址
     */
    private String cAddress;

    private static final long serialVersionUID = 1L;
}