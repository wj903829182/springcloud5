package com.jack.spiderone.test;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * create by jack 2018/11/17
 *
 * @author jack
 * @date: 2018/11/17 15:32
 * @Description:
 *
 *
 * 1,Node（节点)：HTML 中所包含的内容都可以看成一个节点。节点有很多种类型：属性节点（Attribute）、注释节点（Note）、文本节点（Text）、元素节点（Element）等。解析 HTML 内容的过程，其实就是对节点操作的过程。
2,Element（元素）：元素是节点的子集，所以一个元素也是一个节点。
3,Document（文档）：指整个 HTML 文档的源码内容。
 *
 */
public class JsoupTest1 {

    /**
     * 1,网页对应的 HTML 文件
     *
     * @throws IOException
     */
    public static void JsoupTest1() throws IOException {
        //get方式获取html
        //Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").get();
        //System.out.println(doc);

        //通过post方式获取html
        //可以看到该网址通过两种方法都能请求到内容
        //Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").post();
        //System.out.println(doc);

        //设置延迟时间，5000指5秒
        Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").timeout(5000).get();
        System.out.println(doc);

    }


    /**
     * 2,设置请求头信心
     *
     * @throws IOException
     */
    public static void JsoupTest2() throws IOException {
        //获取请求连接
        Connection connect = Jsoup.connect("http://www.w3school.com.cn/b.asp");
        //使用Map集合存储头信息
        Map<String, String> header = new HashMap<String, String>();
        header.put("Host", "www.w3school.com.cn");
        header.put("User-Agent", " Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36");
        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        header.put("Accept-Language", "zh-cn,zh;q=0.5");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Cache-Control", "max-age=0");
        header.put("Connection", "keep-alive");
         //添加头信息
        Connection conheader = connect.headers(header);
         //使用get()请求页面内容
        Document document = conheader.get();
         //输出页面内容
        System.out.println(document);
    }


    /**
     * 3,给定一个 HTML 字符串，jsoup 使用 Jsoup.parse(String html) 的方法，
     * 将 String 类型的 HTML 转化成了 Document 类型，
     * 并可以使用 select 选择器定位要解析的内容
     * @throws IOException
     */
    public static void JsoupTest3() throws IOException{
        //需要解析的HTML文本
        String html = "<html><body><div id=\"w3school\"> <h1>浏览器脚本教程</h1> <p><strong>从左侧的菜单选择你需要的教程！</strong></p> </div>"
                + "<div>  <div id=\"course\"> <ul> <li><a href=\"/js/index.asp\" title=\"JavaScript 教程\">JavaScript</a></li> </ul> </div> </body></html>";

        //转化成Document
        Document doc = Jsoup.parse(html);
        //获取Element
        Element element = doc.select("div[id=w3school]").get(0);
        //从Element提取内容(抽取一个Node对应的信息)
        String text1 = element.select("h1").text();
        //从Element提取内容(抽取一个Node对应的信息)
        String text2 = element.select("p").text();
        System.out.println("输出解析的元素内容为:");
        System.out.println(element);
        System.out.println("抽取的文本信息为:");
        System.out.println(text1 + "\n" + text1);
    }


    /**
     * 4,jsoup解析URL加载的Document
     * @throws IOException
     */
    public static void JsoupTest4() throws IOException{
        //获取URL对应的HTML内容
        Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").timeout(5000).get();
        System.out.println(doc);
        //select选择器解析内容
        //获取Element,这里相当于div[id=w3school]
        Element element = doc.select("div#w3school").get(0);
        //从Element提取内容(抽取一个Node对应的信息)
        String text1 = element.select("h1").text();
        //从Element提取内容(抽取一个Node对应的信息)
        String text2 = element.select("p").text();
        System.out.println("输出解析的元素内容为:");
        System.out.println(element);
        System.out.println("抽取的文本信息为:");
        System.out.println(text1 + "\t" + text2);
    }


    /**
     *5,
     *
     * @throws IOException
     */
    public static void JsoupTest5() throws IOException{
        //获取URL对应的HTML内容
        Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").timeout(5000).get();
         //层层定位到要解析的内容，可以发现包含多个li标签
        //Elements elements = doc.select("div[id=course]").select("li");
        Elements elements = doc.select("div#course").select("li");
         //遍历每一个li节点
        for (Element ele : elements) {
            //.text()为解析标签中的文本内容
            String title = ele.select("a").text();
            //.attr(String)表示获取标签内某一属性的内容
            String course_url = ele.select("a").attr("href");
            System.out.println("课程的标题为:" + title + "\t对应的URL为:" + course_url);
        }
    }


    /**
     * 6,
     * getElementById(String id)  //通过id查找
     getElementsByTag(String tag)  //通过标签查找
     getElementsByClass(String className) //通过类名查找
     getElementsByAttribute(String key) (and related methods)  //通过属性查找
     Element siblings: siblingElements(), firstElementSibling(), lastElementSibling(); nextElementSibling(), previousElementSibling()  //获取兄弟节点
     Graph: parent(), children(), child(int index) //获取节点的父节点，子节点
     * @throws IOException
     */
    public static void JsoupTest6() throws IOException{
        //获取URL对应的HTML内容
        Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").timeout(5000).get();
        //层层定位到要解析的内容，可以发现包含多个li标签
        //通过id定位，并获取文本
        System.out.println("通过id提取的结果为:" + doc.getElementById("course").text());
        System.out.println("通过tag提取的结果为:" + doc.getElementById("course").getElementsByTag("a").text());
        System.out.println("通过attr提取的结果为:" + doc.getElementById("course").getElementsByAttribute("href").text());
        //获取HTML文档中指定class名的所有元素
        Elements elements = doc.getElementsByClass("browserscripting");
        System.out.println("通过class提取的元素为:" + elements);
    }


    /**
     * 7,
     * @throws IOException
     */
    public static void JsoupTest7() throws IOException{
        //获取URL对应的HTML内容
        Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").timeout(5000).get();
        //[attr=value]: 利用属性值来查找元素,例如[id=course]; 通过tagname: 通过标签查找元素，比如：a
        System.out.println(doc.select("[id=course]").select("a").get(0).text());
        //fb[[attr=value]:利用标签属性联合查找
        System.out.println(doc.select("div[id=course]").select("a").get(0).text());
        //#id: 通过ID查找元素,例如，#course
        System.out.println(doc.select("#course").select("a").get(0).text());
        //通过属性属性查找元素，比如：[href]
        System.out.println(doc.select("#course").select("[href]").get(0).text());
       //.class通过class名称查找元素
        System.out.println(doc.select(".browserscripting").text());
       //[attr^=value], [attr$=value], [attr*=value]利用匹配属性值开头、结尾或包含属性值来查找元素(很常用的方法)
        System.out.println(doc.select("#course").select("[href$=index.asp]").text());
        //[attr~=regex]: 利用属性值匹配正则表达式来查找元素,*指匹配所有元素
        System.out.println(doc.select("#course").select("[href~=/*]").text());
    }

    public static void main(String[] args) throws IOException {
        //1,获取html
        //JsoupTest1();
        //2,设置请求头新获取html
        //3,JsoupTest2();
        //JsoupTest3();
        //JsoupTest4();
        //JsoupTest5();
        //JsoupTest6();
        JsoupTest7();
    }
}
