package com.cc.downunder.datastore;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class ReadExcel {

    private static HashMap<String, Integer> nsw;
    public static double[] array = new double[12];

    public static void main(String[] args) {
        try {
//            readEntireFile();
            nsw = new HashMap<>();

            /**
             * row = 0 => state names
             * col = 0 => years
             */

            double output = readCell();
            for (int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
            }
//            System.out.println(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readEntireFile() throws IOException {
///File file = new File("Users/Jess/Documents/Master of IT/Workspace/DownUnder/data/Table11.xlsx");
        File file = new File("/Users/Jess/Documents/Master of IT/Workspace/DownUnder/data/Table11.xlsx");   //creating a new file instance
        FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
//creating Workbook instance that refers to .xlsx file
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
        Iterator<Row> itr = sheet.iterator();    //iterating over excel file

        while (itr.hasNext()) {
            Row row = itr.next();
            Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:    //field that represents string cell type
                        System.out.print(cell.getStringCellValue() + "\t\t\t");
                        break;
//                    case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
//                        System.out.print(cell.getNumericCellValue() + "\t\t\t");
//                        break;
                    default:
                }
            }
            System.out.println();
        }
    }

    public static double readCell() {
        double value = 0;         //variable for storing the cell value
        Workbook wb = null;           //initialize Workbook null
        try {
//reading data from a file in the form of bytes
            FileInputStream fis = new FileInputStream("/Users/Jess/Documents/Master of IT/Workspace/Test/Student.xlsx");
//            FileInputStream fis = new FileInputStream("/Users/Jess/Documents/Master of IT/Workspace/DownUnder/data/Table11.xlsx");

//constructs an XSSFWorkbook object, by buffering the whole stream into the memory
            wb = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);   //getting the XSSFSheet object at given index
//        System.out.println(sheet.getLastRowNum());
        // the state string
//        Row rowState = sheet.getRow(0);
//        Cell cellState = rowState.getCell(1);
//        String state = cellState.getStringCellValue();
////        System.out.println(state);
//
////        System.out.println(cellState);
//
//        for (int i = 1; i< 12; i++) {
//            Row r = sheet.getRow(i);
//            Cell c = r.getCell(1);
//
//           String v = c.getCellFormula();
//
////            Row rState = sheet.getRow(0);
////            Cell cState = rState.getCell(1);
////            String vState = cState.getStringCellValue();
//            System.out.print(state+ "-" + v + " ");
//
//            Cell cell = r.getCell(1);
//            value = cell.getNumericCellValue();
//            System.out.println(value);
//
//        }
        for (int i = 1; i < 12; i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(1);
            value = cell.getNumericCellValue();
            array[i] = value;
//            System.out.println(value);
        }

        //returns the logical row
        //getting the cell representing the given column

        //getting cell value
        return value;               //returns the cell value
    }
}
