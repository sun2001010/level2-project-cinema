package cn.jasonone.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName seats
 */
@Data
public class Seats implements Serializable {
    /**
     * 座位id
     */
    private Integer sId;

    /**
     * 行
     */
    private String sRow;

    /**
     * 列
     */
    private Integer sCol;

    /**
     * 0未选1已选
     */
    private Integer sStatus;

    /**
     * 影厅id
     */
    private Integer hId;

    private static final long serialVersionUID = 1L;
}