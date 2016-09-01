package com.yourtion.cnode_android;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
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

    public void getTopics(String tab, Number page, Number limit) {
        Request request = new Request.Builder().
                url(HOST + "/topics").
                get().
                build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resStr = response.body().string();
                try {
                    JSONObject json = new JSONObject(resStr);
                    JSONArray arr = json.getJSONArray("data");
                    ArrayList<ModelTopic> topics = new ArrayList<>();
                    if (arr != null) {
                        for (int i = 0; i < arr.length(); i++) {
                            topics.add(new ModelTopic(arr.getJSONObject(i)));
                        }
                    }
                    Log.e(TAG, topics.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void getTopic(String id) {
        Request request = new Request.Builder().
                url(HOST + "/topic/" + id).
                get().
                build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resStr = response.body().string();
                try {
                    JSONObject json = new JSONObject(resStr);
                    ModelTopic topic = new ModelTopic(json.getJSONObject("data"));
                    Log.e(TAG, topic.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


}
