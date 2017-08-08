package com.ugoostanleyibe.java;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public final class NumberSpellerFR extends UGGames {
	private final String SIGN = "MOINS ", S = "S", D = "-", CM = ", ",
			ONE = "UN ", EMP = "", HUN = "CENT", THOU = "MILLE",
			MIL = "MILLION", BIL = "BILLION", TRI = "TRILLION",
			QUAD = "QUADRILLION", QUIN = "QUINTILLION", SP = " ";
			
	public static void main(String[] args) {
		new NumberSpellerFR().play();
	}

	public void play() {
		long num = 0;   print("\n\n");
		while (true) {
			try {
				scanTool = new Scanner(System.in);
				print("\tDonnez un entier positif ou négatif >> ");
				num = scanTool.nextLong();   break;
			} catch (Exception bug) {
				print("\tENTRÉE ERRONÉE OU DÉPASSEMENT DE PILE!\n");
			}
		}
		final String arg1 = fmt(num), arg2 = fmt(splOf(num));
		print("\n\tSuper! %s en mots est:\n\n\t%s.\n\n\tEntrez \"0033"
				+ "\" pour jouer encore >> ", arg1, arg2);
		String input = scanTool.next();
		if (input.equals("0033")) play();
		else print("\n\tMERCI DE JOUER LE JEU! :)");
	}

	private void printFrom(long start, long end, long step) {
		long num = start;
		do {
			final String arg1 = fmt(num), arg2 = fmt(splOf(num));
			print("\n\t%s  >>\n\n\t%s\n", arg1, arg2);
			num += step;
		} while (start <= end ? num <= end : num >= end);
	}

	private String splOf(long num) {
		final boolean neg = str(num).startsWith("-");  num = abs(num);
		final String sign = neg ? SIGN : EMP, s = str(num);
		if (num <= 100) return sign + rep((int) num);
		int[] d = new int[s.length()];
		for (int i = 0; i < s.length(); i++)
			d[i] = Character.getNumericValue(s.charAt(i));

		switch (s.length()) {
			case 19:
				String A19 = rep(d[0]) + SP + QUIN + S,
				B19 = ONE + QUIN + CM
				+ splOf(num % 1000000000000000000L),
				C19 = A19 + CM + splOf(num % 1000000000000000000L);
				return sign + (num == 1000000000000000000L ?
					ONE + QUIN : num % 1000000000000000000L == 0 ?
					A19 : d[0] == 1 ? B19 : C19);
			case 18: case 17:
				String A17 = splOf(num / 1000000000000000L)
				+ SP + QUAD + S,
				B17 = A17 + CM + splOf(num % 1000000000000000L);
				return sign + (num % 1000000000000000L == 0 ?
						A17 : B17);
			case 16:
				String A16 = rep(d[0]) + SP + QUAD + S,
				B16 = ONE + QUAD + CM
				+ splOf(num % 1000000000000000L),
				C16 = A16 + CM + splOf(num % 1000000000000000L);
				return sign + (num == 1000000000000000L ? ONE + QUAD :
					num % 1000000000000000L == 0 ? A16 : d[0] == 1 ?
					B16 : C16);
			case 15: case 14:
				String A14 = splOf(num / 1000000000000L)
				+ SP + TRI + S,
				B14 = A14 + CM + splOf(num % 1000000000000L);
				return sign + (num % 1000000000000L == 0 ? A14 : B14);
			case 13:
				String A13 = rep(d[0]) + SP + TRI + S,
				B13 = ONE + TRI + CM + splOf(num % 1000000000000L),
				C13 = A13 + CM + splOf(num % 1000000000000L);
				return sign + (num == 1000000000000L ? ONE + TRI :
					num % 1000000000000L == 0 ? A13 : d[0] == 1 ?
					B13 : C13);
			case 12: case 11:
				String A11 = splOf(num / 1000000000) + SP + BIL + S,
				B11 = A11 + CM + splOf(num % 1000000000);
				return sign + (num % 1000000000 == 0 ? A11 : B11);
			case 10:
				String A10 = rep(d[0]) + SP + BIL + S,
				B10 = ONE + BIL + CM + splOf(num % 1000000000),
				C10 = A10 + CM + splOf(num % 1000000000);
				return sign + (num == 1000000000 ? ONE + BIL :
					num % 1000000000 == 0 ? A10 : d[0] == 1 ?
					B10 : C10);
			case 9: case 8:
				String A8 = splOf(num / 1000000) + SP + MIL + S,
				B8 = A8 + CM + splOf(num % 1000000);
				return sign + (num % 1000000 == 0 ? A8 : B8);
			case 7:
				String A7 = rep(d[0]) + SP + MIL + S,
				B7 = ONE + MIL + CM + splOf(num % 1000000),
				C7 = A7 + CM + splOf(num % 1000000);
				return sign + (num == 1000000 ? ONE + MIL :
					num % 1000000 == 0 ? A7 : d[0] == 1 ? B7 : C7);
			case 6: case 5:
				String A5 = splOf(num / 1000) + SP + THOU,
				B5 = A5 + CM + splOf(num % 1000);
				return sign + (num % 1000 == 0 ? A5 : B5);
			case 4:
				String A4 = rep(d[0]) + SP + THOU,
				B4 = THOU + SP + splOf(num % 1000),
				C4 = A4 + SP + splOf(num % 1000);
				return sign + (num == 1000 ? THOU : num % 1000 == 0 ?
					A4 : d[0] == 1 ? B4 : C4);
			case 3:
				String A3 = rep(d[0]) + SP + HUN,
				B3 = SP + splOf(num % 100), C3 = D + splOf(num % 100);
				return sign + (num < 200 ? HUN + B3 :
					num % 100 == 0 ? A3 + S : A3 + C3);
			default:
				return EMP;
		}
	}

	private long abs(long num) {
		return num >= 0 ? num : -num;
	}

	private String fmt(long num) {
		return NumberFormat.getInstance(Locale.ITALY).format(num);
	}

	private String fmt(String spl) {
		final StringBuilder b = new StringBuilder(spl);
		final int lgt = b.length();
		for (int bgn = 0, end; bgn < lgt; bgn = end + 1) {
			end = bgn + 75;
			int pos = b.lastIndexOf(",", end);
			if (pos > (bgn + 35)) {
				b.replace(pos + 1, pos + 2, "\n\t");   end = pos;
			}
		}
		return b.toString();
	}
	
	private void print(Object format, Object... args) {
		System.out.printf(str(format), args);
	}
	
	private String str(Object arg) {
		return String.valueOf(arg);
	}

	private String rep(int num) {
		return str(KeyWordsFR.values()[num]).replace('_', '-');
	}

	private enum KeyWordsFR {
		ZÉRO, UN, DEUX, TROIS, QUATRE, CINQ, SIX, SEPT, HUIT, NEUF,
		DIX, ONZE, DOUZE, TREIZE, QUATORZE, QUINZE, SEIZE, DIX_SEPT,
		DIX_HUIT, DIX_NEUF, VINGT, VINGT_ET_UN, VINGT_DEUX,
		VINGT_TROIS, VINGT_QUATRE, VINGT_CINQ, VINGT_SIX, VINGT_SEPT,
		VINGT_HUIT, VINGT_NEUF, TRENTE, TRENTE_ET_UN, TRENTE_DEUX,
		TRENTE_TROIS, TRENTE_QUATRE, TRENTE_CINQ, TRENTE_SIX,
		TRENTE_SEPT, TRENTE_HUIT, TRENTE_NEUF, QUARANTE,
		QUARANTE_ET_UN, QUARANTE_DEUX, QUARANTE_TROIS,
		QUARANTE_QUATRE, QUARANTE_CINQ, QUARANTE_SIX, QUARANTE_SEPT,
		QUARANTE_HUIT, QUARANTE_NEUF, CINQUANTE, CINQUANTE_ET_UN,
		CINQUANTE_DEUX, CINQUANTE_TROIS, CINQUANTE_QUATRE,
		CINQUANTE_CINQ, CINQUANTE_SIX, CINQUANTE_SEPT, CINQUANTE_HUIT,
		CINQUANTE_NEUF, SOIXANTE, SOIXANTE_ET_UN, SOIXANTE_DEUX,
		SOIXANTE_TROIS, SOIXANTE_QUATRE, SOIXANTE_CINQ, SOIXANTE_SIX,
		SOIXANTE_SEPT, SOIXANTE_HUIT, SOIXANTE_NEUF, SOIXANTE_DIX,
		SOIXANTE_ET_ONZE, SOIXANTE_DOUZE, SOIXANTE_TREIZE,
		SOIXANTE_QUATORZE, SOIXANTE_QUINZE, SOIXANTE_SEIZE,
		SOIXANTE_DIX_SEPT, SOIXANTE_DIX_HUIT, SOIXANTE_DIX_NEUF,
		QUATRE_VINGTS, QUATRE_VINGT_UN, QUATRE_VINGT_DEUX,
		QUATRE_VINGT_TROIS, QUATRE_VINGT_QUATRE, QUATRE_VINGT_CINQ,
		QUATRE_VINGT_SIX, QUATRE_VINGT_SEPT, QUATRE_VINGT_HUIT,
		QUATRE_VINGT_NEUF, QUATRE_VINGT_DIX, QUATRE_VINGT_ONZE,
		QUATRE_VINGT_DOUZE, QUATRE_VINGT_TREIZE,
		QUATRE_VINGT_QUATORZE, QUATRE_VINGT_QUINZE,
		QUATRE_VINGT_SEIZE, QUATRE_VINGT_DIX_SEPT,
		QUATRE_VINGT_DIX_HUIT, QUATRE_VINGT_DIX_NEUF, CENT;
	}
}