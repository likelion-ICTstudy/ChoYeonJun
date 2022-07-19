import java.util.*;

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

    /**
     5 9
     1 2 3
     1 3 5
     1 4 1
     2 3 6
     2 5 4
     3 4 -3
     3 5 -3
     4 2 -2
     4 5 -1
     */

    /**
     * 3 2
     * 2 3 -2
     * 3 2 -2
     * <p>
     * -1
     * -1
     * <p>
     * 4 3
     * 1 2 1
     * 3 4 -1
     * 4 3 -1
     * <p>
     * 1
     * -1
     * -1
     * <p>
     * 2 3
     * 1 2 3
     * 1 2 2
     * 1 2 1
     * <p>
     * <p>
     * 3 1
     * 2 3 -10000
     * <p>
     * -1
     * -1
     * <p>
     * <p>
     * 4 5
     * 1 4 3
     * 4 2 4
     * 2 3 -4
     * 3 4 -2
     * 4 3 3
     * <p>
     * -1
     * <p>
     * <p>
     * 3 2
     * 2 3 -1
     * 3 2 -1
     * <p>
     * -1
     * -1
     * <p>
     * 3 3
     * 1 2 3
     * 2 1 -1000
     * 2 1 5
     * <p>
     * -1
     * <p>
     * <p>
     * 2 2
     * 1 2 -1
     * 2 1  -1
     * <p>
     * -1
     */
    static int[][] graph;
    static point[] points;
    static double[] distance;
    static int n, m;
    static boolean flag;
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

//        graph = new int[n + 1][n + 1];

//        for (int i = 1; i < n + 1; i++) {
//            for (int j = 1; j < n + 1; j++) {
//                graph[i][j] = ;
//            }
//        }

        points = new point[m];
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            points[i] = new point(from, to, cost);
//            if (graph[from][to] > cost)
//                graph[from][to] = cost;
        }

//        queue.add(new queue(1, 0));
//        for(int i = 1; i < n+1; i++){
//            if(graph[1][i] != 1E9){
//                queue.add(new queue(i, graph[1][i]));
//                break;
//
//            }
//        }

        distance = new double[n + 1];
        for (int i = 1; i < n + 1; i++)
            distance[i] = 1E9;

//        for(int i = 0; i < n; i++)
//            find2(i);

//        find();
        boolean isCycle = find();
        if (isCycle) System.out.println(-1);
        else {
            for (int i = 2; i < n + 1; i++) {
                if (distance[i] == 1E9) System.out.println(-1);
                else System.out.println(distance[i]);

            }

        }
    }

    static boolean find() {

        distance[1] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                point point = points[j];

                int from = point.from;
                int to = point.to;
                int cost = point.cost;

                if (distance[from] == 1E9) continue;

                if (distance[from] + cost < distance[to]) {
                    distance[to] = distance[from] + cost;

                    if (i == n - 1) return true;
                }
            }
        }

        return false;
    }

    static void find2(int count) {
        Queue<point> q = new LinkedList<>();
//        distance = new int[n + 1];
//        for (int i = 1; i < n + 1; i++)
//            distance[i] = 1E9;

        q.add(new point(1, 0));

        while (!q.isEmpty()) {
            point element = q.poll();
            count++;
//            if (count > m) {
//                flag = true;
//                break;
//            }
            int from = element.from;
            int cost = element.cost;

            if (distance[from] < cost) continue;

            for (int i = 1; i < n + 1; i++) {
                if (graph[from][i] != 1E9) {
                    int sum = cost + graph[from][i];

                    if (sum < distance[i]) {
                        if (count == n - 1) {
                            flag = true;
                            return;
                        }

                        distance[i] = sum;
                        q.add(new point(i, sum));
                    }
                }
            }
        }

    }

    static void find3(int count) {
        Queue<point> q = new LinkedList<>();


        for (int i = 1; i < n + 1; i++) {
            if (graph[1][i] != 1E9) {
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
        while (!q.isEmpty()) {
            point element = q.poll();
            count++;

            int from = element.from;
            int to = element.to;
            int cost = element.cost;

            //저장된 값이 비용보다 작으면 다음 사이클
            if (distance[to] < cost) continue;

//            if (distance[from] < 0 && cost < 0) {
//                flag = true;
//                break;
//            }

            /**
             * distance[from] + cost < distance[to]
             */
            if (distance[from] + cost < distance[to]) {
                if (count == n - 1) {
                    flag = true;
                    return;
                }
                distance[to] = distance[from] + cost;
//                if (count > m) {
//                    flag = true;
//                    break;
//                }
                for (int next = 1; next < n + 1; next++) {
                    int nextCost = graph[to][next];
                    if (nextCost != 1E9) {
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

