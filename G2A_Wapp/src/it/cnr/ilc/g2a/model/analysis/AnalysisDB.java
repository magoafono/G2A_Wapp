package it.cnr.ilc.g2a.model.analysis;

import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnalysisDB {

    private static final Logger log = LogManager.getLogger("AnalysisDB");

    private HashMap<String, BaseAnalysis> analisysDB;

    /**
     * @return the analisysDB
     */
    public HashMap<String, BaseAnalysis> getAnalisysDB() {
        return analisysDB;
    }

    /**
     * @param analisysDB the analisysDB to set
     */
    public void setAnalisysDB(HashMap<String, BaseAnalysis> analisysDB) {
        this.analisysDB = analisysDB;
    }

    public void insert(String key, BaseAnalysis value) {

        if (null != analisysDB) {
            analisysDB.put(key, value);
        } else {
            log.error("analisysDB in null!");
        }
    }

}
