package com.taotao.common.vo;

public class EasyUITreeNode {
    private long id;//主键
    private String text;
    private String state;//打开还是关,父类就是打开,子类就是关闭

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
