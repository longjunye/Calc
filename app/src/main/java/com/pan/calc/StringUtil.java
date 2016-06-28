package com.pan.calc;

import android.net.Uri;
import android.text.SpannableString;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Pattern;

public class StringUtil {
	public static final int EMPTY_INT = 0;
	public static final String EMPTY_STRING = "";
	public static final String COMMA_FORMAT = "#,###";
	public static final String PRICE_WON_FORMAT = COMMA_FORMAT + "원";
	public static final String LOWEST_PRICE_WON_FORMAT = PRICE_WON_FORMAT + "~";
	public static final String EA_FORMAT = COMMA_FORMAT + "개";
	public static final String BUY_EA_FORMAT = EA_FORMAT + " 구매중";
	public static final String PERCENT_FORMAT = "%";
	public static final String COMMA_WITH_SPACE = ", ";
	public static final String SINGLE_QUOTATION_MARK = "'";
	public static final String SPACE = " ";
	public static final int INVALID_INDEX = Integer.MIN_VALUE;

	public static String checkEmpty(String str) {
		return checkEmpty(str, EMPTY_STRING);
	}

	public static String checkEmpty(String str, String defaultString) {
		if (str == null || "".equals(str)) {
			return defaultString;
		}
		return str.trim();
	}

	public static int checkInt(String str) {
		return checkInt(str, EMPTY_INT);
	}

