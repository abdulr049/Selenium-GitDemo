package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    static String path = System.getProperty("user.dir") + "//reports//TestResults.xlsx";

    static Workbook workbook;
    static Sheet sheet;

    // Create Excel file
    public static void createExcel() throws Exception {

        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Results");

        Row row = sheet.createRow(0);

        row.createCell(0).setCellValue("TestCase");
        row.createCell(1).setCellValue("Status");

        FileOutputStream fos = new FileOutputStream(path);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }

    // Write result into Excel
    public static void writeResult(String testName, String status) throws Exception {

        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);

        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheetAt(0);

        int lastRow = sheet.getLastRowNum() + 1;

        Row row = sheet.createRow(lastRow);

        row.createCell(0).setCellValue(testName);
        row.createCell(1).setCellValue(status);

        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);

        fos.close();
        workbook.close();
        fis.close();
    }
}