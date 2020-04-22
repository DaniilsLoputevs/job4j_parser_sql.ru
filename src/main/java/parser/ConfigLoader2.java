package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Properties;
import java.util.StringJoiner;

public class ConfigLoader2 {
    private String path;
    private Properties config;
    private static final Logger LOG = LogManager.getLogger(ConfigLoader.class.getName());

    public ConfigLoader2() {
        this.path = new File(Objects.requireNonNull(
                PreviousStart.class.getClassLoader().getResource("test.properties")).getFile()).getPath();

        this.config = new Properties();
    }

    public Properties load() {
        Properties result = null;
        try (var tempIn = Files.newBufferedReader(Paths.get(path))) {
            config.load(tempIn);
            result = config;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public void save() {
        try (Writer tempOut = Files.newBufferedWriter(Paths.get(path))) {
//            new Properties().store(tempOut, "App propirties");
            var ls = System.lineSeparator();
            config.setProperty("previous.start", getCurrentDate());

//            System.out.println("+++previous.start=" + config.getProperty("previous.start"));

            tempOut.write("previous.start=" + config.getProperty("previous.start") + ls);
            tempOut.write("name=" + config.getProperty("name") + ls);
            tempOut.write("surename=" + config.getProperty("surename") + ls);
            tempOut.write("nick=" + config.getProperty("nick") + ls);
            tempOut.write("target=" + config.getProperty("target") + ls);

            tempOut.flush();
        } catch (IOException e) {
            LOG.error("Save exception", e);
        }
    }

    private String getCurrentDate() {
        var rsl = new GregorianCalendar();
        return new SimpleDateFormat("yyy-MM-dd").format(rsl.getTime());
    }


//    public void init() {
//        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app.properties")) {
//            values.load(in);
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        }
//    }
//
//    public String get(String key) {
//        return this.values.getProperty(key);
//    }
//
//    public ConfigLoader2() {
//        this.path = Objects.requireNonNull(
//                PreviousStart.class.getClassLoader().getResource("test.properties")).getFile();
//        config.load();
//        load();
//    }
//
//    /**
//     * Загружает настройки из файла по пути path(path initialize. через конструктор).
//     * Фильтруем через stream от комментариев и пустых строк.
//     */


    /**
     * Получить значение настроек по названию.
     *
//     * @param key - название.
     * @return value.
     */
    public String value(String key) {
        if (this.config.isEmpty()) {
            throw new UnsupportedOperationException("Config is empty || Wrong path");
        }
        return this.config.getProperty(key);
    }

//    public void updateFile() {
//        var temp = new GregorianCalendar();  //   dd-MM-yyyy
//        var date = new SimpleDateFormat("yyy-MM-dd").format(temp.getTime());
//
//        System.out.println("date: " + date);


//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
//            writer.write(newDate);
//        } catch (IOException e) {
//            LOG.error(e.getMessage(), e);
//        }


//    }




//    public List<String> readFile() {
//        List<String> fileLines = null;
//        try (var bufferedReader = new BufferedReader(new FileReader(this.path))) {
//            fileLines = bufferedReader.lines().collect(Collectors.toList());
//        } catch (IOException e) {
//            System.out.println("IOException: IOHelper - read File to List!");
//            e.printStackTrace();
//        }
//        return fileLines;
//    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