	public static int checkInt(String str, int defaultInt) {
		if (str == null) {
			return defaultInt;
		}

		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return defaultInt;
		}
	}

	public static long checkLong(String str, long defaultLong) {
		if (str == null) {
			return defaultLong;
		}

		try {
			return Long.parseLong(str);
		} catch (NumberFormatException nfe) {
			return defaultLong;
		}
	}

	public static boolean isEmpty(String str) {
		return (str == null) || (str.trim().length() == 0);
	}

	public static boolean isEmpty(SpannableString str) {
		return ((str == null) || isEmpty(str.toString()));
	}

	public static boolean isNotEmpty(SpannableString str) {
		return !isEmpty(str);
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isEmpty(StringBuilder sb) {
		return (sb == null || isEmpty(sb.toString()));
	}

	public static boolean isNotEmpty(StringBuilder sb) {
		return !isEmpty(sb);
	}

	public static boolean isEmpty(CharSequence str) {
		return ((str == null) || str.toString().trim().length() == 0);
	}

	public static boolean isNotEmpty(CharSequence str) {
		return !isEmpty(str);
	}

	/**
	 * 금액 원 단위로 분리
	 *
	 * Function Name : stringFormat()
	 * @param stringNum : 변경할 숫자 (int)
	 * @return String : 변경된 금액 반환 Ex)30000->30,000
	 */
	public static String stringFormatNumber(double stringNum) {
		String number;
		if (stringNum != 0) {
			NumberFormat numForm = NumberFormat.getInstance();
			number = numForm.format(stringNum);
		} else {
			number = "0";
		}
		return number;
	}

	/**
	 * 금액 원 단위로 부리
	 *
	 * Function Name : stringFormat()
	 * @param stringNum : 변경할 숫자 (String)
	 * @return String : 변경된 금액 반환 Ex)30000->30,000원
	 */
	public static String stringFormatNumber(String stringNum) {
		String number;
		try {
			if (stringNum != null) {
				int num = Integer.parseInt(stringNum);
				NumberFormat numForm = NumberFormat.getInstance();

				number = numForm.format(num);
			} else {
				number = "0";
			}
		} catch (NumberFormatException e) {
			number = "0";
		}
		return number;
	}

	public static String formatNumber(double num, String format) {
		DecimalFormat df = new DecimalFormat(format);

		return df.format(num);
	}

	public static String formatNumber(String stringNum, String format) {
		try {
			DecimalFormat df = new DecimalFormat(format);

			int num = Integer.parseInt(stringNum);

			return df.format(num);
		} catch (Exception e) {
			return "";
		}
	}

	public static boolean isHttpUri(String str) {
		if (str != null && str.startsWith("http")) {
			try {
				Uri.parse(str);
				return true;
			} catch (Exception ex) {
			}
		}
		return false;
	}

	public static boolean isImageUrl(String url) {
		if (isNotEmpty(url) && isHttpUri(url)) {
			return url.endsWith(".png") || url.endsWith(".jpg");
		}

		return false;
	}

	public static boolean equals(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		} else {
			return str1.equals(str2);
		}
	}

	public static boolean equalsAndNotEmpty(String str1, String str2) {
		return isNotEmpty(str1) && str1.equals(str2);
	}

	/**
	 * str이 길 경우 maxLength 길이만큼만 잘라서 반환
	 * @param str
	 * @param maxLength
	 * @return
	 */
	public static String shorten(String str, int maxLength) {
		if (str == null || str.length() <= maxLength) {
			return str;
		}

		if (maxLength == 0) {
			return "";
		}

		return str.substring(0, maxLength - 1) + "~";
	}

	public static boolean isNumeric(String str) {
		String numPattern = "[+-]?\\d+";

		Pattern pattern = Pattern.compile(numPattern);
		return pattern.matcher(str).matches();
	}



	/**
	 * Represents a failed index search.
	 * @since 2.1
	 */
	public static final int INDEX_NOT_FOUND = -1;

	/**
	 * The empty String {@code ""}.
	 * @since 2.0
	 */
	public static final String EMPTY = "";

	// SubStringAfter/SubStringBefore
	//-----------------------------------------------------------------------
	/**
	 * <p>Gets the substring before the first occurrence of a separator.
	 * The separator is not returned.</p>
	 *
	 * <p>A {@code null} string input will return {@code null}.
	 * An empty ("") string input will return the empty string.
	 * A {@code null} separator will return the input string.</p>
	 *
	 * <p>If nothing is found, the string input is returned.</p>
	 *
	 * <pre>
	 * StringUtils.substringBefore(null, *)      = null
	 * StringUtils.substringBefore("", *)        = ""
	 * StringUtils.substringBefore("abc", "a")   = ""
	 * StringUtils.substringBefore("abcba", "b") = "a"
	 * StringUtils.substringBefore("abc", "c")   = "ab"
	 * StringUtils.substringBefore("abc", "d")   = "abc"
	 * StringUtils.substringBefore("abc", "")    = ""
	 * StringUtils.substringBefore("abc", null)  = "abc"
	 * </pre>
	 *
	 * @param str  the String to get a substring from, may be null
	 * @param separator  the String to search for, may be null
	 * @return the substring before the first occurrence of the separator,
	 *  {@code null} if null String input
	 * @since 2.0
	 */
	public static String substringBefore(final String str, final String separator) {
		if (isEmpty(str) || separator == null) {
			return str;
		}
		if (separator.isEmpty()) {
			return EMPTY;
		}
		final int pos = str.indexOf(separator);
		if (pos == INDEX_NOT_FOUND) {
			return str;
		}
		return str.substring(0, pos);
	}

	/**
	 * <p>Gets the substring after the first occurrence of a separator.
	 * The separator is not returned.</p>
	 *
	 * <p>A {@code null} string input will return {@code null}.
	 * An empty ("") string input will return the empty string.
	 * A {@code null} separator will return the empty string if the
	 * input string is not {@code null}.</p>
	 *
	 * <p>If nothing is found, the empty string is returned.</p>
	 *
	 * <pre>
	 * StringUtils.substringAfter(null, *)      = null
	 * StringUtils.substringAfter("", *)        = ""
	 * StringUtils.substringAfter(*, null)      = ""
	 * StringUtils.substringAfter("abc", "a")   = "bc"
	 * StringUtils.substringAfter("abcba", "b") = "cba"
	 * StringUtils.substringAfter("abc", "c")   = ""
	 * StringUtils.substringAfter("abc", "d")   = ""
	 * StringUtils.substringAfter("abc", "")    = "abc"
	 * </pre>
	 *
	 * @param str  the String to get a substring from, may be null
	 * @param separator  the String to search for, may be null
	 * @return the substring after the first occurrence of the separator,
	 *  {@code null} if null String input
	 * @since 2.0
	 */
	public static String substringAfter(final String str, final String separator) {
		if (isEmpty(str)) {
			return str;
		}
		if (separator == null) {
			return EMPTY;
		}
		final int pos = str.indexOf(separator);
		if (pos == INDEX_NOT_FOUND) {
			return EMPTY;
		}
		return str.substring(pos + separator.length());
	}
}
