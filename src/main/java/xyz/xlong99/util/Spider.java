package xyz.xlong99.util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author xlong
 * @date 2019-08-01 10:42
 */


public class Spider {
    private static String urll = "http://www.baidu.com/s?wd=";

    private static void getNews(String url, int num) {
        try {
            Document doc = Jsoup.connect(url).get();
            Element element = doc.getElementById("content_left");

            for (int i = 1; i < (num + 1); i++) {

                Element result = element.getElementById(String.valueOf(i));
                Elements add = result.select("a");
                Elements add2 = result.select(".c-abstract");
                System.out.println("--------------------- " + i + " ---------------------");
                System.out.println(add.first().text());
                if (add2.size()==0)
                {
                    continue;
                }else{
                    System.out.println(add2.first().text());
                }

                System.out.println(add.first().attr("href"));


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void getResult(int num, String question) {

        String url = "";

        try {
            url = urll + URLEncoder.encode(question, "utf-8") + "&rn=" + num;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        getNews(url, num);


    }


    public static void main(String[] args) {
        getResult(6, "924663941");

    }


}

