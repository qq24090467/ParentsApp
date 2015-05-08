package com.aiparent.parentsapp.bean;

/**
 * Created by weilanzhuan on 2015/5/7.
 */
public class DailyBean {

    private int ariticle_id;
    private String title;
    private String small_title;
    private String desc;
    private String content;
    private String uid;
    private String photos_url;
    private String sort;
    private String add_time;
    private String update_time;
    private int status;
    private int flag;
    private int cate_id;

    public int getAriticle_id() {
        return ariticle_id;
    }

    public void setAriticle_id(int ariticle_id) {
        this.ariticle_id = ariticle_id;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPhotos_url() {
        return photos_url;
    }

    public void setPhotos_url(String photos_url) {
        this.photos_url = photos_url;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSmall_title() {
        return small_title;
    }

    public void setSmall_title(String small_title) {
        this.small_title = small_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
