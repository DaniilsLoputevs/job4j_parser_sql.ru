package parser;
// МУСОР - СТАРАЯ ВЕРСИЯ, СОЖЕТ БУДЕТ ИНТЕРЕСНО ГЛЯНУТЬ!

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

public class PreviousStart {
    private String file;

    public PreviousStart() {
        this.file = Objects.requireNonNull(
                PreviousStart.class.getClassLoader().getResource("test.properties")).getFile();
    }

    private static final Logger LOG = LogManager.getLogger(PreviousStart.class.getName());

//    public Date getDate() {
//        var temp = readFile().split(" ");
//        var result = new Date()

//        var rsl = new GregorianCalendar();
        //       year           month         day
//        rsl.set(temp[1], datePart[1], datePart[2]);
//        result = rsl.getTime();

//        return null;
//    }

    public void updateDate() {
        var temp = new GregorianCalendar();  //   dd-MM-yyyy
        var date = new SimpleDateFormat("yyy-MM-dd").format(temp.getTime());


        System.out.println("date: " + date);
    }

    /**
     *
     * Читает файл с датой последнего запуска и возвращает её.
     */
    // private
//    public String readFile() {
//        var result = "";
//        try (var bufferedReader = new BufferedReader(new FileReader(file))) {
//            result = bufferedReader.lines().collect(Collectors.toList()).get(0);
//        } catch (IOException e) {
//            LOG.error(e.getMessage(), e);
//        }
//        return result;
//    }

    // private
//    public void updateFile(String newDate) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
//            writer.write(newDate);
//        } catch (IOException e) {
//            LOG.error(e.getMessage(), e);
//        }
//    }

}