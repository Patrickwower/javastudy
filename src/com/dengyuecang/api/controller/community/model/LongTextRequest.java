package com.dengyuecang.api.controller.community.model;

/**
 * Created by acang on 16/6/22.
 */
public class LongTextRequest {

    private String content;

    private String title;

    private String headers;

    public LongTextRequest() {
    }

    public LongTextRequest(String content, String title, String headers) {
        this.content = content;
        this.title = title;
        this.headers = headers;
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

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }
}
