/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.hbase.util.Bytes;

/**
 * @author XuehuiHe
 * @date 2013年9月26日
 */
public class TypeParser {
	private static final SimpleDateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@SuppressWarnings("unchecked")
	public static <T> T parse(Object o, Class<T> targetClass) {
		if (o == null) {
			return null;
		}
		if (targetClass.equals(Short.class)) {
			return (T) getAsShort(o);
		} else if (targetClass.equals(Integer.class)) {
			return (T) getAsInteger(o);
		} else if (targetClass.equals(Long.class)) {
			return (T) getAsLong(o);
		} else if (targetClass.equals(Float.class)) {
			return (T) getAsFloat(o);
		} else if (targetClass.equals(Double.class)) {
			return (T) getAsDouble(o);
		} else if (targetClass.equals(Character.class)) {
			return (T) getAsCharacter(o);
		} else if (targetClass.equals(Boolean.class)) {
			return (T) getAsBoolean(o);
		} else if (targetClass.equals(Date.class)) {
			return (T) getAsDate(o);
		} else if (targetClass.equals(String.class)) {
			return (T) getAsString(o);
		} else if (targetClass.equals(Byte.class)) {
			return (T) getAsByte(o);
		}
		return null;
	}

	private static Short booleanToShort(Boolean b) {
		if (b) {
			return (short) 1;
		} else {
			return (short) 0;
		}
	}

	private static Short parseShort(String value) {
		try {
			return Short.parseShort(value);
		} catch (NumberFormatException e) {
			try {
				return ((Integer) Integer.parseInt(value)).shortValue();
			} catch (NumberFormatException nfe) {
				try {
					return ((Long) Long.parseLong(value)).shortValue();
				} catch (NumberFormatException nfe2) {
					return new BigInteger(value).shortValue();
				}

			}
		}
	}

