package com.ycwl.qiny.data.elasticsearch;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataUtil {

    /**
     * 获取路径下的所有文件/文件夹
     *
     * @param directoryPath  需要遍历的文件夹路径
     * @param isAddDirectory 是否将子文件夹的路径也添加到list集合中
     * @return
     */
    public static List<String> getAllFile(String directoryPath, boolean isAddDirectory) {
        List<String> list = new ArrayList<>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if (isAddDirectory) {
                    list.add(file.getAbsolutePath());
                }
                list.addAll(getAllFile(file.getAbsolutePath(), isAddDirectory));
            } else {
                list.add(file.getAbsolutePath());
            }
        }
        return list;
    }

    public static List<Map> getDataFromExcel(String filePath) {
        List<Map> list = new ArrayList<>();
        if (!filePath.endsWith(".xls") && !filePath.endsWith(".xlsx")) {
            System.out.println("文件不是excel类型");
        }
        FileInputStream fis = null;
        Workbook wookbook = null;
        try {
            //获取一个绝对地址的流
            fis = new FileInputStream(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //2003版本的excel，用.xls结尾
            wookbook = new HSSFWorkbook(fis);//得到工作簿
        } catch (Exception ex) {
            //ex.printStackTrace();
            try {
                //2007版本的excel，用.xlsx结尾

                wookbook = new XSSFWorkbook(fis);//得到工作簿
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //得到一个工作表
        Sheet sheet = wookbook.getSheetAt(0);
        //获得表头
        Row rowHead = sheet.getRow(0);
        //判断表头是否正确
        if (rowHead.getPhysicalNumberOfCells() != 4) {
            System.out.println("表头的数量不对!");
            return list;
        }
        //获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();
        Cell cell;
        String[] key = {"room_id", "ammeter", "power", "@timestrap"};
        //获得所有数据
        for (int i = 1; i <= totalRowNum; i++) {
            Row row = sheet.getRow(i);
            Map map = new LinkedHashMap<String, Object>();
            for (short j = 0; j < 4; j++) {
                cell = row.getCell(j);
                cell.setCellType(CellType.STRING);
                map.put(key[j], cell.getStringCellValue());
            }
            String time = (String) map.get(key[3]);
            if (time.matches("^\\d{4}\\-\\d{2}\\-\\d{2}( )+\\d{2}\\:\\d{2}\\:\\d{2}$")) {
                try {

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    map.replace(key[3], dateFormat.parse(time));
                    list.add(map);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
