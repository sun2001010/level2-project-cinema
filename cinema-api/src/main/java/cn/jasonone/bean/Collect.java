package cn.jasonone.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收藏表
 * @TableName collection
 */
@Data
public class Collect implements Serializable {
    /**
     * 收藏id
     */
    private Integer colId;

    /**
     * 用户名
     */
    private String uName;

    /**
     * 影片名
     */
    private String fName;

    /**
     * 影片类型
     */
    private String oType;

    /**
     * 收藏时间
     */
    private Date colTime;

    private static final long serialVersionUID = 1L;
}