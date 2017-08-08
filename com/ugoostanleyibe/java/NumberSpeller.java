package com.ugoostanleyibe.java;

import java.text.NumberFormat;
import java.util.Scanner;

public final class NumberSpeller {
	private final String SIGN = "NEGATIVE ", AND = " AND ", CM = ", ",
			EMP = "", HUN = " HUNDRED", THOU = " THOUSAND",
			MIL = " MILLION", BIL = " BILLION", TRI = " TRILLION",
			QUAD = " QUADRILLION", QUIN = " QUINTILLION";
			
	private String P1, P2, P3;
	
	public static void main(String[] args) {
		new NumberSpeller().play();
	}

	public void play() {
		long num = 0;   print("\n\n");
		while (true) {
			try {
				scanTool = new Scanner(System.in);
				print("\tType in a positive or negative integer >> ");
				num = scanTool.nextLong();   break;
			} catch (Exception bug) {
				print("\tUNACCEPTABLE TOKEN OR OVERFLOW!\n");
			}
		}
		final String arg1 = fmt(num), arg2 = fmt(splOf(num));
		print("\n\tGreat! %s in words is:\n\n\t%s.\n\n\tType in \"00"
				+ "44\" to play again >> ", arg1, arg2);
		String input = scanTool.next();
		if (input.equals("0044")) play();
		else print("\n\tTHANKS FOR PLAYING! :)");
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
		if (num < 100) return sign + rep((int) num);
		int[] d = new int[s.length()];
		
		for (int i = 0; i < s.length(); i++)
			d[i] = Character.getNumericValue(s.charAt(i));

		switch (s.length()) {
			case 19:
				P1 = rep(d[0]) + QUIN;
				P2 = P1 + AND + splOf(num % 100);
				P3 = P1 + CM + splOf(num % 1000000000000000000L);
				return sign + (num % 1000000000000000000L == 0 ? P1 :
						num % 1000000000000000000L < 100 ? P2: P3);
			case 18:
				P1 = splOf(num / 1000000000000000L) + QUAD;
				P2 = P1 + AND + splOf(num % 100);
				P3 = P1 + CM + splOf(num % 1000000000000000L);
				return sign + (num % 100000000000000000L == 0 ||
						num % 10000000000000000L == 0 ||
						num % 1000000000000000L == 0 ? P1 :
						num % 1000000000000000L < 100 ? P2: P3);
			case 17:
				P1 = splOf(num / 1000000000000000L) + QUAD;
				P2 = P1 + AND + splOf(num % 100);
				P3 = P1 + CM + splOf(num % 1000000000000000L);
				return sign + (num % 10000000000000000L == 0 ||
						num % 1000000000000000L == 0 ? P1 :
						num % 1000000000000000L < 100 ? P2: P3);
			case 16:
				P1 = rep(d[0]) + QUAD;
				P2 = P1 + AND + splOf(num % 100);
				P3 = P1 + CM + splOf(num % 1000000000000000L);
				return sign + (num % 1000000000000000L == 0 ? P1 :
						num % 1000000000000000L < 100 ? P2: P3);
			case 15:
				P1 = splOf(num / 1000000000000L) + TRI;
				P2 = P1 + AND + splOf(num % 100);
				P3 = P1 + CM + splOf(num % 1000000000000L);
				return sign + (num % 100000000000000L == 0 ||
						num % 10000000000000L == 0 ||
						num % 1000000000000L == 0 ? P1 :
						num % 1000000000000L < 100 ? P2: P3);
			case 14:
				P1 = splOf(num / 1000000000000L) + TRI;
				P2 = P1 + AND + splOf(num % 100);
				P3 = P1 + CM + splOf(num % 1000000000000L);
				return sign + (num % 10000000000000L == 0 ||
						num % 1000000000000L == 0 ? P1 :
						num % 1000000000000L < 100 ? P2: P3);
			case 13:
				P1 = rep(d[0]) + TRI;
				P2 = P1 + AND + splOf(num % 100);
				P3 = P1 + CM + splOf(num % 1000000000000L);
				return sign + (num % 1000000000000L == 0 ? P1 :
						num % 1000000000000L < 100 ? P2: P3);
			case 12:
				P1 = splOf(num / 1000000000) + BIL;
				P2 = P1 + AND + splOf(num % 100);
				P3 = P1 + CM + splOf(num % 1000000000);
				return sign + (num % 100000000000L == 0 ||
						num % 10000000000L == 0 ||
						num % 1000000000L == 0 ? P1 :
						num % 1000000000 < 100 ? P2: P3);
			case 11:
				P1 = splOf(num / 1000000000) + BIL;
				P2 = P1 + AND + splOf(num % 100);
				P3 = P1 + CM + splOf(num % 1000000000);
				return sign + (num % 10000000000L == 0 ||
						num % 1000000000 == 0 ? P1 :
						num % 1000000000 < 100 ? P2: P3);
			case 10:
				P1 = rep(d[0]) + BIL;
				P2 = P1 + AND + splOf(num % 100);
				P3 = P1 + CM + splOf(num % 1000000000);
				return sign + (num % 1000000000 == 0 ? P1 :
						num % 1000000000 < 100 ? P2: P3);
			case 9:
				P1 = splOf(num / 1000000) + MIL;
				P2 = P1 + AND + splOf(num % 100);
				P3 = P1 + CM + splOf(num % 1000000);
				return sign + (num % 100000000 == 0 ||
						num % 10000000 == 0 || num % 1000000 == 0 ?
						P1 : num % 1000000 < 100 ? P2: P3);
			case 8:
				P1 = splOf(num / 1000000) + MIL;
				P2 = P1 + AND + splOf(num % 100);
				P3 = P1 + CM + splOf(num % 1000000);
				return sign + (num % 10000000 == 0 ||
						num % 1000000 == 0 ? P1 :
						num % 1000000 < 100 ? P2: P3);
			case 7:
				P1 = rep(d[0]) + MIL;
				P2 = P1 + AND + splOf(num % 100);
				P3 = P1 + CM + splOf(num % 1000000);
				return sign + (num % 1000000 == 0 ? P1 :
						num % 1000000 < 100 ? P2: P3);
			case 6:
				P1 = splOf(num / 1000) + THOU;
				P2 = P1 + AND + splOf(num % 100);
				P3 = P1 + CM + splOf(num % 1000);
				return sign + (num % 100000 == 0 ||
						num % 10000 == 0 || num % 1000 == 0 ? P1 :
						num % 1000 < 100 ? P2: P3);
			case 5:
				P1 = splOf(num / 1000) + THOU;
				P2 = P1 + AND + splOf(num % 100);
				P3 = P1 + CM + splOf(num % 1000);
				return sign + (num % 10000 == 0 || num % 1000 == 0 ?
						P1 : num % 1000 < 100 ? P2: P3);
			case 4:
				P1 = rep(d[0]) + THOU;
				P2 = P1 + AND + splOf(num % 100);
				P3 = P1 + CM + splOf(num % 1000);
				return sign + (num % 1000 == 0 ? P1 :
					num % 1000 < 100 ? P2: P3);
			case 3:
				P1 = rep(d[0]) + HUN;
				P2 = P1 + AND + splOf(num % 100);
				return sign + (num % 100 == 0 ? P1 : P2);
			default:
				return EMP;
			}
	}

