import Request from "./lib";

// const HOST = "http://192.168.31.250:8080";
// const HOST = "http://192.168.102.92:8080";
const HOST = "http://124.221.128.195:8080"

const request = new Request({
  baseUrl: HOST + "/api",
  timeout: 1000 * 10,
});

export default request;
