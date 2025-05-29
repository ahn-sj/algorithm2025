import java.io.*;
import java.util.HashSet;
import java.util.Set;

// Stack vs HashSet
// check x: S에 x가 있으면 1을, 없으면 0을 출력한다.

/**
 * 26
 * add 1
 * add 2
 * check 1    1
 * check 2    1
 * check 3    0
 * remove 2
 * check 1   1
 * check 2   0
 * toggle 3
 * check 1   1
 * check 2   0
 * check 3   1
 * check 4   0
 * all
 * check 10  1
 * check 20  1
 * toggle 10
 * remove 20
 * check 10  0
 * check 20  0
 * empty
 * check 1   0
 * toggle 1
 * check 1   1
 * toggle 1
 * check 1   0
 */
public class B11723_집합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Set<Integer> set = new HashSet<>();

        int operationCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < operationCount; i++) {
            String[] operation = br.readLine().split(" ");
            String operator = operation[0];

            switch (operator) {
                case "add":
                    set.add(Integer.parseInt(operation[1]));
                    break;
                case "remove":
                    set.remove(Integer.parseInt(operation[1]));
                    break;
                case "check":
                    if (set.contains(Integer.parseInt(operation[1]))) {
                        bw.write("1");
                    } else {
                        bw.write("0");
                    }
                    bw.write("\n");
                    break;
                case "toggle":
                    if (set.contains(Integer.parseInt(operation[1]))) {
                        set.remove(Integer.parseInt(operation[1]));
                    } else {
                        set.add(Integer.parseInt(operation[1]));
                    }
                    break;
                case "all":
                    set = new HashSet<>();
                    for (int j = 1; j <= 20; j++) {
                        set.add(j);
                    }
                    break;
                case "empty":
                    set = new HashSet<>();
                    break;
            }
        }
        bw.flush();
    }
}
