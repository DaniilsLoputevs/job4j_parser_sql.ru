package parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Properties;

/**
 * Describe and work with all app properties.
 *
 * @author Daniils Loputevs (laiwiense@gmail.com)
 * @version $Id$
 * @since 04.05.20.
 */
public class Config {
    private String path;
    private Properties config;

    private static final Logger LOG = LoggerFactory.getLogger(Config.class);


    public Config() {
        this.path = new File(Objects.requireNonNull(
                Config.class.getClassLoader().getResource("app.properties")).getFile()).getPath();
        this.config = new Properties();
        load();
    }

    public Config(String path) {
        this.path = path;
        this.config = new Properties();
        load();
    }

    /**
     * Load properties from config file.
     * Path to config file init.
     */
    private void load() {
        try (var tempIn = Files.newBufferedReader(Paths.get(path))) {
            config.load(tempIn);
            if (config.isEmpty()) {
                throw new RuntimeException();
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Update config file.
     * Upd: previous.start = today date.
     */
    public void update() {
        try (Writer tempOut = Files.newBufferedWriter(Paths.get(path))) {
            var ls = System.lineSeparator();
            config.setProperty("previous.start", getCurrentDate());
            tempOut.write("jdbc.url=" + config.getProperty("jdbc.url") + ls);
            tempOut.write("jdbc.username=" + config.getProperty("jdbc.username") + ls);
            tempOut.write("jdbc.password=" + config.getProperty("jdbc.password") + ls);
            tempOut.write("target.url=" + config.getProperty("target.url") + ls);
            tempOut.write("cron.time=" + config.getProperty("cron.time") + ls);
            tempOut.write("previous.start=" + config.getProperty("previous.start") + ls);
            tempOut.flush();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Get values by properties name.
     *
     * @param key - properties name for search.
     * @return - properties value.
     */
    public String getValue(String key) {
        if (this.config.isEmpty()) {
            throw new UnsupportedOperationException("Config is empty || Wrong path");
        }
        return this.config.getProperty(key);
    }

    /**
     * Return today date as {@code String} in format "yyy-MM-dd".
     * * Special to method Parser.list(...)
     *
     * @return - Today date.
     */
    private String getCurrentDate() {
        var rsl = new GregorianCalendar();
        return new SimpleDateFormat("yyy-MM-dd").format(rsl.getTime());
    }


    @Override
    public String toString() {
        return "Config: " + config;
    }
}
