import "@dcloudio/uni-app";

declare module "@dcloudio/uni-app" {
  export module "@dcloudio/uni-app";

  declare const onLoad: <T = any>(hook: (option: T) => any) => void;
}
