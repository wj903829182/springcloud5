package com.jack.spiderone.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

/**
 * create by jack 2018/11/17
 *
 * @author jack
 * @date: 2018/11/17 22:02
 * @Description:
 *
 * URLConnection 是 JDK 自带的一个抽象类，其代表应用程序和 URL 之间的通信链接。
 * 在网络爬虫中，我们可以使用 URLConnection 请求一个 URL 地址，
 * 然后获取流信息，通过对流信息的操作，可获得请求到的实体内容
 *
 */
public class URLConnectionTest {


    /**
     * 1,URLConnection 的使用
     */
    public static void test1() throws IOException {
        //使用 URLConnection 时，我们无法直接实例化对象，
        // 但可以通过在 URL 上调用 openConnection() 方法创建一个连接对象
        URL url = new URL("http://www.w3school.com.cn/b.asp");
        URLConnection conn = url.openConnection();

    }

    /**
     * 2,获取数据内容
     * @throws IOException
     */
    public static void test2() throws IOException {
        URL url = new URL("http://www.w3school.com.cn/b.asp");
        URLConnection conn = url.openConnection();
        InputStream in=conn.getInputStream();
        // 定义BufferedReader输入流来读取URL的响应
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(in));
        String line;
        //这里的内容是html
        String html = "";
        while ((line = reader.readLine()) != null) {
            html +=  line;
        }
        System.out.println(html);
    }


    /**
     * 3,GET请求
     * @throws IOException
     */
    public static void test3() throws IOException{
        //初始化URL
        URL url = new URL("http://www.w3school.com.cn/b.asp");
        //使用URLConnection的子类HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 允许Input
        //将此 URLConnection 的 doInput 字段的值设置为true,表示 URL 连接可用于输入
        conn.setDoInput(true);
        //设置请求的方法GET
        conn.setRequestMethod("GET");
         //conn.setRequestMethod("POST"); //注意该网页只能使用GET请求
        //建立实际连接操作,子类 HttpURLConnection 建立实际连接需要使用 connect() 方法
        conn.connect();
        //获取响应状态码
        int statusCode = conn.getResponseCode();
        String responseBody = null;
        //如果响应状态码为200
        if (HttpURLConnection.HTTP_OK == statusCode ) {
            // 定义BufferedReader输入流来读取URL的响应 ,这里设置编码
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "GBK"));
            //读取内容
            String readLine = null;
            StringBuffer response = new StringBuffer();
            while (null != (readLine = bufferedReader.readLine())) {
                response.append(readLine);
            }

            bufferedReader.close();
            responseBody = response.toString();
        }
        System.out.println(responseBody);
    }


    /**
     * 4,POST 请求
     *
     * @throws IOException
     */
    public static void test4() throws IOException{
        //Post表单需要提交的参数
        String wen = "EH629625211CS";
        String action = "ajax";
        //初始化URL
        URL url = new URL("http://www.kd185.com/ems.php");
        //使用URLConnection的子类HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         // //允许Input、Output,不使用Cache
        //将此 URLConnection 的 doOutput 字段的值设置为true,设置 setDoOutput() 为 True。该程序能够成功获取服务器返回的数据
        conn.setDoOutput(true);
        //将此 URLConnection 的 doInput 字段的值设置为true
        conn.setDoInput(true);
        // 将此 URLConnection 的 useCaches 字段的值设置为false
        conn.setUseCaches(false);
        //Post提交参数
        conn.setRequestMethod("POST");
        StringBuffer params = new StringBuffer();
        // 表单参数拼接
        params.append("wen").append("=").append(wen).append("&")
                .append("action").append("=").append(action);
        byte[] bypes = params.toString().getBytes();
        // 在连接中添加参数
        conn.getOutputStream().write(bypes);
        // 定义BufferedReader输入流来读取URL的响应 ,这里设置编码
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"));
        String line;
        String html = "";
        while ((line = bufferedReader.readLine()) != null) {
            html +=  line;
        }
        System.out.println(html);
    }


    /**
     *
     * 5,请求头的设置
     *在 URLConnection 以及 HttpURLConnection 中，
     * 我们可以使 setRequestProperty(key,value) 的形式设置请求头信息
     *
     * @throws IOException
     */
    public static void test5() throws IOException{
        //初始化URL
        URL url = new URL("http://www.w3school.com.cn/b.asp");
        //使用URLConnection
        URLConnection conn =  url.openConnection();
        //添加请求头信息
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
        conn.setRequestProperty("Host", "www.w3school.com.cn");
        conn.setRequestProperty("Cache-Control", "max-age=0");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36");
        //实际连接操作
        conn.connect();
    }


    /**
     * 6,超时设置
     *超时设置，防止网络异常时，可能会导致程序僵死而不继续往下执行的情况
     * @throws IOException
     */
    public static void test6() throws IOException{
        //初始化URL
        URL url = new URL("http://www.w3school.com.cn/b.asp");
        URLConnection conn =  url.openConnection();
        // HttpURLConnection conn = (HttpURLConnection) url.openConnection()
        //连接超时 单位毫秒
        conn.setConnectTimeout(30000);
        //读取超时 单位毫秒
        conn.setReadTimeout(30000);
    }


    /**
     * 7,代理的设置
     *在 URLConnection 以及 HttpURLConnection 中，我们可以使用 Proxy 进行设置代理
     * @throws IOException
     */
    public static void test7() throws IOException{
        //设置代理
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("171.97.67.160", 3128));
        URL url = new URL("http://www.w3school.com.cn/b.asp");
        //以代理的方式建立连接
        URLConnection conn = url.openConnection(proxy);
        //建立实体连接
        conn.connect();
        // 定义BufferedReader输入流来读取URL的响应 ,这里设置编码
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "gbk"));
        String line;
        String html = "";
        while ((line = bufferedReader.readLine()) != null) {
            html +=  line;
        }
        System.out.println(html);
    }


    public static void test8() throws IOException{

    }

    public static void main(String[] args) throws IOException {

        test4();
    }

}
