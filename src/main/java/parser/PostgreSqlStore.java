package parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *  Realize interface {@code Store} - work with PostgreSQL.
 *
 * @author Daniils Loputevs (laiwiense@gmail.com)
 * @version $Id$
 * @since 04.05.20.
 */
public class PostgreSqlStore implements Store {
    private final Connection connection;
    private static final Logger LOG = LoggerFactory.getLogger(PostgreSqlStore.class);

    public PostgreSqlStore() {
        this.connection = initConnectionDefault();
    }

    public PostgreSqlStore(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Post post) {
        try (var st = this.connection.prepareStatement(
                "insert into posts(post_name, post_text, link) values(?, ?, ?) "
                        + "on conflict (link) do nothing;")) {
            st.setString(1, post.getName());
            st.setString(2, post.getDesc());
            st.setString(3, post.getLink());
            st.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void saveAll(List<Post> list) {
        list.forEach(this::save);
    }

    @Override
    public List<Post> get(Predicate<Post> filter) {
        List<Post> result = new ArrayList<>();
        try (var st = this.connection.prepareStatement("select * from posts;")) {
            try (var rs = st.executeQuery()) {

                while (rs.next()) {
                    var temp = new Post(
                            rs.getString("post_name"),
                            rs.getString("post_text"),
                            rs.getString("link")
                    );
                    if (filter.test(temp)) {
                        result.add(temp);
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    private Connection initConnectionDefault() {
        var config = new Config();
        Connection result = null;
        try {
            result = DriverManager.getConnection(
                    config.getValue("jdbc.url"),
                    config.getValue("jdbc.username"),
                    config.getValue("jdbc.password")
            );
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

}