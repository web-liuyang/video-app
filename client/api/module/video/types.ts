import type { Pageinfo, Response } from "../../types";

export interface Videoinfo {
  /**
   * id
   */
  id: string;
  /**
   * 标题
   */
  title: string;
  /**
   * 图片
   */
  img: string;
  /**
   * 简介
   */
  intro: string;
  /**
   * 备注
   */
  remark: string;
  /**
   * 类型
   */
  type: string;
  /**
   * 主演
   */
  starring: string;
  /**
   * 导演
   */
  director: string;
  /**
   * 语言
   */
  language: string;
  /**
   * 年份
   */
  year: string;
}

/** 获取视频列表 */
export type GetVideoList = (params: {
  page: number;
  pageSize: number;
  wd: string;
}) => Promise<Response<Pageinfo<Videoinfo>>>;

export interface Episode {
  epi: number;
  title: string;
}

export interface Playinfo {
  url: string;
  episodes: Episode[];
}

/** 获取视频地址 */
export type GetVideo = (params: {
  epi: Episode["epi"];
  id: Videoinfo["id"];
}) => Promise<Response<Playinfo>>;
