package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Parser implements Parse {

    private static final Logger LOG = LogManager.getLogger(Parser.class.getName());
    /*
       catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            }
     */


    @Override
    public List<Post> list(String link) {
        return null;
    }

    @Override
    public Post detail(String link) {
        return null;
    }
}
