package com.xiong.JZOffer;

/**
 * @author ：xiongcong
 * @date ：Created in 2020/1/3 13:22
 * @description： 给定一个正整数n，将其分解为至少两个正整数的和，并使这些整数的乘积最大化。返回您可以获得的最大乘积。
 * 输入：10
 * 输出：36
 * 说明： 10 = 3 + 3 + 4，3×3×4 = 36。
 * @modified By：
 * @version: $
 */
public class J14_CutRope {
    public static void main(String[] args) {
        System.out.println(integerBreak_greedy(10));
    }

    // 大数情况下取余问题
    public int cuttingRope(int n) {
        int mod = 1000000007;
        if (n <= 3) {
            return n - 1;
        }
        long res = 1L;
        while (n > 4) {
            res = res * 3 % mod;
            n -= 3;
        }
        //此时 n 只可能为 2 3 4
        return (int) (res * n % mod);
    }

    /**
     * @author: xiongcong
     * @Date: 2020/1/3 13:47
     * @Description: 依题意，绳子至少被剪一次，所以绳子长度最小为2。外层for循环从绳长为i=2的情况开始依次计算，直到计算到绳长为n的情况。
     * 内层for循环：当绳长为i时，由于已知至少剪一刀，我们索性假设第一刀剪在长度为j的位置(即第一段绳子长度为j)。
     * 剩下的那段长度为( i - j )的绳子就变成了“可剪可不剪”。
     * 那究竟是“不剪了”得到的乘积大呢，还是“继续剪余下的这段”得到乘积更大？我们不知道，
     * 所以需要两种情况都计算一下进行比较。其中，“不剪了”得到的乘积是j * ( i - j )，
     * “继续剪”得到的乘积是j * dp[ i - j ]。取其中的较大值，就是“第一剪在j位置”能得到的最大乘积。
     * 而第一剪的所有可能位置是1,2,…,i-1。依次计算所有可能情况，取最大值即为dp[ i ]的值。
     * 由上述过程可知，只有先依次计算出dp[2],dp[3],....的值，才能得到dp[n]的值。此为动态规划。
     */
    public static int integerBreak_dp(int n) {

        int[] dp = new int[n + 1];

        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        return dp[n];
    }

    /**
     * @author: xiongcong
     * @Date: 2020/1/3 13:54
     * @Description: 贪心
     * 尽可能多剪长度为 3 的绳子，并且不允许有长度为 1 的绳子出现。
     * 数学证明：设 x 为 每段绳子的长度， 当 f(x) = x^(n/x) 取极大值时，
     * 两边同时取 ln 然后 两边同时求导 可得 y' = n * (1- lnx) / x^2 * y = 0
     * 可得 1 - lnx = 0 -----> x = e
     * x = e  即 每段长度都为 e 时 乘积最大
     * 又 每段长度 为整数 所以取3  即 尽可能多剪长度为 3 的绳子
     */
    public static int integerBreak_greedy(int n) {

        // 长度为 3 的 数量尽可能多即为最最优
        //例外 是 4 不能分成 3 和 1

        if (n <= 3) {
            return n - 1;
        }

        if (n % 3 == 1) {

            int m = n / 3 - 1;
            return (int) Math.pow(3, n / 3 - 1) * 4;
        }

        if (n % 3 == 2) {
            return (int) Math.pow(3, n / 3) * 2;
        }

        return (int) Math.pow(3, n / 3);
    }
}
