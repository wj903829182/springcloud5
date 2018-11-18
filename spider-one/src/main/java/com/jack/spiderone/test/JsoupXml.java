package com.jack.spiderone.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * create by jack 2018/11/18
 *
 * @author jack
 * @date: 2018/11/18 09:42
 * @Description:
 */
public class JsoupXml {



    public static void test1() throws IOException {
        //获取URL对应的HTML内容
        Document doc = Jsoup.connect("http://db.auto.sohu.com/cxdata/xml/sales/model/model1001sales.xml").timeout(5000).get();
       //Jsoup选择器解析
        Elements sales_ele = doc.select("sales");
        for (Element elem:sales_ele) {
            int salesnum=Integer.valueOf(elem.attr("salesnum"));
            String date = elem.attr("date");
            System.out.println("月份:" + date + "\t销量:" + salesnum);

        }
    }

    public static void main(String[] args) throws IOException {
        test1();
    }
}
