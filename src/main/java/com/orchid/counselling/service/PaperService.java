package com.orchid.counselling.service;

import com.orchid.counselling.pojo.Paper;
import com.orchid.counselling.pojo.Topic;
import org.springframework.stereotype.Component;

import java.util.List;

public interface PaperService {


    /**
     * 根据id或者类型名字模糊查询试题
     * @param id 试卷id
     * @param typeName 类型名
     * @return
     */
    public List<Paper> findAllPaperByIdOrType(String id, String typeName);

    /**
     * 查询测试人数最多的前三个
     * @return
     */
    public List<Paper> findPaperTop();

    /**
     * 根据类型查询试题信息
     * @param typeName
     * @return
     */
    public List<Paper> findPaperByType(String typeName);

    /**
     * 根据试题id查询试题信息
     * @param id
     * @return
     */
    public Paper findPaperById(Integer id);



}
