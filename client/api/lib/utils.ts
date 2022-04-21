import type {
  ConfigOption,
  RequestOption,
  InterceptorRequest,
  InterceptorResponse,
  InterceptorDownloadRequest,
  InterceptorDownloadResponse,
  DownloadOption,
} from "./types";

export function handleRequestOption(defaultOption: ConfigOption, option: RequestOption) {
  const url = defaultOption.baseUrl + option.url;

  const method = option.method || defaultOption.method;

  const timeout = option.timeout || defaultOption.timeout;

  const header = {
    ...defaultOption.header,
    ...option.header,
  };

  const params = {
    ...defaultOption.params,
    ...option.params,
  };

  const data = {
    ...defaultOption.data,
    ...option.data,
  };

  const interceptorRequest: InterceptorRequest[] = [
    ...(defaultOption.interceptorRequest = []),
    ...(option.interceptorRequest = []),
  ];

  const interceptorResponse: InterceptorResponse[] = [
    ...(defaultOption.interceptorResponse = []),
    ...(option.interceptorResponse = []),
  ];

  return {
    url,
    method,
    header,
    params,
    data,
    timeout,

    interceptorRequest,
    interceptorResponse,
  };
}

export function handleDownloadOption(defaultOption: ConfigOption, option: DownloadOption) {
  const url = option.url;

  const timeout = option.timeout || defaultOption.timeout;

  const header = {
    ...defaultOption.header,
    ...option.header,
  };

  const onProgressUpdate = typeof option.onProgressUpdate === "function" ? option.onProgressUpdate : () => {};

  const interceptorDownloadRequest: InterceptorDownloadRequest[] =
    option.interceptorDownloadRequest || [];

  const interceptorDownloadResponse: InterceptorDownloadResponse[] =
    option.interceptorDownloadResponse || [];

  return {
    url,
    header,
    timeout,

    onProgressUpdate,
    interceptorDownloadRequest,
    interceptorDownloadResponse,
  };
}
