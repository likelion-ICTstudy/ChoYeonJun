import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int n, m;
    static int[][] map;

    public static void main(String[] args) {
        //test case
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

        //값 입력
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


        /*
        * isDone 함수 실행 시간을 아끼기 위해
        * 처음에는 바로 실행 후 리셋
        * 초기 결과 값 1로 설정
         */
        int result = 1;
        dfs(0, 0);
        reset();


        while(!isDone()){
            result ++;
            dfs(0, 0);
            reset();
        }

        System.out.println(result);

    }

    /*
    * dfs 함수
     */
    public static void dfs(int x, int y){
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        /*
        * 상하좌우 검색 for 문
         */
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            /*
             * 상하좌우가 범위를 벗어나면 continue
             */
            if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                continue;
            /*
             * if - 상하좌우 -1이면 continue
             * else if - 상하좌우 0이면 -1로 변경(marking)
             *           재귀 dfs 실행
             *
             * else - 상하좌우가 -1, 0 이 아니면 치즈
             *        0을 기준으로 1을 검색 한 것
             *                0
             *             1  0  0
             *                0
             *        치즈의 값을 1 더해줌
             *        한 면의 0이 인접해있으면 2, 두 면이 인접해있으면 3
             */
            if(map[nx][ny] == -1)
                continue;
            else if (map[nx][ny] == 0) {
                map[nx][ny] = -1;
                dfs(nx, ny);
            }
            else
                map[nx][ny] += 1;

        }
    }

    /*
     * map을 돌아다니면서 각 값을 보고 치즈와 공기 값으로 되돌려 줌
     * -1, 0 은 다시 0으로
     * 3이상 값은 치즈가 녹아 0으로
     * 다른 값들은 그대로 있는 치즈 1
     */
    public static void reset(){
        for(int i = 0; i < n ; i ++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] == -1 || map[i][j] == 0 || map[i][j] > 2) map[i][j] = 0;
                else map[i][j] = 1;
            }
        }
    }

    /*
     * map을 돌아다니면서 0이 아닌 값, 치즈가 존재하는지 확인
     * 치즈 존재 - false
     * 치즈 없음 - true
     */
    public static boolean isDone(){
        for(int i = 0; i < n ; i ++){
            for(int j = 0 ; j < m ; j ++){
                if(map[i][j] != 0)
                    return false;
            }
        }
            return true;
    }



}


