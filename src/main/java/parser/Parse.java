package parser;

import java.util.List;

/**
 * Описывает извлечения данных с сайта.
 *
 * Этот компонент позволяет собрать короткое описание всех вакансий,
 * а так же загрузить детали по каждому объявлению.
 */
public interface Parse {
    /**
     * Загружает список вакансий по ссылке.
     * ВАЖНО: url на список вакансий.
     *
     * @param link - url.
     * @return - {@code List} результат чтения из источника.
     */
    List<Post> list(String link);

    /**
     * Загружает детали вакансий по ссылке.
     * ВАЖНО: url на вакансию.
     *
     * @param link - url.
     * @return - вакансия.
     */
    Post detail(String link);
}