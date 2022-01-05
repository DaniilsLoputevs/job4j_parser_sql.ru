package parser;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Data Model: Post from: https://www.sql.ru/forum/job-offers/1
 *
 * @author Daniils Loputevs (laiwiense@gmail.com)
 * @version $Id$
 * @since 23.04.20.
 */
public class Post {

    /**
     * Post's name.
     */
    private String name;

    /**
     * Post's context/content/description.
     */
    private String desc;

    /**
     * Post's link.
     */
    private String link;

    /**
     * Post's publications date.
     * * Need for {@code Parser} - compare last start date and this date.
     */
    private Date date;


    public Post(String name, String desc, String link) {
        this.name = name;
        this.desc = desc;
        this.link = link;
    }

    public Post(String name, String desc, String link, Date date) {
        this.name = name;
        this.desc = desc;
        this.link = link;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getLink() {
        return link;
    }

    public Date getDate() {
        return date;
    }

//    @Override
//    public String toString() {
//        return "Post{"
//                + "name='" + name + '\''
//                + ", desc='" + desc + '\''
//                + ", link='" + link + '\''
//                + '}';
//    }
    
    @Override
    public String toString() {
        return System.lineSeparator()
                + "link = " + link + System.lineSeparator()
                + "name = " + name + System.lineSeparator()
                + "desc = " + desc + System.lineSeparator()
                + "date = " + sdf.format(date);
    }
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
}
