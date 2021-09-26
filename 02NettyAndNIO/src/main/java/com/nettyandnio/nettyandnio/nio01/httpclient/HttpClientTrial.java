package com.nettyandnio.nettyandnio.nio01.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientTrial {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://localhost:8801");

        CloseableHttpResponse response = null;
        try {
            //由客户端发起get请求
            response = httpClient.execute(httpGet);
            //获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (null != responseEntity){
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
