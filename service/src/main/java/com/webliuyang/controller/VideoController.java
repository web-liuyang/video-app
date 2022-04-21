package com.webliuyang.controller;


import com.webliuyang.common.Pageinfo;
import com.webliuyang.common.Response;
import com.webliuyang.entity.Playinfo;
import com.webliuyang.entity.Videoinfo;
import com.webliuyang.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/video")
@Slf4j
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/getList")
    public Response<Pageinfo<Videoinfo>> getVideoList(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "wd") String wd) throws IOException {
        log.info("page={}, pageSize={}, wd={}", page, pageSize, wd);
        Pageinfo<Videoinfo> pageinfo = videoService.getVideoList(page, pageSize, wd);
        Response response = new Response(200, "请求成功", pageinfo);
        return response;
    }

    @GetMapping("/getVideo")
    public Response<Playinfo> getVideoById(@RequestParam("epi") Integer epi, @RequestParam("id") String id) throws IOException {
        log.info("epi={}, id={}", epi, id);
        Playinfo playinfo = videoService.getVideoById(epi, id);
        Response response = new Response(200, "请求成功", playinfo);
        return response;
    }
}
