import java.util.*;
import java.util.stream.Collectors;

public class Main {

    /* 요약
    * 1. 값 입력
    * 2. 고정 순서 결과 리스트에 입력
    * 3. 정해진 순서를 위한 빈 공간에서 여유 공간이 없다면 정해진 순서 값을 채워줌
    * 4. 정해진 순서에 1이 없다면 맨 앞에 1을 넣어줌
    * 5. 정해진 순서를 결과 리스트에 순서대로 입력
    * 6. 1의 위치 출력
     */

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        /*
        * 입력 값 저장
        * n - 소의 마리 수
        * m - 정해진 순서
        * k - 고정된 순서를 갖는 소의 마리 수
        *
        * orders - 결과 값 저장 배열
        * requires - 정해진 순서 저장 리스트
         */

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        List<Integer> requires = new ArrayList<>();

        int[] orders = new int[n];

        for(int i = 0; i < m; i++)
            requires.add(scanner.nextInt());

        //고정된 순서 결과에 입력
        for(int i = 0; i < k; i++){
            int cow = scanner.nextInt();
            int index = scanner.nextInt()-1;
            orders[index] = cow;
        }

        //contains, indexOf 함수 사용하기 위해 orders 배열 리스트로 변환
        List<Integer> copyOrders = Arrays.stream(orders).boxed().collect(Collectors.toList());

        /*
        * 정해진 순서에서 앞에 여유 공간이 없으면 결과에 채워 넣음
        * 3 0 5 0 0 0  / 4 5 6
        * 4가 들어갈 자리가 3 5 사이밖에 없기 때문에 결과 리스트에 입력
        * -> 3 4 5 0 0 0
        */
        for(int i = 0; i < requires.size(); i++){
            int require = requires.get(i);
            /*
            * requires 안에 고정 값이 있을 때
            * 고정 값은 이미 결과 값에 입력돼 있음
             */

            if(copyOrders.contains(require)){
                int index = copyOrders.indexOf(require);
                int zeroCount = 0;
                //고정 값 앞에 0의 갯수 카운트
                for(int j = 0; j < index ; j++){
                    if(copyOrders.get(j) == 0)
                        zeroCount ++ ;
                }
                int hit = 0;

                /*
                * 0의 갯수와 고정 값의 결과 값 안 인덱스와 같다면 =>
                *
                * 3 0 5 0 0 0  에서 5 앞의 0의 개수와
                * 4 5 6에서 5 인덱스 값이 같다면
                * 4를 3과 5 사이에 넣어줌
                 */
                if (zeroCount == i){
                    for(int l = 0; l < index; l++){
                        if(copyOrders.get(l) == 0){
                            copyOrders.set(l, requires.get(hit));
                            hit++;
                        }
                    }
                }
            }
        }

        /*
        * 정해진 순서 리스트에 1이 존재하지 않으면 1을 맨 앞에 생성
        * newRequires라는 리스트로 정해진 순서를 참조함.
         */
        List<Integer> newRequires = new ArrayList<>();
        if(!requires.contains(1)){
            newRequires.add(1);
        }
        newRequires.addAll(requires);

        /*
        * 정해진 순서에서 결과 리스트에 들어있지 않다면
        * 순서에 맞게 결과 리스트에 입력
        *
        * 이미 결과 리스트에 들어있다면 해당 인덱스로 이동
         */
        int idx = 0;
        for(int i = 0; i < newRequires.size(); i++) {
            //정해진 순서 값 추출
            int require = newRequires.get(i);

            //이미 결과 리스트에 존재한다면 그 인덱스로 이동
            if (copyOrders.contains(require)) {
                idx = copyOrders.indexOf(require);
                continue;
            }
            //결과 리스트에서 빈 값이 아니면 인덱스 ++
            while (copyOrders.get(idx) != 0)
                idx++;
            //결과 리스트에 값 입력
            copyOrders.set(idx, require);
        }

        //결과 출력
        System.out.println(copyOrders.indexOf(1) + 1);

    }
}
