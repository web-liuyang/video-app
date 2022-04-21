package com.webliuyang.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Playinfo {
    private String url;
    private ArrayList<Episode> episodes;
}
