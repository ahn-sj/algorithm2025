package kit;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class P42587_프로세스 {
    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2};
        int location = 2;

        System.out.println(solution(priorities, location));
    }

    /*
      1. task = pop()
      2. check(PriorityQueue)
      3. if(isSatisfied) PriorityQueue.push(task)
      4. else task.execute()
     */
    private static int solution(int[] priorities, int location) {
        PriorityQueue<Integer> waitingQueue = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> locations = new LinkedList<>();

        for (int taskId = 0; taskId < priorities.length; taskId++) {
            waitingQueue.add(priorities[taskId]);
            locations.add(taskId);
        }

        int completedTask = 0;
        while (!waitingQueue.isEmpty()) {
            Integer currentLocation = locations.poll();
            int currentTaskPriority = priorities[currentLocation];

            Integer highestPriority = waitingQueue.peek();
            if (currentTaskPriority == highestPriority) {
                waitingQueue.poll();
                completedTask++;

                if(currentLocation == location) {
                    return completedTask;
                }
            } else {
                locations.add(currentLocation);
            }
        }
        return completedTask;
    }
}
