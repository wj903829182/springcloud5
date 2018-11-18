package com.jack.spiderone.test;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.*;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * create by jack 2018/11/18
 *
 * @author jack
 * @date: 2018/11/18 09:21
 * @Description:
 */
public class HTMLParserTest {

    public static void test1() throws ParserException, IOException {
        //这里笔者使用Jsoup获取html文件
        Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").timeout(5000).get();
        //转化成String格式
        String html =doc.html();
         //使用Lexer构造
        Lexer lexer = new Lexer(html);
        Parser parser = new Parser(lexer);
       //过滤页面中的链接标签
        NodeFilter filter = new NodeClassFilter(LinkTag.class);
        //获取匹配到的节点
        NodeList list = parser.extractAllNodesThatMatch(filter);
       //遍历每一个节点
        for(int i=0; i<list.size();i++){
            Node node = (Node)list.elementAt(i);
            System.out.println("链接为：" + ((LinkTag) node).getLink() + "\t标题为:" + node.toPlainTextString() );
        }
    }


    public static void test2() throws ParserException, IOException{
        //生成一个解析器对象，用网页的 url 作为参数
        Parser parser = new Parser("http://www.w3school.com.cn/b.asp");
        //设置网页的编码(GBK)
        parser.setEncoding("gbk");
        //过滤页面中的标签
        NodeFilter filtertag= new TagNameFilter("ul");
        //父节点包含ul
        NodeFilter filterParent = new HasParentFilter(filtertag);
        //选择的节点为每个li
        NodeFilter filtername = new TagNameFilter("li");
        //并且li节点中包含id属性
        NodeFilter filterId= new HasAttributeFilter("id");
        //并操作
        NodeFilter filter = new AndFilter(filterParent,filtername);
        //并操作
        NodeFilter filterfinal = new AndFilter(filter,filterId);
        //选择匹配到的内容
        NodeList list = parser.extractAllNodesThatMatch(filterfinal);
        //循环遍历
        for(int i=0; i<list.size();i++){
            //获取li的第一个子节点
            Node node = (Node)list.elementAt(i).getFirstChild();
            System.out.println( "链接为：" + ((LinkTag) node).getLink() +"\t标题为:" + node.toPlainTextString() );
        }
    }


    public static void test3() throws ParserException, IOException{
        //使用URLConnection请求数据
        URL url = new URL("http://www.w3school.com.cn/b.asp");
        URLConnection conn = url.openConnection();
        Parser parser = new Parser(conn);
       //通过css选择器解析内容
        CssSelectorNodeFilter Filter=new CssSelectorNodeFilter ("#course > ul > li");
        //选择匹配到的内容
        NodeList list = parser.extractAllNodesThatMatch(Filter);
        //循环遍历
        for(int i=0; i<list.size();i++){
            //获取li的第一个子节点
            Node node = (Node)list.elementAt(i).getFirstChild();
            System.out.println( "链接为：" + ((LinkTag) node).getLink() +"\t标题为:" + node.toPlainTextString() );
        }
    }

    public static void main(String[] args) throws IOException, ParserException {
            //test1();
            test2();
    }
}
