package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Realize interface {@code Parse} - Parse web-page: https://www.sql.ru/forum/job-offers/1
 *
 * @author Daniils Loputevs (laiwiense@gmail.com)
 * @version $Id$
 * @since 04.05.20.
 */
public class Parser implements Parse {

    private static final Logger LOG = LoggerFactory.getLogger(Parser.class);


    /**
     * add all posts in result, while last start is BEFORE that post's date.
     *
     * @param link - forum-page url.
     * @return - {@code List} with filtered posts.
     */
    @Override
    public List<Post> list(String link) {
        List<Post> result = new LinkedList<>();
        try {
            link = link.substring(0, 36);
            int pageNum = 1;
            var lastStart = getCurrentDate();
            while (true) {
                var newLink = link + pageNum++;  // iterate by forum pages.
                Document doc = Jsoup.connect(newLink).get();
                for (var postLink : listOfPostsLinks(doc)) {
                    Post currentPost = detail(postLink);
                    if (lastStart.after(currentPost.getDate())) {
                       return result;
                    }
                    result.add(currentPost);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Post detail(String link) {
        var parser = new PostParser(link);
        return new Post(
                parser.parseName(),
                parser.parseDesc(),
                parser.parseLink(),
                parser.parseDate()
        );
    }


    /**
     * Collect all post-links from forum-page in {@code List} by link-filter.
     * * filter described in {@code this.filterLinks() }
     *
     * @param doc - {@code Document} HTML forum-page.
     * @return - {@code List} with all post-links.
     */
    private List<String> listOfPostsLinks(Document doc) {
        var tempTable = doc.getElementsByClass("forumTable").first();
        List<String> tempList = tempTable.getElementsByTag("a").eachAttr("href");
        return this.filterLinks(tempList);
    }

    /**
     * filter post-links by matches in name.
     * * Special for {@code listOfPostsLinks() }
     *
     * @param postsLinks - {@code List} with all post-links.
     * @return - {@code List} with correct post-links.
     */
    private List<String> filterLinks(List<String> postsLinks) {
        return postsLinks.stream()
                .filter(link -> link.matches(".*[Jj][Aa][Vv][Aa]+.*")
                        && !link.matches(".*[Ss][Cc][Rr][Ii][Pp][Tt]+.*"))
                .collect(Collectors.toList());
    }


    /**
     * Return date of last program start.
     *
     * @return - Actual date. If it's a first start, - return 1 January Current Year.
     */
    private Date getCurrentDate() {
        var cfgTime = new Config().getValue("previous.start");
        Date result;
        var calendar = new GregorianCalendar();
        if ("".equals(cfgTime)) {
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            calendar.set(Calendar.DATE, 1);
        } else {
            var splitTime = cfgTime.split("-");
            calendar.set(Calendar.YEAR, Integer.parseInt(splitTime[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(splitTime[1]) - 1);
            calendar.set(Calendar.DATE, Integer.parseInt(splitTime[2]));
        }
        calendar.set(Calendar.HOUR, -12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        result = calendar.getTime();
        return result;
    }

}