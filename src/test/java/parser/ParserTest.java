package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/*
TODO - ? Дату публикации вакансии, нужно заносить в базу или нет?
TODO - Thread.sleep(250 ms) - хороший тон.
TODO -

*/
public class ParserTest {
    private String propPath = "./src/main/resources/app.properties";
    private String connectUlr = "https://www.sql.ru/forum/job-offers/1";
    private Properties config;
    private Connection connection;
    private static final Logger LOG = LogManager.getLogger(ParserTest.class.getName());

//    @Before
//    @Ignore
//    public void setUp() throws Exception {
//        initConfig();
//        initConnection();
//    }

    @Test
    public void testNewTry() {
        Document doc = null;
//        String elemList = null;
//        Element elemList = null;
//        Elements elemList = null;
        List<String> elemList = null;
        Elements juh = null;
        try {
            doc = Jsoup.connect(connectUlr).get();

            Element main = doc.getElementsByClass("forumTable").first();
            elemList = main.getElementsByTag("a").eachAttr("href");


            var result = elemList;
            result = result.stream()
                    .filter(link -> link.matches(".*[Jj][Aa][Vv][Aa]+.*") &&
                            !link.matches(".*[Ss][Cc][Rr][Ii][Pp][Tt]+.*"))
                    .collect(Collectors.toList());


//            doc.



        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(elemList + " - this link");

//        for (int i = 0; i < 40; i++) {
//            System.out.println(elemList.get(i));
//        }

        System.out.println(connectUlr.substring(0,36));
        System.out.println(connectUlr + 7);



    }

//    @Test
//    public void testTry() {
//        Document doc = null;
////        Element elemList = null;
//        List elemList = null;
//        Elements juh = null;
//        try {
//            doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/1")
//                    .get();
//
//            //            String temp = "td#postslisttopic"
////            String temp = "a#https://www.sql.ru/forum/1324445/senior-java-developer-minsk-3k-4-5";
////            String temp = "td#postslisttopic.Senior Java developer/ Минск / $3k $4,5";
//
////            elemList = doc.select("div#tabnews_newsc.content-tabs__items.content-tabs__items_active_true");
////            elemList = doc.select(temp);
////            elemList = doc.getElementsByClass("postslisttopic");
////            elemList = doc.getElementsByTag("a").eachAttr("href");
////            elemList = doc.getElementsByClass("forumTable");
//
////            var temp = doc.getElementsByClass("forumTable").first();
//
////            elemList = temp.getElementsByTag("a");
////            var temps = temp.getElementsByClass("postslisttopic");
////            elemList = temp.getElementsByClass("postslisttopic");
//
////            temp = temp.getElementsByClass("postslisttopic");
////            elemList = temp.getElementsByClass("postslisttopic");
//
////            var a = ((Elements) elemList).tagName("td").text();
////            elemList = List.of(a);
////            elemList = temps.getElementsByTag("a").textNodes();
//
////            var t = doc.getElementsByClass("postslisttopic").first();
////            var t = doc.getElementsByClass("postslisttopic").first();
////            var g = t.;
//
//
////            var temp = doc.getElementsByClass("forumTable");
////            var temp = doc.getElementsByClass("postslisttopic").eachAttr("a");
////            temp.
//
////            elemList = temp;
//
////            Element main = doc.getElementsByClass("forumTable").first();
//            elemList = doc.getElementsByClass("forumTable").tagName("a");
//            elemList = elemList;
//
////            List d = main.getElementsByTag("a").textNodes();
////            elemList = main.getElementsByTag("a").textNodes();
//
////            temp = temp.getElementsByClass("postslisttopic");
////            elemList = temp.getElementsByClass("postslisttopic");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        if (doc != null) {
////            for (Element element : elemList.select("a"))
////                System.out.println(element.text());
////        } else {
////            System.out.println("doc = null");
////        }
////        if (elemList != null) {
////            for (Element element : elemList.select("a"))
////                System.out.println(element.text());
////        } else {
////            System.out.println("elemList = null");
////        }
//
//
////        for (var element : elemList)
////                System.out.println(element);
//
//        for (int i = 0; i < 40; i++) {
//            System.out.println(elemList.get(i));
//        }
//
////        for (int i = 0; i < elemList.size(); i++) {
////            System.out.println(elemList.get(i));
////        }
//
//
//    }

//    private


    private void initConfig() {
        config = new Properties();
        try {
            config.load(new FileReader(new File(propPath)));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void initConnection() {
        try {
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            if (connection == null) {
                System.out.println("ParserTest - Exception: connection == null");
                LOG.warn("ParserTest - Exception: connection == null");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}




