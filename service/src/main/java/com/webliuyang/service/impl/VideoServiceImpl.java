package com.webliuyang.service.impl;

import com.webliuyang.common.Pageinfo;
import com.webliuyang.entity.Episode;
import com.webliuyang.entity.Playinfo;
import com.webliuyang.entity.Videoinfo;
import com.webliuyang.service.VideoService;
import com.webliuyang.utils.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    public Pageinfo<Videoinfo> getVideoList(Integer page, Integer pageSize, String wd) throws IOException {

        String url = UrlUtil.getVideoListUrl(page, wd);
        log.info("视频列表地址={}", url);

        Document document = Jsoup.connect(url).get();
        double total = Integer.parseInt(document.select(".search .stit .typetext b").get(1).text());
        Elements oVideoList = document.select(".search .list .li_li");

        ArrayList<Videoinfo> videoList = new ArrayList();
        oVideoList.forEach((video) -> {
            Videoinfo videoinfo = new Videoinfo();
            // 图片
            String img = UrlUtil.getImgUrl(video.select(".li_img .dpic").attr("src"));

            String href = video.select(".li_text > p").get(0).select("a").attr("href");
            Pattern pattern = Pattern.compile("/(\\d+)/");
            Matcher matcher = pattern.matcher(href);
            if (matcher.find()) {
                String id = matcher.group(1);
                videoinfo.setId(id);
            }

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

        double i = total / ((double) pageSize);
        Integer totalPage = (int) Math.ceil(i);
        Pageinfo<Videoinfo> pageinfo = new Pageinfo<>(page, pageSize, (int) total, totalPage, videoList);

        return pageinfo;
    }

    @Override
    public Playinfo getVideoById(Integer epi, String id) throws IOException {

        String url = UrlUtil.getPlayUrl(id, "0", epi);
        log.info("视频地址信息={}", url);
        Document document = Jsoup.connect(url).get();
        Elements oPlayer = document.select(".playerall .player");

        Elements oNumberList = document.select(".playlist .tabConList .tabCon[style=display:block] [style=display:block] .ulNumList li a");

        ArrayList<Episode> episodeList = new ArrayList<>();
        for (Element element : oNumberList) {
            Episode episode = new Episode();
            String title = element.text();
            String href = element.attr("href");
            Pattern pattern = Pattern.compile("-(\\d+)\\.html");
            Matcher matcher = pattern.matcher(href);

            episode.setTitle(title);

            if (matcher.find()) {
                String number = matcher.group(1);
                episode.setEpi(Integer.parseInt(number));
            }

            episodeList.add(episode);

        }

        String[] variableList = oPlayer.select("script").get(0).html().split(";");
        String videoUrl = null;

        for (String variable : variableList) {
            if (variable.contains("now")) {
                videoUrl = variable.split("=")[1].split("\"")[1];
                break;
            }
        }

        Playinfo playinfo = new Playinfo(videoUrl, episodeList);

        return playinfo;
    }
}
