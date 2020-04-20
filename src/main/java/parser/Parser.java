package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Parser implements Parse {

    private static final Logger LOG = LogManager.getLogger(Parser.class.getName());
    /*
       catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            }
     */


    @Override
    public List<Post> list(String link) {
        List<Post> result = null;
        link = link.substring(0,36);
        try {
            Document doc = Jsoup.connect(link).get();


            List<String> postsLinks = listOfPostsLinks(doc);
            var run = true;
            int pageNum = 1;


            while (run) {
                link = link + pageNum++;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Post detail(String link) {
        return null;
    }

//    private List<String> listOfAllLinks(Document doc) {
//
//    }

    private List<String> listOfPostsLinks(Document doc) {
        var tempTable = doc.getElementsByClass("forumTable").first();
        var tempList = tempTable.getElementsByTag("a").eachAttr("href");
        return linksFilter(tempList);
    }

    private List<String> linksFilter(List<String> postsLinks) {
        return postsLinks.stream()
                .filter(link -> link.matches(".*[Jj][Aa][Vv][Aa]+.*") &&
                        !link.matches(".*[Ss][Cc][Rr][Ii][Pp][Tt]+.*"))
                .collect(Collectors.toList());
    }


}
