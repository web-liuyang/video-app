import { stringify } from "qs";
import { ConfigOption, RequestOption, DownloadOption, Response } from "./types";
import { handleDownloadOption, handleRequestOption } from "./utils";

const _configOption: ConfigOption = {
  baseUrl: "",
  method: "GET",
  timeout: 1000 * 30,
  params: {},
  data: {},
  header: {},
  interceptorRequest: [],
  interceptorResponse: [],
};

class Request {
  configOption: ConfigOption;

  constructor(configOption: ConfigOption) {
    this.configOption = {
      ..._configOption,
      ...configOption,
    };
  }
  get<T>(option: RequestOption): Promise<Response<T>> {
    return this.request<T>({
      method: "GET",
      ...option,
    });
  }
  post<T>(option: RequestOption): Promise<Response<T>> {
    return this.request<T>({
      method: "POST",
      ...option,
    });
  }

  async request<T>(option: RequestOption): Promise<Response<T>> {
    const defaultOption = this.configOption;

    const _option = handleRequestOption(defaultOption, option);

    return new Promise((resolve, reject) => {
      _option.interceptorRequest.forEach(before => {
        before(_option);
      });

      const paramsUrl = stringify(_option.params, { addQueryPrefix: true });

      if (option.loading) {
        if (typeof option.loading === "string") {
          uni.showLoading({ title: option.loading });
        } else {
          uni.showLoading();
        }
      }

      uni.request({
        url: _option.url + paramsUrl,
        method: _option.method,
        data: _option.data,
        header: _option.header,
        // @ts-expect-error
        timeout: _option.timeout,
        success: res => {
          let response = res as unknown as Response<T>;

          _option.interceptorResponse.forEach(after => {
            response = after(response);
          });

          resolve(response);
        },
        fail: err => {
          reject(err);
        },
        complete: () => {
          if (option.loading) uni.hideLoading();
        },
      });
    });
  }

  async download(option: DownloadOption): Promise<DownloadSuccessData> {
    const defaultOption = this.configOption;

    const _option = handleDownloadOption(defaultOption, option);

    return new Promise((resolve, reject) => {
      _option.interceptorDownloadRequest.forEach(before => {
        before(_option);
      });

      if (option.loading) {
        if (typeof option.loading === "string") {
          uni.showLoading({ title: option.loading, mask: true });
        } else {
          uni.showLoading({ mask: true });
        }
      }

      const downloadTask = uni.downloadFile({
        url: _option.url,
        // @ts-ignore
        timeout: _option.timeout,
        success: res => {
          let response = res;

          _option.interceptorDownloadResponse.forEach(after => {
            response = after(response);
          });

          resolve(response);
        },
        // @ts-ignore
        fail: err => {
          reject(err);
        },
        complete: () => {
          if (option.loading) uni.hideLoading();
        },
      });

      downloadTask.onProgressUpdate(_option.onProgressUpdate);
    });
  }
}

export default Request;
