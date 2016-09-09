package com.yourtion.cnode_android.Modules;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Yourtion on 9/1/16.
 */

public class Topic implements Serializable {

    public static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String TAG = "CNodeModelTopic";
    private static final String JSON_ID = "id";
    private static final String JSON_AUTHOR_ID = "author_id";
    private static final String JSON_TAB = "tab";
    private static final String JSON_CONTENT = "content";
    private static final String JSON_TITLE = "title";
    private static final String JSON_LAST_REPLY_AT = "last_reply_at";
    private static final String JSON_GOOD = "good";
    private static final String JSON_TOP = "top";
    private static final String JSON_REPLY_COUNT = "reply_count";
    private static final String JSON_VISIT_COUNT = "visit_count";
    private static final String JSON_CREATE_AT = "create_at";
    private static final String JSON_AUTHOR = "author";
    private static final String JSON_REPLIES = "replies";
    public String mTopicId;
    public String mAuthorId;
    public String mTab;
    public String mContent;
    public String mTitle;
    public Date mLastReplyAt;
    public Boolean mGood;
    public Boolean mTop;
    public Number mReplyCount;
    public Number mVisitCount;
    public Date mCreateAt;
    public Author mAuthor;
    public ArrayList<Replie> mReplies;

    public Topic(JSONObject json) throws JSONException {

        DateFormat df = new SimpleDateFormat(JSON_DATE_FORMAT, Locale.CHINA);

        mTopicId = json.getString(JSON_ID);
        mAuthorId = json.getString(JSON_AUTHOR_ID);
        mTab = json.optString(JSON_TAB, "unknow");
        mContent = json.getString(JSON_CONTENT);
        mTitle = json.getString(JSON_TITLE);
        mGood = json.getBoolean(JSON_GOOD);
        mTop = json.getBoolean(JSON_TOP);
        mReplyCount = json.getInt(JSON_REPLY_COUNT);
        mVisitCount = json.getInt(JSON_VISIT_COUNT);
        try {
            mLastReplyAt = df.parse(json.getString(JSON_LAST_REPLY_AT));
            mCreateAt = df.parse(json.getString(JSON_CREATE_AT));
        } catch (ParseException ignored) {
            Log.e(TAG, ignored.toString());
        }


        if (json.has(JSON_AUTHOR)) {
            mAuthor = new Author(json.getJSONObject(JSON_AUTHOR));
        }

        if (json.has(JSON_REPLIES)) {
            mReplies = new ArrayList<>();
            JSONArray jArray = json.getJSONArray(JSON_REPLIES);
            if (jArray != null) {
                for (int i = 0; i < jArray.length(); i++) {
                    mReplies.add(new Replie(jArray.getJSONObject(i)));
                }
            }
        }
    }

    public String getTopicId() {
        return mTopicId;
    }

    public String getAuthorId() {
        return mAuthorId;
    }

    public String getTab() {
        return mTab;
    }

    public String getContent() {
        return mContent;
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getLastReplyAt() {
        return mLastReplyAt;
    }

    public Boolean getGood() {
        return mGood;
    }

    public Boolean getTop() {
        return mTop;
    }

    public Number getReplyCount() {
        return mReplyCount;
    }

    public Number getVisitCount() {
        return mVisitCount;
    }

    public Date getCreateAt() {
        return mCreateAt;
    }

    public Author getAuthor() {
        return mAuthor;
    }

    public ArrayList<Replie> getReplies() {
        return mReplies;
    }

    public String getCountText() {
        StringBuilder countText = new StringBuilder();
        countText.append(this.getReplyCount());
        countText.append(" / ");
        countText.append(this.getVisitCount());
        return countText.toString();
    }

    public String getTabString() {
        if (this.mTop) return "置顶";
        if (this.mGood) return "精华";
        switch (this.mTab) {
            case "share":
                return "分享";
            case "job":
                return "招聘";
            case "ask":
                return "问答";
            default:
                return null;
        }
    }
}

