package com.orchid.counselling.util;

import com.orchid.counselling.pojo.Topic;
import com.orchid.counselling.pojo.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ImportExcel {

    private static int flag = 0;//进度条的值


    private static Map<String, Object> map = new HashMap<>();//返回的信息

    private static StringJoiner buffer = new StringJoiner("\n");

    /**
     * 读取用户信息excel
     * @param multipartFile
     * @param name
     * @return
     */
    public static Map<String, Object> uploadExcel(MultipartFile multipartFile, String name){

        List<User> userList = new ArrayList<>();//保存获取到的数据

        try {
            if(name != null){
                InputStream inputStream = multipartFile.getInputStream();
                Workbook workbook =  null;
                if (isExcel2003(name)){
                    workbook = new HSSFWorkbook(inputStream);
                }
                if(isExcel2007(name)){
                    workbook = new XSSFWorkbook(inputStream);
                }
                int numberOfSheets = workbook.getNumberOfSheets();

                Sheet sheet = workbook.getSheetAt(0);
                int rowNum = sheet.getLastRowNum();

                if(rowNum == 0){
                    flag = 100;
                    buffer.add("导入文件数据为空");
                }

                for (int i = 1; i <= rowNum; i++){
                    if (flag < 100){
                        flag = flag + (100/i);
                    }else {
                        flag = 100;
                    }

                    //加载状态值，当前进度
                    User user = new User();//需要插入的数据
                    Row row = sheet.getRow(i);//获取第i行
                    if (row != null){
                        Cell cell1 = row.getCell(0);//获取第一个单元格的数据
                        Cell cell2 = row.getCell(1);
                        if (cell1 != null){//用户名列验证
                            cell1.setCellType(CellType.STRING);
                            user.setUsername(cell1.getStringCellValue());
                        }else {
                            buffer.add("第" + i + "行的第一列数据不能为空");
                        }

                        if (cell2 != null){//真实姓名列验证
                            cell2.setCellType(CellType.STRING);
                            user.setRealName(cell2.getStringCellValue());
                        }else {
                            buffer.add("第" + i + "行的第一列数据不能为空");
                        }

                        if (user.getUsername() != null && user.getRealName() != null){
                            userList.add(user);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        map.put("userList", userList);
        map.put("errorInfo", buffer);

        return map;
    }

    /**
     * 读取试题信息excel
     * @param multipartFile
     * @param name
     * @return
     */
    public static Map<String, Object> readTopicExcel(MultipartFile multipartFile, String name){

        List<Topic> topicList = new ArrayList<>();

        try {
            if(name != null){
                InputStream inputStream = multipartFile.getInputStream();
                Workbook workbook =  null;
                if (isExcel2003(name)){
                    workbook = new HSSFWorkbook(inputStream);
                }
                if(isExcel2007(name)){
                    workbook = new XSSFWorkbook(inputStream);
                }
                int numberOfSheets = workbook.getNumberOfSheets();

                Sheet sheet = workbook.getSheetAt(0);
                int rowNum = sheet.getLastRowNum();

                if(rowNum == 0){
                    flag = 100;
                    buffer.add("导入文件数据为空");
                }

                for (int i = 1; i <= rowNum; i++){
                    if (flag < 100){
                        flag = flag + (100/i);
                    }else {
                        flag = 100;
                    }

                    //加载状态值，当前进度
                    Topic topic = new Topic();
                    Row row = sheet.getRow(i);//获取第i行
                    if (row != null){
                        Cell cell1 = row.getCell(0);//获取第一个单元格的数据
                        Cell cell2 = row.getCell(1);

                        if (cell1 != null){//用户名列验证
                            cell1.setCellType(CellType.STRING);
                            topic.setTestQuestion(cell1.getStringCellValue());
                        }else {
                            buffer.add("第" + i + "行的第一列数据不能为空");
                        }

                        if (cell2 != null){//真实姓名列验证
                            cell2.setCellType(CellType.STRING);
                            topic.setDescription(cell2.getStringCellValue());
                        }else {
                            buffer.add("第" + i + "行的第一列数据不能为空");
                        }

                        if (topic.getTestQuestion() != null && topic.getDescription() != null){
                            topicList.add(topic);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        map.put("list", topicList);
        map.put("errorInfo", buffer);

        return map;
    }

    /***
     *判断文件类型是不是2003版本
     * @param
     * @return
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     *判断文件类型是不是2007版本
     * @param
     * @return
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

}
