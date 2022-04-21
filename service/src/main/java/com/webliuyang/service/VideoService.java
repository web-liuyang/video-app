package com.webliuyang.service;

import com.webliuyang.common.Pageinfo;
import com.webliuyang.entity.Playinfo;
import com.webliuyang.entity.Videoinfo;

import java.io.IOException;

public interface VideoService {

    Pageinfo<Videoinfo> getVideoList(Integer page, Integer pageSize, String wd) throws IOException;

    Playinfo getVideoById(Integer epi, String id) throws IOException;

}
