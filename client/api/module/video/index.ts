import req from "../../request";
import type { ReturnTypeAndAwaited } from "../../types";
import type { GetVideoList, GetVideo } from "./types";

const api = {
  GET_VIDEO_LIST: "/video/getList",
  GET_VIDEO: "/video/getVideo",
};

/** 获取视频列表 */
export const getVideoList: GetVideoList = async params => {
  try {
    const res = await req.get<ReturnTypeAndAwaited<GetVideoList>>({
      url: api.GET_VIDEO_LIST,
      params,
    });

    return res.data;
  } catch (err) {
    return Promise.reject(err);
  }
};

/** 获取视频地址 */
export const getVideo: GetVideo = async params => {
  try {
    const res = await req.get<ReturnTypeAndAwaited<GetVideo>>({
      url: api.GET_VIDEO,
      params,
      loading: "等哈哈儿",
    });
    return res.data;
  } catch (err) {
    return Promise.reject(err);
  }
};
