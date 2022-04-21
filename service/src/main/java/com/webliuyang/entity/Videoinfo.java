package com.webliuyang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Videoinfo {
    /**
     * id
     */
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 图片
     */
    private String img;
    /**
     * 简介
     */
    private String intro;
    /**
     * 备注
     */
    private String remark;
    /**
     * 类型
     */
    private String type;
    /**
     * 主演
     */
    private String starring;
    /**
     * 导演
     */
    private String director;
    /**
     * 语言
     */
    private String language;
    /**
     * 年份
     */
    private String year;
}
