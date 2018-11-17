package com.jack.spiderone.test;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * create by jack 2018/11/17
 *
 * @author jack
 * @date: 2018/11/17 16:43
 * @Description:
 *
 * HttpClient 是 Apache Jakarta Common 下的子项目，用来提供高效的、最新的、功能丰富的支持
 * HTTP 协议的客户端编程工具包，并且它支持 HTTP 协议最新的版本和建议。
 * 增加了易用性和灵活性。
 * 其功能主要是用来向服务器发送请求，并返回相关资源。在网络爬虫实战中，
 * 经常使用 HttpClient 获取网页内容，使用 jsoup 解析网页内容。
 *
 */
public class HttpClientTest {

    /**
     * HttpClient的get请求
     */
    public static void HttpClientGetTest() throws IOException {
        //初始化httpclient,方法一
        //HttpClient httpClient = new DefaultHttpClient();
        ////初始化httpclient,方法二
        //HttpClientBuilder builder = HttpClientBuilder.create();
        //CloseableHttpClient httpClient = builder.build();
        //初始化httpclient,方法三
        //HttpClient httpClient = HttpClients.custom().build();
        //初始化httpclient,方法四
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //请求的地址URL
        String personalUrl = "http://www.w3school.com.cn/b.asp";
        //  get方法请求
        HttpGet httpGet = new HttpGet(personalUrl);
        //初始化HTTP响应
        HttpResponse response = null;
        //执行响应
        response = httpClient.execute(httpGet);
        //响应状态
        String status = response.getStatusLine().toString();
        //获取响应状态码
        int StatusCode = response.getStatusLine().getStatusCode();
        //协议的版本号
        ProtocolVersion protocolVersion = response.getProtocolVersion();
        //是否ok
        String phrase = response.getStatusLine().getReasonPhrase();
        //状态码200表示响应成功
        if(StatusCode == 200){
            //获取实体内容,这里为HTML内容
            String entity = EntityUtils.toString (response.getEntity(),"gbk");
            //输出实体内容
            System.out.println(entity);
            //消耗实体
            EntityUtils.consume(response.getEntity());
        }else {
            //关闭HttpEntity的流实体，
            EntityUtils.consume(response.getEntity());
        }
        System.out.println("status="+status);
        System.out.println("StatusCode="+StatusCode);
        System.out.println("protocolVersion="+protocolVersion);
        System.out.println("phrase="+phrase);
        httpClient.close();


    }


    /**
     * HttpClient的POST请求
     * @throws IOException
     */
    public static void HttpClientPostTest() throws IOException{
        /*List<NameValuePair> nvps= new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("param1", "value1"));
        nvps.add(new BasicNameValuePair("param2", "value2"));
        //UrlEncodedFormEntity 实例将会使用 URL encoding 来编码参数，产生以下内容,param1=value1&param2=value2
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
        HttpPost httppost = new HttpPost("http://localhost/handler.do");
        httppost.setEntity(entity);*/
        HttpClient httpclient = HttpClients.custom().build(); //初始化httpclient
        String renRenLoginURL = "http://www.renren.com/PLogin.do"; //登陆的地址
        HttpPost httpost = new HttpPost(renRenLoginURL);  //采用post方法
       //建立一个NameValuePair数组，用于存储欲传送的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("origURL","http://www.renren.com/home"));
        nvps.add(new BasicNameValuePair("email", "你的邮箱地址"));
        nvps.add(new BasicNameValuePair("password", "你的登陆密码"));
        HttpResponse response = null;
        try {
            //表单参数提交
            httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
            response = httpclient.execute(httpost);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpost.abort();
        }
    }


    /**
     * HttpClient的get请求获取，设置请求头
     * @throws IOException
     */
    public static void HttpClientGetTest2() throws IOException{
        //初始化httpclient
        HttpClient httpClient = HttpClients.custom().build();
        //使用的请求方法
        HttpGet httpget = new HttpGet("http://www.w3school.com.cn/b.asp");
       //请求头配置
        httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpget.setHeader("Accept-Encoding", "gzip, deflate");
        httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpget.setHeader("Cache-Control", "max-age=0");
        httpget.setHeader("Host", "www.w3school.com.cn");
        //这项内容很重要
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36");
        //发出get请求
        HttpResponse response = httpClient.execute(httpget);
        //获取响应状态码
        int code = response.getStatusLine().getStatusCode();
        //获取网页内容流
        HttpEntity httpEntity = response.getEntity();
        //以字符串的形式(需设置编码)
        String entity = EntityUtils.toString(httpEntity, "gbk");
        //输出所获得的的内容
        System.out.println(code + "\n" + entity);
        //关闭内容流
        EntityUtils.consume(httpEntity);
    }