	private long abs(long num) {
		return num >= 0 ? num : -num;
	}

	private String fmt(long num) {
		return NumberFormat.getInstance().format(num);
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
		return str(KeyWords.values()[num]).replace('_', '-');
	}

	private enum KeyWords {
		ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
		TEN, ElEVEN, TWElVE, THIRTEEN, FOURTEEN, FIFTEEN, SIXTEEN,
		SEVENTEEN, EIGHTEEN, NINETEEN, TWENTY, TWENTY_ONE, TWENTY_TWO,
		TWENTY_THREE, TWENTY_FOUR, TWENTY_FIVE, TWENTY_SIX,
		TWENTY_SEVEN, TWENTY_EIGHT, TWENTY_NINE, THIRTY, THIRTY_ONE,
		THIRTY_TWO, THIRTY_THREE, THIRTY_FOUR, THIRTY_FIVE,
		THIRTY_SIX, THIRTY_SEVEN, THIRTY_EIGHT, THIRTY_NINE, FORTY,
		FORTY_ONE, FORTY_TWO, FORTY_THREE, FORTY_FOUR, FORTY_FIVE,
		FORTY_SIX, FORTY_SEVEN, FORTY_EIGHT, FORTY_NINE, FIFTY,
		FIFTY_ONE, FIFTY_TWO, FIFTY_THREE, FIFTY_FOUR, FIFTY_FIVE,
		FIFTY_SIX, FIFTY_SEVEN, FIFTY_EIGHT, FIFTY_NINE, SIXTY,
		SIXTY_ONE, SIXTY_TWO, SIXTY_THREE, SIXTY_FOUR, SIXTY_FIVE,
		SIXTY_SIX, SIXTY_SEVEN, SIXTY_EIGHT, SIXTY_NINE, SEVENTY,
		SEVENTY_ONE, SEVENTY_TWO, SEVENTY_THREE, SEVENTY_FOUR,
		SEVENTY_FIVE, SEVENTY_SIX, SEVENTY_SEVEN, SEVENTY_EIGHT,
		SEVENTY_NINE, EIGHTY, EIGHTY_ONE, EIGHTY_TWO, EIGHTY_THREE,
		EIGHTY_FOUR, EIGHTY_FIVE, EIGHTY_SIX, EIGHTY_SEVEN,
		EIGHTY_EIGHT, EIGHTY_NINE, NINETY, NINETY_ONE, NINETY_TWO,
		NINETY_THREE, NINETY_FOUR, NINETY_FIVE, NINETY_SIX,
		NINETY_SEVEN, NINETY_EIGHT, NINETY_NINE;
	}
}