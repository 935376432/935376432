/*
 * Copyright (c) 2015-2015, Zhejiang QiZhi Technologies Co., Ltd.
 * <http://www.shterm.com/>
 */
package com.shterm.web.api;

public class UploadedFile {

    private String name;
    private String locaton;
    private Long size;
    private String type;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocaton() {
        return locaton;
    }
    public void setLocaton(String locaton) {
        this.locaton = locaton;
    }
    public Long getSize() {
        return size;
    }
    public void setSize(Long size) {
        this.size = size;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}
