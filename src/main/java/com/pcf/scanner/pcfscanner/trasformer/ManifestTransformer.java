package com.pcf.scanner.pcfscanner.trasformer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.pcf.scanner.pcfscanner.apps.vo.PcfAppMetaData;
import com.pcf.scanner.pcfscanner.manifest.vo.Application;
import com.pcf.scanner.pcfscanner.manifest.vo.ManifestRoot;
import com.pcf.scanner.pcfscanner.util.Util;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class ManifestTransformer {


    public  void transform(List<PcfAppMetaData> appResultList) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        ManifestRoot manifestRoot = null;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Manifest");
        FileOutputStream outputStream = new FileOutputStream(Util.getAbsolutePath()+"/manifest.csv");
        int rowNum = 0;
        try {
            for (PcfAppMetaData appResult : appResultList) {
                System.out.println("Reading this manifest for the app " + appResult.getAppName());
                manifestRoot = objectMapper.readValue(new File(Util.getAbsolutePath()+"/manifest/" + appResult.getAppName() + "_manifest.yml"), ManifestRoot.class);
                excelWriter(manifestRoot, workbook, sheet, rowNum, outputStream);
                rowNum++;
            }
        } catch (IOException e) {
            e.fillInStackTrace();
            System.out.println("Exception ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try {
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.fillInStackTrace();
            e.printStackTrace();
        } catch (IOException e) {
            e.fillInStackTrace();
            e.printStackTrace();
        }
    }

    public static void excelWriter(ManifestRoot root, XSSFWorkbook workbook, XSSFSheet sheet, int rowNum, FileOutputStream outputStream) {
        if (rowNum == 1) {
            Object[][] datatypes = {
                    {"app_name", "services", "route"}
            };
            System.out.println("Creating excel sheet for manifest ");

            for (Object[] datatype : datatypes) {
                Row row = sheet.createRow(0);
                int colNum = 0;
                for (Object field : datatype) {
                    Cell cell = row.createCell(colNum++);
                    cell.setCellValue((String) field);
                }
            }
        }
        if (root != null && root.getApplications() != null && root.getApplications().size() > 0) {
            for (Application application : root.getApplications()) {
                int colNum = 0;
                Row row = sheet.createRow(rowNum);

                Cell cell2 = row.createCell(colNum++);
                cell2.setCellValue(application.getName());

                Cell cell3 = row.createCell(colNum++);
                if (application.getServices() != null)
                    cell3.setCellValue(application.getServices().toString().replace("[", "")
                            .replace("]", "")
                            .replace(" ", ""));
                else
                    cell3.setCellValue("");

                Cell cell4 = row.createCell(colNum++);
                if (application.getRoutes() != null && application.getRoutes().size() > 0)
                    cell4.setCellValue(application.getRoutes().get(0).getRoute());
                else
                    cell4.setCellValue("");

            }
        }


        System.out.println("Done");
    }

}
