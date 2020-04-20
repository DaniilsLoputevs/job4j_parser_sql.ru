package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import parser.connection.ConnectionRollback;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PostgreSqlStoreTest {
    private String propPath = "./src/main/resources/app.properties";
    private Properties config;
    private Connection connection;

    private Store store;
    private static final Logger LOG = LogManager.getLogger(Store.class.getName());

    @Before
    public void setUp() {
        initConnection();
        this.store = new PostgreSqlStore(this.connection);
    }

    @Test
    public void testSaveAndGet() {
        var list = List.of(
                new Post("job1", "111", "www.link1.ru"),
                new Post("job2", "222", "www.link2.ru"),
                new Post("job3", "333", "www.link3.ru")
        );
        list.forEach(store::save);

        var r1 = store.get(x -> x.getName().length() > 0);
        assertEquals(3, r1.size());

        var r2 = store.get(x -> x.getDesc().equals("111"));
        assertEquals(1, r2.size());

        var r3 = store.get(x -> x.getLink().contains("link3"));
        assertTrue(r3.size() > 0);
    }

    private void initConnection() {
        try {
            this.config = new Properties();
            this.config.load(new FileReader(new File(propPath)));

            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            // just make a comment line below if you don't need a rollback connection
            this.connection = ConnectionRollback.create(connection);

            this.store = new PostgreSqlStore(connection);

        } catch (SQLException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}