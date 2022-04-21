import req from "../../request";
import type { ReturnTypeAndAwaited } from "../../types";
import type { UpdateApp } from "./types";

const api = {
  UPDATE_APP: "/app/update",
};

/** 更新App */
export const updateApp: UpdateApp = async params => {
  try {
    const res = await req.get<ReturnTypeAndAwaited<UpdateApp>>({
      url: api.UPDATE_APP,
      params,
      loading: "等我看哈有没有新版本",
    });

    return res.data;
  } catch (err) {
    return Promise.reject(err);
  }
};
