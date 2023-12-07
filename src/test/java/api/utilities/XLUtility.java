package api.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class XLUtility {

    public FileInputStream fileInputStream;
    public FileOutputStream fileOutputStream;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;


    public XLUtility (String path){

        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {

        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fileInputStream.close();

        return rowCount;
    }


    public int getCellCount (String sheetName, int rowNum) throws IOException{

        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fileInputStream.close();

        return cellCount;
    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException{

        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        DataFormatter formatter = new DataFormatter();
        String data;

        try{
            data = formatter.formatCellValue(cell);

        } catch (Exception e) {
            data = "";
        }

        workbook.close();
        fileInputStream.close();

        return data;
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException{


        File xlFile = new File(path);

        if(!xlFile.exists()){
            workbook = new XSSFWorkbook();
            fileOutputStream = new FileOutputStream(path);
            workbook.write(fileOutputStream);
        }

        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);

        if(workbook.getSheetIndex(sheetName) == -1)
            workbook.createSheet(sheetName);
        sheet = workbook.getSheet(sheetName);

        if(sheet.getRow(rowNum) == null)
            sheet.createRow(rowNum);
        row = sheet.getRow(rowNum);

        cell = row.createCell(colNum);
        cell.setCellValue(data);
        fileOutputStream = new FileOutputStream(path);
        workbook.write(fileOutputStream);
        workbook.close();
        fileInputStream.close();
        fileOutputStream.close();

    }

}
