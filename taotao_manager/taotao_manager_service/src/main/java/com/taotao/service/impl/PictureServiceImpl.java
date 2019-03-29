package com.taotao.service.impl;

import com.taotao.common.utils.FastDFSClient;
import com.taotao.common.vo.PictureResult;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class PictureServiceImpl implements PictureService {
    //可以取出spring加载的properties文件中的值
    @Value("${IMAGE_SERVER_BASE_URL}")
    private String IMAGE_SERVER_BASE_URL;
    /**
     * 图片上传方法
     * @param file 前台传过来的文件
     * @return 返回为封装vo类
     * @throws Exception
     */
    @Override
    public PictureResult uploadPic(MultipartFile file)  {
        PictureResult result = new PictureResult();
        //判断图片是否为空
        if(file.isEmpty()){
            result.setError(1);
            result.setMessage("图片上传失败");
            return result;
        }
        //上传到图片服务器
        try {
            String originalFileName = file.getOriginalFilename();
            String extName = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
            FastDFSClient client = new FastDFSClient("classpath:client.conf");
            String url = client.uploadFile(file.getBytes(),extName);
            //拼接图片服务器的地址
            url = IMAGE_SERVER_BASE_URL+url;
            System.out.println(url);
            result.setError(0);
            result.setUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(1);
            result.setMessage("图片上传失败");
        }
        System.out.println(result.getError());
        return result;
    }
}
