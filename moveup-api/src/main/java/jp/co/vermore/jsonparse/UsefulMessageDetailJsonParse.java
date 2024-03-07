package jp.co.vermore.jsonparse;

import jp.co.vermore.common.mvc.BaseJsonParse;

import java.util.List;

/**
 * UsefulMessageDetailJsonParse
 * Created by wubin.
 *
 * DateTime: 2018/03/13 16:52
 * Copyright: sLab, Corp
 */

public class UsefulMessageDetailJsonParse extends BaseJsonParse {

    private Long usefulMessageId;

    private String entry;

    private String date;

    private String typeStr;

    private int type;

    private String color;

    private String title;

    private String detail;

    private List<String> topPic;

    private List<String> footPic;

    private List<UsefulMessageJsonParse> usefulMessagePre;

    private List<UsefulMessageJsonParse> usefulMessageNext;

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<UsefulMessageJsonParse> getUsefulMessagePre() {
        return usefulMessagePre;
    }

    public void setUsefulMessagePre(List<UsefulMessageJsonParse> usefulMessagePre) {
        this.usefulMessagePre = usefulMessagePre;
    }

    public List<UsefulMessageJsonParse> getUsefulMessageNext() {
        return usefulMessageNext;
    }

    public void setUsefulMessageNext(List<UsefulMessageJsonParse> usefulMessageNext) {
        this.usefulMessageNext = usefulMessageNext;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public Long getUsefulMessageId() {
        return usefulMessageId;
    }

    public void setUsefulMessageId(Long usefulMessageId) {
        this.usefulMessageId = usefulMessageId;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public List<String> getTopPic() {
        return topPic;
    }

    public void setTopPic(List<String> topPic) {
        this.topPic = topPic;
    }

    public List<String> getFootPic() {
        return footPic;
    }

    public void setFootPic(List<String> footPic) {
        this.footPic = footPic;
    }
}
