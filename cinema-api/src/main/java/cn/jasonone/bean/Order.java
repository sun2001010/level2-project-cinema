package cn.jasonone.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单表
 * @TableName order
 */
@Data
public class Order implements Serializable {
    /**
     * 订单id
     */
    private Integer oId;

    /**
     * 影片名
     */
    private String fName;

    /**
     * 影片类型
     */
    private String oType;

    /**
     * 影片开始时间
     */
    private Date startTime;

    /**
     * 影片结束时间
     */
    private Date endTime;

    /**
     * 用户名
     */
    private Integer uId;

    /**
     * 影片票价
     */
    private Double oPrice;

    /**
     * 影厅名
     */
    private String chName;

    /**
     * 影院名
     */
    private String cName;

    /**
     * 影院地址
     */
    private String cAddress;
    private Integer cStatus;

    private static final long serialVersionUID = 1L;
}