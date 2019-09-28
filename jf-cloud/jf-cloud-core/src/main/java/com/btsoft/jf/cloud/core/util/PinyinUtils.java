package com.btsoft.jf.cloud.core.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音工具类
 * 
 * @author chenbin
 */
public class PinyinUtils {
	
	private final static String CHINESE_REGULAR="[\\u4e00-\\u9fa5]+";

	public static String toPinyin(String str) {
		char[] chars = str.trim().toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		// 输出拼音全部小写
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		// 不带声调
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		StringBuffer sb=new StringBuffer();
		try {
			for (int i = 0; i < chars.length; i++) {
				// 如果字符是中文,则将中文转为汉语拼音
				if (String.valueOf(chars[i]).matches(CHINESE_REGULAR)) {
					sb.append(PinyinHelper.toHanyuPinyinStringArray(chars[i], defaultFormat)[0]);
				} else {// 如果字符不是中文,则不转换
					sb.append(chars[i]);
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			return sb.toString();
		}
		return sb.toString();
	}
}
