class Blast {



    public static void main(String[] args) {
        System.out.println("Path exists: "+findPath(new int[][]{{0,0},{1,1}}));
        System.out.println("Path exists: "+findPath(new int[][]{{0,1},{1,1}}));
        System.out.println("Path exists: "+findPath(new int[][]{{0,1},{1,0}}));
        System.out.println("Path exists: "+findPath(new int[][]{{1,0},{1,0}}));
    }

    private static boolean findPath(int[][] a) {
        if(a[0][0] == 1){
            a[0][0] = 0;
            return dfs(0, 0, a, false);
        }
        return dfs(0, 0, a, true);
    }

    private static boolean dfs(int r, int c, int[][] a, boolean blast) {
        if (r < 0 || r >= a.length) return false;
        if (c < 0 || c >= a.length) return false;


        if (a[r][c] == 1) return false;
        if (a[r][c] == -1) return false;
        if (r == a.length - 1 && c == a.length - 1) return true;

        a[r][c] = -1;
        if (checkPoint(r + 1, c, a, blast)) return true;
        if (checkPoint(r, c + 1, a, blast)) return true;
        if (checkPoint(r-1, c, a, blast)) return true;
        if (checkPoint(r, c - 1, a, blast)) return true;

        a[r][c] = 0;
        return false;
    }

    private static boolean checkPoint(int r, int c, int[][] a, boolean blast) {
        if (r < 0 || r >= a.length) return false;
        if (c < 0 || c >= a.length) return false;
        boolean reached = dfs(r, c, a, blast);
        if (reached) return true;

        if (a[r][c] == 1 && blast) {
            a[r][c] = 0;
            reached = dfs(r, c, a, false);
            if(reached) return true;
            a[r][c] = 1;
        }

        return false;
    }
}


