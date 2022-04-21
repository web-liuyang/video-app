<script setup lang="ts">
import { ref, reactive } from "vue";
import { getVideoList } from "../../api/module/video/index";
import { Videoinfo } from "../../api/module/video/types";
import { onReachBottom } from "@dcloudio/uni-app";
import { Pageinfo } from "../../api/types";

enum LOADING_STATUS {
  MORE = "more",
  NO_MORE = "noMore",
  LOADING = "loading",
}

const LOADING_STATUS_TEXT = {
  contentdown: "上拉一哈试试",
  contentrefresh: "不要慌在加载了",
  contentnomore: "哦吼，没得了",
};

const videoList = ref<Videoinfo[]>([]);

const videoPageinfo = reactive<Omit<Pageinfo, "data">>({
  curPage: 0,
  pageSize: 30,
  total: 0,
  totalPage: 0,
});

const searchText = ref<string>("");

const loadingStatus = ref<LOADING_STATUS>();

const handleSearchVideoList = (page: number = 1) => {
  if (!searchText.value) {
    return uni.showToast({ icon: "none", title: "请输入搜索内容" });
  }

  loadingStatus.value = LOADING_STATUS.LOADING;

  getVideoList({ wd: searchText.value, page, pageSize: 30 })
    .then(res => {
      const { data, curPage, pageSize, total, totalPage } = res.data;
      if (page === 1) {
        videoList.value = data;
      } else {
        videoList.value.push(...data);
      }

      console.log(res.data);

      videoPageinfo.curPage = curPage;
      videoPageinfo.pageSize = pageSize;
      videoPageinfo.total = total;
      videoPageinfo.totalPage = totalPage;

      loadingStatus.value = curPage >= totalPage ? LOADING_STATUS.NO_MORE : LOADING_STATUS.MORE;
    })
    .catch(err => {
      uni.showToast({ title: "获取视频列表失败", icon: "none" });
    });
};

const handleClickVideo = (video: Videoinfo) => {
  uni.navigateTo({
    url: `/pages/Video/index?videoinfo=${encodeURIComponent(JSON.stringify(video))}`,
  });
};

onReachBottom(() => {
  if (loadingStatus.value === LOADING_STATUS.MORE) {
    const { curPage, totalPage } = videoPageinfo;
    if (totalPage !== curPage) {
      handleSearchVideoList(curPage + 1);
    }
  }
});
</script>

<template>
  <div>
    <div class="fixed-top z-1 w-100 bg-FFFFFF">
      <uni-search-bar
        v-model="searchText"
        placeholder="请输入搜索内容"
        @submit="handleSearchVideoList()"
      />
    </div>
    <div class="px-10 pt-56 pb-20">
      <ul>
        <li
          v-for="(video, index) in videoList"
          :key="index"
          class="flex-s-start mt-10"
          @click="handleClickVideo(video)"
        >
          <div class="w-40 mr-10"><image class="w-100 rounded-4" :src="video.img" /></div>
          <div class="w-60">
            <div class="f20">{{ video.title }}</div>
            <div class="line-clamp-1">{{ video.remark }}</div>
            <div class="line-clamp-1">{{ video.type }}</div>
            <div class="line-clamp-1">{{ video.starring }}</div>
            <div class="line-clamp-1">{{ video.director }}</div>
            <div class="line-clamp-1">{{ video.language }}</div>
            <div class="line-clamp-1">{{ video.year }}</div>
          </div>
        </li>
      </ul>
      <uni-load-more
        v-if="loadingStatus"
        iconType="circle"
        :status="loadingStatus"
        :contentText="LOADING_STATUS_TEXT"
      />
    </div>
  </div>
</template>

<style></style>
