package it.cnr.ilc.g2a.utils;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BuckwalterArabicConverter {

	private static final Logger logger = LogManager.getLogger("Buckwalter2Arabic");

	static boolean  outputUnicodeValues = false;

	private final static char[] arabicChars = {
		'\u0621', '\u0622', '\u0623', '\u0624', '\u0625', '\u0626', '\u0627',
		'\u0628', '\u0629', '\u062A', '\u062B',
		'\u062C', '\u062D', '\u062E', '\u062F',
		'\u0630', '\u0631', '\u0632', '\u0633',
		'\u0634', '\u0635', '\u0636', '\u0637', '\u0638', '\u0639', '\u063A',
		'\u0640', '\u0641', '\u0642', '\u0643',
		'\u0644', '\u0645', '\u0646', '\u0647',
		'\u0648', '\u0649', '\u064A', '\u064B',
		'\u064C', '\u064D', '\u064E', '\u064F',
		'\u0650', '\u0651', '\u0652',
		'\u0670', /*'\u0627',*/ '\u0671',
		'\u067E', '\u0686', '\u0698', '\u06A4', '\u06AF',
		/*'\u0625', '\u0623', '\u0624',*/    // add Tim's "XML-friendly" just in case
		'\u060C', '\u061B', '\u061F', // from BBN script; status unknown
		'\u066A', '\u066B', // from IBM script
		'\u06F0','\u06F1','\u06F2','\u06F3','\u06F4', //Farsi/Urdu cardinals
		'\u06F5','\u06F6','\u06F7','\u06F8','\u06F9',
		'\u0660', '\u0661', '\u0662', '\u0663', '\u0664',
		'\u0665', '\u0666', '\u0667', '\u0668', '\u0669',
		'\u00AB', '\u00BB' // French quotes used in e.g. Gulf newswire
	};

	private final static char[] buckChars = {
		'\'', '|', '>', '&', '<', '}', 'A',
		'b', 'p', 't', 'v',
		'j', 'H', 'x', 'd', // end 062x
		'*', 'r', 'z', 's',
		'$', 'S', 'D', 'T', 'Z', 'E', 'g', // end 063x
		'_', 'f', 'q', 'k',
		'l', 'm', 'n', 'h',
		'w', 'Y', 'y', 'F',
		'N', 'K', 'a', 'u', // end 0064x
		'i', '~', 'o',
		'`', '{',
		'P', 'J', 'R', 'V', 'G', // U+0698 is Farsi Jeh: R to ATB POS guidelines
		/*'I', 'O', 'W', */  // add Tim's "XML-friendly" versions just in case
		',', ';', '?', // from BBN script; status unknown
		'%', '.', // from IBM script
		'0', '1', '2', '3', '4',
		'5', '6', '7', '8', '9',
		'0', '1', '2', '3', '4',
		'5', '6', '7', '8', '9',
		'"', '"' // French quotes used in e.g. Gulf newswire
	};

	/* BBN also maps to @: 0x007B 0x066C 0x066D 0x0660 0x0661 0x0662 0x0663
	                         0x0664 0x0665 0x0666 0x0667 0x0668 0x0669 0x066A
	                         0x0686 0x06AF 0x066D 0x06AF 0x06AA 0x06AB 0x06B1
	                         0x06F0 0x06EC 0x06DF 0x06DF 0x06F4 0x002A 0x274A
	                         0x00E9 0x00C9 0x00AB 0x00BB 0x00A0 0x00A4
	 */
	/* BBNWalter dispreferring punct chars:
	     '\u0624', '\u0625', '\u0626',  -> 'L', 'M', 'Q',
	     '\u0630', -> 'C', '\u0640', -> '@', '\u0651', -> 'B',
	 */
	/* IBM also deletes: 654 655 670 */

	private final static HashMap<Character,Character> u2bMap;
	private final static HashMap<Character,Character> b2uMap;


	private static final boolean PASS_ASCII_IN_UNICODE = true;
	private static boolean SUPPRESS_DIGIT_MAPPING_IN_B2A = true;
	private static boolean SUPPRESS_PUNC_MAPPING_IN_B2A = true;

	//wsg: I have included _ in this list, which actually maps to tatweel.
	//In practice we strip tatweel as part of orthographic normalization,
	//so any instances of _ in the Buckwalter should actually be treated as
	//punctuation.
	private static final Pattern latinPunc = Pattern.compile("[\"\\?%,-;\\._]+");

	static {
		if (arabicChars.length != buckChars.length)
			throw new RuntimeException(": Inconsistent u2b/b2u arrays.");

		u2bMap = new HashMap<Character,Character>(arabicChars.length);
		b2uMap = new HashMap<Character,Character>(buckChars.length);
		for (int i = 0; i < arabicChars.length; i++) {
			Character charU = Character.valueOf(arabicChars[i]);
			Character charB = Character.valueOf(buckChars[i]);
			u2bMap.put(charU, charB);
			b2uMap.put(charB, charU);
		}
	}


	public void suppressBuckDigitConversion(boolean b) { SUPPRESS_DIGIT_MAPPING_IN_B2A = b; }

	public void suppressBuckPunctConversion(boolean b) { SUPPRESS_PUNC_MAPPING_IN_B2A = b; }

	public static String  apply(String in) { return convert(in, false); }

	public static String buckwalterToUnicode(String in) { return convert(in, false);	}

	public static String unicodeToBuckwalter(String in) { return convert(in, true); }

	public static String convert(String in, boolean unicodeToBuckwalter) {
		final StringTokenizer st = new StringTokenizer(in);
		final StringBuilder result = new StringBuilder(in.length());

		while(st.hasMoreTokens()) {
			final String token = st.nextToken();
			for (int i = 0; i < token.length(); i++) {

				final Character inCh = Character.valueOf(token.charAt(i));
				Character outCh = null;

				if (unicodeToBuckwalter) {
					outCh = (PASS_ASCII_IN_UNICODE && inCh.charValue() < 127) ? inCh : u2bMap.get(inCh);

				} else if((SUPPRESS_DIGIT_MAPPING_IN_B2A && Character.isDigit(inCh)) ||
						(SUPPRESS_PUNC_MAPPING_IN_B2A && latinPunc.matcher(inCh.toString()).matches())) {
					outCh = inCh;

				} else {
					outCh = b2uMap.get(inCh);
				}

				if (outCh == null) {
					result.append(inCh);  // pass through char

				} else if(outputUnicodeValues) {
					result.append("\\u").append(StringUtils.leftPad(Integer.toString(inCh, 16).toUpperCase(), 4, '0'));

				} else {
					result.append(outCh);
				}
			}
			result.append(" ");
		}

		return result.toString().trim();
	}


	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		logger.debug(BuckwalterArabicConverter.buckwalterToUnicode("Eaqoliy~"));
		logger.debug(BuckwalterArabicConverter.unicodeToBuckwalter("وقد"));
	}

}
