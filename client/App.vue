<script lang="ts">
import { checkUpdateApp } from "./utils/update";

export default {
  onLaunch: async () => {
    console.log("App Launch");
    /** 检测更新 */
    checkUpdateApp()
      .then(res => {
        console.log(res);
      })
      .catch(err => {
        console.log(err);
      });
  },
  onShow: () => {
    console.log("App Show");
  },
  onHide: () => {
    console.log("App Hide");
  },
  onError: (err: { errMsg: string }) => {
    console.log(err.errMsg);
  },
};
</script>

<style>
/*每个页面公共css */
</style>
