package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/*
TODO - ? Дату публикации вакансии, нужно заносить в базу или нет?
TODO - Thread.sleep(250 ms) - хороший тон.
TODO -

*/
public class ParserTest {
    private String connectUlr = "https://www.sql.ru/forum/job-offers/1";
    private static final Logger LOG = LogManager.getLogger(ParserTest.class.getName());


    @Test
    public void developerRunBecauseClassIsntReady() {
        Document doc = null;
//        String elemList = null;
//        Element elemList = null;
//        Elements elemList = null;
        List<String> elemList = null;
        try {
            doc = Jsoup.connect(connectUlr).get();

            Element main = doc.getElementsByClass("forumTable").first();
            elemList = main.getElementsByTag("a").eachAttr("href");


            var result = elemList;
            result = result.stream()
                    .filter(link -> link.matches(".*[Jj][Aa][Vv][Aa]+.*")
                            && !link.matches(".*[Ss][Cc][Rr][Ii][Pp][Tt]+.*"))
                    .collect(Collectors.toList());


            System.out.println(doc.location());



        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(elemList + " - this link");

//        for (int i = 0; i < 40; i++) {
//            System.out.println(elemList.get(i));
//        }

        int a = 0;
        for (String anElemList : elemList) {
//            System.out.println(anElemList);
            a++;
            if (a == 2) {
                break;
            }

        }

//        System.out.println(connectUlr.substring(0,36));
//        System.out.println(connectUlr + 7);

        int pageNum = 1;
        var link = "link: ";


        for (int i = 0; i < 3; i++) {
            var newLink = link + pageNum++;
            System.out.println(newLink);
        }



    }

}