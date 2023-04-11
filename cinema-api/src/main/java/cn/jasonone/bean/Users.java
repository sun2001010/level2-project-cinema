package cn.jasonone.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户表
 * @TableName users
 */
@Data
public class Users implements Serializable {
    /**
     * 用户id
     */
    private Integer uId;

    /**
     * 用户名
     */
    private String uName;

    /**
     * 用户密码
     */
    private String uPassword;

    /**
     * 用户密保
     */
    private String uConfidentiality;

    /**
     * 盐值
     */
    private String uSalt;

    /**
     * 用户电话号码
     */
    private String uPhone;

    /**
     * 用户邮箱
     */
    private String uEmail;

    private static final long serialVersionUID = 1L;
}