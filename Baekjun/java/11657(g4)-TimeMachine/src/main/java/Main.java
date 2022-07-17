import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    /**
     * testcase 1
     *
     3 4
     1 2 4
     1 3 3
     2 3 -1
     3 1 -2

     4
     3
     */

    /**
     * test case2
     *
     3 4
     1 2 4
     1 3 3
     2 3 -4
     3 1 -2

     -1
     */

    /**
     * test case 3
     * 3 2
     * 1 2 4
     * 1 2 3
     * <p>
     * 3
     * -1
     */
    static int[][] graph;
    static int[] distance;
    static int n, m;
    static boolean flag;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        graph = new int[n + 1][n + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                graph[i][j] = 10001;
            }
        }

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            if (graph[from][to] > cost) graph[from][to] = cost;
        }

//        queue.add(new queue(1, 0));
//        for(int i = 1; i < n+1; i++){
//            if(graph[1][i] != 10001){
//                queue.add(new queue(i, graph[1][i]));
//                break;
//
//            }
//        }
        find();

//        find2();
        if (flag)
            System.out.println("-1");
        else {
            for (int i = 2; i < n + 1; i++) {
                if (distance[i] != 10001)
                    System.out.println(distance[i]);
                else
                    System.out.println("-1");

            }

        }
    }

    static void find2() {
        Queue<point> q = new LinkedList<>();
        distance = new int[n + 1];
        for (int i = 1; i < n + 1; i++)
            distance[i] = 10001;

        q.add(new point(1, 0));

        int count = 0;
        while (!q.isEmpty()) {
            point element = q.poll();
            count++;
            if (count > m) {
                flag = true;
                break;
            }
            int from = element.from;
            int cost = element.cost;

            if (distance[from] < cost)
                continue;

            if (distance[from] < 0 && cost < 0) {
                flag = true;
                break;
            }
            for (int i = 1; i < n + 1; i++) {
                if (graph[from][i] != 10001) {
                    int sum = cost + graph[from][i];

                    if (sum < distance[i]) {
                        distance[i] = sum;
                        q.add(new point(i, sum));
                    }
                }
            }
        }

    }

    static void find() {
        Queue<point> q = new LinkedList<>();

        distance = new int[n + 1];
        for (int i = 1; i < n + 1; i++)
            distance[i] = 10001;

        distance[1] = 0;

        for (int i = 1; i < n + 1; i++) {
            if (graph[1][i] != 10001) {
                q.add(new point(1, i, graph[1][i]));
//                break;
            }
        }
//        int now = 1;

        /**
         * graph[][]
         * 1 [2, 4]
         * 2 [3]
         * 3 [1]
         */

        /**
         * cost - 한 간선 비용
         * from - 출발 정점
         * to - 도착 정점
         *
         *
         */
        int count = 0;
        while (!q.isEmpty()) {
            point element = q.poll();
            count++;

            int from = element.from;
            int to = element.to;
            int cost = element.cost;

            //저장된 값이 비용보다 작으면 다음 사이클
            if (distance[to] < cost)
                continue;

            if (distance[from] < 0 && cost < 0) {
                flag = true;
                break;
            }

            /**
             * distance[from] + cost < distance[to]
             */
            if (distance[from] + cost < distance[to]) {
                distance[to] = distance[from] + cost;
                if (count > m) {
                    flag = true;
                    break;
                }
                for (int next = 1; next < n + 1; next++) {
                    int nextCost = graph[to][next];
                    if (nextCost != 10001) {
                        q.add(new point(to, next, nextCost));
                    }
                }
            }
        }
    }
}

class point {
    int from;
    int to;
    int cost;

    public point(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public point(int from, int cost) {
        this.from = from;
        this.cost = cost;
    }
}

