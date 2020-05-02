package com.cc.downunder.datastore;

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

public class ReadExcel {
    public static void main(String[] args) {
        try {
            String projectId = "s3763636-myapi";
            String instanceId = "cc2020";
            String tableName = "new-table";
//            String[] states = makeMaps();
            Map<String, Integer> columnMap = getColumnName();
            Map<String, Double> mapNSW = null;
            Map<String, Double> mapVIC = null;
            for (String colName : columnMap.keySet()) {
                if (colName.equalsIgnoreCase("age")) {
                    mapNSW = readColData(columnMap.get(colName));
                    HelloWorld helloWorld = new HelloWorld(projectId, instanceId, tableName, "state", "age", mapNSW);
                    helloWorld.run();
                } else if (colName.equalsIgnoreCase("height")) {
                    mapVIC = readColData(columnMap.get(colName));
                }

            }
//            System.out.println(mapVIC.size());
//

//            Iterator i = mapVIC.entrySet().iterator();
//            while (i.hasNext()) {
//                Map.Entry pair = (Map.Entry) i.next();
//                System.out.println(pair.getKey() + " " + pair.getValue());
////
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//                if (colName.equalsIgnoreCase("NSW")) {
//                    Map<String, Double> mapNSW = readCol(columnMap.get(colName));
//                } else if (colName.equalsIgnoreCase("VIC")) {
//                    Map<String, Double> mapVIC = readCol(columnMap.get(colName));
//                } else if (colName.equalsIgnoreCase("SA")) {
//                    Map<String, Double> mapSA = readCol(columnMap.get(colName));
//                } else if (colName.equalsIgnoreCase("WA")) {
//                    Map<String, Double> mapWA = readCol(columnMap.get(colName));
//                } else if (colName.equalsIgnoreCase("TAS")) {
//                    Map<String, Double> mapTAS = readCol(columnMap.get(colName));
//                } else if (colName.equalsIgnoreCase("NT")) {
//                    Map<String, Double> mapNT = readCol(columnMap.get(colName));
//                } else if (colName.equalsIgnoreCase("QLD")) {
//                    Map<String, Double> mapQLD = readCol(columnMap.get(colName));
//                } else if (colName.equalsIgnoreCase("ACT")) {
//                    Map<String, Double> mapACT = readCol(columnMap.get(colName));
//                } else if (colName.equalsIgnoreCase("OTHER")) {
//                    Map<String, Double> mapOther = readCol(columnMap.get(colName));
//                }else if (colName.equalsIgnoreCase("TOTAL")) {
//                    Map<String, Double> mapAUS = readCol(columnMap.get(colName));
//                }
    }

//            Map<String, Double> nswMap = readCol(1);


    public static Map<String, Integer> getColumnName() {
        XSSFWorkbook wb = null;
        try (OPCPackage pkg = OPCPackage.open("./data/Student.xlsx")) {
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

        try (OPCPackage pkg = OPCPackage.open("./data/Student.xlsx")) {
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
//
        return map;
    }

}




