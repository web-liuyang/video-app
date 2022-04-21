// @ts-expect-error
import App from "./App";
import { createSSRApp } from "vue";
import "./assets/style/global/index.less";

console.log("vue3");
export function createApp() {
  const app = createSSRApp(App);
  return {
    app,
  };
}
