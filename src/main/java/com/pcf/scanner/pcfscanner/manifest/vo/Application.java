package com.pcf.scanner.pcfscanner.manifest.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)


public class Application {
    public String name;
    public Env env;
    public ArrayList<String> buildpacks;
    public String stack;
    public ArrayList<String> services;
    public ArrayList<Route> routes;
    public ArrayList<Process> processes;
}
