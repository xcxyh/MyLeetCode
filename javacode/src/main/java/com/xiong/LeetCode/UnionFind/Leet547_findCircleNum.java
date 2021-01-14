package com.xiong.LeetCode.UnionFind;

/**
 * @author ：xiongcong
 * @date ：Created in 2020/6/8 15:53
 * @description： 547. 朋友圈
 * @modified By：
 * @version: $
 */
public class Leet547_findCircleNum {

    int count;
    public int findCircleNum_new(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }

        //并查集
        int n = isConnected.length;
        count = n;
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = i;
        }

        //
        for (int i  = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    merge(f, i, j);
                }
            }
        }

        return count;

    }

    private void merge(int[] f, int p, int q) {
        int fp = findf(f, p);
        int fq = findf(f, q);

        if (fp != fq) {

            f[fp] =fq;
            count--;
        }
    }


    private int findf(int[] f, int x) {

        while (f[x] != x) {
            f[x] = f[f[x]];
            x = f[x];
        }
        return f[x];
    }




    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) {
            return 0;
        }
        int n = M.length;

        UF uf = new UF(n);

        //遍历建立连通关系
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    uf.union(i, j);
                }

            }
        }

        return uf.count();
    }

    // 内部类
    class UF {
        private int count;
        private int[] parent;
        public UF(int n) {
            this.count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int p, int q) {
            int rootp = find(p);
            int rootq = find(q);
            // 相等就直接返回，已经是一个了，不再合并
            if (rootp == rootq) {
                return;
            }
            parent[rootp] = rootq;
            this.count--;
        }
        public boolean connected(int p, int q) {
            int rootp = find(p);
            int rootq = find(q);

            return rootp == rootq;
        }
        public int count() {
            return this.count;
        }
        private int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
    }

}
