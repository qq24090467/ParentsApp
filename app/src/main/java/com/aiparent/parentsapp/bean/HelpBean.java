package com.aiparent.parentsapp.bean;

/**
 * @deprecated 帮助实体
 * Created by weilanzhuan on 2015/4/11.
 */
public class HelpBean {

    private  int id;
    private  String title;
    private  String content;
    private  String add_time;
    private  int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getAdd_time() {
        return add_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
