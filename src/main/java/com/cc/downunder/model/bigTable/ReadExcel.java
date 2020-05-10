package com.cc.downunder.model.bigTable;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jessica Cui
 * @Project: Cloud Computing
 * @Date: 2020/05/02
 */
public class ReadExcel {

    public static void main(String[] args) {

        Map<String, Integer> columnMap = getColumnName("./data/Table11.xlsx");
        for (String colName : columnMap.keySet()) {

            if (colName.equalsIgnoreCase("NSW")) {
                Map<String, Double> mapNSW = readColData(columnMap.get(colName));
                insertIntoBigTable(mapNSW, "NSW");
            } else if (colName.equalsIgnoreCase("VIC")) {
                Map<String, Double> mapVIC = readColData(columnMap.get(colName));
                insertIntoBigTable(mapVIC, "VIC");
            } else if (colName.equalsIgnoreCase("SA")) {
                Map<String, Double> mapSA = readColData(columnMap.get(colName));
                insertIntoBigTable(mapSA, "SA");
            } else if (colName.equalsIgnoreCase("WA")) {
                Map<String, Double> mapSA = readColData(columnMap.get(colName));
                insertIntoBigTable(mapSA, "WA");
            } else if (colName.equalsIgnoreCase("TAS")) {
                Map<String, Double> mapSA = readColData(columnMap.get(colName));
                insertIntoBigTable(mapSA, "TAS");
            } else if (colName.equalsIgnoreCase("NT")) {
                Map<String, Double> mapNT = readColData(columnMap.get(colName));
                insertIntoBigTable(mapNT, "NT");
            } else if (colName.equalsIgnoreCase("QLD")) {
                Map<String, Double> mapQLD = readColData(columnMap.get(colName));
                insertIntoBigTable(mapQLD, "QLD");
            } else if (colName.equalsIgnoreCase("ACT")) {
                Map<String, Double> mapACT = readColData(columnMap.get(colName));
                insertIntoBigTable(mapACT, "ACT");
            } else if (colName.equalsIgnoreCase("OTHER")) {
                Map<String, Double> mapOther = readColData(columnMap.get(colName));
                insertIntoBigTable(mapOther, "OTHER");
            } else if (colName.equalsIgnoreCase("TOTAL")) {
                Map<String, Double> mapAUS = readColData(columnMap.get(colName));
                insertIntoBigTable(mapAUS, "AUS");
            }
        }

    }

    //    public void insertIntoBigTable(String projectId, String instanceId, String tableName, Map<String, Double> mapName, String stateName) {
    public static void insertIntoBigTable(Map<String, Double> mapName, String stateName) {
        String projectId = "s3763636-myapi";
        String instanceId = "s3763636v7";
        String tableName = "new-table";
        try {
            BigTable bigTable = new BigTable(projectId, instanceId, tableName, "state", stateName, mapName);
            bigTable.run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static Map<String, Integer> getColumnName(String filePath) {
        XSSFWorkbook wb = null;
        try (OPCPackage pkg = OPCPackage.open(filePath)) {
            wb = new XSSFWorkbook(pkg);
            XSSFReader r = new XSSFReader(pkg);
        } catch (InvalidFormatException | IOException e) {
            System.out.println(e.getMessage());
        } catch (OpenXML4JException e) {
            System.out.println(e.getMessage());
        }

        Map<String, Integer> columnNameMap = new HashMap<>();


        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);
        String[] states = new String[row.getLastCellNum()];

        for (int i = 0; i < row.getLastCellNum(); i++) {
            if (row.getCell(i) == null) {
                columnNameMap.put("null", i);
            } else {
                Cell cell = row.getCell(i);
                String cellName = cell.getStringCellValue();
                columnNameMap.put(cellName, i);
            }
        }
        return columnNameMap;
    }

    public static HashMap<String, Double> readColData(int colIndex) {
        XSSFWorkbook wb = null;

        try (OPCPackage pkg = OPCPackage.open("./data/Table11.xlsx")) {
            wb = new XSSFWorkbook(pkg);
            XSSFReader r = new XSSFReader(pkg);
        } catch (InvalidFormatException | IOException e) {
            System.out.println(e.getMessage());
        } catch (OpenXML4JException e) {
            System.out.println(e.getMessage());
        }

        Sheet sheet = wb.getSheetAt(0);
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

        HashMap<String, Double> map = new HashMap<>();

        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row rows = sheet.getRow(i);

            // get the year values
            Cell dateCell = rows.getCell(0);

            String dateKey = null;
            DateFormat df = new SimpleDateFormat("yyyy/MM");
            Date date = dateCell.getDateCellValue();
            dateKey = df.format(date);
//            System.out.println(name);

            // next column
            Cell valueCell = rows.getCell(colIndex);

            double value = -1;

            CellType type = valueCell.getCellType();
            if (type == CellType.NUMERIC) {
                value = (int) valueCell.getNumericCellValue();
            } else if (type == CellType.STRING) {
                throw new IllegalArgumentException("This cell is string data type");
            } else if (type == CellType.FORMULA) {
                switch (evaluator.evaluateFormulaCell(valueCell)) {
                    case BOOLEAN:
                        throw new IllegalArgumentException("This cell is boolean data type");
                    case NUMERIC:
                        value = valueCell.getNumericCellValue();
                        break;
                    case STRING:
                        throw new IllegalArgumentException("This cell is string data type");
                }


            }
            map.put(dateKey, value);
        }
        return map;
    }

}




