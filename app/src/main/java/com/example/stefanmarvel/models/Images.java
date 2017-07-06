package com.example.stefanmarvel.models;

/**
 * Created by stefanmay on 06/07/2017.
 */

public class Images {
    public String path;
    public String extension;

    public static String getPathAndExt(Images images) {
        if(images != null) {
            if(images.path != null && images.extension != null)
                return images.getPathAndExt();
        }
        return null;
    }

    public String getPathAndExt() {
        return path + "." + extension;
    }
}
