package com.yourtion.cnode_android.Modules;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Yourtion on 9/1/16.
 */
public class Author implements Serializable {

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

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

}
