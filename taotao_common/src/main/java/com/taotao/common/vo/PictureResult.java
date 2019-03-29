package com.taotao.common.vo;

public class PictureResult {
    private Integer error;//成功为0 失败时为1
    private String url;//成功的时候返回生成的url,这个用于回显
    private String message;//失败的时候返回的

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
