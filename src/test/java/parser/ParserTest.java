package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
TODO - Thread.sleep(250 ms) - хороший тон.
TODO - Парсиг Даты. ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
TODO - javaDoc - spelling + translate to Ang.
TODO - Тест для Parser && Main
TODO - в постПарсере ты будешь постоянно складывать значения для парсинга дат - мб лучше это в конструктор засунуть?
TODO - Переписать ВСЁ Properties на ConfigLoader
TODO -
*/
public class ParserTest {
    private String connectUlr = "https://www.sql.ru/forum/job-offers/1";
    private static final Logger LOG = LogManager.getLogger(ParserTest.class.getName());

    @Test
    public void developerRunBecauseClassIsntReady3() {
//        var test = new PreviousStart();
        // Tue Apr 07 17:04:19 BST 2020
        // 2020-04-22
//        test.updateFile("adsfdgfhsghfhdghrhdrthdrhhdrjtsehejxfgnf");
//        test.updateDate();


        var path = new File(Objects.requireNonNull(
                PreviousStart.class.getClassLoader().getResource("test.properties")).getFile()).getPath();

        System.out.println("Is file exists: " + new File(path).exists());
        System.out.println("Path to create file: " + path);

        var cLoader = new ConfigLoader2();
        var config = cLoader.load();
        cLoader.save();
//
//        System.out.println("name: " + config.getProperty("name"));
//        System.out.println("surename: " + config.getProperty("surename"));
//        System.out.println("nick: " + config.getProperty("nick"));




        try (
//                var tempIn = Files.newBufferedReader(Paths.get(path));
             Writer tempOut = Files.newBufferedWriter(Paths.get(path))

        ) {
//            var config = new Properties();

            var ls = System.lineSeparator();
//            config.load(tempIn);



//            var list = config.entrySet();
//            for (var elem : list) {
//                System.out.println(elem);
//            }

            tempOut.write("previous.start=" + config.getProperty("previous.start") + ls);
            tempOut.write("name=" + config.getProperty("name") + ls);
            tempOut.write("surename=" + config.getProperty("surename") + ls);
            tempOut.write("nick=" + config.getProperty("nick") + ls);
            tempOut.write("target=" + config.getProperty("target") + ls);

            tempOut.flush();

//            config.setProperty("name", "www");
//            config.setProperty("previous.start", "2020-04-22");
//            config.setProperty("cron.time", "0 12 0 0 0");

//            config.store(tempOut,"");


//            config.store(tempOut,
//                    "name=test" + ls
//                            + "previous.start=2020-04-22" + ls
//                            + "cron.time=0 12 0 0 0"  + ls);


//            tempOut.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void developerRunBecauseClassIsntReady2() {
        var text = new PostParser("https://www.sql.ru/forum/1324373/java-razrabotchik-krasnodar-moskva-90-000-150-000-net")
//                .convertDate("7 апр 20,");
                .convertDate("сегодня,");

        System.out.println("14 апр 20 === 7 апр 20");
        System.out.println(text);
//        System.out.println("07-04-2020");
        System.out.println("Tue Apr 07 17:04:19 BST 2020");
//        System.out.println("Wed Apr 07 16:56:58 GMT 1");
        // Tue Apr 14 00:00:00 BST 2020
        // Tue Apr 14 00:00:00 BST 2020

    }


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