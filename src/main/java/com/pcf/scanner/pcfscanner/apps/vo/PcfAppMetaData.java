package com.pcf.scanner.pcfscanner.apps.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PcfAppMetaData {
    private String guiId;
    private String appName;
    private String buildPack;
    private int num_of_instances;
    private int diskQuota;
    private int memory;

}
