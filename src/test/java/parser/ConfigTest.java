package parser;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class ConfigTest {
    private Config config = new Config();

    @Test
    public void oStringTest() {
        var result = config.toString();
        var expected = "Config: {"
                + "jdbc.password=password, "
                + "jdbc.username=postgres, "
                + "previous.start=2020-01-01, cron.time=280, "
                + "jdbc.url=jdbc:postgresql://localhost:5432/parser_sql, "
                + "target.url=https://www.sql.ru/forum/job-offers/1}";
        assertEquals(expected, result);
    }

    @Test
    public void getValue() {
        assertEquals("postgres", config.getValue("jdbc.username"));
    }

    @Test
    public void update() {
        // 2) prepare
        var newCfg = new Config("./src/test/resources/config_test.properties");
        // 3) action
        newCfg.update();
        var result = newCfg.getValue("previous.start");
        // 4) expected
        var tmp = new GregorianCalendar();
        var expected = new SimpleDateFormat("yyy-MM-dd").format(tmp.getTime());
        // 5) compare
        assertEquals(expected, result);
    }

}