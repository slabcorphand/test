package jp.co.vermore.service;

import jp.co.vermore.common.Constant;
import jp.co.vermore.common.util.DateUtil;
import jp.co.vermore.common.util.StringUtil;
import jp.co.vermore.entity.UsefulMessage;
import jp.co.vermore.entity.UsefulMessageDetail;
import jp.co.vermore.form.admin.UsefulMessageListForm;
import jp.co.vermore.form.admin.UsefulMessageForm;
import jp.co.vermore.mapper.UsefulMessageDetailMapper;
import jp.co.vermore.mapper.UsefulMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * UsefulMessageService
 */

@Service

public class UsefulMessageService {

    @Autowired
    private UsefulMessageMapper usefulMessageMapper;

    public UsefulMessage getUsefulMessageByUuid(String uuid) {
        UsefulMessage entity = usefulMessageMapper.getUsefulMessageByUuid(uuid);
        return entity;
    }

    public List<UsefulMessage> getUsefulMessageAll() {
        List<UsefulMessage> usefulMessageList = usefulMessageMapper.getUsefulMessageAll();
        return usefulMessageList;
    }

    public List<UsefulMessage> getUsefulMessageAllForTop() {
        String nowMin = DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(0);
        String nextMin = DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(1);
//        String now= DateUtil.dateToStringyyyy_MM_dd_HH_mm(new Date(System.currentTimeMillis()));
        List<UsefulMessage> usefulMessageList = usefulMessageMapper.getUsefulMessageAllForTop(nowMin, nextMin);
        return usefulMessageList;
    }

    public List<UsefulMessage> getUsefulMessageCategory(int type, int limit, int offset) {
        List<UsefulMessage> usefulMessageList = usefulMessageMapper.getUsefulMessageMapperCategory(type, limit, offset);
        return usefulMessageList;
    }

    public List<UsefulMessage> getUsefulMessagePre(Date date) {
        String nowMin = DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(0);
        String nextMin = DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(1);
        List<UsefulMessage> usefulMessage = usefulMessageMapper.getUsefulMessageMapperPre(date, nowMin, nextMin);
        return usefulMessage;
    }

    public List<UsefulMessage> getUsefulMessageNext(Date date) {
        String nowMin = DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(0);
        String nextMin = DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(1);
        List<UsefulMessage> usefulMessage = usefulMessageMapper.getUsefulMessageMapperNext(date, nowMin, nextMin);
        return usefulMessage;
    }

    public List<UsefulMessage> getUsefulMessageAll(int type, int limit, int offset) {
        String nowMin = DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(0);
        String nextMin = DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(1);
        List<UsefulMessage> usefulMessageList = usefulMessageMapper.getUsefulMessageJsonAll(type, nowMin, nextMin, limit, offset);
        return usefulMessageList;
    }

    public List<UsefulMessage> getUsefulMessageAllByType(int type) {
        String nowMin = DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(0);
        String nextMin = DateUtil.getTimeByMinuteYyyy_MM_ddHHmm(1);
        List<UsefulMessage> usefulMessageList = usefulMessageMapper.getUsefulMessageJsonAllByType(type, nowMin, nextMin);
        return usefulMessageList;
    }

    public UsefulMessage getUsefulMessageByIdAndType(long id, int type) {
        UsefulMessage usefulMessage = usefulMessageMapper.getUsefulMessageByIdAndType(id, type);
        return usefulMessage;
    }

    public List<UsefulMessage> getUsefulMessageEventAll(int type1, int type2, int limit, int offset) {
        String today = DateUtil.getYyyyMMdd();
        String tomorrow = DateUtil.getTomorrow();
        List<UsefulMessage> usefulMessageList = usefulMessageMapper.getUsefulMessageEventAll(type1, type2, tomorrow, today, limit, offset);
        return usefulMessageList;
    }

    private List<UsefulMessage> convertTo(List<UsefulMessage> demoList) {
        List<UsefulMessage> resultList = new LinkedList<UsefulMessage>();
        for (UsefulMessage entity : demoList) {
            resultList.add(entity);
        }
        return resultList;
    }

    @Autowired
    private UsefulMessageMapper addUsefulMessageMapper;

