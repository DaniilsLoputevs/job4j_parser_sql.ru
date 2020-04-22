package parser;

import java.util.Date;

/**
 * Модель данных: Вакансия.
 *
 * В базу должен быть id для каждой вкансии.
 */
public class Post {
//    id - первичный ключ

    /**
     * Имя вакансии.
     */
    private String name;

    /**
     * Текст вакансии.
     */
    private String desc;

    /**
     * Ссылка на вакансию.
     */
    private String link;

    /**
     * Дата публикации вакансии.
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

    @Override
    public String toString() {
        return "Post{"
                + "name='" + name + '\''
                + ", desc='" + desc + '\''
                + ", link='" + link + '\''
                + '}';
    }
}
