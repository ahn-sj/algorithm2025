package kit;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P42885_구명보트 {
    public static void main(String[] args) {
        int[] people = {70, 50, 80, 50};
        int limit = 100;

        System.out.println(solution(people, limit));
    }

    private static int solution(int[] people, int limit) {
        Arrays.sort(people);

        Queue<Integer> weightQueue = new LinkedList<>();
        for (int weight : people) {
            weightQueue.add(weight);
        }

        int boat = 0;
        int sum = weightQueue.poll();

        while (!weightQueue.isEmpty()) {
            if (sum + weightQueue.peek() <= limit) {
                weightQueue.poll();
            }
            boat++;
        }
        return boat;
    }
}
