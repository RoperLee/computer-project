package com.computer.boot.service;

import com.computer.boot.model.User;
import com.computer.boot.vo.LastDateVo;
import com.computer.boot.vo.RankListVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by roper on 2017/5/15.
 */
@Service
public interface UtilServiceFacade {

    void showPic(HttpServletResponse response, String fileName);

    LastDateVo getExamDate();

    RankListVo getRankList(Long userId, int pageStart, int pageSize);

    boolean addExecuteQuestionCount(Long userId, int count);

    List<String> getBanner();

    User getUserInfo(Long userId);

    Object catchAndSaveImg(MultipartFile[] uploadImg);
}