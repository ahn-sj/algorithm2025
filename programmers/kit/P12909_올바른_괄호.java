package kit;

import java.util.Stack;

public class P12909_올바른_괄호 {
    public static void main(String[] args) {
        String s = "()()";
        System.out.println(solution(s));

        s = "(())()";
        System.out.println(solution(s));

        s = ")()(";
        System.out.println(solution(s));

        s = "(()(";
        System.out.println(solution(s));
    }

    private static boolean solution(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty() ? true : false;
    }
}
