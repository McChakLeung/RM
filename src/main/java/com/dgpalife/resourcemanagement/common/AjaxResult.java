package com.dgpalife.resourcemanagement.common;

public class AjaxResult {

    private boolean success;

    private String message;

    private Page page;

    public boolean isSuccess() {
        return success;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