	private static Integer parseInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException nfe) {
			try {
				return ((Long) Long.parseLong(value)).intValue();
			} catch (NumberFormatException nfe2) {
				return new BigInteger(value).intValue();
			}

		}
	}

	private static Long parseLong(String value) {
		try {
			return (Long) Long.parseLong(value);
		} catch (NumberFormatException nfe2) {
			return new BigInteger(value).longValue();
		}
	}

	public static Short getAsShort(Object o) {
		if (o instanceof Short) {
			return (Short) o;
		} else if (o instanceof Integer) {
			return ((Integer) o).shortValue();
		} else if (o instanceof Long) {
			return ((Long) o).shortValue();
		} else if (o instanceof Float) {
			return ((Float) o).shortValue();
		} else if (o instanceof Double) {
			return ((Double) o).shortValue();
		} else if (o instanceof Character) {
			return ((Integer) (int) (((Character) o).charValue())).shortValue();
		} else if (o instanceof Boolean) {
			return booleanToShort((Boolean) o);
		} else if (o instanceof Date) {
			return ((Long) ((Date) o).getTime()).shortValue();
		} else if (o instanceof String) {
			return parseShort((String) o);
		} else if (o instanceof Byte) {
			// TODO
			return null;
		}
		return null;
	}

	public static Integer getAsInteger(Object o) {
		if (o instanceof Short) {
			return ((Short) o).intValue();
		} else if (o instanceof Integer) {
			return (Integer) o;
		} else if (o instanceof Long) {
			return ((Long) o).intValue();
		} else if (o instanceof Float) {
			return ((Float) o).intValue();
		} else if (o instanceof Double) {
			return ((Double) o).intValue();
		} else if (o instanceof Character) {
			return ((Integer) (int) (((Character) o).charValue())).intValue();
		} else if (o instanceof Boolean) {
			return booleanToShort((Boolean) o).intValue();
		} else if (o instanceof Date) {
			return ((Long) ((Date) o).getTime()).intValue();
		} else if (o instanceof String) {
			return parseInt((String) o);
		} else if (o instanceof Byte) {
			// TODO
			return null;
		}
		return null;
	}

	public static Long getAsLong(Object o) {
		if (o instanceof Short) {
			return ((Short) o).longValue();
		} else if (o instanceof Integer) {
			return ((Integer) o).longValue();
		} else if (o instanceof Long) {
			return ((Long) o).longValue();
		} else if (o instanceof Float) {
			return ((Float) o).longValue();
		} else if (o instanceof Double) {
			return ((Double) o).longValue();
		} else if (o instanceof Character) {
			return ((Integer) (int) (((Character) o).charValue())).longValue();
		} else if (o instanceof Boolean) {
			return booleanToShort((Boolean) o).longValue();
		} else if (o instanceof Date) {
			return ((Date) o).getTime();
		} else if (o instanceof String) {
			return parseLong((String) o);
		} else if (o instanceof Byte) {
			// TODO
			return null;
		}
		return null;
	}

	public static Float getAsFloat(Object o) {
		if (o instanceof Short) {
			return ((Short) o).floatValue();
		} else if (o instanceof Integer) {
			return ((Integer) o).floatValue();
		} else if (o instanceof Long) {
			return ((Long) o).floatValue();
		} else if (o instanceof Float) {
			return ((Float) o).floatValue();
		} else if (o instanceof Double) {
			return ((Double) o).floatValue();
		} else if (o instanceof Character) {
			return ((Integer) (int) (((Character) o).charValue())).floatValue();
		} else if (o instanceof Boolean) {
			return booleanToShort((Boolean) o).floatValue();
		} else if (o instanceof Date) {
			return ((Long) ((Date) o).getTime()).floatValue();
		} else if (o instanceof String) {
			return Float.parseFloat((String) o);
		} else if (o instanceof Byte) {
			// TODO
			return null;
		}
		return null;
	}

	public static Double getAsDouble(Object o) {
		if (o instanceof Short) {
			return ((Short) o).doubleValue();
		} else if (o instanceof Integer) {
			return ((Integer) o).doubleValue();
		} else if (o instanceof Long) {
			return ((Long) o).doubleValue();
		} else if (o instanceof Float) {
			return ((Float) o).doubleValue();
		} else if (o instanceof Double) {
			return ((Double) o).doubleValue();
		} else if (o instanceof Character) {
			return ((Integer) (int) (((Character) o).charValue()))
					.doubleValue();
		} else if (o instanceof Boolean) {
			return booleanToShort((Boolean) o).doubleValue();
		} else if (o instanceof Date) {
			return ((Long) ((Date) o).getTime()).doubleValue();
		} else if (o instanceof String) {
			return Double.parseDouble((String) o);
		} else if (o instanceof Byte) {
			// TODO
			return null;
		}
		return null;
	}

	public static Character getAsCharacter(Object o) {
		if (o instanceof Short) {
			return (char) ((Short) o).intValue();
		} else if (o instanceof Integer) {
			return (char) ((Integer) o).intValue();
		} else if (o instanceof Long) {
			return (char) ((Long) o).intValue();
		} else if (o instanceof Float) {
			return (char) ((Float) o).intValue();
		} else if (o instanceof Double) {
			return (char) ((Double) o).intValue();
		} else if (o instanceof Character) {
			return (char) ((Integer) (int) (((Character) o).charValue()))
					.intValue();
		} else if (o instanceof Boolean) {
			return (char) booleanToShort((Boolean) o).intValue();
		} else if (o instanceof Date) {
			return null;
		} else if (o instanceof String) {
			return (char) Double.parseDouble((String) o);
		} else if (o instanceof Byte) {
			// TODO
			return null;
		}
		return null;
	}

	public static Boolean getAsBoolean(Object o) {
		if (o instanceof Short) {
			return (Short) o != 0;
		} else if (o instanceof Integer) {
			return (Integer) o != 0;
		} else if (o instanceof Long) {
			return (Long) o != 0;
		} else if (o instanceof Float) {
			return (Float) o != 0;
		} else if (o instanceof Double) {
			return (Double) o != 0;
		} else if (o instanceof Character) {
			return (int) (char) (Character) o != 0;
		} else if (o instanceof Boolean) {
			return (Boolean) o;
		} else if (o instanceof Date) {
			return true;
		} else if (o instanceof String) {
			return ((String) o).length() == 0;
		} else if (o instanceof Byte) {
			// TODO
			return null;
		}
		return null;
	}

	public static Date getAsDate(Object o) {
		if (o instanceof Long) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis((Long) o);
			return c.getTime();
		} else if (o instanceof String) {
			return parseDate((String) o);
		}
		return null;
	}

	private static Date parseDate(String value) {
		if (value.matches("^\\d+$")) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(Long.parseLong(value));
			return c.getTime();
		} else {
			Pattern pattern = Pattern
					.compile("^\\d{4}\\-\\d{2}-\\d{2}[\\s/]\\d{2}:\\d{2}:\\d{2}");
			Matcher matcher = pattern.matcher(value);
			String dateStr = null;
			if (matcher.find()) {
				dateStr = matcher.group();
			}
			if (dateStr == null) {
				return null;
			}
			dateStr = dateStr.replace("/", " ");

			Date date = null;
			try {
				date = df.parse(dateStr);
			} catch (ParseException e) {
			}
			return date;
		}
	}

	public static String getAsString(Object o) {
		if (o instanceof Date) {
			return df.format((Date) o);
		} else {
			return o.toString();
		}
	}

	public static byte[] toBytes(Object b) {
		if (b == null) {
			return null;
		}
		Class<?> clazz = b.getClass();
		if (clazz.equals(boolean.class) || clazz.equals(Boolean.class)) {
			return Bytes.toBytes((Boolean) b);
		} else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
			return Bytes.toBytes((Long) b);
		} else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
			return Bytes.toBytes((Short) b);
		} else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
			return Bytes.toBytes((Double) b);
		} else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
			return Bytes.toBytes((Float) b);
		} else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
			return Bytes.toBytes((Integer) b);
		} else if (clazz.equals(String.class)) {
			return Bytes.toBytes((String) b);
		} else if (clazz.equals(BigDecimal.class)) {
			return Bytes.toBytes((BigDecimal) b);
		} else if (clazz.equals(Date.class)) {
			Date d = (Date) b;
			return toBytes(d.getTime());
		} else if (clazz.equals(BigInteger.class)) {
			// return Bytes.toBytes((BigInteger) b);
			// TODO
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromBytes(byte[] b, Class<T> clazz) {
		if (b == null) {
			return null;
		}
		if (clazz.equals(boolean.class) || clazz.equals(Boolean.class)) {
			return (T) (Boolean) Bytes.toBoolean(b);
		} else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
			return (T) (Long) Bytes.toLong(b);
		} else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
			return (T) (Short) Bytes.toShort(b);
		} else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
			return (T) (Double) Bytes.toDouble(b);
		} else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
			return (T) (Float) Bytes.toFloat(b);
		} else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
			return (T) (Integer) Bytes.toInt(b);
		} else if (clazz.equals(String.class)) {
			return (T) Bytes.toString(b);
		} else if (clazz.equals(BigDecimal.class)) {
			return (T) Bytes.toBigDecimal(b);
		} else if (clazz.equals(Date.class)) {
			Date d = new Date();
			d.setTime(Bytes.toLong(b));
			return (T) d;
		} else if (clazz.equals(BigInteger.class)) {
			return (T) new BigInteger(b);
		}
		return null;
	}

	public static Byte getAsByte(Object o) {
		// TODO
		return null;
	}

	public static void main(String[] args) {
	}
}
