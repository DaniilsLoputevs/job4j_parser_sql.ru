package parser;

import java.util.List;

/**
 * Describe take and parse data from URL{@code String}.
 *
 * @author Daniils Loputevs (laiwiense@gmail.com)
 * @version $Id$
 * @since 23.04.20.
 */
public interface Parse {
    /**
     * Parse forum-page to {@code List<Post>} with correct posts.
     *
     * @param link - forum-page url.
     * @return - {@code List} with filtered posts.
     */
    List<Post> list(String link);

    /**
     * Parse post-page to {@code Post}.
     *
     * @param link - post-pag url.
     * @return - {@code Post}
     */
    Post detail(String link);
}