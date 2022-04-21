package com.webliuyang.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pageinfo<T> {
    private Integer curPage = 0;
    private Integer pageSize = 0;
    private Integer total = 0;
    private Integer totalPage = 0;
    private ArrayList<T> data;
}
