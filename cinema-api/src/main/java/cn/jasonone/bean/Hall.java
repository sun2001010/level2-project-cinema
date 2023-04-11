package cn.jasonone.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * 影厅表
 * @TableName hall
 */
@Data
public class Hall implements Serializable {
    /**
     * 影厅id
     */
    private Integer hId;

    /**
     * 影厅名
     */
    private String hName;

    /**
     * 影厅级别
     */
    private Integer hLevel;

    /**
     * 影院名
     */
    private String cName;

    private static final long serialVersionUID = 1L;
}