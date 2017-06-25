package com.computer.boot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.computer.boot.config.ImagePropertyUtils;
import com.computer.boot.config.ServicePropertyUtils;
import com.computer.boot.mapper.DirectoryMapper;
import com.computer.boot.mapper.ExamDateMapper;
import com.computer.boot.mapper.UserMapper;
import com.computer.boot.model.*;
import com.computer.boot.service.UtilServiceFacade;
import com.computer.boot.vo.LastDateVo;
import com.computer.boot.vo.QuestionVo;
import com.computer.boot.vo.RankListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by roper on 2017/5/15.
 */

@Service
public class UtilService implements UtilServiceFacade {
    private static final Logger logger = LoggerFactory.getLogger(UtilService.class);

    @Autowired
    private ImagePropertyUtils imagePropertyUtils;
    @Autowired
    private ExamDateMapper examDateMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ServicePropertyUtils servicePropertyUtils;
    @Autowired
    private DirectoryMapper directoryMapper;

    @Override
    public void showPic(HttpServletResponse response, String fileName) {

        String fileUrl = imagePropertyUtils.getImagePath() + fileName;
        String[] imageFormat = fileName.split("\\.");
        try {
            File filePath = new File(fileUrl);
            if (filePath.exists()) {
                //读图片
                FileInputStream inputStream = new FileInputStream(filePath);
                int available = inputStream.available();
                byte[] data = new byte[available];
                inputStream.read(data);
                inputStream.close();
                //写图片
                response.setContentType("image/" + imageFormat[1]);
                response.setCharacterEncoding("UTF-8");
                OutputStream stream = new BufferedOutputStream(response.getOutputStream());
                stream.write(data);
                stream.flush();
                stream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public LastDateVo getExamDate() {
        ExamDate examDate = examDateMapper.getExamDate();
        int date = (int) ((examDate.getExamDate().getTime() - new Date().getTime()) / (1000 * 3600 * 24));
        LastDateVo result = new LastDateVo();
        int one = date / 100;
        int two = (date - one * 100) / 10;
        int three = date - one * 100 - two * 10;
        result.setOne(one);
        result.setTwo(two);
        result.setThree(three);
        return result;
    }

    @Override
    public RankListVo getRankList(Long userId, int pageStart, int pageSize) {
        RankListVo result = new RankListVo();
        if (null == userId) {
            return result;
        }
        result.setSelf(userMapper.getUser(userId));
        result.setUserList(userMapper.queryUserRankingList(pageStart, pageSize));
        return result;
    }

    @Override
    public boolean addExecuteQuestionCount(Long userId, int count) {
        if (null == userId) {
            return false;
        }
        userMapper.addExecuteQuestionCount(userId, count);
        return true;
    }

    @Override
    public List<String> getBanner() {
        String host = servicePropertyUtils.getDns();
        String port = servicePropertyUtils.getPort();
        List<String> bannerList = new ArrayList<>();
        String IMAGE_PATH = host + ":" + port + "/image/showPic?fileName=";
        bannerList.add(IMAGE_PATH + "banner_1.jpg");
        bannerList.add(IMAGE_PATH + "banner_2.jpg");
        bannerList.add(IMAGE_PATH + "banner_3.jpg");
        return bannerList;
    }

    @Override
    public User getUserInfo(Long userId) {
        return userMapper.getUser(userId);
    }

    public QuestionVo parseQuestion2QuestionVo(Question item) {
        QuestionVo temp = new QuestionVo();
        temp.setId(item.getId());


        Directory directory = directoryMapper.getDirectoryById(item.getDirectoryId());
        temp.setTitle(parseTitle(directory.getTitle()));
        temp.setShowAnswer(false);
        temp.setContent(JSON.parseObject(item.getContent()));

        JSONObject option = JSON.parseObject(item.getOption());
        temp.setSelect(option.getJSONArray("optionList"));
        temp.setRight(option.getString("correctOption"));
        temp.setDetail(JSON.parseObject(item.getAnalysis()));

        int typei;
        if (IssueKind.EXAM.name().equalsIgnoreCase(item.getKind())) {
            typei = 1;
        } else {
            typei = 2;
        }
        int typex;
        if (QuestionType.CHOICE.name().equalsIgnoreCase(item.getQuestionType())) {
            typex = 1;
        } else if (QuestionType.BLANK.name().equalsIgnoreCase(item.getQuestionType())) {
            typex = 2;
        } else {
            typex = 3;
        }
        temp.setType(item.getSubjectId() * 100 + typei * 10 + typex);
        return temp;
    }

    public List<QuestionVo> parseQuestionList2QuestionVoList(List<Question> itemList) {
        List<QuestionVo> resultList = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            resultList.add(parseQuestion2QuestionVo(itemList.get(i)));
        }
        return resultList;
    }

    public String parseTitle(String origin) {
        if (origin.contains("真题")) {
            return origin.replaceAll("计算机二级考试", "");
        } else if (origin.contains("模拟题")) {
            return origin.replaceAll("机考", "");
        } else {
            return origin;
        }
    }

    /**
     * 保存试题
     *
     * @param postData {"subjectId":"1","directoryId":"42","issuseType":"CHOICE","sortKeyNumber":"1","isContentImg":"N","contentStrList":["asdf asf  "],"contentImgNameList":null,"isSelectImg":false,"selectOptionStrList":["asdf asd","asdfa sdf "],"correctSelectOption":"B","isAnswerImg":"N","answerStrList":["asdf asdf "],"answerImgNameList":null}
     * @return
     */
    @Override
    public boolean easyAddQuestion(String postData) {

        return true;
    }


    /**
     * 保存图片
     *
     * @param uploadImg
     * @return
     */
    @Override
    public Object catchAndSaveImg(MultipartFile[] uploadImg) {
        try {
            String dir = "/Users/roper/Desktop/img/";
            for (MultipartFile myfile : uploadImg) {
                if (myfile.isEmpty()) {
                    logger.info("文件未上传");
                } else {
                    logger.info("========================================");
                    logger.info("文件长度: " + myfile.getSize());
                    logger.info("文件类型: " + myfile.getContentType());
                    logger.info("文件名称: " + myfile.getName());
                    logger.info("文件原名: " + myfile.getOriginalFilename());
                    logger.info("========================================");
                    writeFile(myfile.getOriginalFilename(), dir, myfile.getInputStream(), myfile.getSize());

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 文件上传
     *
     * @param srcName 原文件名
     * @param dirName 目录名
     * @param input   要保存的输入流
     * @return 返回要保存到数据库中的路径
     */
    private String writeFile(String srcName, String dirName, InputStream input, Long size) throws IOException {


        // 得到要上传的文件路径
        String filename = dirName + size + "-" + srcName;

        logger.info(filename);

        File file = new File(filename);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file);
        // 一次30kb
        byte[] readBuff = new byte[1024 * 30];
        int count = -1;
        while ((count = input.read(readBuff, 0, readBuff.length)) != -1) {
            fos.write(readBuff, 0, count);
        }
        fos.flush();
        fos.close();
        input.close();
        return filename;
    }
}























