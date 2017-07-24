package com.jason.synology.mmc.entity;

/**
 * Copyright with Industics company.
 * Author: jason.xu
 * Date: 2017/7/24
 * Time: 下午3:11
 */
public class Cast {
    private int doubanId;
    private String name;
    private String doubanAlt;
    private Image avatars;

    public int getDoubanId() {
        return doubanId;
    }

    public void setDoubanId(int doubanId) {
        this.doubanId = doubanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoubanAlt() {
        return doubanAlt;
    }

    public void setDoubanAlt(String doubanAlt) {
        this.doubanAlt = doubanAlt;
    }

    public Image getAvatars() {
        return avatars;
    }

    public void setAvatars(Image avatars) {
        this.avatars = avatars;
    }
}
