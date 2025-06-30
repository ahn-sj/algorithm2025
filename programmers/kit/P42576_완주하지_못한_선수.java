package kit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class P42576_완주하지_못한_선수 {

    public static void main(String[] args) {
        final String[] participant = {"leo", "kiki", "eden", "kiki"};
        final String[] completion = {"eden", "kiki"};

        System.out.println(solution(participant, completion));
    }

    public static String solution(String[] participant, String[] completion) {
        Map<String, Long> race = new HashMap<>();

        for (int i = 0; i < participant.length; i++) {
            race.put(participant[i], race.getOrDefault(participant[i], 0L) + 1);
        }
        for (int i = 0; i < completion.length; i++) {
            race.put(completion[i], race.get(completion[i]) - 1);
        }

        for (final Map.Entry<String, Long> entry : race.entrySet()) {
            if (entry.getValue() != 0) { // 완주하지 못한 선수
                return entry.getKey();
            }
        }
        return "";
    }
}
