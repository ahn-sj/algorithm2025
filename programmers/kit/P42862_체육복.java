package kit;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class P42862_체육복 {
    public static void main(String[] args) {
        int n = 5;
        int[] lost = {2, 4};
        int[] reserve = {1, 3, 5};

        System.out.println(solution(n, lost, reserve));
    }

    public static int solution(int n, int[] lost, int[] reserve) {
        Set<Integer> losted = new HashSet<>();
        Set<Integer> reserved = new HashSet<>();

        for (int number : lost) {
            losted.add(number);
        }
        for (int number : reserve) {
            if (losted.contains(number)) {
                losted.remove(number);
            } else {
                reserved.add(number);
            }
        }

        Iterator<Integer> it = losted.iterator();
        while (it.hasNext()) {
            Integer studentId = it.next();
            if (reserved.contains(studentId - 1)) {
                it.remove();
                reserved.remove(studentId - 1);
            } else if (reserved.contains(studentId + 1)) {
                it.remove();
                reserved.remove(studentId + 1);
            }
        }
        return n - losted.size();
    }
}
