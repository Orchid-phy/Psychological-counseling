package com.orchid.counselling.mapper;

import com.orchid.counselling.pojo.GroupMy;
import com.orchid.counselling.pojo.Paper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PaperMapper {

    /**
     * 根据id或者类型名字模糊查询试题
     * @param paper
     * @return
     */
    public List<Paper> selAllPaperByIdOrType(Paper paper);

    /**
     * 查询测试人数最多的前三个
     * @return
     */
    public List<Paper> selPaperTop();

    /**
     * 根据类型查询试题信息
     * @param typeName
     * @return
     */
    public List<Paper> selPaperByType(String typeName);

    /**
     * 根据试题id查询试题信息
     * @param id
     * @return
     */
    public Paper selPaperById(Integer id);
}
