import type { Response } from "../../types";

/** 更新App */
export type UpdateApp = (params: { name: string; version: string }) => Promise<
  Response<{
    /** 是否应该更新 */
    shouldUpdate: boolean;
    /** 是否强制更新 */
    force: boolean;
    /** 后缀 */
    ext: "wgt" | "apk";
    /** 下载地址 */
    url: string;
  }>
>;
