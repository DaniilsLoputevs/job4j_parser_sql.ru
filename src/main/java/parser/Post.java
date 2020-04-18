package parser;

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

    public Post(String name, String desc, String link) {
        this.name = name;
        this.desc = desc;
        this.link = link;
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

    @Override
    public String toString() {
        return "Post{"
                + "name='" + name + '\''
                + ", desc='" + desc + '\''
                + ", link='" + link + '\''
                + '}';
    }
}
