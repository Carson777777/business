package com.taotao.controller;

import com.taotao.common.vo.PictureResult;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PictureController {
    @Autowired
    PictureService service;
    @RequestMapping("pic/upload")
    @ResponseBody
    public PictureResult uploadFile (MultipartFile uploadFile){
        PictureResult result = service.uploadPic(uploadFile);
        return result;
    }
}
