package com.jack.spiderone.service;

import com.alibaba.fastjson.JSONObject;
import com.jack.spiderone.entity.CommentModel;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * create by jack 2018/11/18
 *
 * @author jack
 * @date: 2018/11/18 11:35
 * @Description:
 */
public class CookBookSpider {

    /**
     * 通过url获取json字符串
     * @param url
     * @return
     */
    public static String getJson(String url) throws IOException {
        //初始化httpclient
        HttpClient httpClient = HttpClients.custom().build();
        //使用的请求方法
        HttpGet httpget = new HttpGet(url);
        //发出get请求
        HttpResponse response = httpClient.execute(httpget);
        //获取网页内容流
        HttpEntity httpEntity = response.getEntity();
        //以字符串的形式(需设置编码)
        String entity = EntityUtils.toString(httpEntity, "gbk");
        //关闭内容流
        EntityUtils.consume(httpEntity);
        //返回JSON字符串
        return entity;
    }


    /**
     * 解析json字符串为对象数组
     * @param jsonStr
     * @return
     */
    public static List<CommentModel> parseData(String jsonStr){
        //将uncode码转化为中文
        jsonStr = decode(jsonStr);
        //使用分割以及正则取代，处理成标准化JSON数组
        String jsondata  = "{"+jsonStr.split("data\":\\{")[2].split("\"avatar")[0].replaceAll("\"_\\d*[0-9]\":", "");
        jsonStr = jsondata.substring(0, jsondata.length()-2);
        //将json数组解析成对象集合
        List<CommentModel>  datalis = JSONObject.parseArray("["+jsonStr.substring(1,jsonStr.length())+"]", CommentModel.class);
        return datalis;
    }

   public static void spiderCookBook() throws IOException {
       //需要解析的URL
       String url = "http://www.haodou.com/comment.php?do=list&callback=jQuery18304706379730622201_1542510303429&channel=recipe&item=853171&sort=desc&page=1&size=5&comment_id=0&cate=0&purify=common&_=1542510303816";
       //获取JSON数据
       String jsonstring = getJson(url);
       //解析JSON数据
       List<CommentModel> datalist = parseData(jsonstring);
       //输出数据
       for (CommentModel comm : datalist) {
           System.out.println(comm.getCommentId() + "\t" + comm.getItemId() + "\t" + comm.getContent());
       }
   }



    /**
     * 将unicode码转化为中文
     * @param unicodeStr
     * @return
     */
    public static String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr
                        .charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(
                                unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }

    public static void main(String[] args) throws IOException {
        spiderCookBook();
    }

}
