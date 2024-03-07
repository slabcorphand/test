package jp.co.vermore.controller.admin;

import jp.co.vermore.common.Constant;
import jp.co.vermore.common.DatatablesJsonObject;
import jp.co.vermore.common.mvc.BaseController;
import jp.co.vermore.common.util.DateUtil;
import jp.co.vermore.entity.EntryMail;
import jp.co.vermore.entity.News;
import jp.co.vermore.entity.Pic;
import jp.co.vermore.entity.UsefulMessage;
import jp.co.vermore.form.admin.NewsForm;
import jp.co.vermore.form.admin.NewsListForm;
import jp.co.vermore.form.admin.UsefulMessageForm;
import jp.co.vermore.form.admin.UsefulMessageListForm;
import jp.co.vermore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * AdminUsefulMessageController
 * Created by wubin.
 * <p>
 * DateTime: 2018/03/03 11:13
 * Copyright: sLab, Corp
 */
@Controller
public class AdminUsefulMessageController extends BaseController {

    @Autowired
    private UsefulMessageService usefulMessageService;

    @Autowired
    private EntryService entryService;

    @Autowired
    private AWSService awsService;

    @Autowired
    private PicService picService;

    @Autowired
    PlatformTransactionManager txManager;

    @RequestMapping(value = "/admin/usefulMessage/list/", method = RequestMethod.GET)
    public String usefulMessageAll(Model model,HttpServletRequest request) {

        int errorCode = 0;
        if(!request.getSession().isNew()){
            if(request.getSession().getAttribute("error") != null && request.getSession().getAttribute("error") != ""){
                errorCode = (int)request.getSession().getAttribute("error");
                request.getSession().setAttribute("error",0);
            }
        }
        model.addAttribute("errorCode", errorCode);

        List<UsefulMessage> usefulMessage = usefulMessageService.getUsefulMessageAll();
        UsefulMessageForm form = new UsefulMessageForm();
        model.addAttribute("usefulMessageDeleteForm", form);
        model.addAttribute("usefulMessage_all", usefulMessage);
        return "admin/usefulMessageList";
    }

    @RequestMapping(value = "/admin/usefulMessage/list/", method = RequestMethod.POST)
    @ResponseBody
    public DatatablesJsonObject usefulMessageList(@RequestBody UsefulMessageListForm form){
        logger.debug("----1----");
        // set order statement
        if(form.getOrder().size() > 0
                && form.getColumns().get(form.getOrder().get(0).getColumn()).getName() != null
                && form.getColumns().get(form.getOrder().get(0).getColumn()).getName().length() > 0){
            form.setOrderStatement(form.getColumns().get(form.getOrder().get(0).getColumn()).getName() + " " + form.getOrder().get(0).getDir());
            logger.debug("----2----order statement="+form.getOrderStatement());
        }else{
            form.setOrderStatement("id");
            logger.debug("----2----order statement="+form.getOrderStatement());
        }
        logger.debug("----3----");

        // query data
        List<UsefulMessage> dataList = usefulMessageService.getusefulMessageAllByCondition(form);

        for(UsefulMessage usefulMessage:dataList){
            int type =0;
            //it's my faults
            if(usefulMessage.getType() == Constant.UsefulMessage_TYPE.EVENT){
                type =  Constant.UsefulMessage_TYPE.MOVEUP;
            }else if(usefulMessage.getType() == Constant.UsefulMessage_TYPE.MOVEUP){
                type = Constant.UsefulMessage_TYPE.EVENT;
            }
            EntryMail entity = entryService.getEntryMailByEntryIdAndType( usefulMessage.getId(),type);
            if(entity != null){
                usefulMessage.setEntryType(1);
            }else{
                usefulMessage.setEntryType(0);
            }
        }

        int totalCountFiltered = usefulMessageService.getusefulMessageCountByCondition(form);
        int totalCount = usefulMessageService.getusefulMessageCount();
        logger.debug("----4----data count="+dataList.size());
        logger.debug("----5----total filtered="+totalCountFiltered);
        logger.debug("----6----total count="+totalCount);
        logger.debug("----7----page="+form.getDraw());

        // return json data
        DatatablesJsonObject jsonparse = new DatatablesJsonObject();
        jsonparse.setDraw(form.getDraw());
        jsonparse.setRecordsFiltered(totalCountFiltered);
        jsonparse.setRecordsTotal(totalCount);
        jsonparse.setData(dataList);
        logger.debug("----8----");
        return jsonparse;
    }

