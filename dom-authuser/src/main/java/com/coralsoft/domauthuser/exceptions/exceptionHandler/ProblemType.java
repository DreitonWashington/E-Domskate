package com.coralsoft.domauthuser.exceptions.exceptionHandler;

import lombok.Data;

public enum ProblemType {
    USER_NOT_FOUND("/user-not-found", "User Not Found.");

    private String uri;
    private String title;

    ProblemType(String uri, String title){
        this.uri = "https://coralgroup/dom-userapi/exceptions" + uri;
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
