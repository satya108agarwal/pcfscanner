package com.pcf.scanner.pcfscanner.manifest.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Process {
    public String type;
    public int instances;
    public String memory;
    public String disk_quota;
    public String command;
    @JsonProperty("health-check-type")
    public String healthCheckType;
}
