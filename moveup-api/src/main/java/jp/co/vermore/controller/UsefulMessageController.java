package jp.co.vermore.controller;

import jp.co.vermore.common.Constant;
import jp.co.vermore.common.JsonObject;
import jp.co.vermore.common.JsonStatus;
import jp.co.vermore.common.mvc.BaseController;
import jp.co.vermore.common.util.DateUtil;
import jp.co.vermore.entity.*;
import jp.co.vermore.jsonparse.UsefulMessageDetailJsonParse;
import jp.co.vermore.jsonparse.UsefulMessageJsonParse;
import jp.co.vermore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UsefulMessageController
 */
@Controller
public class UsefulMessageController extends BaseController {

    @Autowired
    private UsefulMessageService usefulMessageService;

    @Autowired
    private EntryService entryService;

    @Autowired
    private PicService picService;

    @Autowired
    private WidgetService widgetService;

    @Value(value = "${hosturl}")
    private String hosturl;

    //eg: http://localhost:8081/moveup_war/api/usefulMessage/list/0/1/0/
    @RequestMapping(value = "/api/usefulMessage/list/{type}/{limit}/{offset}/", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject getUsefulMessageList(@PathVariable int type,@PathVariable int limit, @PathVariable int offset) {
        List<UsefulMessage> list = usefulMessageService.getUsefulMessageAll(type,limit, offset);
        List<UsefulMessage> countlist = usefulMessageService.getStudiousefulMessageALL(type);
        List<UsefulMessageJsonParse> ejpList = new ArrayList<>();
        ejpList = list(ejpList, list);
        Map<String,Object> map = new HashMap<>();
        map.put("usefulMessageList",ejpList);
        map.put("count",countlist.size());
        jsonObject.setResultList(map);
        return jsonObject;
    }

    //eg:http://localhost:8081/moveup_war/api/usefulMessage/list/0/1/1/0/
    @RequestMapping(value = "/api/usefulMessage/list/{type1}/{type2}/{limit}/{offset}/", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject getUsefulMessageEventList(@PathVariable int type1,@PathVariable int type2,@PathVariable int limit, @PathVariable int offset) {
        List<UsefulMessage> list = usefulMessageService.getUsefulMessageEventAll(type1,type2,limit, offset);
        List<UsefulMessageJsonParse> ejpList = new ArrayList<>();
        ejpList = list(ejpList, list);
        Map<String,Object> map = new HashMap<>();
        map.put("usefulMessageList",ejpList);
        map.put("count",ejpList.size());
        jsonObject.setResultList(map);
        return jsonObject;
    }

    //eg:http://localhost:8081/moveup_war/api/usefulMessage/detail/4hIZRgPJFu/
    @RequestMapping(value = "/api/usefulMessage/detail/{uuid}/", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject getUsefulMessageDetailList(@PathVariable String uuid) {
        UsefulMessage usefulMessage = usefulMessageService.getUsefulMessageByUuid(uuid);
        List<UsefulMessageDetailJsonParse> ejpList = new ArrayList<>();
        List<UsefulMessageDetail> list = usefulMessageService.getusefulMessageDetailAll(usefulMessage.getId());
        UsefulMessageDetailJsonParse ejp = new UsefulMessageDetailJsonParse();
        if(list.size()>0){
            for (UsefulMessageDetail ed: list) {
                ejp.setUsefulMessageId(ed.getUsefulMessageId());
                ejp.setTitle(ed.getTitle());
                ejp.setDate(DateUtil.dateToStringyyyy_MM_dd(ed.getDate()));
                ejp.setTypeStr(widgetService.getUsefulMessageType(ed.getType()));
                ejp.setType(ed.getType());
                ejp.setColor(widgetService.getUsefulMessageColor(ed.getType()));
                ejp.setDetail(ed.getDetail());

                Pic topPic = new Pic();
                List<Pic> topPicList = picService.getPic(ed.getUsefulMessageId(), Constant.EVENT_PIC_TYPE.NEWS_TOP);
                List<String> topList = new ArrayList<String>();
                for(Pic pic:topPicList){
                    topList.add(pic.getPicUrl());
                }
                ejp.setTopPic(topList);

                List<Pic> footPicList = picService.getPic(ed.getUsefulMessageId(),Constant.EVENT_PIC_TYPE.NEWS_FOOT);
                List<String> footList = new ArrayList<String>();
                for(Pic pic:footPicList){
                    footList.add(pic.getPicUrl());
                }
                ejp.setFootPic(footList);
                List<UsefulMessage> listPre = usefulMessageService.getUsefulMessagePre(ed.getDate());
                List<UsefulMessage> listNext = usefulMessageService.getUsefulMessageNext(ed.getDate());
                List<UsefulMessageJsonParse> ejpListPre = new ArrayList<>();
                List<UsefulMessageJsonParse> ejpListNext = new ArrayList<>();
                ejpListPre = list(ejpListPre, listPre);
                ejpListNext = list(ejpListNext, listNext);
                if(listPre.size()>0){
                    ejpListPre.get(0).setColor(widgetService.getUsefulMessageDetailColor(listPre.get(0).getType()));
                }
                if(listNext.size()>0){
                    ejpListNext.get(0).setColor(widgetService.getUsefulMessageDetailColor(listNext.get(0).getType()));
                }
                ejp.setUsefulMessagePre(ejpListPre);
                ejp.setUsefulMessageNext(ejpListNext);
                ejpList.add(ejp);
            }

            int type =0;
            if(usefulMessage.getType() == Constant.UsefulMessage_TYPE.EVENT){
                type =  Constant.UsefulMessage_TYPE.MOVEUP;
            }else if(usefulMessage.getType() == Constant.UsefulMessage_TYPE.MOVEUP){
                type = Constant.UsefulMessage_TYPE.EVENT;
            }

            EntryMail entryMail = entryService.getEntryMailByEntryIdAndType(usefulMessage.getId(),type);
            if(entryMail != null){
                Date startTime = entryMail.getPublishStart();
                Date endTime = entryMail.getPublishEnd();
                Date nowTime = new Date(System.currentTimeMillis());
                if(nowTime.getTime() >= startTime.getTime() && nowTime.getTime() <= endTime.getTime()){
                    ejp.setEntry("1");//応募可能
                }else{
                    ejp.setEntry(null);
                }
            }else {
                ejp.setEntry(null);
            }
            jsonObject.setResultList(ejpList);
        }else{
            jsonObject.setResultList(null);
        }
        return jsonObject;
    }

    private List<UsefulMessageJsonParse> list(List<UsefulMessageJsonParse> jpList, List<UsefulMessage> list) {

        for (UsefulMessage nd: list) {
            UsefulMessageJsonParse njp = new UsefulMessageJsonParse();
            njp.setUuid(nd.getUuid());
            njp.setTitle(nd.getTitle());
            njp.setDate(DateUtil.dateToStringyyyy_MM_dd(nd.getDate()));
            njp.setType(widgetService.getUsefulMessageType(nd.getType()));
            njp.setColor(widgetService.getUsefulMessageColor(nd.getType()));
            njp.setExcerpt(nd.getExcerpt());
            jpList.add(njp);
        }
        return jpList;
    }

    // usefulMessage detail for sns
    //eg:http://localhost:8081/moveup_war/sns/usefulMessageDetail/4hIZRgPJFu/
    @RequestMapping(value = "/sns/usefulMessageDetail/{uuid}/", method = RequestMethod.GET)
    public Object getUsefulMessageSNSDetail(@PathVariable String uuid, Model model, HttpServletRequest hsr) {

        UsefulMessage usefulMessage = usefulMessageService.getUsefulMessageByUuid(uuid);
        List<UsefulMessageDetail> usefulMessageDetailList = usefulMessageService.getusefulMessageDetailAll(usefulMessage.getId());
        if(usefulMessageDetailList.size()>0){
            UsefulMessageDetail usefulMessageDetail = usefulMessageDetailList.get(0);

            model.addAttribute("title", usefulMessageDetail.getTitle());
            model.addAttribute("url", "https://www.japanmoveupwest.com" + "/usefulMessageDetail/" + usefulMessage.getUuid() + "/");
            model.addAttribute("desc",  usefulMessage.getExcerpt());
            model.addAttribute("image",  "");
        }
        
        String userAgent = hsr.getHeader("User-Agent");
        logger.debug("-------user-agent=" + userAgent);

        String regex = "facebookexternalhit|Facebot|Twitterbot|Pinterest|Google.*snippet";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(userAgent);
        if (m.find()) {
            logger.debug("-------tosns");
            return "sns";
        } else {
            logger.debug("-------tourl");
            return "redirect:"+ hosturl + "/usefulMessageDetail/" + uuid + "/";
        }
    }

    // usefulMessage detail for sns
    //eg:http://localhost:8081/moveup_war/api/sns/usefulMessageDetail/app/4hIZRgPJFu/
    @RequestMapping(value = "/api/sns/usefulMessageDetail/app/{uuid}/", method = RequestMethod.GET)
    @ResponseBody
    public JsonObject getUsefulMessageDetailSNSForApp(@PathVariable String uuid) {

        Map<String, Object> urlMap = new HashMap<String, Object>();
        urlMap.put("twitter","https://twitter.com/share?url="+hosturl+"/usefulMessageDetail/"+uuid+"/");
        urlMap.put("facebook","https://www.facebook.com/sharer/sharer.php?u="+hosturl+"/usefulMessageDetail/"+uuid+"/");

        jsonObject.setResultList(urlMap);
        jsonObject.setStatus(JsonStatus.SUCCESS);
        return jsonObject;
    }
}