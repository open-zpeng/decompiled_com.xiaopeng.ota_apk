package com.xiaopeng.ota.presenter.update.bean;

import com.xiaopeng.ota.utils.Base64Utils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class Feature {
    private String content;
    private List<String> images;
    private List<FeatureResource> resources;
    private String title;
    private List<String> videos;

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public List<String> getImages() {
        return this.images;
    }

    public void setImages(List<String> list) {
        this.images = list;
    }

    public List<String> getVideos() {
        return this.videos;
    }

    public void setVideos(List<String> list) {
        this.videos = list;
    }

    private List<String> base64decode(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            arrayList.add(Base64Utils.decodeString(str));
        }
        return arrayList;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public List<FeatureResource> getResources() {
        return this.resources;
    }

    public void setResources(List<FeatureResource> list) {
        this.resources = list;
    }
}
