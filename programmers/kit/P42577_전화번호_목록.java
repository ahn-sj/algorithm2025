package kit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P42577_전화번호_목록 {

    public static void main(String[] args) {
        String[] phoneBook = {"119", "97674223", "1195524421"};
        System.out.println(solution(phoneBook));
    }

    /*
    // sol 1: O(n log n + n)
    public static boolean solution(String[] phoneBook) {
        Arrays.sort(phoneBook); // O(n log n)

        for (int i = 0; i < phoneBook.length - 1; i++) { // O(n - 1)
            if (phoneBook[i + 1].startsWith(phoneBook[i])) {
                return false;
            }
        }
        return true;
    }
    */

    // sol 2: O(n log n + n)
    public static boolean solution(String[] phoneBook) {
        Set<String> phoneSet = new HashSet<>();

        for (int i = 0; i < phoneBook.length; i++) {
            phoneSet.add(phoneBook[i]);
        }

        for (int i = 0; i < phoneBook.length; i++) {
            for (int j = 0; j < phoneBook[i].length(); j++) {
                if (phoneSet.contains(phoneBook[i].substring(0, j))) {
                    return false;
                }
            }

        }
        return true;
    }
}
