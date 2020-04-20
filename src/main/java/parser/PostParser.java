package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostParser {

    private final Map<String, String> dateMap = new HashMap<>();
    private Document postDoc;

    public PostParser(String link) {
        dateMap.put("янв", "01");
        dateMap.put("фев", "02");
        dateMap.put("мар", "03");
        dateMap.put("апр", "04");
        dateMap.put("май", "05");
        dateMap.put("июн", "06");
        dateMap.put("июл", "07");
        dateMap.put("авг", "08");
        dateMap.put("сен", "09");
        dateMap.put("окт", "10");
        dateMap.put("ноя", "11");
        dateMap.put("дек", "12");
        parsePost(link);
    }

    private void parsePost(String link) {
        try {
            this.postDoc = Jsoup.connect(link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertDate(String date) {
        return dateMap.get(date);
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

    public String parseDate() {
        //just copy from previous method
//        var temp1 = postDoc.getElementsByClass("msgTable").first();
//        return temp1.getElementsByClass("msgFooter").tagName("td").text();


        return null;
    }
}
