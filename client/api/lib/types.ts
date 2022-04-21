export type Loop = (...args: any) => any;

export type InterceptorRequest = (option: RequestOption) => void;
export type InterceptorResponse = (response: Response) => Response;

export type InterceptorDownloadRequest = (downloadOption: DownloadOption) => void;
export type InterceptorDownloadResponse = (response: DownloadSuccessData) => DownloadSuccessData;

export interface ConfigOption {
  baseUrl?: string;
  method?: RequestOptions["method"];
  timeout?: number;
  params?: Record<string, any>;
  data?: Record<string, any>;
  header?: Record<string, string>;
  interceptorRequest?: InterceptorRequest[];
  interceptorResponse?: InterceptorResponse[];
}

export interface RequestOption {
  url: string;
  loading?: string | boolean;
  timeout?: number;
  method?: RequestOptions["method"];
  header?: Record<string, string>;
  params?: Record<string, any>;
  data?: Record<string, any>; // RequestOptions["data"];
  interceptorRequest?: InterceptorRequest[];
  interceptorResponse?: InterceptorResponse[];
}

export interface DownloadOption
  extends Pick<RequestOption, "url" | "header" | "loading" | "timeout"> {
  interceptorDownloadRequest?: InterceptorDownloadRequest[];
  interceptorDownloadResponse?: InterceptorDownloadResponse[];
  onProgressUpdate?: (result: OnProgressDownloadResult) => void;
}

export interface Response<T = any> {
  data: T;
  statusCode: number;
  header: Record<string, string>;
  cookies: string[];
  errMsg: string;
}