    /**
     * HttpClient的get请求获取，设置请求头
     * @throws IOException
     */
    public static void HttpClientGetTest3() throws IOException{
        //通过集合封装头信息
        List<Header> headerList = new ArrayList<Header>();
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8"));
        headerList.add(new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36"));
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate"));
        headerList.add(new BasicHeader(HttpHeaders.CACHE_CONTROL, "max-age=0"));
        headerList.add(new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"));
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9"));
        headerList.add(new BasicHeader(HttpHeaders.HOST, "www.w3school.com.cn"));
       //构造自定义的HttpClient对象
        HttpClient httpClient = HttpClients.custom().setDefaultHeaders(headerList).build();
        //使用的请求方法
        HttpGet httpget = new HttpGet("http://www.w3school.com.cn/b.asp");
        //获取结果
        HttpResponse response = httpClient.execute(httpget);
    }


    /**
     * HttpClient中的超时问题
     * @throws IOException
     */
    public static void HttpClientGetTest4() throws IOException{
        //全部设置为10秒
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(10000).setConnectTimeout(10000).setConnectionRequestTimeout(10000).build();
        //配置HttpClient
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        CloseableHttpResponse response = null;
        try {
            //使用get方法发送请求
            response = httpClient.execute(new HttpGet("http://www.w3school.com.cn/b.asp"));
        }catch (Exception e){
            e.printStackTrace();
        }
        //获取结果，html
        String result = EntityUtils.toString(response.getEntity(),"gbk");
        //输出结果
        System.out.println(result);
    }

    /**
     * HttpClient中的超时问题,请求配置
     * @throws IOException
     */
    public static void HttpClientGetTest5() throws IOException {
        //初始化httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //Get请求(Post方法相似)
        HttpGet httpGet=new HttpGet("http://www.w3school.com.cn/b.asp");
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
        //httpget信息配置
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            //使用get方法发送请求
            response = httpClient.execute(httpGet);
        }catch (Exception e){
            e.printStackTrace();
        }
        //获取结果，html
        String result = EntityUtils.toString(response.getEntity(),"gbk");
        //输出结果
        System.out.println(result);
        httpClient.close();
    }


    /**
     *
     * 代理服务器的使用:
     *
     * 代理服务器（Proxy Server）是网上提供转接功能的服务器，一般情况下，
     * 我们使用网络浏览器直接连接其他Internet站点取得网络信息。
     * 代理服务器是介于客户端和 Web 服务器之间的另一台服务器，基于代理，浏览器不再直接向 Web 服务器取数据，
     * 而是向代理服务器发出请求，信号会先送到代理服务器，由代理服务器取回浏览器所需要的信息。简单的可以理解为中介
     *
     * 在网络爬虫中，使用代理服务器访问站点内容，能够隐藏爬虫的真实 IP 地址，从而防止网络爬虫被封。
     * 另外，普通网络爬虫使用固定 IP 请求时，往往需要设置随机休息时间，而通过代理却不需要，从而提高了爬虫的效率。
     * @throws IOException
     */
    public static void HttpClientGetTest6() throws IOException{
        //添加代理配置
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setProxy(new HttpHost("171.97.67.160", 3128, null))
                .build();
        //设置请求的方式及网页
        HttpGet httpGet = new HttpGet("http://www.w3school.com.cn/b.asp");
        //配置httpClient
        HttpClient httpClient = HttpClients.custom().
                setDefaultRequestConfig(defaultRequestConfig).build();
        //执行请求
        HttpResponse httpResponse = httpClient.execute(httpGet);
        System.out.println("状态码为:"+httpResponse.getStatusLine().getStatusCode());
        //获取结果，html
        String result = EntityUtils.toString(httpResponse.getEntity(),"gbk");
        //输出结果,请求的结果
        System.out.println(result);

    }


    public static void main(String[] args) throws IOException {
        HttpClientGetTest();
    }
}
