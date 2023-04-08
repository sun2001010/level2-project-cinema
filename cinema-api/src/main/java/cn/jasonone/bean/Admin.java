package cn.jasonone.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * 管理员表
 * @TableName admin
 */
@Data
public class Admin implements Serializable {
    /**
     * 管理员id
     */
    private Integer aId;

    /**
     * 管理员名
     */
    private String aName;

    /**
     * 管理员密码
     */
    private String aPassword;

    /**
     * 盐值
     */
    private String aSalt;

    /**
     * 管理员密保
     */
    private String aConfidentiality;

    /**
     * 管理员电话号码
     */
    private String aPhone;

    /**
     * 管理员邮箱
     */
    private String aEmail;

    private static final long serialVersionUID = 1L;
}