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

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int next = sc.nextInt();
                map[i][j] = next;

            }
        }



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
                if(map[i][j] == -1 || map[i][j] == 0 || map[i][j] > 2) map[i][j] = 0;
                else map[i][j] = 1;
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

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                continue;

            if(map[nx][ny] == -1)
                continue;
            else if (map[nx][ny] == 0) {
                map[nx][ny] = -1;
                dfs(nx, ny);
            } else
                map[nx][ny] += 1;

        }
    }
}