    @RequestMapping(value = "/admin/usefulMessage/regist/", method = RequestMethod.GET)
    public String usefulMessageInsert(Model model) {
        UsefulMessageForm form = new UsefulMessageForm();
        model.addAttribute("usefulMessageForm", form);
        return "admin/usefulMessageRegist";
    }

    @RequestMapping(value = "/admin/usefulMessage/regist/", method = RequestMethod.POST)
    public String usefulMessageInsert(@ModelAttribute UsefulMessageForm form ,HttpServletRequest request) {
        HttpSession session = request.getSession();
        // トランザクション管理の開始
        DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
        txDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus txStatus = txManager.getTransaction(txDefinition);

        try {
            long usefulMessageId = usefulMessageService.insertUsefulMessage(form);
            usefulMessageService.insertDetailUsefulMessage(form,usefulMessageId);
            MultipartFile[] top = form.getPicFile1();
            MultipartFile[] foot = form.getPicFile2();

            if(!form.getPicFile1()[0].isEmpty()) {
                Pic topPic = new Pic();
                if (top.length>0) {
                    for(int i = 0 ; i < top.length; i++){
                        topPic.setPicUrl(awsService.postFile(top[i]));
                        topPic.setItemId(usefulMessageId);
                        topPic.setItemType(Constant.EVENT_PIC_TYPE.NEWS_TOP);
                        picService.insertPic(topPic);
                    }
                }
            }

            if(!form.getPicFile2()[0].isEmpty()) {
                Pic footPic = new Pic();
                if (foot.length>0) {
                    for(int i = 0 ; i < foot.length; i++){
                        footPic.setPicUrl(awsService.postFile(foot[i]));
                        footPic.setItemId(usefulMessageId);
                        footPic.setItemType(Constant.EVENT_PIC_TYPE.NEWS_FOOT);
                        picService.insertPic(footPic);
                    }
                }
            }

            txManager.commit(txStatus);
            session.setAttribute("error",0);
        } catch (Exception e) {
            txManager.rollback(txStatus);
            session.setAttribute("error",1);
            logger.error("insert usefulMessage failed!, error=" + e.getMessage());
            logger.error("insert usefulMessage failed!, error=" + e.toString());
            e.printStackTrace();
        }
        return "redirect:/admin/usefulMessage/list/";
    }

    @RequestMapping(value = "/admin/usefulMessage/delete/", method = RequestMethod.POST)
    public String usefulMessageDetailDelete(@ModelAttribute UsefulMessageForm form,HttpServletRequest request) {
        HttpSession session = request.getSession();
        // トランザクション管理の開始
        DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
        txDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus txStatus = txManager.getTransaction(txDefinition);

        try {
            usefulMessageService.deleteUsefulMessage(form);
            usefulMessageService.deleteDetailUsefulMessage(form);
            picService.deleteUsefulMessagePicUrl(form.getId(),Constant.EVENT_PIC_TYPE.NEWS_TOP);
            picService.deleteUsefulMessagePicUrl(form.getId(),Constant.EVENT_PIC_TYPE.NEWS_FOOT);
            txManager.commit(txStatus);
            session.setAttribute("error",0);
        } catch (Exception e) {
            txManager.rollback(txStatus);
            session.setAttribute("error",1);
            logger.error("delete usefulMessage failed!, error=" + e.getMessage());
            logger.error("delete usefulMessage failed!, error=" + e.toString());
            e.printStackTrace();
        }
        return "redirect:/admin/usefulMessage/list/";
    }

