package com.computer.boot.service.impl;

import com.computer.boot.config.PropertyUtils;
import com.computer.boot.mapper.ExamDateMapper;
import com.computer.boot.model.ExamDate;
import com.computer.boot.service.UtilServiceFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by roper on 2017/5/15.
 */

@Service
public class UtilService implements UtilServiceFacade {
    private static final Logger logger = LoggerFactory.getLogger(UtilService.class);

    @Autowired
    private PropertyUtils propertyUtils;
    @Autowired
    private ExamDateMapper examDateMapper;

    @Override
    public void showPic(HttpServletResponse response, String fileName) {

        String fileUrl = propertyUtils.getImagePath() + fileName;
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
}