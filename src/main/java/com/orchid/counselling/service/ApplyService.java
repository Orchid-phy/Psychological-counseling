package com.orchid.counselling.service;

import com.orchid.counselling.pojo.Apply;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApplyService {

    /**
     * 申请管理员,证件照上传审核
     * @param mechanism
     * @param inputfile1
     * @param inputfile2
     * @return
     */
    public boolean adminApply(String mechanism, String IDCard, String inputfile1, String inputfile2);

    /**
     * 插入心理咨询师申请请求
     * @param IDCard 身份证编号
     * @param IDNum 心理咨询师编号
     * @param inputfile1 身份证正面
     * @param inputfile2  身份照背面
     * @param inputfile3 心理咨询师证件照片
     * @return
     */
    public Boolean addCouncellingApply(String IDCard, String IDNum, String inputfile1, String inputfile2, String inputfile3);

    /**
     * 查询申请记录
     * @param applyUserId
     * @return
     */
    public List<Apply> findApply(Long applyUserId);

    /**
     * 查询咨询师申请记录
     * @param applyUserId
     * @return
     */
    public List<Apply> findCounselorApply(Long applyUserId);

}
