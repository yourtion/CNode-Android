package com.yourtion.cnode_android;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Yourtion on 9/1/16.
 */

public class ModelTopic {
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
    public ModelAuthor mAuthor;
    public ArrayList<ModelReplie> mModelReplies;

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

    public ModelAuthor getAuthor() {
        return mAuthor;
    }

    public void setAuthor(ModelAuthor author) {
        mAuthor = author;
    }

    public ArrayList<ModelReplie> getModelReplies() {
        return mModelReplies;
    }

    public void setModelReplies(ArrayList<ModelReplie> modelReplies) {
        mModelReplies = modelReplies;
    }
}

class ModelAuthor {
    public String mAuthotId;
    public String mLoginname;
    public String mAvatarUrl;

    public String getAuthotId() {
        return mAuthotId;
    }

    public void setAuthotId(String authotId) {
        mAuthotId = authotId;
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

class ModelReplie {
    public String mReplieId;
    public ModelAuthor mAuthor;
    public ArrayList<String> mUps;
    public Date mCreateAt;
    public String mReplyId;

    public String getReplieId() {
        return mReplieId;
    }

    public void setReplieId(String replieId) {
        mReplieId = replieId;
    }

    public ModelAuthor getAuthor() {
        return mAuthor;
    }

    public void setAuthor(ModelAuthor author) {
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
