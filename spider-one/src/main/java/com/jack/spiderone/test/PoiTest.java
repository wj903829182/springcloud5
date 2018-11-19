package com.jack.spiderone.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * create by jack 2018/11/19
 *
 * @author jack
 * @date: 2018/11/19 20:51
 * @Description: POI操作excel
 */
public class PoiTest {


    /**
     * 使用POI写入数据
     *
     * @throws IOException
     */
    public static void test1() throws IOException {
        //创建文件输出流
        File file = new File("F:\\mystudy\\b.xlsx");
        OutputStream outputStream = new FileOutputStream(file);
        //创建工作簿及工作表
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        //添加表头
        XSSFRow row = sheet.createRow(0); //常见某行
        row.createCell(0).setCellValue("post_id");
        row.createCell(1).setCellValue("post_title");
        //添加内容
        for (int i = 0; i < 2; i++) {
            XSSFRow everyRow = sheet.createRow(i + 1);
            everyRow.createCell(0).setCellValue("帖子id为：0" + i);
            everyRow.createCell(1).setCellValue("帖子内容为：" + i);
        }
        //数据写入工作目录，并释放资源
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();

    }


    /**
     * POI 数据读取
     *
     * @throws IOException
     */
    public static void test2() throws IOException {
        //文件名称
        File file = new File("F:\\mystudy\\b.xlsx");
        //File file = new File("data/c.xlsx");
        //根据文件名称获取操作工作簿
        Workbook workbook = getWorkBook(file);
        //获取读取的工作表，这里有两种方式
        Sheet sheet = workbook.getSheet("Sheet1");
        //Sheet sheet=workbook.getSheetAt(0);

        //获取行数
        int allRow = sheet.getLastRowNum();
        //按行读取数据
        for (int i = 0; i <= allRow; i++) {
            Row row = sheet.getRow(i);
            //获取列数
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                String cellValue = row.getCell(j).getStringCellValue();
                System.out.print(cellValue + "\t");
            }
            System.out.println();
        }
        //释放资源
        workbook.close();
    }

    /**
     * 判断是2003版本，还是2007之后的版本
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkBook(File file) throws IOException {
        //输入流
        InputStream in = new FileInputStream(file);
        Workbook workbook = null;
        //Excel 2003
        if (file.getName().endsWith("xls")) {
            workbook = new HSSFWorkbook(in);
            // Excel 2007以上版本
        } else if (file.getName().endsWith("xlsx")) {
            workbook = new XSSFWorkbook(in);
        }
        in.close();
        return workbook;
    }

    public static void main(String[] args) throws IOException {
        //test1();
        test2();
    }


}
