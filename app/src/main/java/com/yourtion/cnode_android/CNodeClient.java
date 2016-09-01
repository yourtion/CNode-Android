package com.yourtion.cnode_android;

import android.util.Log;

import com.yourtion.cnode_android.Modules.Topic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Yourtion on 9/1/16.
 */
public class CNodeClient {

    private static final String TAG = "CNodeClient";
    private static final String HOST = "https://cnodejs.org/api/v1";
    private OkHttpClient okHttpClient = new OkHttpClient();

    public interface Callback{
        public abstract void success(Object res);
        public abstract void fail();
    }

    public void getTopics(String tab, Number page, Number limit,  final Callback callback) {
        Request request = new Request.Builder().
                url(HOST + "/topics").
                get().
                build();
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resStr = response.body().string();
                try {
                    JSONObject json = new JSONObject(resStr);
                    JSONArray arr = json.getJSONArray("data");
                    ArrayList<Topic> topics = new ArrayList<>();
                    if (arr != null) {
                        for (int i = 0; i < arr.length(); i++) {
                            topics.add(new Topic(arr.getJSONObject(i)));
                        }
                    }
                    Log.d(TAG, topics.toString());
                    callback.success(topics);
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.fail();
                }

            }
        });
    }

    public void getTopic(String id, final Callback callbak) {
        Request request = new Request.Builder().
                url(HOST + "/topic/" + id).
                get().
                build();
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resStr = response.body().string();
                try {
                    JSONObject json = new JSONObject(resStr);
                    Topic topic = new Topic(json.getJSONObject("data"));
                    Log.d(TAG, topic.toString());
                    callbak.success(topic);
                } catch (JSONException e) {
                    e.printStackTrace();
                    callbak.fail();
                }

            }
        });
    }


}
