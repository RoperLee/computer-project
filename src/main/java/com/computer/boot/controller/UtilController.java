package com.computer.boot.controller;

import com.computer.boot.model.User;
import com.computer.boot.service.UtilServiceFacade;
import com.computer.boot.vo.LastDateVo;
import com.computer.boot.vo.RankListVo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    public LastDateVo getExamDate(HttpServletRequest request, HttpServletResponse response) {
        return utilServiceFacade.getExamDate();
    }


    /**
     * 查询排名
     *
     * @param userId
     * @param pageStart
     * @param pageSize
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/rank/getRankList")
    public RankListVo getRankList(@RequestParam("userId") Long userId,
                                  @RequestParam("pageStart") int pageStart,
                                  @RequestParam("pageSize") int pageSize,
                                  HttpServletRequest request, HttpServletResponse response) {
        return utilServiceFacade.getRankList(userId, pageStart, pageSize);
    }

    /**
     * 给某个用户的做题数增加count
     *
     * @param userId
     * @param count
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/rank/addExecuteQuestionCount")
    public boolean addExecuteQuestionCount(@RequestParam("userId") Long userId,
                                           @RequestParam("count") int count,
                                           HttpServletRequest request, HttpServletResponse response) {
        return utilServiceFacade.addExecuteQuestionCount(userId, count);
    }

    /**
     * 获取Banner
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getBanner")
    public List<String> getBanner(HttpServletRequest request, HttpServletResponse response) {
        return utilServiceFacade.getBanner();
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserInfo")
    public User getUserInfo(@RequestParam("userId") Long userId,
                            HttpServletRequest request, HttpServletResponse response) {
        return utilServiceFacade.getUserInfo(userId);
    }


    /**
     * 图片文件的上传
     *
     * @param uploadImg
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/question/ajax/img/uploade")
    public Object catchAndSaveImg(@RequestParam MultipartFile[] uploadImg,
                                  HttpServletRequest request, HttpServletResponse response) throws IOException {
        return utilServiceFacade.catchAndSaveImg(uploadImg);
    }

    /**
     * 添加试题
     *
     * @param postData {"subjectId":"1","directoryId":"42","issuseType":"CHOICE","sortKeyNumber":"1","isContentImg":"N","contentStrList":["asdf asf  "],"contentImgNameList":null,"isSelectImg":false,"selectOptionStrList":["asdf asd","asdfa sdf "],"correctSelectOption":"B","isAnswerImg":"N","answerStrList":["asdf asdf "],"answerImgNameList":null}
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/easyAddQuestion")
    public boolean easyAddQuestion(@RequestParam("postData") String postData,
                                   HttpServletRequest request, HttpServletResponse response) throws IOException {
        return utilServiceFacade.easyAddQuestion(postData);
    }


}
