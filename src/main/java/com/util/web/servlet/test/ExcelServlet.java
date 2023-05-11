package com.util.web.servlet.test;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@RestController
public class ExcelServlet {
    @RequestMapping("/importExcel")
    @ResponseBody
    public String importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse
            response) {
        System.out.println("file"+file.getSize());
        try {
            // @RequestParam("file") MultipartFile file 是用来接收前端传递过来的文件
            // 1.创建workbook对象，读取整个文档
            InputStream inputStream = file.getInputStream();
            //POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            // 2.读取页脚sheet
            XSSFSheet sheetAt = wb.getSheetAt(0);

            // 3.循环读取某一行
            int index = 0;
            for (Row row : sheetAt) {
                // 4.读取每一行的单元格
                if (index == 0) {
                    index++;
                    continue;
                }
                System.out.println(row.toString());
//                //创建一个学生对象
//                SysStudent student = new SysStudent();
//
//                //将Excel表中单元格的值与学生对象的值对应
//                student.setName(row.getCell(0).getStringCellValue());
//                //因为学号是数字，Excel默认是数字类型，我的数据库是字符串类型，所以需要设置下类型
//                row.getCell(1).setCellType(CellType.STRING);
//                student.setStuId(row.getCell(1).getStringCellValue());
//                student.setIdentity(row.getCell(2).getStringCellValue());
//                student.setDescription(row.getCell(3).getStringCellValue());
//                student.setProvince(row.getCell(4).getStringCellValue());
//                sysStudentService.insertSysStudent(student);
//
//
//                row.getCell(0).setCellType(CellType.STRING);
//                String stringCellValue = row.getCell(0).getStringCellValue();
//                row.getCell(1).setCellType(CellType.STRING);
//                String stringCellValue2 = row.getCell(1).getStringCellValue();
//                row.getCell(2).setCellType(CellType.STRING);
//                String stringCellValue3 = row.getCell(2).getStringCellValue();
//                row.getCell(3).setCellType(CellType.STRING);
//                String stringCellValue4 = row.getCell(3).getStringCellValue();
//                row.getCell(4).setCellType(CellType.STRING);
//                String stringCellValue5 = row.getCell(4).getStringCellValue();


//                // 写多少个具体看大家上传的文件有多少列.....
//                // 测试是否读取到数据,及数据的正确性
//                System.out.println(stringCellValue);
//                System.out.println(stringCellValue2);
//                System.out.println(stringCellValue3);
//                System.out.println(stringCellValue4);
//                System.out.println(stringCellValue5);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "上传成功";
    }
}
