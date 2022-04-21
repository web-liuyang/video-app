package com.webliuyang.utils;

public class UrlUtil {
    public static String getVideoListUrl(Integer page, String wd) {
        return "https://www.88ysw.org/search.php?page=" + page + "&searchword=" + wd;
    }

    public static String getPlayUrl(String id, String source, Integer epi) {
        return "https://www.88ysw.org/play/?" + id + "-" + source + "-" + (epi) + ".html";
    }

    public static String getImgUrl(String src) {
        return "https://www.88ysw.org" + src;
    }
}
