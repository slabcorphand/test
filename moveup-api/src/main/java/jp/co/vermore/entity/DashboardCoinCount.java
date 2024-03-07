package jp.co.vermore.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DashboardCoinCount {
    private Long id;
    private String monthYear;
    private Integer coinAmount;
    private Date createDatetime;
    private Date updateDatetime;
    private Boolean delFlg;
    private String note;
}