    public long insertUsefulMessage(UsefulMessageForm usefulMessageForm) {
        UsefulMessage usefulMessage = new UsefulMessage();
        String uuid = "";
        int flagUuid = 0;
        int cntSelect = 0;
        while (flagUuid != 1 && cntSelect < 100) {
            uuid = StringUtil.getUuid();
            if (getUsefulMessageByUuid(uuid) == null) {
                flagUuid = 1;
            }
            cntSelect++;
        }

        usefulMessage.setUuid(uuid);
        String date = usefulMessageForm.getDate();
        usefulMessage.setDate(DateUtil.stringToDateyyyy_MM_dd_HH_mm(date.replace("T", " ")));
        usefulMessage.setTitle(usefulMessageForm.getTitle());
        usefulMessage.setType(usefulMessageForm.getType());
        usefulMessage.setSortScore(usefulMessageForm.getSortScore());
        usefulMessage.setExcerpt(usefulMessageForm.getExcerpt());
        if (usefulMessageForm.getPublishStart() == null || "".equals(usefulMessageForm.getPublishStart())) {
            usefulMessage.setPublishStart(DateUtil.getDefaultDate());
        } else {
            usefulMessage.setPublishStart(DateUtil.stringToDateyyyy_MM_dd_HH_mm(usefulMessageForm.getPublishStart().replace("T", " ")));
        }
        if (usefulMessageForm.getPublishEnd() == null || "".equals(usefulMessageForm.getPublishEnd())) {
            usefulMessage.setPublishEnd(DateUtil.getDefaultPublishEnd());
        } else {
            usefulMessage.setPublishEnd(DateUtil.stringToDateyyyy_MM_dd_HH_mm(usefulMessageForm.getPublishEnd().replace("T", " ")));
        }
        usefulMessage.setCreateDatetime(new Date(System.currentTimeMillis()));
        usefulMessage.setDelFlg(Boolean.FALSE);
        usefulMessage.setNote(Constant.EMPTY_STRING);
        addUsefulMessageMapper.insertUsefulMessage(usefulMessage);
        return usefulMessage.getId();
    }

    public long insertStudioUsefulMessage(UsefulMessageForm usefulMessageForm) {
        UsefulMessage usefulMessage = new UsefulMessage();
        String uuid = "";
        int flagUuid = 0;
        int cntSelect = 0;
        while (flagUuid != 1 && cntSelect < 100) {
            uuid = StringUtil.getUuid();
            if (getUsefulMessageByUuid(uuid) == null) {
                flagUuid = 1;
            }
            cntSelect++;
        }

        usefulMessage.setUuid(uuid);
        String date = usefulMessageForm.getDate();
        usefulMessage.setDate(DateUtil.stringToDateyyyy_MM_dd(date));
        usefulMessage.setTitle(usefulMessageForm.getTitle());
        usefulMessage.setType(usefulMessageForm.getType());
        usefulMessage.setSortScore(usefulMessageForm.getSortScore());
        usefulMessage.setExcerpt(usefulMessageForm.getExcerpt());
        if (usefulMessageForm.getPublishStart() == null || "".equals(usefulMessageForm.getPublishStart())) {
            usefulMessage.setPublishStart(DateUtil.getDefaultDate());
        } else {
            usefulMessage.setPublishStart(DateUtil.stringToDateyyyy_MM_dd(usefulMessageForm.getPublishStart()));
        }
        if (usefulMessageForm.getPublishEnd() == null || "".equals(usefulMessageForm.getPublishEnd())) {
            usefulMessage.setPublishEnd(DateUtil.getDefaultPublishEnd());
        } else {
            usefulMessage.setPublishEnd(DateUtil.stringToDateyyyy_MM_dd(usefulMessageForm.getPublishEnd()));
        }
        usefulMessage.setCreateDatetime(new Date(System.currentTimeMillis()));
        usefulMessage.setDelFlg(Boolean.FALSE);
        usefulMessage.setNote(Constant.EMPTY_STRING);
        addUsefulMessageMapper.insertUsefulMessage(usefulMessage);
        return usefulMessage.getId();
    }

    @Autowired
    private UsefulMessageDetailMapper usefulMessageDetailMapper;

    public long insertDetailUsefulMessage(UsefulMessageForm usefulMessageForm, long usefulMessageDetailId) {
        UsefulMessageDetail usefulMessageDetail = new UsefulMessageDetail();
        usefulMessageDetail.setUsefulMessageId(usefulMessageDetailId);
        String date = usefulMessageForm.getDate();
        usefulMessageDetail.setDate(DateUtil.stringToDateyyyy_MM_dd_HH_mm(date.replace("T", " ")));
        usefulMessageDetail.setTitle(usefulMessageForm.getTitle());
        usefulMessageDetail.setType(usefulMessageForm.getType());
        usefulMessageDetail.setDetail(usefulMessageForm.getDetail());
        usefulMessageDetail.setCreateDatetime(new Date(System.currentTimeMillis()));
        usefulMessageDetail.setDelFlg(Boolean.FALSE);
        usefulMessageDetail.setNote(Constant.EMPTY_STRING);
        usefulMessageDetailMapper.insertDetailUsefulMessage(usefulMessageDetail);
        return usefulMessageDetail.getId();
    }

