package jp.co.vermore.entity;

import jp.co.vermore.common.mvc.BaseEntity;

import java.util.Date;

public class Event extends BaseEntity{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.id
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.uuid
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    private String uuid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.sort_score
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    private Integer sortScore;


    private Integer type;

    private Integer entryType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.hold_date
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    private Date holdDate;

    private String dateStr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.pic_url
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    private String picUrl;

    private Integer picListNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.title
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.guest_name
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    private String guestName;

    private String hallName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.excerpt
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    private String excerpt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.publish_start
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    private Date publishStart;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.publish_end
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    private Date publishEnd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.create_datetime
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    private Date createDatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.update_datetime
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    private Date updateDatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.del_flg
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    private Boolean delFlg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.note
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    private String note;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.id
     *
     * @return the value of event.id
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.id
     *
     * @param id the value for event.id
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.uuid
     *
     * @return the value of event.uuid
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.uuid
     *
     * @param uuid the value for event.uuid
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.sort_score
     *
     * @return the value of event.sort_score
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public Integer getSortScore() {
        return sortScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.sort_score
     *
     * @param sortScore the value for event.sort_score
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public void setSortScore(Integer sortScore) {
        this.sortScore = sortScore;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEntryType() {
        return entryType;
    }

    public void setEntryType(Integer entryType) {
        this.entryType = entryType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.hold_date
     *
     * @return the value of event.hold_date
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public Date getHoldDate() {
        return holdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.hold_date
     *
     * @param holdDate the value for event.hold_date
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public void setHoldDate(Date holdDate) {
        this.holdDate = holdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.pic_url
     *
     * @return the value of event.pic_url
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.pic_url
     *
     * @param picUrl the value for event.pic_url
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getPicListNo() {
        return picListNo;
    }

    public void setPicListNo(Integer picListNo) {
        this.picListNo = picListNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.title
     *
     * @return the value of event.title
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.title
     *
     * @param title the value for event.title
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.guest_name
     *
     * @return the value of event.guest_name
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.guest_name
     *
     * @param guestName the value for event.guest_name
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.hall_name
     *
     * @return the value of event.hall_name
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public String getHallName() {
        return hallName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.hall_name
     *
     * @param hallName the value for event.hall_name
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.excerpt
     *
     * @return the value of event.excerpt
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public String getExcerpt() {
        return excerpt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.excerpt
     *
     * @param excerpt the value for event.excerpt
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.publish_start
     *
     * @return the value of event.publish_start
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public Date getPublishStart() {
        return publishStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.publish_start
     *
     * @param publishStart the value for event.publish_start
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public void setPublishStart(Date publishStart) {
        this.publishStart = publishStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.publish_end
     *
     * @return the value of event.publish_end
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public Date getPublishEnd() {
        return publishEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.publish_end
     *
     * @param publishEnd the value for event.publish_end
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public void setPublishEnd(Date publishEnd) {
        this.publishEnd = publishEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.create_datetime
     *
     * @return the value of event.create_datetime
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public Date getCreateDatetime() {
        return createDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.create_datetime
     *
     * @param createDatetime the value for event.create_datetime
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.update_datetime
     *
     * @return the value of event.update_datetime
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.update_datetime
     *
     * @param updateDatetime the value for event.update_datetime
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.del_flg
     *
     * @return the value of event.del_flg
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public Boolean getDelFlg() {
        return delFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.del_flg
     *
     * @param delFlg the value for event.del_flg
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public void setDelFlg(Boolean delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.note
     *
     * @return the value of event.note
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.note
     *
     * @param note the value for event.note
     *
     * @mbggenerated Mon Apr 16 16:47:42 JST 2018
     */
    public void setNote(String note) {
        this.note = note;
    }


    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

}