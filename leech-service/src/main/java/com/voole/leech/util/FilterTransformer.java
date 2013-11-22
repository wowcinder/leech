/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.leech.util;

import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.DependentColumnFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.FilterList.Operator;
import org.apache.hadoop.hbase.filter.NullComparator;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.filter.WritableByteArrayComparable;
import org.apache.hadoop.hbase.util.Bytes;

import com.voole.leech.shared.entity.filter.EtlFilter;
import com.voole.leech.shared.entity.filter.EtlFilterBase;
import com.voole.leech.shared.entity.filter.EtlFilterList;
import com.voole.leech.shared.entity.filter.EtlFilterBase.EtlFilterBaseOp;
import com.voole.leech.shared.entity.filter.EtlFilterList.EtlFilterListOp;

/**
 * @author XuehuiHe
 * @date 2013年10月22日
 */
public class FilterTransformer {
	public static Filter getFilter(EtlFilterList filterList) {
		FilterList fl = null;
		EtlFilterListOp op = filterList.getOperator();
		switch (op) {
		case MUST_PASS_ALL:
			fl = new FilterList(Operator.MUST_PASS_ALL);
			break;
		default:
			fl = new FilterList(Operator.MUST_PASS_ONE);
			break;
		}
		for (EtlFilter ef : filterList.getFilters()) {
			Filter f = null;
			if (ef instanceof EtlFilterBase) {
				f = getFilter((EtlFilterBase) ef);

			} else {
				f = getFilter((EtlFilterList) ef);
			}
			if (f != null) {
				fl.addFilter(f);
			}
		}
		if (fl.getFilters().size() > 0) {
			return fl;
		}

		return null;
	}

	public static Filter getFilter(EtlFilterBase filter) {
		EtlFilterBaseOp op = filter.getOperator();
		String columnName = filter.getColumn().getName();
		Object o = filter.getValue();
		if (o == null) {
			return null;
		}
		if (isSimpleCompare(op)) {
			return new SingleColumnValueFilter(Bytes.toBytes("d"),
					Bytes.toBytes(columnName), getFilterCompareOp(op),
					TypeParser.toBytes(o));
		}
		if (isNullCompare(op)) {
			if (op.equals(EtlFilterBaseOp.IS_NULL)) {
				SingleColumnValueFilter f = new SingleColumnValueFilter(
						Bytes.toBytes("d"), Bytes.toBytes(columnName),
						CompareOp.EQUAL, new NullComparator());
				f.setFilterIfMissing(true);
				return f;
			} else {
				return new DependentColumnFilter(Bytes.toBytes("d"),
						Bytes.toBytes(columnName));
			}
		}
		if (isMatchCompare(op)) {
			SingleColumnValueFilter f = new SingleColumnValueFilter(
					Bytes.toBytes("d"), Bytes.toBytes(columnName),
					getFilterCompareOp(op), getMatchComparator(op, (String) o));
			return f;
		}
		return null;
	}

	private static WritableByteArrayComparable getMatchComparator(
			EtlFilterBaseOp op, String expr) {
		switch (op) {
		case MATCH:
		case NOT_MATCH:
			return new RegexStringComparator(expr);
		default:
			return new SubstringComparator(expr);
		}

	}

	private static boolean isMatchCompare(EtlFilterBaseOp op) {
		return op.equals(EtlFilterBaseOp.MATCH)
				|| op.equals(EtlFilterBaseOp.NOT_MATCH)
				|| op.equals(EtlFilterBaseOp.CONTAINS)
				|| op.equals(EtlFilterBaseOp.NOT_CONTAINS);
	}

	private static boolean isNullCompare(EtlFilterBaseOp op) {
		return op.equals(EtlFilterBaseOp.IS_NULL)
				|| op.equals(EtlFilterBaseOp.IS_NOT_NULL);
	}

	private static boolean isSimpleCompare(EtlFilterBaseOp op) {
		return op.equals(EtlFilterBaseOp.LESS)
				|| op.equals(EtlFilterBaseOp.LESS_OR_EQUAL)
				|| op.equals(EtlFilterBaseOp.EQUAL)
				|| op.equals(EtlFilterBaseOp.NOT_EQUAL)
				|| op.equals(EtlFilterBaseOp.GREATER)
				|| op.equals(EtlFilterBaseOp.GREATER_OR_EQUAL);

	}

	private static CompareOp getFilterCompareOp(EtlFilterBaseOp op) {
		switch (op) {
		case LESS:
			return CompareOp.LESS;
		case LESS_OR_EQUAL:
			return CompareOp.LESS_OR_EQUAL;
		case EQUAL:
		case MATCH:
		case CONTAINS:
			return CompareOp.EQUAL;
		case NOT_MATCH:
		case NOT_CONTAINS:
		case NOT_EQUAL:
			return CompareOp.NOT_EQUAL;
		case GREATER:
			return CompareOp.GREATER;
		case GREATER_OR_EQUAL:
			return CompareOp.GREATER_OR_EQUAL;
		default:
			return CompareOp.EQUAL;
		}
	}
}
