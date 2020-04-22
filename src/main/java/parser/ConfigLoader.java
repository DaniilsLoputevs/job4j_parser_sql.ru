package parser;
// МУСОР - СТАРАЯ ВЕРСИЯ, СОЖЕТ БУДЕТ ИНТЕРЕСНО ГЛЯНУТЬ!

/**
 * Читаем файл конфигурации.
 * сохранить "Настройки" во внутреннюю map<String, String>.
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 22.04.20.
 */
public class ConfigLoader {
//    private String path;
//    private Map<String, String> values = new HashMap<>();
//    private static final Logger LOG = LogManager.getLogger(ConfigLoader.class.getName());
//
//    public ConfigLoader() {
//        this.path = Objects.requireNonNull(
//                PreviousStart.class.getClassLoader().getResource("test.properties")).getFile();
//        load();
//    }
//
//    /**
//     * Загружает настройки из файла по пути path(path initialize. через конструктор).
//     * Фильтруем через stream от комментариев и пустых строк.
//     */
//    public void load() {
//        List<String> fileLines = this.readFile();
//        values.putAll(fileLines.stream()
//                .filter(line -> !line.startsWith("//"))
//                .filter(line -> !line.startsWith("#"))
//                .filter(line -> !line.startsWith(" "))
//                .filter(line -> line.contains("="))
//                .collect(Collectors.toMap(
//                        line -> line.substring(0, line.indexOf("=")),
//                        line -> line.substring(line.indexOf("=") + 1))));
//    }
//
//    /**
//     * Получить значение настроек по названию.
//     *
//     * @param key - название.
//     * @return value.
//     */
//    public String value(String key) {
//        if (values.isEmpty()) {
//            throw new UnsupportedOperationException("Config is empty || Wrong path");
//        }
//        return values.get(key);
//    }
//
//    public void updateFile() {
//        var temp = new GregorianCalendar();  //   dd-MM-yyyy
//        var date = new SimpleDateFormat("yyy-MM-dd").format(temp.getTime());
//
//        System.out.println("date: " + date);
//
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
//            writer.write(newDate);
//        } catch (IOException e) {
//            LOG.error(e.getMessage(), e);
//        }
//
//
//    }
//
//
//
//
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
//
//    @Override
//    public String toString() {
//        StringJoiner out = new StringJoiner(System.lineSeparator());
//        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
//            read.lines().forEach(out::add);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return out.toString();
//    }

}