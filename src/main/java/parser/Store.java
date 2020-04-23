package parser;

import java.util.List;
import java.util.function.Predicate;

/**
 * Interface describe work with DB.
 *
 * @author Daniils Loputevs (laiwiense@gmail.com)
 * @version $Id$
 * @since 23.04.20.
 */
public interface Store {
    /**
     * Save post in DB.
     *
     * @param post - {@code Post}.
     */
    void save(Post post);

    /**
     * Save {@code List<Post>} in DB.
     * * list.forEach(this::save); - iterate save()
     *
     * @param list - {@code List<Post>}
     */
    void saveAll(List<Post> list);

    /**
     * Load {@code Post} from DB, by filter.
     *
     * @param filter - Predicate<Post> - filter like sql query.
     * @return - {@code List<Post>} .
     */
    List<Post> get(Predicate<Post> filter);
}