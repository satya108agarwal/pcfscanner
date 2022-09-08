package com.pcf.scanner.pcfscanner.util;

import com.pcf.scanner.pcfscanner.Pcfscanner2Application;

import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class Util {

    public static String getAbsolutePath() {
       return System.getProperty("user.dir");
    }

}
