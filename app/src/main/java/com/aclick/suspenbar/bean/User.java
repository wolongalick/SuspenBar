package com.aclick.suspenbar.bean;

/**
 * Created by cxw on 2017/1/6.
 */

public class User {
    private int avatarResId;
    private String name;

    public User(int avatarResId, String name) {
        this.avatarResId = avatarResId;
        this.name = name;
    }

    public int getAvatarResId() {
        return avatarResId;
    }

    public void setAvatarResId(int avatarResId) {
        this.avatarResId = avatarResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
