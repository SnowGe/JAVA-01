package com.javatest;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientTest {
    public static void main(String[] argus){

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet hg = new HttpGet("http://localhost:8801");

        CloseableHttpResponse response= null;

        try {
            response = httpClient.execute(hg);
            HttpEntity responseEntity = response.getEntity();
            if(responseEntity != null){
                System.out.println("响应内容为：" + EntityUtils.toString(responseEntity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
