package cn.jasonone.bean;

import lombok.Data;

import javax.servlet.annotation.WebServlet;

@Data
public class Seat {
    private Integer sId;
    private String sRow;
    private Integer sCol;
    private Integer sStatus;
    private Integer hId;
}
