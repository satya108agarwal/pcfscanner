package com.pcf.scanner.pcfscanner;

import com.pcf.scanner.pcfscanner.apps.vo.PcfAppMetaData;
import com.pcf.scanner.pcfscanner.rest.client.PcfRestClient;
import com.pcf.scanner.pcfscanner.trasformer.JsontoCSVTransformer;
import com.pcf.scanner.pcfscanner.trasformer.ManifestTransformer;
import com.pcf.scanner.pcfscanner.util.Util;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Pcfscanner2Application {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Pcfscanner2Application.class, args);
        cleanUp();
        PcfRestClient pcfRestClient = new PcfRestClient();
        String accessToken= "eyJhbGciOiJSUzI1NiIsImprdSI6Imh0dHBzOi8vdWFhLnN5cy50YXMuaDJvLTQtMTA5Ni5oMm8udm13YXJlLmNvbS90b2tlbl9rZXlzIiwia2lkIjoia2V5LTEiLCJ0eXAiOiJKV1QifQ.eyJqdGkiOiJmNTc0MTZhNDg1ZTg0MmFkYmNkOWI0YzY5N2IzNjZhZiIsInN1YiI6ImQ1MmNiMTU3LWJjMWQtNGUxZC05NTczLTE0NzE5OWEyMjQ3ZSIsInNjb3BlIjpbIm5ldHdvcmsud3JpdGUiLCJjbG91ZF9jb250cm9sbGVyLmFkbWluIiwicm91dGluZy5yb3V0ZXJfZ3JvdXBzLnJlYWQiLCJjbG91ZF9jb250cm9sbGVyLndyaXRlIiwibmV0d29yay5hZG1pbiIsImRvcHBsZXIuZmlyZWhvc2UiLCJvcGVuaWQiLCJyb3V0aW5nLnJvdXRlcl9ncm91cHMud3JpdGUiLCJzY2ltLnJlYWQiLCJ1YWEudXNlciIsImNsb3VkX2NvbnRyb2xsZXIucmVhZCIsInBhc3N3b3JkLndyaXRlIiwic2NpbS53cml0ZSJdLCJjbGllbnRfaWQiOiJjZiIsImNpZCI6ImNmIiwiYXpwIjoiY2YiLCJncmFudF90eXBlIjoicGFzc3dvcmQiLCJ1c2VyX2lkIjoiZDUyY2IxNTctYmMxZC00ZTFkLTk1NzMtMTQ3MTk5YTIyNDdlIiwib3JpZ2luIjoidWFhIiwidXNlcl9uYW1lIjoiYWRtaW4iLCJlbWFpbCI6ImFkbWluIiwiYXV0aF90aW1lIjoxNjYyNTc5MTM3LCJyZXZfc2lnIjoiYmY3OWZmODciLCJpYXQiOjE2NjI1NzkxMzcsImV4cCI6MTY2MjU4NjMzNywiaXNzIjoiaHR0cHM6Ly91YWEuc3lzLnRhcy5oMm8tNC0xMDk2Lmgyby52bXdhcmUuY29tL29hdXRoL3Rva2VuIiwiemlkIjoidWFhIiwiYXVkIjpbImNsb3VkX2NvbnRyb2xsZXIiLCJzY2ltIiwicGFzc3dvcmQiLCJjZiIsInVhYSIsIm9wZW5pZCIsImRvcHBsZXIiLCJuZXR3b3JrIiwicm91dGluZy5yb3V0ZXJfZ3JvdXBzIl19.M7LUosKPRXmGyKI7N3jv_uvAWelTQ4y2IKqnYX4jtShyGMSSRYVBpA5JyjQnxwDYsSL9C7mlqvi62Mjfr28JAsLKHeGk8D048fVRDNR4RqugCVbQbGnNG6vF2ovN1KYmYYuDsDradbvDhrdIhEch1FkALVMobxIfbzRki2Fk4q8uIlM1n76M9DOU8PU8L8VhQoNXQ0fDisDuCoylfnNcFH24I1yjIxJYmxfv-rRgklpnGwe5Oq7gcglzB6XzcHK2JsKqFxii97KIhtXkR4FzHgNDH-MLdVRGSslriGxSVTkwbWToUM_6gra6Yv_lXU4lQuz5iwnu_DeW2f8susxFFg";
        String domain_url = "https://api.sys.tas.h2o-4-1096.h2o.vmware.com";
       // Scanner scanner = new Scanner(System.in);
        System.out.println("Enter domain url of pcf .. for example: https://api.sys.tas.h2o-4-1096.h2o.vmware.com");
        domain_url = args[0];
        System.out.println("Enter a oauth token: You can generate this by running cf oauth-token command using cf cli");
        accessToken = args[1];

        System.out.println("domain url entered is ..."+ domain_url);
        System.out.println("access token entered is ..."+ accessToken);

        int page = 1;
        int total_pages = 1;
        int rowNum = 1;
        int results_per_page = 50;

        for (int i = 1; i <= total_pages; i++) {
            total_pages = pcfRestClient.retrieveAppMetaData(domain_url + "/v2/apps", accessToken, page, results_per_page, rowNum);
            rowNum = rowNum + results_per_page;
            page++;
        }

        JsontoCSVTransformer appsTransformer = new JsontoCSVTransformer();
        List<PcfAppMetaData> pcfAppMetaDataList = appsTransformer.transform();
        System.out.println("***********");
        System.out.println(pcfAppMetaDataList);
        System.out.println("***********");
        appsTransformer.writeToCSV(pcfAppMetaDataList);


        for(PcfAppMetaData pcfAppMetaData : pcfAppMetaDataList) {
            pcfRestClient.retriveManifestFile(domain_url, accessToken, pcfAppMetaData.getGuiId(), pcfAppMetaData.getAppName());
        }
        ManifestTransformer manifestTransformer = new ManifestTransformer();
        manifestTransformer.transform(pcfAppMetaDataList);
    }

    public static void cleanUp() throws IOException {

        File file = new File(Util.getAbsolutePath()+"/apps.csv");
        if (file.exists())
            file.delete();
        File extract = new File(Util.getAbsolutePath()+"/extract");
        if (extract.exists())
            FileUtils.cleanDirectory(extract);

        File manifest = new File(Util.getAbsolutePath()+"/manifest");
        if (manifest.exists())
        FileUtils.cleanDirectory(manifest);


    }
}
