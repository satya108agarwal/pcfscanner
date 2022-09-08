package com.pcf.scanner.pcfscanner.apps.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationRoot {
    public int total_results;
    public int total_pages;
    public String prev_url;
    public String next_url;
    public ArrayList<Resource> resources;
}
