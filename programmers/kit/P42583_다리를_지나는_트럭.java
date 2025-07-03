package kit;

import java.util.LinkedList;
import java.util.Queue;

public class P42583_다리를_지나는_트럭 {
    public static void main(String[] args) {
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7, 4, 5, 6};

        System.out.println(solution(bridge_length, weight, truck_weights));
    }

    private static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> waitingTrucks = new LinkedList<>();
        Queue<Integer> trucksOnBridge = new LinkedList<>();

        for (int truckWeight : truck_weights) {
            waitingTrucks.add(truckWeight); // 4: [7,4,5,6]
        }
        for (int i = 0; i < bridge_length; i++) { // 2: [0 0]
            trucksOnBridge.add(0);
        }

        int second = 0;
        int currentWeight = 0;

        while(!trucksOnBridge.isEmpty()) {
            Integer movedWeight = trucksOnBridge.poll();
            currentWeight = currentWeight - movedWeight; // 다리위에 트럭을 옮기는 경우

            second++;

            if (waitingTrucks.isEmpty()) {
                continue;
            }

            int expectedWeight = currentWeight + waitingTrucks.peek();
            if (expectedWeight > weight) { // 트럭이 다리에 최대로 올라간 경우
                trucksOnBridge.add(0);
            } else { // 트럭을 올릴 수 있는 상태
                Integer move = waitingTrucks.poll();
                trucksOnBridge.add(move); // 대기하는 트럭을 다리에 올림
                currentWeight = currentWeight + move;
            }
        }
        return second;
    }
}
