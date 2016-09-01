package com.yourtion.cnode_android.Modules;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Yourtion on 9/1/16.
 */
class Replie {

    private static final String TAG = "CNodeModelTopic";

    private static final String JSON_ID = "id";
    private static final String JSON_AUTHOR = "author";
    private static final String JSON_CONTENT = "content";
    private static final String JSON_UP = "ups";
    private static final String JSON_CREATE_AT = "create_at";
    private static final String JSON_REPLY_ID = "reply_id";

    public String mReplieId;
    public Author mAuthor;
    public String mContent;
    public ArrayList<String> mUps;
    public Date mCreateAt;
    public String mReplyId;

    public Replie(JSONObject json) throws JSONException {

        DateFormat df = new SimpleDateFormat(Topic.JSON_DATE_FORMAT, Locale.CHINA);

        mReplieId = json.getString(JSON_ID);
        mAuthor = new Author(json.getJSONObject(JSON_AUTHOR));
        mContent = json.getString(JSON_CONTENT);
        mReplyId = json.getString(JSON_REPLY_ID);
        try {
            mCreateAt = df.parse(json.getString(JSON_CREATE_AT));
        } catch (ParseException ignored) {
            Log.e(TAG, ignored.toString());
        }

        mUps = new ArrayList<>();
        JSONArray jArray = json.getJSONArray(JSON_UP);
        if (jArray != null) {
            for (int i = 0; i < jArray.length(); i++) {
                mUps.add(jArray.get(i).toString());
            }
        }
    }

    public String getReplieId() {
        return mReplieId;
    }

    public void setReplieId(String replieId) {
        mReplieId = replieId;
    }

    public Author getAuthor() {
        return mAuthor;
    }

    public void setAuthor(Author author) {
        mAuthor = author;
    }

    public ArrayList<String> getUps() {
        return mUps;
    }

    public void setUps(ArrayList<String> ups) {
        mUps = ups;
    }

    public Date getCreateAt() {
        return mCreateAt;
    }

    public void setCreateAt(Date createAt) {
        mCreateAt = createAt;
    }

    public String getReplyId() {
        return mReplyId;
    }

    public void setReplyId(String replyId) {
        mReplyId = replyId;
    }
}
