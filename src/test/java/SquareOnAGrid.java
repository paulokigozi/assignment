public class SquareOnAGrid {

    /*
        Given an array of points ( x,y ), determine if any group of 4 points make a square.
        i.e.
        Example. 1
        |
        |  *(1,4)   *(4,4)
        |         *(3,2)
        |  *(1,1)   *(4,1)
        |__ __ __ __ __ __ __ __

        Returns true;


        Example. 2
        |
        |      *(2,3)   *(5,3)
        |         *(3,2)
        |   *(1,1)   *(4,1)
        |__ __ __ __ __ __ __ __

        Returns false;




    */

    public static void main(String[] args){
        System.out.println(hasSquare(new Point[]{new Point(1, 1), new Point(3, 1), new Point(1,3), new Point(3,3)}));
        System.out.println(hasSquare(new Point[]{new Point(1, 0), new Point(3, 1), new Point(1,3), new Point(3,3)}));
        System.out.println(hasSquare(new Point[]{new Point(1, 0), new Point(3, 1), new Point(1,3),
                new Point(4, 0)
                , new Point(3,3)
                , new Point(4, 3)
                , new Point(1, 3)
        }));


        System.out.println(hasSquare(new Point[]{new Point(1, 1), new Point(4, 1), new Point(3,2),
                new Point(2, 3)
                , new Point(5,3)
        }));


        System.out.println(hasSquare(new Point[]{
                  new Point(1, 1)
                , new Point(4, 1)
                , new Point(3,2)
                , new Point(1, 4)
                , new Point(4,4)
        }));

        System.out.println(hasSquare(new Point[]{
                  new Point(1, 1)
                , new Point(4, 1)
                , new Point(3,2)
                , new Point(2, 3)
                , new Point(5,3)
        }));



    }


    public static boolean hasSquare(Point[] points) {
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Point p : points) {
            maxX = Math.max(maxX, p.x);
            maxY = Math.max(maxY, p.y);
        }

        int[][] grid = new int[maxX + 1][maxY + 1];

        for (Point p : points) {
            grid[p.x][p.y] = 1;
        }

        return hasSquare(0, grid, points);
    }

    private static boolean hasSquare(int m, int[][] grid, Point[] points) {
        if(m >= points.length) return false;

        Point p = points[m];
        int x = p.x;
        int y = p.y;

        if(checkLeft(x, y, grid)) return true;
        return hasSquare(m+1, grid, points);

    }

    private static boolean checkLeft(int x, int y, int[][] grid) {
        for(int i = x + 1; i < grid.length; i++){
            if(grid[i][y] == 1){
                if(topMakesSquare(x, y, i-x, grid)){
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean topMakesSquare(int x, int y, int wid, int[][] grid) {
        int x2 = x + wid;
        int y2 = y;
        if(!isPointOnGrid(x2, y2, grid)) return false;

        int x3 = x;
        int y3 = y+ wid;

        if(!isPointOnGrid(x3, y3, grid)) return false;

        int y4 = y + wid;
        int x4 = x2;

        if(!isPointOnGrid(x4, y4, grid)) return false;

        return true;
    }


    private static boolean isPointOnGrid(int x, int y, int[][] grid) {
        if(x < 0 || x >= grid.length) return false;
        if(y < 0 || y >= grid[0].length) return false;

        if(grid[x][y] == 1) return true;
        return false;

    }


    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}