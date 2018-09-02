package com.group.integrate.service;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author xinjing.tang
 * @since 2018/8/29.
 */
@Service
public class ExcelService {

    public Object generateExecl() throws IOException {

//        //创建HSSFWorkbook对象
//        HSSFWorkbook wb = new HSSFWorkbook();
////创建HSSFSheet对象
//        HSSFSheet sheet = wb.createSheet("sheet0");
////创建HSSFRow对象
//        HSSFRow row = sheet.createRow(0);
////创建HSSFCell对象
//        HSSFCell cell=row.createCell(0);
////设置单元格的值
//        cell.setCellValue("单元格中的中文");
////输出Excel文件
//        FileOutputStream output=new FileOutputStream("d:\\workbook.xls");
//        wb.write(output);
//        output.flush();

        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet=wb.createSheet("成绩表");
//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);
//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("学员考试成绩一览表");
//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
//在sheet里创建第二行
        HSSFRow row2=sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("姓名");
        row2.createCell(1).setCellValue("班级");
        row2.createCell(2).setCellValue("笔试成绩");
        row2.createCell(3).setCellValue("机试成绩");
        //在sheet里创建第三行
        HSSFRow row3=sheet.createRow(2);
        row3.createCell(0).setCellValue("李明");
        row3.createCell(1).setCellValue("As178");
        row3.createCell(2).setCellValue(87);
        row3.createCell(3).setCellValue(78);
        //.....省略部分代码


//输出Excel文件
//        OutputStream output=response.getOutputStream();
//        response.reset();
//        response.setHeader("Content-disposition", "attachment; filename=details.xls");
//        response.setContentType("application/msexcel");
//        wkb.write(output);
        FileOutputStream output=new FileOutputStream("d:\\workbook1.xls");
        wb.write(output);
        output.flush();
        output.close();
        return null;
    }

    public void updateExexl() throws IOException {
        Workbook workbook = new XSSFWorkbook(new FileInputStream(new File("d:\\workbook1.xls")));
        Sheet sheet = null;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
             sheet = workbook.getSheetAt( 0 );
            for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {// getLastRowNum，获取最后一行的行标
                Row row = sheet.getRow(j);
                if (row != null) {
                    for (int k = 0; k < row.getLastCellNum(); k++) {// getLastCellNum，是获取最后一个不为空的列是第几个
                        if (row.getCell(k) != null) { // getCell 获取单元格数据
                            System.out.print(row.getCell(k) + "\t");
                        } else {
                            System.out.print("\t");
                        }
                    }
                }
                System.out.println(""); // 读完一行后换行
            }
            System.out.println("读取sheet表：" + workbook.getSheetName(i) + " 完成");
        }

    }


}
