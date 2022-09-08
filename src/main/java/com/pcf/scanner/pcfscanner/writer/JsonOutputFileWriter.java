package com.pcf.scanner.pcfscanner.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.pcf.scanner.pcfscanner.apps.vo.ApplicationRoot;
import com.pcf.scanner.pcfscanner.manifest.vo.ManifestRoot;

import java.io.*;

public class JsonOutputFileWriter {

    public void write(ApplicationRoot root, String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("./extract/" + fileName);
        file.getParentFile().mkdirs();
        try {
            // Serialize Java object info JSON file.
            mapper.writeValue(file, root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeYml(String manifestRoot,String fileName) {
       // System.out.println("Content is  "+ manifestRoot);
        System.out.println("writing file name "+fileName);
        File file = null;
        FileWriter fileWriter = null;
        BufferedWriter output = null;
        String s = null;
        try {
            file = new File("./manifest/" + fileName);
            file.getParentFile().mkdirs();
            fileWriter = new FileWriter(file);
            output = new BufferedWriter(fileWriter);
            Reader inFromUser = new StringReader(manifestRoot);
            BufferedReader br = new BufferedReader(inFromUser);
            while ((s = br.readLine()) != null) {
                output.write(s);
                output.newLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (fileWriter != null && output != null) {
                try {
                    output.close();
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

}