    public long insertDetailStudioUsefulMessage(UsefulMessageForm usefulMessageForm, long usefulMessageId) {
        UsefulMessageDetail usefulMessageDetail = new UsefulMessageDetail();
        usefulMessageDetail.setUsefulMessageId(usefulMessageId);
        String date = usefulMessageForm.getDate();
        usefulMessageDetail.setDate(DateUtil.stringToDateyyyy_MM_dd(date));
        usefulMessageDetail.setTitle(usefulMessageForm.getTitle());
        usefulMessageDetail.setType(usefulMessageForm.getType());
        usefulMessageDetail.setDetail(usefulMessageForm.getDetail());
        usefulMessageDetail.setCreateDatetime(new Date(System.currentTimeMillis()));
        usefulMessageDetail.setDelFlg(Boolean.FALSE);
        usefulMessageDetail.setNote(Constant.EMPTY_STRING);
        usefulMessageDetailMapper.insertDetailUsefulMessage(usefulMessageDetail);
        return usefulMessageDetail.getId();
    }

    public int deleteUsefulMessage(UsefulMessageForm usefulMessageForm) {
        UsefulMessage usefulMessage = new UsefulMessage();
        usefulMessage.setId(usefulMessageForm.getId());
        usefulMessage.setDelFlg(Boolean.TRUE);
        int count = usefulMessageMapper.deleteUsefulMessage(usefulMessage);
        System.out.println(count);
        return count;
    }

    public int deleteDetailUsefulMessage(UsefulMessageForm usefulMessageForm) {
        UsefulMessageDetail usefulMessageDetail = new UsefulMessageDetail();
        usefulMessageDetail.setUsefulMessageId(usefulMessageForm.getId());
        usefulMessageDetail.setDelFlg(Boolean.TRUE);
        int count = usefulMessageDetailMapper.deleteDetailUsefulMessage(usefulMessageDetail);
        return count;
    }

    public int updateUsefulMessage(UsefulMessageForm usefulMessageForm) {
        UsefulMessage usefulMessage = new UsefulMessage();
        usefulMessage.setId(usefulMessageForm.getId());
        String date = usefulMessageForm.getDate();
        usefulMessage.setDate(DateUtil.stringToDateyyyy_MM_dd_HH_mm(date.replace("T", " ")));
        usefulMessage.setTitle(usefulMessageForm.getTitle());
        usefulMessage.setType(usefulMessageForm.getType());
        usefulMessage.setSortScore(usefulMessageForm.getSortScore());
        usefulMessage.setExcerpt(usefulMessageForm.getExcerpt());
        if (usefulMessageForm.getPublishStart() == null || "".equals(usefulMessageForm.getPublishStart())) {
            usefulMessage.setPublishStart(DateUtil.getDefaultDate());
        } else {
            usefulMessage.setPublishStart(DateUtil.stringToDateyyyy_MM_dd_HH_mm(usefulMessageForm.getPublishStart().replace("T", " ")));
        }
        if (usefulMessageForm.getPublishEnd() == null || "".equals(usefulMessageForm.getPublishEnd())) {
            usefulMessage.setPublishEnd(DateUtil.getDefaultPublishEnd());
        } else {
            usefulMessage.setPublishEnd(DateUtil.stringToDateyyyy_MM_dd_HH_mm(usefulMessageForm.getPublishEnd().replace("T", " ")));
        }
        usefulMessage.setUpdateDatetime(new Date(System.currentTimeMillis()));
        usefulMessage.setDelFlg(Boolean.FALSE);
        usefulMessage.setNote(Constant.EMPTY_STRING);
        int count = usefulMessageMapper.updateUsefulMessage(usefulMessage);
        return count;
    }

