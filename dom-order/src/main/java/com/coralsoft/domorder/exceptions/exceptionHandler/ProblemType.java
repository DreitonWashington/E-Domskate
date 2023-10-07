package com.coralsoft.domorder.exceptions.exceptionHandler;

public enum ProblemType {
    ORDER_NOT_FOUND("/order-not-found","Order Not Found."),
    PRODUCT_NOT_FOUND("/product-not-found", "Product Not Found."),
    ORDERSTATUS_CONFLICT("/order-status-conflict", "OrderStatus Conflict"),
    USER_NOT_FOUND("/user-not-found", "User Not Found");

    private String uri;
    private String title;

    ProblemType(String uriPath, String title) {
        this.uri = "https://coralgroup/dom-api/exceptions" + uriPath;
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
