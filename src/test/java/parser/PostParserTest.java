package parser;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PostParserTest {
    private PostParser parser;
    private String postUrl = "https://www.sql.ru/forum/1324373/java-razrabotchik-krasnodar-moskva-90-000-150-000-net";

    @Before
    public void setUp() {
        parser = new PostParser(postUrl);
    }

    @Test
    public void parseName() {
        var result = parser.parseName();
        var expected = "Java-разработчик Краснодар, Москва 90 000 - 150 000 net [new]";
        assertEquals(expected, result);
    }

    @Test
    public void parseDesc() {
        var result = parser.parseDesc();
        var expected = "Знание Java 8+; ";
        assertTrue(result.contains(expected));
    }

    @Test
    public void parseLink() {
        var result = parser.parseLink();
        var expected = postUrl;
        assertEquals(expected, result);
    }

    @Test
    public void parseDate() {
        var result = parser.parseDate();
        var expected = "14 апр 20, 19:44  ";
        assertEquals(expected, result);
    }


}