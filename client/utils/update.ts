import req from "../api/request";
import { updateApp } from "../api/module/app/index";

/** 检测更新 */
export function checkUpdateApp(): Promise<boolean | Error> {
  return new Promise((resolve, reject) => {
    plus.runtime.getProperty(plus.runtime.appid, deviceInfo => {
      const { name = "", version = "" } = deviceInfo;

      updateApp({ name, version })
        .then(res => {
          const { shouldUpdate, force, ext, url } = res.data;
					console.log("res.data", res.data)
          const updater = () => {
            req
              .download({ url, loading: "别慌，在下载了" })
              .then(result => {
                plus.runtime.install(
                  result.tempFilePath,
                  { force: true },
                  () => {
                    resolve(true);
                    if (ext === "wgt") plus.runtime.restart();
                  },
                  reject
                );
              })
              .catch(reject);
          };

          if (shouldUpdate) {
            if (force) {
              updater();
            } else {
              uni.showModal({
                title: "更新提示",
                content: "是否进行更新",
                success: ({ confirm }) => {
                  if (confirm) updater();
                },
              });
            }
          }
        })
        .catch(reject);
    });
  });
}
