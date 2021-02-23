package it.cnr.ilc.g2a.action.userbean;

import it.cnr.ilc.g2a.action.management.RepositoryBean;
import it.cnr.ilc.g2a.model.view.ResultViewEntity;
import it.cnr.ilc.g2a.model.view.TokenViewEntity;
import it.cnr.ilc.g2a.utils.Consts;
import it.cnr.ilc.g2a.utils.Utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class SearchViewBean implements Serializable {

    private static final Logger log = LogManager.getLogger("SearchViewBean");

    /**
     *
     */
    private static final long serialVersionUID = 5106285848810682454L;

    @ManagedProperty(value = "#{repository}")
    private RepositoryBean repositoryBean;

    @ManagedProperty(value = "#{sharedBean}")
    private SharedBean sharedBean;

    //Greek input
    private SearchBean grSb = null;

    //Arabic Input
    private SearchBean arSb = null;

    private String combinedOperation = null;

    private List<Pair> grItemType;
    private List<Pair> arItemType;

    private List<SelectItem> grItemPos;

    private List<SelectItem> arItemPos;

    //Result
    List<ResultViewEntity> searchResult = null;

    //CVS Result
    StreamedContent downloadableResult = null;

    /**
     * @return the file
     */
    public StreamedContent getDownloadableResult() {
        return downloadableResult;
    }

    /**
     * @param file the file to set
     */
    public void setDownloadableResult(StreamedContent file) {
        this.downloadableResult = file;
    }

    @PostConstruct
    public void init() {
        //numberOfentries = new ArrayList<Integer>(Arrays.asList(1,2,3));
        setGrSb(new SearchBean());
        setArSb(new SearchBean());
        grItemPos = new ArrayList<SelectItem>();
        /* adj		adv		art		art_pron		conj		conj_adj		conj_adv		conj_partic		n		noun
		 * 		numeral		partic		prep		pron		pron_partic		punct		verb		*/
        SelectItemGroup grAnyGroup = new SelectItemGroup("Any Pos");
        grAnyGroup.setSelectItems(new SelectItem[]{new SelectItem("", "Any")});

        SelectItemGroup grAllGroup = new SelectItemGroup("ALL");
        grAllGroup.setSelectItems(new SelectItem[]{new SelectItem("adj", "ADJECTIVE"), new SelectItem("adv", "ADVERB"),
            new SelectItem("art", "ARTICLE"), new SelectItem("art_pron", "Art_Pron"), new SelectItem("conj", "CONJUNCTION"),
            new SelectItem("conj_adj", "CONJUNCTION ADJECTIVE"), new SelectItem("conj_adv", "CONJUNCTION ADVERB"), new SelectItem("conj_partic", "CONJUNCTION PARTICLE"),
            new SelectItem("n", "N"), new SelectItem("noun", "NOUN"), new SelectItem("numeral", "NUMERAL"),
            new SelectItem("partic", "PARTICLE"), new SelectItem("prep", "PREPOSITION"), new SelectItem("pron", "PRONOUN"),
            new SelectItem("pron_partic", "PRONOUN PARTICLE"), new SelectItem("verb", "VERB")});

        grItemPos.add(grAnyGroup);
        grItemPos.add(grAllGroup);

        arItemPos = new ArrayList<SelectItem>();

        SelectItemGroup anyGroup = new SelectItemGroup("Any Pos");
        anyGroup.setSelectItems(new SelectItem[]{new SelectItem("", "Any")});

        SelectItemGroup adjGroup = new SelectItemGroup("ADJECTIVE");
        adjGroup.setSelectItems(new SelectItem[]{new SelectItem("NEL", "ELATIVE NOUN"), new SelectItem("ADJ", "ADJECTIVE")});

        SelectItemGroup advGroup = new SelectItemGroup("ADVERB");
        advGroup.setSelectItems(new SelectItem[]{new SelectItem("ADV", "ADVERB")});

        SelectItemGroup conjGroup = new SelectItemGroup("CONJUNCTION");
        conjGroup.setSelectItems(new SelectItem[]{new SelectItem("CONJ", "COORDINATING CONJUNCTION"), new SelectItem("SUB_CONJ", "SUBORDINATING CONJUNCTION")});

        SelectItemGroup nounGroup = new SelectItemGroup("NOUN");
        nounGroup.setSelectItems(new SelectItem[]{new SelectItem("INTERROG_NOUN", "INTERROGATIVE NOUN"), new SelectItem("NOUN", "NOUN"), new SelectItem("NOUN_PROP", "PROPER NOUN")});

        SelectItemGroup particGroup = new SelectItemGroup("PARTICLE");
        particGroup.setSelectItems(new SelectItem[]{new SelectItem("ACC_PART", "ACCUSATIVE PARTICLE"), new SelectItem("ANSWER_PART", "ANSWER PARTICLE"),
            new SelectItem("CONDIZIONALE_PART", "CONDITIONAL PARTICLE"), new SelectItem("EXCEPTIVE_PART", "EXCEPTIVE PARTICLE"),
            new SelectItem("EXCLAMATIVE_NOUN", "EXCLAMATIVE NOUN"),
            new SelectItem("EXPLANATION_PART", "EXPLANATION PARTICLE"), new SelectItem("EXPLICATIVE_PART", "EXPLICATIVE PARTICLE"),
            new SelectItem("FUT_PART", "FUTURE PARTICLE"), new SelectItem("INTERROG_PART", "INTEROGATIVE PARTICLE"), new SelectItem("NEG_PART", "NEGATIVE PARTICLE"),
            new SelectItem("CERTAINTY_PART", "PARTICLE OF CERTAINTY"), new SelectItem("JUS_CONJ_V", "PARTICLE OF JUSSIVE"),
            new SelectItem("PREVENTIVE_PART", "PREVENTIVE PARTICLE"), new SelectItem("PURPOSE_PART", "PURPOSE PARTICLE"), new SelectItem("EMPHATIC_COND", "RESULT PARTICLE"),
            new SelectItem("SUPPLEMENTAL_PART", "SUPPLEMENTAL PARTICLE"), new SelectItem("VOCATIVE_PART", "VOCATIVE PARTICLE")});

        SelectItemGroup prepGroup = new SelectItemGroup("PREPOSITION");
        prepGroup.setSelectItems(new SelectItem[]{new SelectItem("PREP", "PREPOSITION")});

        SelectItemGroup pronGroup = new SelectItemGroup("PRONOUN");
        pronGroup.setSelectItems(new SelectItem[]{new SelectItem("ACC_PRON", "ACCUSATIVE PRONOUN"), new SelectItem("DEM_PRON", "DEMONSTRATIVE PRONOUN"),
            new SelectItem("GEN_PRON", "GENETIVE PRONOUN"), new SelectItem("NOM_PRON", "NOMINATIVE PRONOUN"), new SelectItem("REL_PRON", "RELATIVE PRONOUN")});

        SelectItemGroup verbGroup = new SelectItemGroup("VERB");
        verbGroup.setSelectItems(new SelectItem[]{new SelectItem("VERB", "VERB")});

        arItemPos.add(anyGroup);
        arItemPos.add(adjGroup);
        arItemPos.add(advGroup);
        arItemPos.add(conjGroup);
        arItemPos.add(nounGroup);
        arItemPos.add(particGroup);
        arItemPos.add(prepGroup);
        arItemPos.add(pronGroup);
        arItemPos.add(verbGroup);

        grItemType = new ArrayList<Pair>();
        grItemType.add(new Pair<String, String>("Form", "forma"));
        grItemType.add(new Pair<String, String>("Lemma", "lemma"));
        arItemType = new ArrayList<Pair>();
        arItemType.add(new Pair<String, String>("Form", "forma"));
        arItemType.add(new Pair<String, String>("Lemma", "lemma"));
        arItemType.add(new Pair<String, String>("Root", "radice"));
        arItemType.add(new Pair<String, String>("Word", "word"));
        //arItemType.add(new Pair<String, String>("Pos",  "pos"));

        log.debug("SearchViewBean init()");
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return the repositoryBean
     */
    public RepositoryBean getRepositoryBean() {
        return repositoryBean;
    }

    /**
     * @param repositoryBean the repositoryBean to set
     */
    public void setRepositoryBean(RepositoryBean repositoryBean) {
        this.repositoryBean = repositoryBean;
    }

    /**
     * @return the sharedBean
     */
    public SharedBean getSharedBean() {
        return sharedBean;
    }

    /**
     * @param sharedBean the sharedBean to set
     */
    public void setSharedBean(SharedBean sharedBean) {
        this.sharedBean = sharedBean;
    }

    /**
     * @return the grSb
     */
    public SearchBean getGrSb() {
        return grSb;
    }

    /**
     * @param grSb the grSb to set
     */
    public void setGrSb(SearchBean grSb) {
        this.grSb = grSb;
    }

    /**
     * @return the arSb
     */
    public SearchBean getArSb() {
        return arSb;
    }

    /**
     * @param arSb the arSb to set
     */
    public void setArSb(SearchBean arSb) {
        this.arSb = arSb;
    }

    /**
     * @return the combinedOperation
     */
    public String getCombinedOperation() {
        return combinedOperation;
    }

    /**
     * @param combinedOperation the combinedOperation to set
     */
    public void setCombinedOperation(String combinedOperation) {
        this.combinedOperation = combinedOperation;
    }

    /**
     * @return the searchResult
     */
    public List<ResultViewEntity> getSearchResult() {
        return searchResult;
    }

    /**
     * @param searchResult the searchResult to set
     */
    public void setSearchResult(List<ResultViewEntity> searchResult) {
        this.searchResult = searchResult;
    }

    /**
     * @return the grItemType
     */
    public List<Pair> getGrItemType() {
        return grItemType;
    }

    /**
     * @param grItemType the grItemType to set
     */
    public void setGrItemType(List<Pair> grItemType) {
        this.grItemType = grItemType;
    }

    /**
     * @return the arItemType
     */
    public List<Pair> getArItemType() {
        return arItemType;
    }

    /**
     * @param arItemType the arItemType to set
     */
    public void setArItemType(List<Pair> arItemType) {
        this.arItemType = arItemType;
    }

    /**
     * @return the arItemPos
     */
    public List<SelectItem> getArItemPos() {
        return arItemPos;
    }

    /**
     * @param arItemPos the arItemPos to set
     */
    public void setArItemPos(List<SelectItem> arItemPos) {
        this.arItemPos = arItemPos;
    }

    /**
     * @return the grItemPos
     */
    public List<SelectItem> getGrItemPos() {
        return grItemPos;
    }

    /**
     * @param grItemPos the grItemPos to set
     */
    public void setGrItemPos(List<SelectItem> grItemPos) {
        this.grItemPos = grItemPos;
    }

    public void grSubmit() {

        log.debug("grSubmit: " + grSb.toString());
        try {
            setSearchResult(repositoryBean.simpleGreekSearchLinksByTokens(grSb));
            fileDownloadView();
        } catch (Exception e) {
            log.fatal("Greek parameter: " + grSb.toString());
            e.printStackTrace();
        }

    }

    public void arSubmit() {
        log.debug("arSubmit: " + arSb.toString());
        try {
            setSearchResult(repositoryBean.simpleArabicSearchLinksByTokens(arSb));
            fileDownloadView();

        } catch (Exception e) {
            log.fatal("Arabic parameter: " + arSb.toString());
            e.printStackTrace();
        }

    }

    public void combinedSubmit() {
        log.debug("combinedSubmit: ar:" + arSb.toString() + " gr: " + grSb.toString() + " op: " + getCombinedOperation());
        try {
            setSearchResult(repositoryBean.combinedSearchLinksByTokens(grSb, arSb, getCombinedOperation()));
            fileDownloadView();

        } catch (Exception e) {
            log.fatal("Greek parameter: " + grSb.toString() + ", Arabic parameter: " + arSb.toString());
            e.printStackTrace();
        }

    }

    public void reset() {

        setGrSb(new SearchBean());
        setArSb(new SearchBean());
    }

    /**
     * Costruisce una rappresentazione CVS tab based a partire da una lista di
     * risultati della ricerca
     *
     * @param lrve
     * @return
     */
    public String resultViewEntity2CVSOld(List<ResultViewEntity> lrve) {

        StringBuffer result = null;
        List<TokenViewEntity> tvel = null;
        if (null != lrve) {
            result = new StringBuffer();
            for (ResultViewEntity rve : lrve) {

                //Info GR
                result.append("\"" + rve.getGrPericopeInfo() + "\"" + '\t');
                StringBuffer sb = new StringBuffer();
                //Pericope GR
                log.debug(repositoryBean.getPericopeTextById(rve.getGrPericopeId(), Consts.GREEK));
                tvel = rve.getGreekTVE();
                for (Iterator iterator = tvel.iterator(); iterator.hasNext();) {
                    TokenViewEntity tve = (TokenViewEntity) iterator.next();
                    if ("↲".equals(tve.getText())) {
                        sb.deleteCharAt(sb.length() - 1);
                        sb.append('\n');
                    } else {
                        sb.append(tve.getText());
                        if (iterator.hasNext()) {
                            sb.append(" ");
                        }
                    }

                }
                result.append("\"" + sb.toString() + "\"" + '\t');

                sb = new StringBuffer(); //clean the stringbuffer
                //Pericope AR
                tvel = rve.getArabicTVE();
                for (Iterator iterator = tvel.iterator(); iterator.hasNext();) {
                    TokenViewEntity tve = (TokenViewEntity) iterator.next();
                    //System.err.println("(" + tve.getText()+")");
                    if ("↳".equals(tve.getText())) {
                        sb.deleteCharAt(sb.length() - 1);
                        sb.append('\n');
                    } else {
                        sb.append(tve.getText());
                        if (iterator.hasNext()) {
                            sb.append(" ");
                        }
                    }
                }

                result.append("\"" + sb.toString() + "\"" + '\t');
                //Info AR
                result.append("\"" + rve.getArPericopeInfo() + "\"");
                result.append(System.getProperty("line.separator"));

            }
        }

        return (result != null) ? result.toString() : null;
    }

    /**
     * Costruisce una rappresentazione CVS tab based a partire da una lista di
     * risultati della ricerca
     *
     * @param lrve
     * @return
     */
    public String resultViewEntity2CVS(List<ResultViewEntity> lrve) {

        StringBuffer result = null;

        if (null != lrve) {
            result = new StringBuffer();
            for (ResultViewEntity rve : lrve) {

                if (null != rve.getGrPericopeInfo()) {
                    //Info GR
                    result.append("\"" + rve.getGrPericopeInfo() + "\"" + '\t');

                    //Pericope GR
                    String greekPericope = repositoryBean.getPericopeTextById(rve.getGrPericopeId(), Consts.GREEK);
                    if (null != greekPericope) {
                        greekPericope = greekPericope.replaceAll("\\s*↲\\s*", System.getProperty("line.separator"));
                        greekPericope = greekPericope.replaceAll("\\s*([,.;:])", "$1");
                        result.append("\"" + greekPericope + "\"" + '\t');
                    }
                }
                //Pericope AR
                if (null != rve.getArPericopeInfo()) {

                    String arabicPericope = repositoryBean.getPericopeTextById(rve.getArPericopeId(), Consts.ARABIC);
                    if (null != arabicPericope) {
                        arabicPericope = arabicPericope.replaceAll("\\s*↳\\s*", System.getProperty("line.separator"));
                        arabicPericope = arabicPericope.replaceAll("\\s*([\u060C\u061b\\.:])", "$1");

                        result.append("\"" + arabicPericope + "\"" + '\t');
                    }
                    //Info AR
                    result.append("\"" + rve.getArPericopeInfo() + "\"");
                    result.append(System.getProperty("line.separator"));
                }
            }
        }

        return (result != null) ? result.toString() : null;
    }

    public void fileDownloadView() {
        InputStream stream;
        try {
            if (null != getSearchResult()) {
                //repositoryBean.getPericopeTextById(pericopeId, langId);
                stream = new ByteArrayInputStream(resultViewEntity2CVS(getSearchResult()).getBytes("UTF-8"));
                setDownloadableResult(new DefaultStreamedContent(stream, "text/csv", "results.csv"));
            } else {
                setDownloadableResult(null);
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
