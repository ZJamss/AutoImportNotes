package com.zjamss.model.dto;

/**
 * @Program: AutoImportNote
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-30 20:20
 **/
public class PostResponse {
    int status;
    String message;
    POST data;

    public PostResponse() {
    }

    public PostResponse(int status, String message, POST data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public POST getData() {
        return data;
    }

    public void setData(POST data) {
        this.data = data;
    }

    public class POST {
        int id;

        public POST() {
        }

        public POST(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
