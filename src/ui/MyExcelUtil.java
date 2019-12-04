package ui;

import model.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MyExcelUtil {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    public static Workbook getWorkbook(FileInputStream inputStream, String fileType) throws IOException {
        if (fileType.equalsIgnoreCase(XLS)) {
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            return workbook;
        } else{
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            return workbook;
        }
    }

    public static List<Student> readExcel(String fileName) {

        Workbook workbook = null;
        FileInputStream inputStream = null;

        try {
            // get suffix
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            // get excel file
            File excelFile = new File(fileName);
            if (!excelFile.exists()) {
                System.out.println("file does not exist!");
                return null;
            }

            inputStream = new FileInputStream(fileName);
            workbook = getWorkbook(inputStream, fileType);

            // 读取excel中的数据
            List<Student> resultDataList = parseExcel(workbook);

            return resultDataList;
        } catch (Exception e) {
            System.out.println("fail to parse file" + fileName);
            return null;
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                System.out.println("fail to close data flow!" + e.getMessage());
                return null;
            }
        }
    }

    private static List<Student> parseExcel(Workbook workbook) {
        List<Student> resultDataList = new ArrayList<>();
        // 解析sheet
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);

            // 校验sheet是否合法
            if (sheet == null) {
                continue;
            }

            // 获取第一行数据
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            if (null == firstRow) {
                System.out.println("fail to parse excel, there is no data in first row!");
            }

            // 解析每一行的数据，构造数据对象
            int rowStart = firstRowNum + 1;
            int rowEnd = sheet.getPhysicalNumberOfRows();
            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);

                if (null == row) {
                    continue;
                }

                Student resultData = convertRowToData(row);
                if (null == resultData) {
                    System.out.println("ignore the illegal data in row " + row.getRowNum());
                    continue;
                }
                resultDataList.add(resultData);
            }
        }

        return resultDataList;
    }

    private static String convertCellValueToString(Cell cell) {
        if(cell==null){
            return null;
        }
        String returnValue = null;
        switch (cell.getCellType()) {
            case NUMERIC:   //数字
                Double doubleValue = cell.getNumericCellValue();

                // 格式化科学计数法，取一位整数
                DecimalFormat df = new DecimalFormat("0");
                returnValue = df.format(doubleValue);
                break;
            case STRING:    //字符串
                returnValue = cell.getStringCellValue();
                break;
            case BOOLEAN:   //布尔
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case BLANK:     // 空值
                break;
            case FORMULA:   // 公式
                returnValue = cell.getCellFormula();
                break;
            case ERROR:     // 故障
                break;
            default:
                break;
        }
        return returnValue;
    }

    private static Student convertRowToData(Row row) {
        Student resultData = new Student();

        Cell cell;
        int cellNum = 0;
        // get first name
        cell = row.getCell(cellNum++);
        String fname = convertCellValueToString(cell);
        // get last name
        cell = row.getCell(cellNum++);
        String lname = convertCellValueToString(cell);
        //get middle name
        cell = row.getCell(cellNum++);
        String mname = convertCellValueToString(cell);
        resultData.setName(fname, lname, mname);
        // get email
        cell = row.getCell(cellNum++);
        String email = convertCellValueToString(cell);
        resultData.setEmail(email);
        return resultData;
    }
}
