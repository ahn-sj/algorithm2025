package kit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P42578_의상 {

    public static void main(String[] args) {
        String[][] clothes = {
                {"yellow_hat", "headgear"},
                {"blue_sunglasses", "eyewear"},
                {"green_turban", "headgear"},
        };
        System.out.println(solution(clothes));
    }

    public static int solution(String[][] clothes) {
        Map<String, List<String>> collect = new HashMap<>();

        // 종류별로 collect
        for (String[] clothePair : clothes) {
            String kind = clothePair[1];
            String name = clothePair[0];

            collect.computeIfAbsent(kind, key -> new ArrayList<>()).add(name);
        }

        int answer = 1;
        for (List<String> value : collect.values()) {
            answer = answer * (value.size() + 1);
        }
        return answer - 1;
    }
}