    @RequestMapping(value = "/admin/usefulMessage/edit/{id}/", method = RequestMethod.GET)
    public String usefulMessageUpdate(Model model , @PathVariable long id) {
        UsefulMessageForm usefulMessageForm = new UsefulMessageForm();
        List<UsefulMessage> list = usefulMessageService.getusefulMessageList(id);
        String detail = usefulMessageService.getusefulMessageDetail(id);

        List<Pic> topPicList = picService.getPic(id,Constant.EVENT_PIC_TYPE.NEWS_TOP);
        List<String> topList = new ArrayList<String>();
        for(Pic pic:topPicList){
            topList.add(pic.getPicUrl());
        }

        List<Pic> footPicList = picService.getPic(id,Constant.EVENT_PIC_TYPE.NEWS_FOOT);
        List<String> footList = new ArrayList<String>();
        for(Pic pic:footPicList){
            footList.add(pic.getPicUrl());
        }

        usefulMessageForm.setPicUrl1(topList);
        usefulMessageForm.setPicUrl2(footList);

        if(list != null && list.size() > 0){
            usefulMessageForm.setId(list.get(0).getId());
            usefulMessageForm.setDetail(detail);
            usefulMessageForm.setTitle(list.get(0).getTitle());
            usefulMessageForm.setType(list.get(0).getType());
            usefulMessageForm.setExcerpt(list.get(0).getExcerpt());
            usefulMessageForm.setPublishStart(DateUtil.dateToStringyyyy_MM_dd_HH_mm(list.get(0).getPublishStart()));
            usefulMessageForm.setPublishEnd(DateUtil.dateToStringyyyy_MM_dd_HH_mm(list.get(0).getPublishEnd()));
            String date = DateUtil.dateToStringyyyy_MM_dd_HH_mm(list.get(0).getDate());
//            usefulMessageForm.setDate(date.replace(" ", "T"));
            usefulMessageForm.setDate(date);
            usefulMessageForm.setSortScore(list.get(0).getSortScore());

            model.addAttribute("usefulMessageForm", usefulMessageForm);
            return "admin/usefulMessageEdit";
        }else {
            return "redirect:/admin/usefulMessage/list/";
        }
    }

    @RequestMapping(value = "/admin/usefulMessage/update/", method = RequestMethod.POST)
    public String usefulMessageUpdate1(@ModelAttribute UsefulMessageForm form,HttpServletRequest request) {
        HttpSession session = request.getSession();
        // トランザクション管理の開始
        DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
        txDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus txStatus = txManager.getTransaction(txDefinition);

        try {
            List<String> picUrl1 = form.getPicUrl1();
            usefulMessageService.updateUsefulMessage(form);
            usefulMessageService.updateDetailUsefulMessage(form);

            if(form.getPicUrl1().size()==0 && form.getPicFile1()[0].isEmpty()){
                picService.deleteUsefulMessagePicUrl(form.getId(),Constant.EVENT_PIC_TYPE.NEWS_TOP);
            }

            if(form.getPicUrl2().size()==0 && form.getPicFile2()[0].isEmpty()){
                picService.deleteUsefulMessagePicUrl(form.getId(),Constant.EVENT_PIC_TYPE.NEWS_FOOT);
            }

            if(!form.getPicFile1()[0].isEmpty()) {
                picService.deleteUsefulMessagePicUrl(form.getId(),Constant.EVENT_PIC_TYPE.NEWS_TOP);
                MultipartFile[] top = form.getPicFile1();
                Pic topPic = new Pic();
                if (top.length>0) {
                    for(int i = 0 ; i < top.length; i++){
                        topPic.setPicUrl(awsService.postFile(top[i]));
                        topPic.setItemId(form.getId());
                        topPic.setItemType(Constant.EVENT_PIC_TYPE.NEWS_TOP);
                        picService.insertPic(topPic);
                    }
                }
            }

            if(!form.getPicFile2()[0].isEmpty()){
                picService.deleteUsefulMessagePicUrl(form.getId(),Constant.EVENT_PIC_TYPE.NEWS_FOOT);
                MultipartFile[] foot = form.getPicFile2();
                Pic footPic = new Pic();
                if (foot.length>0) {
                    for(int i = 0 ; i < foot.length; i++){
                        footPic.setPicUrl(awsService.postFile(foot[i]));
                        footPic.setItemId(form.getId());
                        footPic.setItemType(Constant.EVENT_PIC_TYPE.NEWS_FOOT);
                        picService.insertPic(footPic);
                    }
                }
            }

            txManager.commit(txStatus);
            session.setAttribute("error",0);
        } catch (Exception e) {
            txManager.rollback(txStatus);
            session.setAttribute("error",1);
            logger.error("update usefulMessage failed!, error=" + e.getMessage());
            logger.error("update usefulMessage failed!, error=" + e.toString());
            e.printStackTrace();
        }
        return "redirect:/admin/usefulMessage/list/";
    }
}
