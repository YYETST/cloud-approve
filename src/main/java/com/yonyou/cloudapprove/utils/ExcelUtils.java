package com.yonyou.cloudapprove.utils;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nishch
 * @version 1.0
 * @date 2020/7/1
 * @des
 */
public class ExcelUtils {

    public static List<String> getAccounts(String fileName,int column){
        List<String> list = new ArrayList<String>();
        try {
            //excel模板路径
            File f = new File(fileName);
            InputStream in =new FileInputStream(f);
            //读取excel模板
            HSSFWorkbook  wb = new HSSFWorkbook(in);
            int sheets = wb.getNumberOfSheets();
            HSSFSheet sheetAt = wb.getSheetAt(0);
            int rows = sheetAt.getPhysicalNumberOfRows();
            for(int i=1;i<rows;i++){
                HSSFRow row = sheetAt.getRow(i);
                row.getCell(column).setCellType(CellType.STRING);
                String value = row.getCell(column).getStringCellValue();
                list.add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void useRow(String fileName,List<String> useAccount,int column){
        try {
            //excel模板路径
            File f = new File(fileName);
            InputStream in =new FileInputStream(f);
            //读取excel模板
            HSSFWorkbook  wb = new HSSFWorkbook(in);
            int sheets = wb.getNumberOfSheets();
            HSSFSheet sheetAt = wb.getSheetAt(0);
            int rows = sheetAt.getPhysicalNumberOfRows();
            for(int i=1;i<rows;i++){
                HSSFRow row = sheetAt.getRow(i);
                if(null!=row.getCell(column)&&!" ".equals(row.getCell(column))){
                    String value = row.getCell(column).getStringCellValue();
                    if(!useAccount.contains(value)){
                        row.getCell(column).setCellValue(new HSSFRichTextString("sssss"));
                    }
                }
            }
            FileOutputStream out= new FileOutputStream(fileName);
            wb.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
