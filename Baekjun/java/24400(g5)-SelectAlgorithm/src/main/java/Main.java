import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * input
 5 3
 2 5 1 4 3
 2 1 5 4 3
 * <p>
 10 3
 5 9 2 10 4 6 3 8 7 1
 1 2 3 4 6 5 7 8 9 10
 * <p>
 7 3
 10 4 5 8 6 11 26
 8 6 26 11 10 4 5

 7 3
 10 4 5 8 6 11 26
 4 5 6 8 10 11 26

 * <p>
 5 3
 3 6 2 5 4
 2 3 4 5 6
 */
public class Main {
    static int[] B;
    static int iMax = 0;

    public static void main(String args[]) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] A = new int[n];
        B = new int[n];
        st = new StringTokenizer(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i = 0; i < A.length; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st1.nextToken());
        }

        int result = select(A, 0, n - 1, q);
        if (result < 1)
            System.out.println(1);
        else
            System.out.println(0);

    }

    /**
     * select(A[], p, r, q) { # A[p..r]에서 q번째 작은 원소를 찾는다.
     * if (p = r) then return A[p];
     * t <- partition(A, p, r);  # 분할
     * k <- t - p + 1;           # 기준원소가 전체에서 k번째 작은 원소임
     * if (q < k) then return select(A, p, t - 1, q);  # 왼쪽 그룹으로 범위를 좁힘
     * else if (q = k) then return A[t];               # 기준원소가 찾는 원소임
     * else return select(A, t + 1, r, q - k);         # 오른쪽 그룹으로 범위를 좁힘
     * }
     */

    static int select(int[] A, int p, int r, int q) {
        if (Arrays.equals(A, B)) {
            return -2;
        }

        if (p == r) {
            return A[p];
        }

        int t = partition(A, p, r); // i + 1
        if (t == -2) {
            return -2;
        }
        /**
         * n번째 작은 값은 정렬된 배열에서 element 의 인덱스 + 1 값과 같다.
         * 1 2 3 4 5
         * 2 번째로 작은 값 = 2
         * 2의 인덱스 1
         * 1 + 1 = 2
         *
         * k = t / i + 1(기준 값 r의 인덱스) - p(찾아야 할 배열의 범위에서 가장 첫 인덱스) + 1
         * -> r은 k 번째 작은 값이다 !
         */
        int k = t - p + 1;

        /**
         * 기준 값 r의 순서 k를 찾아야 할 순서 q와 비교하여 탐색할 배열 범위 조정
         *
         * q가 k 보다 작다면 k 이전에 위치하는 element를 한정하여 탐색
         *
         * q가 k 보다 크다면 k 이후에 위치하는 element를 한정하여 탐색
         *
         * 시작점 p = t + 1, r
         *
         * k 가 7인데 q 가 9일 경우 앞에 7개의 element는 잘려나가기 때문에 q는 q-k가 된다.
         */
        if (q < k) {
            return select(A, p, t - 1, q);
        } else if (q == k) {
            return A[t];
        } else
            return select(A, t + 1, r, q - k);
    }

    /**
     * partition(A[], p, r) {
     * x <- A[r];    # 기준원소
     * i <- p - 1;   # i는 x보다 작거나 같은 원소들의 끝 지점
     * for j <- p to r - 1  # j는 아직 정해지지 않은 원소들의 시작 지점
     * if (A[j] ≤ x) then A[++i] <-> A[j]; # i값 증가 후 A[i] <-> A[j] 교환
     * if (i + 1 ≠ r) then A[i + 1] <-> A[r];  # i + 1과 r이 서로 다르면 A[i + 1]과 A[r]을 교환
     * return i + 1;
     * }
     */

    static int partition(int[] A, int p, int r) {
        int x = A[r];
        int i = p - 1;

        for (int j = p; j < r; j++) {

            if (A[j] < x) {
                i++;
                if(i == j) continue;

                swap(A, i, j);

                if (Arrays.equals(A, B)) {
                    return -2;
                }

            }
        }
        if (i + 1 != r) {
            swap(A,i+1, r);

            if (Arrays.equals(A, B)) {
                return -2;
            }
        }

        return i + 1;
    }

    public static void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] =temp;
    }
}