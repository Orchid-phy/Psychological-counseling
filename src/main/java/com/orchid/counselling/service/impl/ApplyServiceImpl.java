package com.orchid.counselling.service.impl;

import com.orchid.counselling.mapper.ApplyMapper;
import com.orchid.counselling.pojo.Apply;
import com.orchid.counselling.pojo.GroupMy;
import com.orchid.counselling.pojo.User;
import com.orchid.counselling.service.ApplyService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ApplyMapper applyMapper;

    @Override
    public boolean adminApply(String mechanism, String IDCard, String inputfile1, String inputfile2) {

        //获取用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();

        //构建添加参数
        Apply apply = new Apply(IDCard, inputfile1, inputfile2, new User(username), mechanism);

        //添加申请记录
        Integer integer = applyMapper.inAdminApply(apply);

        if (integer > 0){
            return true;
        }

        return false;
    }

    @Override
    public Boolean addCouncellingApply(String IDCard, String IDNum, String inputfile1, String inputfile2, String inputfile3) {

        //获取用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();

        //设置添加参数
        Apply apply = new Apply(IDCard, inputfile1, inputfile2, IDNum, inputfile3, new User());

        //添加数据
        Integer isAdd = applyMapper.inCouncellingApply(apply);

        if (isAdd > 0){
            return true;
        }

        return false;
    }

    @Override
    public List<Apply> findApply(Long applyUserId) {

        List<Apply> applyList = applyMapper.selApply(applyUserId);

        return applyList;
    }

    @Override
    public List<Apply> findCounselorApply(Long applyUserId) {

        List<Apply> applyList = applyMapper.selCounselorApply(applyUserId);

        return applyList;
    }

}
