package com.webliuyang.controller;


import com.webliuyang.common.Response;
import com.webliuyang.vo.UpdateAppVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/app")
@Slf4j
public class AppController {

    @Value("${os.port}")
    private String port;

    @Value("${os.host}")
    private String host;

    @GetMapping("/update")
    public Response<UpdateAppVO> update(@RequestParam("name") String name, @RequestParam("version") String version) {
        log.info("name={}, vertsion={}", name, version);

        UpdateAppVO updateApp = new UpdateAppVO(false, false, "", "");

        Integer versionNumber = Integer.valueOf(version.replaceAll("\\.", ""));

        Integer newVersionNumber = 100;

        if (versionNumber < newVersionNumber) {
            String url = "http://" + host + ":" + port + "/app/video-app.wgt";
            updateApp.setExt("wgt");
            updateApp.setUrl(url);
            updateApp.setShouldUpdate(true);
            updateApp.setForce(true);
        }

        return new Response<>(200, "请求成功", updateApp);
    }
}
