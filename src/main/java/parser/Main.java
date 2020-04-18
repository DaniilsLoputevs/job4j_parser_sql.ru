package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class Main {
    private Properties config = new Properties();
    private Connection connection;
    private static final Logger LOG = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        var main = new Main();
        main.initConfig();
        Parse parser = new Parser();
        var temp = parser.list(main.config.getProperty("targeturl"));
        main.initConnection();
        Store store = new PostgreSqlStore(main.connection);
        store.saveAll(temp);

    }

    private void initConfig() {
        try {
            var temp = Objects.requireNonNull(
                    Main.class.getClassLoader().getResource("app.properties")).getFile();
            config.load(new FileReader(temp));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void initConnection() {
        try {
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
