package parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *  Realize interface {@code Store} - work with PostgreSQL.
 *
 * @author Daniils Loputevs (laiwiense@gmail.com)
 * @version $Id$
 * @since 23.04.20.
 */
public class PostgreSqlStore implements Store {
    private Connection connection;
    private static final Logger LOG = LoggerFactory.getLogger(PostgreSqlStore.class);


    public PostgreSqlStore(Connection connection) {
        this.connection = connection;
//        createTableIfNotExits();
    }

    @Override
    public void save(Post post) {
        try (var st = this.connection.prepareStatement(
                "insert into posts(name, text, link) values(?, ?, ?) "
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
                            rs.getString("name"),
                            rs.getString("text"),
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

//    private void createTableIfNotExits() {
//        try {
//            var st = this.connection.createStatement();
//            st.execute("create table if not exists posts ("
//                    + "id         serial primary key,"
//                    + "name       varchar(200),"
//                    + "text       text,"
//                    + "link       varchar(100) UNIQUE);");
//        } catch (SQLException e) {
//            LOG.error(e.getMessage(), e);
//        }
//    }

}