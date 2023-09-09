package com.coralsoft.domproduct.exceptions.exceptionHandler;

public enum ProblemType {
    BRAND_NOT_FOUND("/brand-not-found", "Brand Not Found."),
    SIZE_NOT_FOUND("/size-not-found", "Size Not Found."),
    PRODUCT_NOT_FOUND("/product-not-found", "Product Not Found.");
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
