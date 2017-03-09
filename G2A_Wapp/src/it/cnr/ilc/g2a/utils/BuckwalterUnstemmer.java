package it.cnr.ilc.g2a.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BuckwalterUnstemmer {

	private static final Logger logger = LogManager.getLogger("BuckwalterUnstemmer");

	/*
	 * Pattern di unstemming (ricollegamento degli stem creati da Aramorph
	 */
	static Pattern patternNounArabicVocal   = Pattern.compile("(.+\\+)?(.+)=(NOUN|NOUN_PROP|ADJ|ADV)\\+([aiuFKN])?=(CASE_.*)(\\+.*)?");
	static Pattern patternNounAtAp          = Pattern.compile("(.+\\+)?(.+)=(NOUN|NOUN_PROP|ADJ|ADV)\\+(At|ap)=NSUFF_(FEM_.*)\\+([aiuFKN])?=(CASE_.*)(\\+.*)?");
	static Pattern patternNounSuffix        = Pattern.compile("(.*\\+)?(.+)=(NOUN|NOUN_PROP|ADJ|ADV)\\+(.+)=NSUFF_(.*)(\\+.*)?");
	static Pattern patternVerbPerfect       = Pattern.compile("(.*\\+)?(.+)=VERB_PERFECT\\+(.+)=(PVSUFF_SUBJ:.+)(\\+.*)?");
	static Pattern patternVerbImperfectSuff = Pattern.compile("(.*\\+)?(.+)=IV(.+)\\+(.+)=VERB_IMPERFECT\\+(.+)=IVSUFF_(MOOD|SUBJ)([^\\+]+)(\\+.*)?");
	static Pattern patternVerbImperfectDo   = Pattern.compile("(.*\\+)?(.+)=IV(.+)\\+(.+)=VERB_IMPERFECT\\+(.+)(=IVSUFF_DO)(.*)?");
	static Pattern patternVerbImperfectMood = Pattern.compile("(.*\\+)?(.+)=IV(.+)\\+(.+)=VERB_IMPERFECT\\+=(MOOD:[^\\+]+)(\\+.*)?");
	static Pattern patternVerbImperative    = Pattern.compile("(.*\\+)?(.+)=VERB_IMPERATIVE\\+(.+)=(CVSUFF_SUBJ:.+)(\\+.*)?");

	static String[] replaces = {
		"$1$2$4=$3*$5$6", 
		"$1$2$4$6=$3*$5*$7$8", 
		"$1$2$4=$3*$5$6", 
		"$1$2$3=VERB_PERFECT*$4$5",
		"$1$2$4$5=VERB_IMPERFECT*$6$7*$3$8",
		"$1$2$4=VERB_IMPERFECT*$3+$5$6$7",
		
		"$1$2$4=VERB_IMPERFECT*$5*$3$6", 
		"$1$2$3=VERB_IMPERATIVE*$4$5"};
	
	static Pattern[] patterns = { patternNounArabicVocal, patternNounAtAp, patternNounSuffix, 
			patternVerbPerfect, patternVerbImperfectSuff, patternVerbImperfectDo, patternVerbImperfectMood, patternVerbImperative};
	
	public static String unstemm(String s) {

		String ret = s;
		boolean match = false;
		for (int i=0; i < patterns.length; i++) {
			
			Matcher m = patterns[i].matcher(s);
			if (m.matches()){
				ret = m.replaceFirst(replaces[i]);
				logger.debug("For " + s + " Matches " + i+ ": " + patterns[i].pattern() + " => " + ret);
				match = true;
				break;
			} 
			
		}
		//if (!match) System.err.println("no match for " + s);
		return ret;
	}

	public static void main(String[] args) {

	/*	BuckwalterUnstemmer.unstemm("Al=DET+raHomAn=ADJ+i=CASE_GEN+");
		BuckwalterUnstemmer.unstemm("Al=DET+xayor=NOUN+At=NSUFF_FEM_PL+i=CASE_ACC+");
		BuckwalterUnstemmer.unstemm("Al=DET+Harak=NOUN+atayoni=NSUFF_FEM_DU_GEN+");
		BuckwalterUnstemmer.unstemm("taHar~ak=VERB_PERFECT+a=PVSUFF_SUBJ:3MS+");
		BuckwalterUnstemmer.unstemm(">arad=VERB_PERFECT+ota=PVSUFF_SUBJ:2MS+");
		BuckwalterUnstemmer.unstemm("ta=IV3FS+*okur=VERB_IMPERFECT+u=IVSUFF_MOOD:ID+");
		BuckwalterUnstemmer.unstemm("ya=IV3MS+boqaY=VERB_IMPERFECT+=MOOD:ID+");
		BuckwalterUnstemmer.unstemm("fa=CONJ+{noZur=VERB_IMPERATIVE+o=CVSUFF_SUBJ:2MS+");*/
		//BuckwalterUnstemmer.unstemm("li=SUB_CONJ_V+yu=IV3MP+may~iz=VERB_IMPERFECT+uwA=IVSUFF_SUBJ:MP_MOOD:SJ");
		BuckwalterUnstemmer.unstemm("ta=IV3FS+rA=VERB_IMPERFECT+hu=IVSUFF_DO:3MS");
			
		
	}


}
