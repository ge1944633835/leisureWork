package com.gcg.leisureWork.commons.beans;

import java.io.Serializable;

/**
 * Created by 戈传光 on 2016/7/19.
 * 邮箱 1944633835@qq.com
 */
public class NewsDetail  implements Serializable {
    private String picUrl;
    private  String title;
    private  String content;

    public NewsDetail(String picUrl, String content, String title) {
        this.picUrl = picUrl;
        this.content = content;
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "NewsDetail{" +
                "picUrl='" + picUrl + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
