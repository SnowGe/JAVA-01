package com.javatest;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpTest {
    public static void main(String[] argus)
    {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .get()
                .url("http://localhost:8801")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println("响应内容为：" + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
