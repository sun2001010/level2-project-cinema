package cn.jasonone.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 场次表
 * @TableName session
 */
@Data
public class Session implements Serializable {
    /**
     * 场次id
     */
    private Integer seId;

    /**
     * 影片开始时间
     */
    private Date seStarttime;

    /**
     * 影片结束时间
     */
    private Date seEndtime;

    /**
     * 影片名
     */
    private String fName;

    /**
     * 影厅名
     */
    private String chName;

    /**
     * 影院名
     */
    private String cName;

    private static final long serialVersionUID = 1L;
}