package parser;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParserTest {
    private String forumUrl = "https://www.sql.ru/forum/job-offers/1";
    private String pageUrl = "https://www.sql.ru/forum/1324373/java-razrabotchik-krasnodar-moskva-90-000-150-000-net";
    private Parser parser = new Parser();

    private static final Logger LOG = LoggerFactory.getLogger(ParserTest.class);


    @Test
    public void list() {
        var result = parser.list(forumUrl);

//        System.out.println("Start Run ####");
//        System.out.println("Result size: " + result.size());
//
//        System.out.println("Finish Run ####");
        assertTrue(result.size() >= 24);
    }

    @Test
    public void detail() {
        var result = parser.detail(pageUrl);
        var expectedName = "Java-разработчик Краснодар, Москва 90 000 - 150 000 net";
        var expectedDesc = "Знание Java 8+; ";
        var expectedLink = pageUrl;
        var expectedDate = "Tue Apr 14";

        assertTrue(result.getName().contains(expectedName));
        assertTrue(result.getDesc().contains(expectedDesc));
        assertEquals(result.getLink(), expectedLink);
        assertTrue(result.getDate().toString().contains(expectedDate));
    }
}