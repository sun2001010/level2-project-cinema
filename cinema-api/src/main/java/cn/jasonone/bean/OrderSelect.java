package cn.jasonone.bean;

import lombok.Data;

@Data
public class OrderSelect {
    private Integer id;
    private Integer page;
    private Integer limit;
}
