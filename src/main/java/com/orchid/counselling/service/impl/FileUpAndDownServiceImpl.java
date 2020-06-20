package com.orchid.counselling.service.impl;

import com.orchid.counselling.service.FileUpAndDownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class FileUpAndDownServiceImpl implements FileUpAndDownService {

    @Override
    public String uploadPicture(MultipartFile file, String savePath) {

        //上传文件
        String fileName = file.getOriginalFilename();

        //重新定义文件名
        fileName = new Date().getTime() + fileName;

        File file1 = new File(savePath + "/" + fileName);

        //上传
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }

        return fileName;
    }
}

