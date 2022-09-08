package com.pcf.scanner.pcfscanner.trasformer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcf.scanner.pcfscanner.apps.vo.ApplicationRoot;
import com.pcf.scanner.pcfscanner.apps.vo.PcfAppMetaData;
import com.pcf.scanner.pcfscanner.apps.vo.Resource;
import com.pcf.scanner.pcfscanner.util.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsontoCSVTransformer {

    private static final String CSV_SEPARATOR = ",";

    public void writeToCSV(List<PcfAppMetaData> pcfAppMetaDataArrayList) {
        BufferedWriter bw = null;
        try {

             bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Util.getAbsolutePath()+"/apps.csv"), "UTF-8"));
            for (PcfAppMetaData pcfAppMetaData : pcfAppMetaDataArrayList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(pcfAppMetaData.getGuiId());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(pcfAppMetaData.getAppName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(pcfAppMetaData.getBuildPack());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(pcfAppMetaData.getNum_of_instances());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(pcfAppMetaData.getDiskQuota());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(pcfAppMetaData.getMemory());
                bw.write(oneLine.toString());
                bw.newLine();
            }

        } catch (Exception exception){
            throw new RuntimeException(exception);
        }
        finally {
            try {
                bw.flush();
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<PcfAppMetaData> transform() {
        List<PcfAppMetaData> pcfAppMetaDataList = null;
        pcfAppMetaDataList = new ArrayList<>();
        File folder = new File(Util.getAbsolutePath()+"/extract/");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {

                System.out.println("processing this file ...." + file.getName());
                ObjectMapper objectMapper = new ObjectMapper();
                ApplicationRoot root = null;
                try {
                    root = objectMapper.readValue(file, ApplicationRoot.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (root != null && root.getResources() != null && root.getResources().size() > 0) {
                    for (Resource resource : root.getResources()) {
                        PcfAppMetaData pcfAppMetaData = new PcfAppMetaData();
                             pcfAppMetaData.setGuiId(resource.getMetadata().getGuid());
                        if (resource.getEntity() != null) {
                            pcfAppMetaData.setAppName(resource.getEntity().getName());
                            pcfAppMetaData.setBuildPack(resource.getEntity().getDetected_buildpack());
                            pcfAppMetaData.setMemory(resource.getEntity().getMemory());
                            pcfAppMetaData.setDiskQuota(resource.getEntity().getDisk_quota());
                            pcfAppMetaData.setNum_of_instances(resource.getEntity().getInstances());
                            pcfAppMetaDataList.add(pcfAppMetaData);
                        }
                    }

                }
            }
        }
        return pcfAppMetaDataList;
    }
}