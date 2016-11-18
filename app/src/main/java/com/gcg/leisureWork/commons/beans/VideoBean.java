package com.gcg.leisureWork.commons.beans;

import java.util.List;

/**
 * Created by 戈传光 on 2016/8/12.
 * 邮箱 1944633835@qq.com
 */
public class VideoBean {


    /**
     * topicImg : http://vimg3.ws.126.net/image/snapshot/2016/9/H/H/VBVHCC2HH.jpg
     * videosource : 新媒体
     * mp4Hd_url : null
     * topicDesc : 神镜头是一个汇集精彩瞬间的视频社区，你能看到最精彩的镜头、最搞笑的八卦、最娱乐的内容，随时随地关注热门短视频。神镜头社区流畅的播放体验，让你换一种姿势看视频。
     * topicSid : VBVHCC255
     * cover : http://vimg1.ws.126.net/image/snapshot/2016/10/H/9/VC37NFPH9.jpg
     * title : 炉石传说：我好像射到啥东西了
     * playCount : 0
     * replyBoard : video_bbs
     * videoTopic : {"alias":"一个汇集精彩瞬间的短视频社区","tname":"神镜头","ename":"T1473066648781","tid":"T1473066648781"}
     * sectiontitle :
     * replyid : C37N1H2M008535RB
     * description : 更多视频请关注微信公众号：v神镜头。
     * mp4_url : http://flv2.bn.netease.com/videolib3/1610/24/tAERw7087/SD/tAERw7087-mobile.mp4
     * length : 43
     * playersize : 1
     * m3u8Hd_url : null
     * vid : VC37N1H2M
     * m3u8_url : http://flv2.bn.netease.com/videolib3/1610/24/tAERw7087/SD/movie_index.m3u8
     * ptime : 2016-10-24 16:27:02
     * topicName : 神镜头
     */

    private List<VideoInfo> V9LG4B3A0;

    public List<VideoInfo> getV9LG4B3A0() {
        return V9LG4B3A0;
    }

    public void setV9LG4B3A0(List<VideoInfo> V9LG4B3A0) {
        this.V9LG4B3A0 = V9LG4B3A0;
    }

    public static class VideoInfo {
        private String videosource;
        private String topicDesc;
        private String cover;
        private String title;
        private int playCount;
        private String description;
        private String mp4_url;
        private String ptime;
        private String topicName;

        @Override
        public String toString() {
            return "VideoInfo{" +
                    "videosource='" + videosource + '\'' +
                    ", topicDesc='" + topicDesc + '\'' +
                    ", cover='" + cover + '\'' +
                    ", title='" + title + '\'' +
                    ", playCount=" + playCount +
                    ", description='" + description + '\'' +
                    ", mp4_url='" + mp4_url + '\'' +
                    ", ptime='" + ptime + '\'' +
                    ", topicName='" + topicName + '\'' +
                    '}';
        }

        public String getVideosource() {
            return videosource;
        }

        public void setVideosource(String videosource) {
            this.videosource = videosource;
        }

        public String getTopicDesc() {
            return topicDesc;
        }

        public void setTopicDesc(String topicDesc) {
            this.topicDesc = topicDesc;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMp4_url() {
            return mp4_url;
        }

        public void setMp4_url(String mp4_url) {
            this.mp4_url = mp4_url;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }
    }
}
