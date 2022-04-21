package com.webliuyang;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.net.NetUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.webliuyang.entity.Videoinfo;
import com.webliuyang.utils.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@SpringBootTest
@Slf4j
public class ApplicationTest {

    /**
    @Test
    public void getHtml() throws IOException {

        HashMap<String, Object> hashMap = new HashMap<>();

        String wd = "蜡笔小新";
//        String wd = "毒液";
        String page = "1";

        String url = "https://www.88ysw.org/search.php?page=" + page + "&searchword=" + wd;

        Document document = Jsoup.connect(url).get();
        Elements oVideoList = document.select(".search .list .li_li");

        ArrayList videoList = new ArrayList();

        oVideoList.forEach((video) -> {
            String img = "https://www.88ysw.org" + video.select(".li_img .dpic").attr("src");
            // 备注
            String title = video.select(".li_text > p").get(0).text();
            // 备注
            String remark = video.select(".li_text > p").get(1).text();
            // 类型
            String type = video.select(".li_text > p").get(2).text();
            // 主演
            String starring = video.select(".li_text > p").get(3).text();
            // 导演
            String director = video.select(".li_text > p").get(4).text();
            // 语言
            String language = video.select(".li_text > p").get(5).text();
            // 年代
            String year = video.select(".li_text > p").get(6).text();
            // 简介
            String intro = video.select(".li_text > p").get(7).text();

            String id = video.select(".alink").attr("href").split("/")[2];

            Videoinfo videoinfo = new Videoinfo();

            videoinfo.setId(id);
            videoinfo.setTitle(title);
            videoinfo.setImg(img);
            videoinfo.setIntro(intro);
            videoinfo.setRemark(remark);
            videoinfo.setType(type);
            videoinfo.setStarring(starring);
            videoinfo.setDirector(director);
            videoinfo.setLanguage(language);
            videoinfo.setYear(year);
            videoList.add(videoinfo);
        });

        hashMap.put("videoList", videoList);

    }


    @Test
    public String getVideoUrl() throws IOException {
        String id = "1";


        String url = UrlUtil.getPlayUrl(id, "0", 1);

        Document document = Jsoup.connect(url).get();
        Elements oPlayer = document.select(".playerall .player");
        String[] variableList = oPlayer.select("script").get(0).html().split(";");
        String videoUrl = null;


        for (String variable : variableList) {
            if (variable.contains("now")) {
                videoUrl = variable.split("=")[1];
                break;
            }
        }


        return videoUrl;
    }

    @Test
    public void getPage() throws IOException {
        Document document = Jsoup.connect("https://www.88ysw.org/search.php?page=1&searchword=刘洋").get();
        String total = document.select(".search .stit .typetext b").get(1).text();

    }

    @Test
    public void ceil() throws IOException {
        double a = 35;

        double b = 20;

        double c = a / b;

        System.out.println("c===>" + c); //1.75

        System.out.println("c===>" + Math.ceil(c)); //2.0
        System.out.println("c===>" + Math.ceil(35 / 20)); //2.0

        System.out.println(Math.floor(c)); //1.0
    }


    @Value("${server.port}")
    private String port;

    @Test
    public void getSysteminfo() {
        System.out.println(port);
        String host = NetUtil.getNetworkInterface("en0").getInterfaceAddresses().get(1).getAddress().getHostAddress();
    }

    @Autowired
    private Environment environment;

    @Test
    public void getEnv() {
//        log.info("LiuYang");
//        log.debug("LiuYang");
//        log.warn("LiuYang");
//        log.error("LiuYang");
//        log.trace("LiuYang");
        environment.getActiveProfiles();
    }

    @Test
    public void downloadImage() {


    }

    */
}
