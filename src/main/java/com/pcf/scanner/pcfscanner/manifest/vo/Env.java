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

public class Env {
    @JsonProperty("ACCENT_COLOR")
    public String aCCENT_COLOR;
    @JsonProperty("ACCOUNT_URL")
    public String aCCOUNT_URL;
    @JsonProperty("AMJS_ENV_VAR_KEYS")
    public String aMJS_ENV_VAR_KEYS;
    @JsonProperty("APPS_DOMAIN")
    public String aPPS_DOMAIN;
    @JsonProperty("APP_POLL_INTERVAL")
    public String aPP_POLL_INTERVAL;
    @JsonProperty("COMPANY_NAME")
    public String cOMPANY_NAME;
    @JsonProperty("CREATE_UPS_ENABLED")
    public String cREATE_UPS_ENABLED;
    @JsonProperty("CURRENCY_LOOKUP")
    public String cURRENCY_LOOKUP;
    @JsonProperty("DISPLAY_PLAN_PRICES")
    public String dISPLAY_PLAN_PRICES;
    @JsonProperty("ENABLE_INVITING_USERS")
    public String eNABLE_INVITING_USERS;
    @JsonProperty("FAVICON_SRC")
    public String fAVICON_SRC;
    @JsonProperty("FOOTER_LINKS")
    public String fOOTER_LINKS;
    @JsonProperty("FOOTER_TEXT")
    public String fOOTER_TEXT;
    @JsonProperty("FOUNDATIONS")
    public String fOUNDATIONS;
    @JsonProperty("GLOBAL_WRAPPER_BG_COLOR")
    public String gLOBAL_WRAPPER_BG_COLOR;
    @JsonProperty("GLOBAL_WRAPPER_FOOTER_CONTENT")
    public String gLOBAL_WRAPPER_FOOTER_CONTENT;
    @JsonProperty("GLOBAL_WRAPPER_HEADER_CONTENT")
    public String gLOBAL_WRAPPER_HEADER_CONTENT;
    @JsonProperty("GLOBAL_WRAPPER_TEXT_COLOR")
    public String gLOBAL_WRAPPER_TEXT_COLOR;
    @JsonProperty("HOME_FOUNDATION")
    public String hOME_FOUNDATION;
    @JsonProperty("LOGO_SRC")
    public String lOGO_SRC;
    @JsonProperty("MARKETPLACE_NAME")
    public String mARKETPLACE_NAME;
    @JsonProperty("MARKETPLACE_URL")
    public String mARKETPLACE_URL;
    @JsonProperty("METRIC_REGISTRAR_ENABLED")
    public String mETRIC_REGISTRAR_ENABLED;
    @JsonProperty("NETWORKING_SELF_SERVICE")
    public String nETWORKING_SELF_SERVICE;
    @JsonProperty("POLL_INTERVAL")
    public String pOLL_INTERVAL;
    @JsonProperty("PRODUCT_NAME")
    public String pRODUCT_NAME;
    @JsonProperty("SEARCH_SERVICE_URL")
    public String sEARCH_SERVICE_URL;
    @JsonProperty("SECONDARY_NAVIGATION_LINKS")
    public String sECONDARY_NAVIGATION_LINKS;
    @JsonProperty("SKIP_SSL_VALIDATION")
    public String sKIP_SSL_VALIDATION;
    @JsonProperty("UAA_SAML_PROVIDER_NAME")
    public String uAA_SAML_PROVIDER_NAME;
}
