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

public class Topic {

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

    public static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

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

    public void setTopicId(String topicId) {
        mTopicId = topicId;
    }

    public String getAuthorId() {
        return mAuthorId;
    }

    public void setAuthorId(String authorId) {
        mAuthorId = authorId;
    }

    public String getTab() {
        return mTab;
    }

    public void setTab(String tab) {
        mTab = tab;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getLastReplyAt() {
        return mLastReplyAt;
    }

    public void setLastReplyAt(Date lastReplyAt) {
        mLastReplyAt = lastReplyAt;
    }

    public Boolean getGood() {
        return mGood;
    }

    public void setGood(Boolean good) {
        mGood = good;
    }

    public Boolean getTop() {
        return mTop;
    }

    public void setTop(Boolean top) {
        mTop = top;
    }

    public Number getReplyCount() {
        return mReplyCount;
    }

    public void setReplyCount(Number replyCount) {
        mReplyCount = replyCount;
    }

    public Number getVisitCount() {
        return mVisitCount;
    }

    public void setVisitCount(Number visitCount) {
        mVisitCount = visitCount;
    }

    public Date getCreateAt() {
        return mCreateAt;
    }

    public void setCreateAt(Date createAt) {
        mCreateAt = createAt;
    }

    public Author getAuthor() {
        return mAuthor;
    }

    public void setAuthor(Author author) {
        mAuthor = author;
    }

    public ArrayList<Replie> getReplies() {
        return mReplies;
    }

    public void setReplies(ArrayList<Replie> replies) {
        mReplies = replies;
    }
}

