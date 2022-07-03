import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        List<Integer> items = new ArrayList<>();
        List<Integer> dp = new ArrayList<>();
        for (int k = 0; k < n; k++) {
            items.add(scanner.nextInt());
        }
        if(n >= 1)
            dp.add(items.get(0));
        if(n >= 2)
            dp.add(items.get(1) + items.get(0));
        if(n >= 3){
            dp.add(Math.max(items.get(1) + items.get(2), items.get(0) + items.get(2)));
            for (int i = 3; i < n; i++) {
                dp.add(Math.max(items.get(i) + items.get(i-1)+ dp.get(i-3) , items.get(i) + dp.get(i-2)));
            }
        }


        System.out.println(dp.get(n - 1));
    }

}
