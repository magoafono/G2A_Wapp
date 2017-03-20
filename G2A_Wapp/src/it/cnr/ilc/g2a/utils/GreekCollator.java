package it.cnr.ilc.g2a.utils;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class GreekCollator {
	private static final Locale GREEK_LOCALE = new Locale("el", "GR");
	private static final int NO_COLLATOR = -1;

	public enum CollatorEnum {
		NoCollator(NO_COLLATOR),
		Primary(Collator.PRIMARY),
		Secondary(Collator.SECONDARY),
		Tertiary(Collator.TERTIARY),
		Identical(Collator.IDENTICAL);

		private int collatorStrength;
		CollatorEnum(int collatorStrength) {
			this.collatorStrength = collatorStrength;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> words = Arrays.asList("άλλος", "αλάνι", "βασικός", "ελεύθερος", "Ελεύθερος", "Άλλος", "Αλάνι", "ἀγαθός");

		System.out.println(words + " - Original Data\n");
		sort(words, CollatorEnum.NoCollator); // don't use a collator
		sort(words, CollatorEnum.Primary);
		sort(words, CollatorEnum.Secondary);
		sort(words, CollatorEnum.Tertiary);
		sort(words, CollatorEnum.Identical);

		System.out.println();
		compare("αβγ", "ΆΒΓ", CollatorEnum.NoCollator);
		compare("αβγ", "ΆΒΓ", CollatorEnum.Primary);
		compare("αβγ", "ΆΒΓ", CollatorEnum.Secondary);
		compare("αβγ", "ΆΒΓ", CollatorEnum.Tertiary);
		compare("αβγ", "ΆΒΓ", CollatorEnum.Identical);

		System.out.println();
		compare("αβγ", "ΑΒΓ", CollatorEnum.NoCollator);
		compare("αβγ", "ΑΒΓ", CollatorEnum.Primary);
		compare("αβγ", "ΑΒΓ", CollatorEnum.Secondary);
		compare("αβγ", "ΑΒΓ", CollatorEnum.Tertiary);
		compare("αβγ", "ΆΒΓ", CollatorEnum.Identical);
	}

	public static void sort(List<String> aWords, CollatorEnum collatorEnum) {
		if (collatorEnum.collatorStrength < 0) {
			Collections.sort(aWords);
		} else {
			Collator collator = Collator.getInstance(GREEK_LOCALE);
			collator.setStrength(collatorEnum.collatorStrength);
			Collections.sort(aWords, collator);
		}
		//System.out.println(aWords.toString() + " " + collatorEnum);
	}

	private static void compare(String aThis, String aThat, CollatorEnum collatorEnum) {
		int comparison = 999;
		if (collatorEnum.collatorStrength < 0) {
			comparison = aThis.compareTo(aThat);
		} else {
			Collator collator = Collator.getInstance(GREEK_LOCALE);
			collator.setStrength(collatorEnum.collatorStrength);
			comparison = collator.compare(aThis, aThat);
		}
		if (comparison == 0) {
			System.out.println("Collator sees them as the same : " + aThis
					+ ", " + aThat + " - " + collatorEnum);
		} else {
			System.out.println("Collator sees them as DIFFERENT  : " + aThis
					+ ", " + aThat + " - " + collatorEnum);
		}
	}

}
