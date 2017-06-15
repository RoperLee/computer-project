package com.computer.boot.service.impl;

import com.computer.boot.config.ImagePropertyUtils;
import com.computer.boot.config.ServicePropertyUtils;
import com.computer.boot.mapper.ExamDateMapper;
import com.computer.boot.mapper.UserMapper;
import com.computer.boot.model.ExamDate;
import com.computer.boot.service.UtilServiceFacade;
import com.computer.boot.vo.RankListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
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
    public int getExamDate() {
        ExamDate examDate = examDateMapper.getExamDate();
        return (int) ((examDate.getExamDate().getTime() - new Date().getTime()) / (1000 * 3600 * 24));
    }

    @Override
    public RankListVo getRankList(Long userId, int pageStart, int pageSize) {
        RankListVo result = new RankListVo();
        if (null == userId) {
            return result;
        }
        result.setSelf(userMapper.getUserRanking(userId));
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
        String ip = servicePropertyUtils.getAddress();
        String port = servicePropertyUtils.getPort();
        List<String> bannerList = new ArrayList<>();
        String IMAGE_PATH = ip + ":" + port + "/image/showPic?fileName=";
        bannerList.add(IMAGE_PATH + "banner_1.png");
        bannerList.add(IMAGE_PATH + "banner_2.png");
        bannerList.add(IMAGE_PATH + "banner_3.png");
        return bannerList;
    }
}