<script setup lang="ts">
import type { Playinfo, Videoinfo } from "../../api/module/video/types";
import { ref } from "vue";
import { onLoad } from "@dcloudio/uni-app";
import { getVideo } from "../../api/module/video/index";

const videoinfo = ref<Videoinfo & { playinfo: Playinfo }>();
const episode = ref<number>(0);

const handleClickeEisode = (epi: number) => {
  if (episode.value === epi) return;

  episode.value = epi;
  getVideo({ epi, id: videoinfo.value!.id })
    .then(res => {
      videoinfo.value!.playinfo.url = res.data.url;
    })
    .catch(() => {
      uni.showToast({ icon: "none", title: "再重新进来告哈" });
    });
};

/** 播放失败 */
const handleErrorVideo = () => {
  uni.showToast({ icon: "none", title: "看不起就换一个撒" });
};

/** 播放结束 */
const handleEndedVideo = () => {
  const episodes = videoinfo.value!.playinfo.episodes;
  const curEpiIndex = episodes.findIndex(({ epi }) => epi === episode.value);

  if (curEpiIndex === episodes.length - 1) return;

  const nextEpisode = episodes[curEpiIndex + 1];

  handleClickeEisode(nextEpisode.epi);
};

onLoad<{ videoinfo: string }>(_option => {
  const option: Videoinfo = JSON.parse(decodeURIComponent(_option.videoinfo));
  uni.setNavigationBarTitle({ title: option.title });
  getVideo({ epi: episode.value, id: option.id })
    .then(res => {
      videoinfo.value = { ...option, playinfo: res.data };
    })
    .catch(() => {
      uni.showToast({ icon: "none", title: "再重新进来告哈" });
    });
});
</script>
<template>
  <div v-if="videoinfo">
    <div>
      <video
        autoplay
        id="video"
        class="w-100"
        play-strategy="3"
        :src="videoinfo.playinfo.url"
        @ended="handleEndedVideo"
        @error="handleErrorVideo"
      />
    </div>
    <main class="p-10">
      <div>
        <div class="f20">{{ videoinfo.title }}</div>
        <div>{{ videoinfo.remark }}</div>
        <div>{{ videoinfo.type }}</div>
        <div>{{ videoinfo.starring }}</div>
        <div>{{ videoinfo.director }}</div>
        <div>{{ videoinfo.language }}</div>
        <div>{{ videoinfo.year }}</div>
      </div>
      <div class="grid-template-columns-4 gap-10 mt-20">
        <div
          :class="
            'lh-50 text-center border rounded-4 ' + (episode === episodeinfo.epi && 'color-FF6524')
          "
          v-for="episodeinfo in videoinfo.playinfo.episodes"
          :key="episodeinfo.epi"
          @click="handleClickeEisode(episodeinfo.epi)"
        >
          {{ episodeinfo.title }}
        </div>
      </div>
    </main>
  </div>
</template>

<style></style>
