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
        try {
            link = link.substring(0, 36);
            var run = true;
            int pageNum = 1;

            while (run) {
                // iterate forum pages
                var newLink = link + pageNum++;
                Document doc = Jsoup.connect(newLink).get();
                List<String> postsLinks = listOfPostsLinks(doc);

//                postsLinks.stream().flatMap(postLink -> postLink)


                for (var postLink : postsLinks) {
                    var temp = detail(postLink);
                    // if date less that need >> break from forEach && brake from while
                    // else add temp in result
//                    if (temp.getDate() >)
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
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
     * Даёт {@code List} всех ссылок на вакансии, на странице форума.
     * Фильтрует только вакансии подходящие под фильтр. filterLinks() - метод фильтрации.
     *
     * @param doc - Документ(Страница форума).
     * @return - Список ссылок что прошли фильтр.
     */
    private List<String> listOfPostsLinks(Document doc) {
        var tempTable = doc.getElementsByClass("forumTable").first();
        List<String> tempList = tempTable.getElementsByTag("a").eachAttr("href");
        return filterLinks(tempList);
    }

    /**
     * Метод фильтрации ссылок на вакансии. Специально для listOfPostsLinks(...)
     *
     * @param postsLinks - Список ссылок для фильтра.
     * @return Прошедшие фильтр.
     */
    private List<String> filterLinks(List<String> postsLinks) {
        return postsLinks.stream()
                .filter(link -> link.matches(".*[Jj][Aa][Vv][Aa]+.*")
                        && !link.matches(".*[Ss][Cc][Rr][Ii][Pp][Tt]+.*"))
                .collect(Collectors.toList());
    }

}