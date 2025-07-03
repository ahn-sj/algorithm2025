package kit;

import java.util.Arrays;
import java.util.Stack;

public class P12906_같은_숫자는_싫어 {

    public static void main(String[] args) {
        int[] arr = {1, 1, 3, 3, 0, 1, 1};

        System.out.println(Arrays.toString(solution(arr)));
    }

    private static int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();

        for (int element : arr) {
            if (stack.empty() || !stack.peek().equals(element)) {
                stack.add(element);
            }
        }
        return stack.stream().mapToInt(i -> i).toArray();
    }
}
