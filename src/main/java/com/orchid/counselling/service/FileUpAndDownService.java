package com.orchid.counselling.service;

import com.orchid.counselling.util.JsonResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileUpAndDownService {

    /**
     * 上传图片
     * @param file
     * @return
     */
    public String uploadPicture(MultipartFile file, String savePath);

}
