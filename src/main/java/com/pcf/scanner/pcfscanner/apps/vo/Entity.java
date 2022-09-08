package com.pcf.scanner.pcfscanner.apps.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class Entity {
    public String name;
    public boolean production;
    public String space_guid;
    public String stack_guid;
    public String buildpack;
    public String detected_buildpack;
    public String detected_buildpack_guid;
    public EnvironmentJson environment_json;
    public int memory;
    public int instances;
    public int disk_quota;
    public String state;
    public String version;
    public String command;
    public boolean console;
    public String debug;
    public String staging_task_id;
    public String package_state;
    public String health_check_type;
    public String health_check_timeout;
    public String health_check_http_endpoint;
    public String staging_failed_reason;
    public String staging_failed_description;
    public boolean diego;
    public String docker_image;
    public DockerCredentials docker_credentials;
    public Date package_updated_at;
    public String detected_start_command;
    public boolean enable_ssh;
    public ArrayList<String> ports;
    public String space_url;
    public String stack_url;
    public String routes_url;
    public String events_url;
    public String service_bindings_url;
    public String route_mappings_url;
}
