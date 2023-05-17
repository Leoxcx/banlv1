package com.util.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AddExcelInfo {
    public static void main(String[] args) {
        String fileName = "D:\\360MoveData\\Users\\Administrator\\Desktop\\my\\China1.xlsx"; // Excel文件名
        String sheetName = "Sheet1"; // 工作表名称
        int i = 1;

        // 读取Excel文件
        try (FileInputStream fis = new FileInputStream(new File(fileName));
             Workbook workbook = WorkbookFactory.create(fis)) {

            // 获取指定的工作表
            Sheet sheet = workbook.getSheet(sheetName);

            // 遍历工作表中的每一行
            for (Row row : sheet) {
                // 遍历当前行中的每一列
                ArrayList<String> objects = new ArrayList<>();

                for (Cell cell : row) {
                    if(!cell.toString().isEmpty()) {
                        objects.add(cell.toString());
                    }
                }
                AddInfo.addCity(objects.get(1), objects.get(0), i+"");
                // 获取单元格中的值并打印到控制台上
                System.out.println(i++);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}