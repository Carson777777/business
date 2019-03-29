package com.taotao.service;

import com.taotao.common.vo.PictureResult;
import org.springframework.web.multipart.MultipartFile;


public interface PictureService {
    /**
     * 上传图片方法
     * @param file 前台传过来的文件
     * @return 返回一个vo类型
     */
    PictureResult uploadPic(MultipartFile  file);
}
