type Loop = (...args: any) => any;

/** 获取函数的返回类型并解析Promise */
export type ReturnTypeAndAwaited<T extends Loop> = Awaited<Awaited<ReturnType<T>>>;

export interface Response<T> {
  code: number;
  msg: string;
  data: T;
}

export interface Pageinfo<T = any> {
  curPage: number;
  pageSize: number;
  total: number;
  totalPage: number;
  data: T[];
}
