package com.computer.boot.controller;

import com.computer.boot.service.UtilServiceFacade;
import com.computer.boot.vo.RankListVo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by roper on 2017/6/12.
 */
@RestController
public class UtilController {

    private static final Logger logger = LoggerFactory.getLogger(UtilController.class);

    @Autowired
    private UtilServiceFacade utilServiceFacade;

    /**
     * 获取磁盘图片
     *
     * @param fileName
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/image/showPic")
    public void showPic(@RequestParam("fileName") String fileName,
                        HttpServletRequest request, HttpServletResponse response) {
        utilServiceFacade.showPic(response, fileName);
    }

    /**
     * 距离考试剩余的天数
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/date/getExamDate")
    public int getExamDate(HttpServletRequest request, HttpServletResponse response) {
        return utilServiceFacade.getExamDate();
    }


    @ResponseBody
    @RequestMapping(value = "/rank/getRankList")
    public RankListVo getRankList(@RequestParam("userId") Long userId,
                                  @RequestParam("pageStart") int pageStart,
                                  @RequestParam("pageSize") int pageSize,
                                  HttpServletRequest request, HttpServletResponse response) {
        return utilServiceFacade.getRankList(userId, pageStart, pageSize);
    }


}
