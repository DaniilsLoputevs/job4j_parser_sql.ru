package parser;

import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс осуществляет связь с базой Postgresql.
 */
public interface Store {
    /**
     * Сохраняет объявление в базе.
     *
     * @param post - Вакансия.
     */
    void save(Post post);

    /**
     * Сохраняет список Вакансий.
     * list.forEach(this::save); - Итерарот save()
     *
     * @param list - {@code List} Вакансий.
     */
    void saveAll(List<Post> list);

    /**
     * Извлекает список вакансий из базы, по подходящему услоию.
     *
     * @param filter - условие поиска.
     * @return - {@code List} результат поиска.
     */
    List<Post> get(Predicate<Post> filter);
}