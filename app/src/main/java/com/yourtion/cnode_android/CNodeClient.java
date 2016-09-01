package com.yourtion.cnode_android;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
                    Log.d(TAG, json.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


}
