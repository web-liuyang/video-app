package com.webliuyang.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAppVO {
    /**
     * 是否应该更新
     */
    private Boolean shouldUpdate;
    /**
     * 是否强制更新
     */
    private Boolean force;
    /**
     * 后缀
     */
    private String ext;
    /**
     * 下载地址
     */
    private String url;
}