    public int updateStudioUsefulMessage(UsefulMessageForm usefulMessageForm) {
        UsefulMessage usefulMessage = new UsefulMessage();
        usefulMessage.setId(usefulMessageForm.getId());
        String date = usefulMessageForm.getDate();
        usefulMessage.setDate(DateUtil.stringToDateyyyy_MM_dd(date));
        usefulMessage.setTitle(usefulMessageForm.getTitle());
        usefulMessage.setType(usefulMessageForm.getType());
        usefulMessage.setSortScore(usefulMessageForm.getSortScore());
        usefulMessage.setExcerpt(usefulMessageForm.getExcerpt());
        if (usefulMessageForm.getPublishStart() == null || "".equals(usefulMessageForm.getPublishStart())) {
            usefulMessage.setPublishStart(DateUtil.getDefaultDate());
        } else {
            usefulMessage.setPublishStart(DateUtil.stringToDateyyyy_MM_dd(usefulMessageForm.getPublishStart()));
        }
        if (usefulMessageForm.getPublishEnd() == null || "".equals(usefulMessageForm.getPublishEnd())) {
            usefulMessage.setPublishEnd(DateUtil.getDefaultPublishEnd());
        } else {
            usefulMessage.setPublishEnd(DateUtil.stringToDateyyyy_MM_dd(usefulMessageForm.getPublishEnd()));
        }
        usefulMessage.setUpdateDatetime(new Date(System.currentTimeMillis()));
        usefulMessage.setDelFlg(Boolean.FALSE);
        usefulMessage.setNote(Constant.EMPTY_STRING);
        int count = usefulMessageMapper.updateUsefulMessage(usefulMessage);
        return count;
    }

    public int updateDetailUsefulMessage(UsefulMessageForm usefulMessageForm) {
        UsefulMessageDetail usefulMessageDetail = new UsefulMessageDetail();
        usefulMessageDetail.setUsefulMessageId(usefulMessageForm.getId());
        String date = usefulMessageForm.getDate();
        usefulMessageDetail.setDate(DateUtil.stringToDateyyyy_MM_dd_HH_mm(date.replace("T", " ")));
        usefulMessageDetail.setTitle(usefulMessageForm.getTitle());
        usefulMessageDetail.setType(usefulMessageForm.getType());
        usefulMessageDetail.setDetail(usefulMessageForm.getDetail());
        usefulMessageDetail.setUpdateDatetime(new Date(System.currentTimeMillis()));
        usefulMessageDetail.setDelFlg(Boolean.FALSE);
        usefulMessageDetail.setNote(Constant.EMPTY_STRING);
        int count = usefulMessageDetailMapper.updateDetailUsefulMessage(usefulMessageDetail);
        return count;
    }

    public int updateDetailStudiousefulMessage(UsefulMessageForm usefulMessageForm) {
        UsefulMessageDetail usefulMessageDetail = new UsefulMessageDetail();
        usefulMessageDetail.setUsefulMessageId(usefulMessageForm.getId());
        String date = usefulMessageForm.getDate();
        usefulMessageDetail.setDate(DateUtil.stringToDateyyyy_MM_dd(date));
        usefulMessageDetail.setTitle(usefulMessageForm.getTitle());
        usefulMessageDetail.setType(usefulMessageForm.getType());
        usefulMessageDetail.setDetail(usefulMessageForm.getDetail());
        usefulMessageDetail.setUpdateDatetime(new Date(System.currentTimeMillis()));
        usefulMessageDetail.setDelFlg(Boolean.FALSE);
        usefulMessageDetail.setNote(Constant.EMPTY_STRING);
        int count = usefulMessageDetailMapper.updateDetailUsefulMessage(usefulMessageDetail);
        return count;
    }

    public List<UsefulMessage> getusefulMessageList(long id) {
        List<UsefulMessage> usefulMessageList = usefulMessageMapper.getUsefulMessageList(id);
        return usefulMessageList;
    }

    public String getusefulMessageDetail(long id) {
        String detail = usefulMessageDetailMapper.getUsefulMessageDetail(id);
        return detail;
    }

    public List<UsefulMessageDetail> getusefulMessageDetailAll(Long id) {
        List<UsefulMessageDetail> usefulMessageDetail = usefulMessageDetailMapper.getUsefulMessageDetailAll(id);
        List<UsefulMessageDetail> resultList = convertToDetail(usefulMessageDetail);
        return resultList;
    }

    private List<UsefulMessageDetail> convertToDetail(List<UsefulMessageDetail> demoList) {
        List<UsefulMessageDetail> resultList = new LinkedList<UsefulMessageDetail>();
        for (UsefulMessageDetail entity : demoList) {
            resultList.add(entity);
        }
        return resultList;
    }

    public List<UsefulMessage> getusefulMessageAllByCondition(UsefulMessageListForm form) {
        List<UsefulMessage> usefulMessage = usefulMessageMapper.getUsefulMessageMapperAllByCondition(form);
        return usefulMessage;
    }

    public int getusefulMessageCountByCondition(UsefulMessageListForm form) {
        return usefulMessageMapper.getStudioCountByCondition(form);
    }

    public int getusefulMessageCount() {
        return usefulMessageMapper.getUsefulMessageMapperCount();
    }

    public List<UsefulMessage> getStudiousefulMessageALL(int type) {
        List<UsefulMessage> usefulMessage = usefulMessageMapper.getStudioUsefulMessageMapperALL(type);
        return usefulMessage;
    }
}