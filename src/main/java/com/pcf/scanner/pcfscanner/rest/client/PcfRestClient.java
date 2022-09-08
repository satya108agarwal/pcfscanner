package com.pcf.scanner.pcfscanner.rest.client;

import com.pcf.scanner.pcfscanner.apps.vo.ApplicationRoot;
import com.pcf.scanner.pcfscanner.manifest.vo.ManifestRoot;
import com.pcf.scanner.pcfscanner.writer.JsonOutputFileWriter;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class PcfRestClient {

    public int retrieveAppMetaData(String url, String accessToken, int page, int results_per_page, int rowNum) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);
        RestTemplate restTemplate = new RestTemplate(new CustomClientHttpRequestFactory(30000, 30000, true));
        String pcfResourceUrl
                = url + "?page=" + page + "&results-per-page=" + results_per_page;
        ResponseEntity<ApplicationRoot> responseEntity = restTemplate.exchange(pcfResourceUrl, HttpMethod.GET, new HttpEntity<>("parameters", headers), ApplicationRoot.class);
        // System.out.println(responseEntity.getBody());
        // write to file
        JsonOutputFileWriter jsonOutputFileWriter = new JsonOutputFileWriter();
        jsonOutputFileWriter.write(responseEntity.getBody(), "apps_" + page + ".json");
        return responseEntity.getBody().getTotal_pages();
    }

    public void retriveManifestFile(String url, String accessToken, String guid,String appName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);
        RestTemplate restTemplate = new RestTemplate(new CustomClientHttpRequestFactory(30000, 30000, true));
        String pcfResourceUrl
                = url + "/v3/apps/"+ guid+"/manifest";
        ResponseEntity<String> responseEntity = restTemplate.exchange(pcfResourceUrl, HttpMethod.GET, new HttpEntity<>("parameters", headers), String.class);
        JsonOutputFileWriter jsonOutputFileWriter = new JsonOutputFileWriter();
        jsonOutputFileWriter.writeYml(responseEntity.getBody(),  appName + "_manifest.yml");
    }

}
