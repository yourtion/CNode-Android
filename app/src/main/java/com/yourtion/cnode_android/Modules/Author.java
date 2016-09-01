package com.yourtion.cnode_android.Modules;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yourtion on 9/1/16.
 */
class Author {

    private static final String JSON_LOGINNAME = "loginname";
    private static final String JSON_AVATAR_URL = "avatar_url";

    public String mLoginname;
    public String mAvatarUrl;

    public Author(JSONObject json) throws JSONException {
        mLoginname = json.getString(JSON_LOGINNAME);
        mAvatarUrl = json.getString(JSON_AVATAR_URL);
    }

    public String getLoginname() {
        return mLoginname;
    }

    public void setLoginname(String loginname) {
        mLoginname = loginname;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }
}
