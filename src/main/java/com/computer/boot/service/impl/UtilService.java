package com.computer.boot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.computer.boot.config.ImagePropertyUtils;
import com.computer.boot.config.ServicePropertyUtils;
import com.computer.boot.mapper.DirectoryMapper;
import com.computer.boot.mapper.ExamDateMapper;
import com.computer.boot.mapper.QuestionMapper;
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
import org.springframework.util.CollectionUtils;
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
    @Autowired
    private QuestionMapper questionMapper;

    private static final String OPTION_ABC = "{\"0\":\"A\",\"1\":\"B\",\"2\":\"C\",\"3\":\"D\",\"4\":\"E\",\"5\":\"F\",\"6\":\"G\",\"7\":\"H\",\"8\":\"I\",\"9\":\"J\",\"10\":\"K\"}";

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
     * @param postData {"subjectId":"1","directoryId":"42","issuseType":"CHOICE","sortKeyNumber":"1","isContentImg":"Y","contentStrList":[""],"contentImgNameList":["10176-AN-icon.png","10108-AU-icon.png"],"isSelectImg":false,"selectOptionStrList":["asdfasdf","asdfasdf","asdfadsf"],"correctSelectOption":"A","isAnswerImg":"N","answerStrList":["asdfasdf"],"answerImgNameList":null}
     * @return
     */
    @Override
    public boolean easyAddQuestion(String postData) {
        JSONObject originData = JSON.parseObject(postData);
        int subjectId = originData.getInteger("subjectId");
        Long directoryId = originData.getLong("directoryId");
        String issuseType = originData.getString("issuseType");//CHOICE-BLANK-OPERATION
        Long sortKeyNumber = originData.getLong("sortKeyNumber");
        String isContentImg = originData.getString("isContentImg");//"Y"-"N";
        List<String> contentStrList = JSONObject.parseArray(JSONObject.toJSONString(originData.getJSONArray("contentStrList"), SerializerFeature.WriteClassName),
                String.class); // 空的时候转化结果为null或者其他的空，正常判断就行
        List<String> contentImgNameList = JSONObject.parseArray(JSONObject.toJSONString(originData.getJSONArray("contentImgNameList"), SerializerFeature.WriteClassName),
                String.class);
        boolean isSelectImg = originData.getBoolean("isSelectImg");
        List<String> selectOptionStrList = JSONObject.parseArray(JSONObject.toJSONString(originData.getJSONArray("selectOptionStrList"), SerializerFeature.WriteClassName),
                String.class);
        String correctSelectOption = originData.getString("correctSelectOption");

        String isAnswerImg = originData.getString("isAnswerImg");//"Y"-"N"
        List<String> answerStrList = JSONObject.parseArray(JSONObject.toJSONString(originData.getJSONArray("answerStrList"), SerializerFeature.WriteClassName),
                String.class);
        List<String> answerImgNameList = JSONObject.parseArray(JSONObject.toJSONString(originData.getJSONArray("answerImgNameList"), SerializerFeature.WriteClassName),
                String.class);

        //首先根据subjectId 、directoryId 下是否已经存在 sortKeyNumber的题，如果存在则删除原有的，插入新的题
        if (questionMapper.hasExistCurrentQuestion(Long.valueOf(subjectId), directoryId, sortKeyNumber) > 0) {
            questionMapper.deleteCurrentQuestion(Long.valueOf(subjectId), directoryId, sortKeyNumber);
        }
        Question willInsert = new Question();

        willInsert.setIsDeleted("N");
        willInsert.setQuestionType(issuseType);
        willInsert.setSubjectId(subjectId);
        willInsert.setDirectoryId(directoryId);
        willInsert.setSortKeyNumber(sortKeyNumber);
        willInsert.setKind(directoryMapper.getDirectoryById(directoryId).getKind()); //真题还是模拟题
        //拼接题目内容
        JSONObject contentObject = new JSONObject();
        contentObject.put("isImg", isContentImg.equalsIgnoreCase("Y"));
        if (CollectionUtils.isEmpty(contentStrList)) {
            contentObject.put("text", new ArrayList<>());
        } else {
            contentObject.put("text", contentStrList);
        }
        if (CollectionUtils.isEmpty(contentImgNameList)) {
            contentObject.put("imgUrl", new ArrayList<>());
        } else {
            contentObject.put("imgUrl", parseImgName2UrlName(contentImgNameList));
        }
        willInsert.setContent(JSON.toJSONString(contentObject));
        //拼接选项option
        JSONObject optionObject = new JSONObject();
        optionObject.put("correctOption", correctSelectOption);
        JSONArray optionList = new JSONArray();
        JSONObject ABC = JSONObject.parseObject(OPTION_ABC);
        for (int i = 0; i < selectOptionStrList.size(); i++) {
            JSONObject item = new JSONObject();
            item.put("isImg", false);
            item.put(ABC.getString(String.valueOf(i)), selectOptionStrList.get(i));
            optionList.add(item);
        }
        optionObject.put("optionList", optionList);
        willInsert.setOption(JSON.toJSONString(optionObject));
        //拼接答案解析analysis
        JSONObject analysisObject = new JSONObject();
        analysisObject.put("isImg", isAnswerImg.equalsIgnoreCase("Y"));
        if (CollectionUtils.isEmpty(answerStrList)) {
            analysisObject.put("text", new ArrayList<>());
        } else {
            analysisObject.put("text", answerStrList);
        }
        if (CollectionUtils.isEmpty(answerImgNameList)) {
            analysisObject.put("imgUrl", new ArrayList<>());
        } else {
            analysisObject.put("imgUrl", parseImgName2UrlName(answerImgNameList));
        }
        willInsert.setAnalysis(JSON.toJSONString(analysisObject));

        //用于搜索的extra_info
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < contentStrList.size(); i++) {
            sb.append(contentStrList.get(i));
        }
        willInsert.setExtraInfo(sb.toString());

        questionMapper.insertQuestionData(willInsert);
        return true;
    }

    private List<String> parseImgName2UrlName(List<String> imgList) {
        List<String> resultList = new ArrayList<>();
        String host = servicePropertyUtils.getDns();
        String port = servicePropertyUtils.getPort();
        String IMAGE_PATH = host + ":" + port + "/image/showPic?fileName=";
        for (int i = 0; i < imgList.size(); i++) {
            resultList.add(IMAGE_PATH + imgList.get(i));
        }
        return resultList;
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
            String dir = imagePropertyUtils.getImagePath();
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























