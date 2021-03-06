package com.xiong.LeetCode.StringsProblem;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：xiongcong
 * @date ：Created in 2020/3/20 16:20
 * @description： 205. 同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * <p>
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * <p>
 * 所有出现的字符都必须用另一个字符替换
 * 同时保留字符的顺序。两个字符不能映射到同一个字符上，
 * 但字符可以映射自己本身。
 * @modified By：
 * @version: $
 */
public class S6_isIsomorphic {
    public static void main(String[] args) {
        System.out.println(isIsomorphic("abcdbe", "cgfkzc"));
    }

    /**
     * @author: xiongcong
     * @Date: 2020/3/20 16:21
     * @Description: 别人写的
     * 输入: s = "egg", t = "add"
     * 输出: true
     */
    public static boolean isIsomorphic(String s, String t) {
        int[] sc = new int[256];
        int[] tc = new int[256];

        for (int i = 0; i < s.length(); i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            //上一轮中 分别所映射的值不等
            if (sc[c1] != tc[c2]){
                return false;
            }
            // 将不同的字符映射到相同的值 ，这个值为 当前下标 + 1
            sc[c1] = i + 1;
            tc[c2] = i + 1;
        }
        return true;
    }

}
