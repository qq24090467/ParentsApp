package com.aiparent.parentsapp.utill;

import net.sourceforge.pinyin4j.PinyinHelper;


public class LetterUtil
{
    /**
     * @param chinese 一个汉字
     * @return 拼音首字母
     * @Description:
     * @Author Justlcw
     * @Date 2014-5-8
     */
    public static String[] getFirstPinyin(char chinese)
    {
        return PinyinHelper.toHanyuPinyinStringArray(chinese);
    }

    /**
     * 是否是字母
     *
     * @return true 字母,false 非字母
     * @Description:
     * @Author Justlcw
     * @Date 2014-5-8
     */
    public static boolean isLetter(char c)
    {
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 112);
    }
}
