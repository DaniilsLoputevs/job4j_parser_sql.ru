package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * Parse post-page to {@code Post}.
 *
 * @author Daniils Loputevs (laiwiense@gmail.com)
 * @version $Id$
 * @since 23.04.20.
 */
public class PostParser {
    private final Map<String, Integer> dateMap = new HashMap<>();
    private Document postDoc;
    private static final Logger LOG = LoggerFactory.getLogger(PostParser.class);

    public PostParser(String link) {
        dateMap.put("янв", 0);
        dateMap.put("фев", 1);
        dateMap.put("мар", 2);
        dateMap.put("апр", 3);
        dateMap.put("май", 4);
        dateMap.put("июн", 5);
        dateMap.put("июл", 6);
        dateMap.put("авг", 7);
        dateMap.put("сен", 8);
        dateMap.put("окт", 9);
        dateMap.put("ноя", 10);
        dateMap.put("дек", 11);
        connectTo(link);
    }

    private void connectTo(String link) {
        try {
            this.postDoc = Jsoup.connect(link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Get name for {@code Post}.
     *
     * @return - Name from post-page.
     */
    public String parseName() {
        var temp = postDoc.getElementsByClass("messageHeader").first();
        return temp.getElementsByTag("td").text();
    }

    /**
     * Get describe for {@code Post}.
     *
     * @return - describe from post-page.
     */
    public String parseDesc() {
        var temp1 = postDoc.getElementsByClass("msgTable").first();
        return temp1.getElementsByClass("msgBody").next().text();
    }

    /**
     * Get link for {@code Post}.
     *
     * @return - link from post-page.
     */
    public String parseLink() {
        return postDoc.location();
    }

    /**
     * Get {@code Date} for {@code Post}.
     * * Special for Parser.list(...).
     *
     * @return - {@code Date} from post-page.
     */
    public Date parseDate() {
        var temp1 = postDoc.getElementsByClass("msgTable").first();
        var temp2 = temp1.getElementsByClass("msgFooter").tagName("td").text();
        return convertDate(temp2);
    }

    /**
     * Convert date from post-page in normal format.
     *
     * @param complexDate - date from post-page.
     * @return - date in normal format.
     */
    private Date convertDate(String complexDate) {
        var tmp = complexDate.substring(0, complexDate.indexOf(","));
        String[] splitDate = tmp.split(" ");
        Date result = null;
//        String result = null;

        if (splitDate.length == 1) {
            var cal = Calendar.getInstance();

            if ("вчера".equals(splitDate[0])) {
                cal.add(Calendar.DATE, -1);
                result = cal.getTime();
            }
            if ("сегодня".equals(splitDate[0])) {
                result = cal.getTime();
            }

        } else {

            var year = 2000 + Integer.parseInt(splitDate[2]);  // set year
            var month = this.dateMap.get(splitDate[1]);      // set month
            var day = Integer.parseInt(splitDate[0]);        // set day

            var calendar = new GregorianCalendar();
            calendar.set(year, month, day);
            result = calendar.getTime();
//            result = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        }
        return result;
    }

}
