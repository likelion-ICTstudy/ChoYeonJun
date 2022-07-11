import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int n, m;
    static int[][] map;
    static int result = 1;

    public static void main(String[] args) {

        //값 입력
        /*
        *
         8 9
         0 0 0 0 0 0 0 0 0
         0 0 0 1 1 0 0 0 0
         0 0 0 1 1 0 1 1 0
         0 0 1 1 1 1 1 1 0
         0 0 1 1 1 1 1 0 0
         0 0 1 1 0 1 1 0 0
         0 0 0 0 0 0 0 0 0
         0 0 0 0 0 0 0 0 0
        *
         */
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];

//        int startx = -1;
//        int starty = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int next = sc.nextInt();
                map[i][j] = next;
//                if(startx == - 1 && next == 1)
//                {
//                    startx = i;
//                    starty = j;
//                }

            }
        }



//        for (int i = startx; i < n; i++) {
//            for (int j = starty; j < m; j++) {
//                if(dfs(i, j, new boolean[n][m])){
//                    for(Point point : points){
//                        int row = point.x;
//                        int col = point.y;
//
//                        map[row][col] = 0;
//                    }
//
//                    points.clear();
//                    result++;
//                }
//            }
//        }
        dfs(0, 0);
        reset();
        while(!isDone()){
            result ++;
            dfs(0, 0);
            reset();
        }


        System.out.println(result);

    }

    public static void reset(){
        for(int i = 0; i < n ; i ++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] == -1 || map[i][j] == 0) map[i][j] = 0;
                else if(map[i][j] > 2) map[i][j] = 0;
                else map[i][j] = 1;

//                if(map[i][j] <= 2 && map[i][j] >= 1)

            }
        }
    }

    public static boolean isDone(){
        for(int i = 0; i < n ; i ++){
            for(int j = 0 ; j < m ; j ++){
                if(map[i][j] != 0)
                    return false;
            }
        }
            return true;
    }


    public static void dfs(int x, int y){
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

//        if (x < 0 || x >= n || y < 0 || y >= m)
//            return;

//        if(map[x][y] == 0)
//            map[x][y] = -1;
//        else if(map[x][y] == -1)
//            return;
//        else map[x][y] += 1;
//        else if(map[x][y] == 0)
//            map[x][y] = -1;
//        else map[x][y] = -1;
//        if(map[x][y] == 0)
//            map[x][y] = -1;
//        else if(map[x][y] == -1)
//            return;
//        else{
//            map[x][y] += 1;
//        }


//        if (x < 0 || x >= n || y < 0 || y >= m)
//            return;
//
//        if (map[x][y] == 1)
//            return;

//        dfs(x+1, y);
//        dfs(x-1, y);
//        dfs(x, y+1);
//        dfs(x, y-1);
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

//            if(map[nx][ny] != 0 && map[nx][ny] != -1)
//                map[nx][ny] += 1;
//            else
//            {
            if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                continue;

            if(map[nx][ny] == -1)
                continue;
            else if (map[nx][ny] == 0) {
                map[nx][ny] = -1;
                dfs(nx, ny);
            } else
//                if (map[nx][ny] != 0)
                map[nx][ny] += 1;

//            }
        }
    }
}

//    public static void findInsideZero(int x, int y, boolean[][] isVisited){
//        int[] dx = {1, 0, -1, 0};
//        int[] dy = {0, 1, 0, -1};
//
//        if (x < 0 || x >= n || y < 0 || y >= m)
//            return;
//
//        if(map[x][y] == 1)
//            return;
//
//        if(isVisited[x][y])
//            return;
//
//        isVisited[x][y] = true;
//
//        for (int i = 0; i < 4; i++) {
//            int nx = x + dx[i];
//            int ny = y + dy[i];
//
//            if (map[nx][ny] == 0 && !zeros.contains(new Point(nx, ny)))
//                zeros.add(new Point(nx, ny));
//
//        }
//    }

//    public static boolean dfs(int x, int y, boolean[][] isVisited) {
//        int[] dx = {1, 0, -1, 0};
//        int[] dy = {0, 1, 0, -1};
//        if (x < 0 || x >= n || y < 0 || y >= m)
//            return false;
//
//        if(map[x][y] == 0)
//            return false;
//
//        if(isVisited[x][y])
//           return false;
//
//        isVisited[x][y] = true;
//        int zeroCount = 0;
//
//        for (int i = 0; i < 4; i++) {
//            int nx = x + dx[i];
//            int ny = y + dy[i];
//
//
//            if (map[nx][ny] == 0){
//                zeroCount++;
//                if(!zeros.contains(new Point(nx, ny)))
//                    zeros.add(new Point(nx, ny));
//            }
//        }
//        if (zeroCount >= 2 && !points.contains(new Point(x, y))) {
//            points.add(new Point(x, y));
////                map[x][y] = 0;
//        }
//        dfs(x + 1, y, isVisited);
//        dfs(x - 1, y, isVisited);
//        dfs(x, y + 1, isVisited);
//        dfs(x, y - 1, isVisited);
//
////        dfs(x+1, y);
////        dfs(x-1, y);
////        dfs(x, y+1);
////        dfs(x, y-1);
//        return true;
//    }
//
//}


