import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        List<Integer> requires = new ArrayList<>();

        int[] orders = new int[n];

        for(int i = 0; i < m; i++)
            requires.add(scanner.nextInt());
        for(int i = 0; i < k; i++){
            int cow = scanner.nextInt();
            int index = scanner.nextInt()-1;
            orders[index] = cow;
        }
        int idx = 0;
        List<Integer> copyOrders = Arrays.stream(orders).boxed().collect(Collectors.toList());

        for(int i = 0; i < requires.size(); i++){
            int require = requires.get(i);

            if(copyOrders.contains(require)){
                int index = copyOrders.indexOf(require);
                int zeroCount = 0;
                for(int j = 0; j < index ; j++){
                    if(copyOrders.get(j) == 0)
                        zeroCount ++ ;
                }
                int hit = 0;
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


        List<Integer> newRequires = new ArrayList<>();
        if(!requires.contains(1)){
            newRequires.add(1);
        }
        newRequires.addAll(requires);

        for(int i = 0; i < newRequires.size(); i++) {


            int require = newRequires.get(i);
            if (copyOrders.contains(require)) {
                idx = copyOrders.indexOf(require);
                continue;
            }

            while (copyOrders.get(idx) != 0)
                idx++;

            copyOrders.set(idx, require);
        }
            System.out.println(copyOrders.indexOf(1) + 1);



    }
}
