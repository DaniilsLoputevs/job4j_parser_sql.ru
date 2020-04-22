package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

public class PostParser {

    private final Map<String, Integer> dateMap = new HashMap<>();
    private Document postDoc;

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
        parsePost(link);
    }

    private void parsePost(String link) {
        try {
            this.postDoc = Jsoup.connect(link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String parseName() {
        var temp = postDoc.getElementsByClass("messageHeader").first();
        return temp.getElementsByTag("td").text();
    }

    public String parseDesc() {
        var temp1 = postDoc.getElementsByClass("msgTable").first();
        return temp1.getElementsByClass("msgBody").next().text();
    }

    public String parseLink() {
        return postDoc.location();
    }

    public Date parseDate() {
        var temp1 = postDoc.getElementsByClass("msgTable").first();
        var temp2 = temp1.getElementsByClass("msgFooter").tagName("td").text();
        return convertDate(temp2);
    }

    public Date convertDate(String complexDate) {
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
            int[] datePart = new int[3];

            datePart[0] = 2000 + Integer.valueOf(splitDate[2]);  // set year
            datePart[1] = this.dateMap.get(splitDate[1]);  // set month
            datePart[2] = Integer.valueOf(splitDate[0]);  // set day

            var rsl = new GregorianCalendar();
            //       year           month         day
            rsl.set(datePart[0], datePart[1], datePart[2]);
            result = rsl.getTime();
//            result = new SimpleDateFormat("yyyy-MM-dd").format(rsl.getTime());
        }

        return result;
    }

}
