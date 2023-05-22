package com.util.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.banlv.bean.Picture;
import com.banlv.bean.ScenicZone;
import com.banlv.bean.ScenicZoneEntry;
import com.banlv.bean.ScenicZone_scenicZoneEntry;
import com.banlv.independent.myService.myImpl.MyCityServiceImpl;
import com.banlv.independent.myService.myImpl.MyScenicZoneServiceImpl;
import com.banlv.service.PictureService;
import com.banlv.service.impl.PictureServiceImpl;
import com.banlv.service.impl.ScenicZoneEntryServiceImpl;
import com.banlv.service.impl.ScenicZoneServiceImpl;
import com.banlv.service.impl.ScenicZone_scenicZoneEntryServiceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InsertSZExcel {
    public static void main(String[] args) throws IOException {
        // 指定 Excel 文件路径
        File file = new File("D:\\L\\jingquspider\\zhejiang\\wenzhou\\data.xlsx");
        FileInputStream inputStream = new FileInputStream(file);

        // 创建 Workbook 对象
        Workbook workbook = new XSSFWorkbook(inputStream);

        // 获取第一个 Sheet
        Sheet sheet = workbook.getSheetAt(0);
        HashMap<String, Integer> map = new HashMap<>();

        // 遍历每一行
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // 遍历每一列
            Iterator<Cell> cellIterator = row.cellIterator();
            ArrayList<String> sz = new ArrayList<>();
            List<ScenicZoneEntry> all = new ScenicZoneEntryServiceImpl().findAll();
            if(!all.isEmpty()) {
                for (ScenicZoneEntry sze : all) {
                    map.put(sze.getScenicZoneEntry_name(),sze.getScenicZoneEntry_id());
                }

            }

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                sz.add(cell.toString());
                System.out.println(cell);


                // 根据数据类型获取单元格中的值
//                switch (cell.getCellType()) {
//                    case STRING:
//                        System.out.print(cell.getStringCellValue() + "\t");
//                        break;
//                    case NUMERIC:
//                        System.out.print(cell.getNumericCellValue() + "\t");
//                        break;
//                    case BOOLEAN:
//                        System.out.print(cell.getBooleanCellValue() + "\t");
//                        break;
//                    default:
//                        System.out.print("\t");
//                }
            }
            if(sz.get(0) != "None") {
                String sz_name = sz.get(0);
                String sz_grade = sz.get(1);
                String sz_cityId = sz.get(2);
                String sz_entry = sz.get(3);
                String sz_picture = sz.get(4);
                String sz_introduce = sz.get(5);
                ScenicZone scenicZone = new ScenicZone();
                scenicZone.setScenicZone_name(sz_name);
                scenicZone.setScenicZone_grade(sz_grade);
                MyCityServiceImpl myCityService = new MyCityServiceImpl();
                int i = myCityService.searchIdByCityName(sz_cityId);
                scenicZone.setCity_id(i);
                scenicZone.setScenicZoneType_id(5);
                scenicZone.setScenicZone_introduce(sz_introduce);

                ScenicZone_scenicZoneEntry szszEntry = new ScenicZone_scenicZoneEntry();

                //处理景区分类
                if(!map.containsKey(sz_entry)) {
                    //向景区词条表中添加数据
                    ScenicZoneEntry szEntry = new ScenicZoneEntry();
                    szEntry.setScenicZoneEntry_name(sz_entry);
                    new ScenicZoneEntryServiceImpl().addScenicZoneEntry(szEntry);

                    //获取当前词条id
                    List<ScenicZoneEntry> scenicZoneEntries = new ScenicZoneEntryServiceImpl().searchAll(szEntry);
                    int szeId = scenicZoneEntries.get(0).getScenicZoneEntry_id();
                    map.put(sz_entry, szeId);

                    //向景区词条中间表添加数据
                    szszEntry.setScenicZoneEntry_id(szeId);

                } else {
                    //从map表中获取当前词条id
                    szszEntry.setScenicZoneEntry_id(map.get(sz_entry));
                }
                szszEntry.setScenicZone_scenicSpot_use(true);

                //处理图片url
                Picture picture = new Picture();
                picture.setPicture_name(sz_name + "背景图");
                picture.setPicture_url(sz_picture);
                picture.setPicture_use(true);
                PictureServiceImpl pictureService = new PictureServiceImpl();
                pictureService.addPicture(picture);
                long picture_id = pictureService.searchAll(picture).get(0).getPicture_id();
                scenicZone.setScenicZone_bg(picture_id + ";");

                scenicZone.setScenicManage_id(1l);
                scenicZone.setScenicZoneType_id(5);


                ScenicZoneServiceImpl scenicZoneService = new ScenicZoneServiceImpl();
                scenicZoneService.addScenicZone(scenicZone);
                //获取景区id
                ScenicZone sz1 = new ScenicZone();
                sz1.setScenicZone_name(sz_name);
                List<ScenicZone> scenicZones = scenicZoneService.searchAll(sz1);
                long scenicZone_id = scenicZones.get(0).getScenicZone_id();
                szszEntry.setScenicZone_id(scenicZone_id);
                //添加数据到景区词条中间表
                new ScenicZone_scenicZoneEntryServiceImpl().addScenicZone_scenicZoneEntry(szszEntry);

            }
            System.out.println();
        }

        // 关闭资源
        workbook.close();
        inputStream.close();
    }

    public int DealWith(List list) {
        boolean empty = list.isEmpty();

        return 0;

    }

    public static int GetCityId(String cityName) {
        return new MyCityServiceImpl().searchIdByCityName(cityName);
    }

}

