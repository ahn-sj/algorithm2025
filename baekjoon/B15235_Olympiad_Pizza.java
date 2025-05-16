import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 ******** input *********
 4
 1 3 1 4

 ******** output ********
 1 7 3 9
 */
// 1. arr[n] 만큼을 만들고 하나씩 증가
public class B15235_Olympiad_Pizza {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int studentCount = Integer.parseInt(st.nextToken());

        int[] given = new int[studentCount];
        Queue<int[]> stock = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < given.length; i++) {
            int pizza = Integer.parseInt(st.nextToken());
            stock.add(new int[]{pizza, i});
        }

        int time = 0;
        while (!stock.isEmpty()) {
            int[] arr = stock.poll(); // 순차적으로 하나씩 지급

            arr[0] -= 1; // 피자 지급
            time++;      // 지급 시간을 카운트

            if(arr[0] == 0) {
                given[arr[1]] = time;
            } else {
                stock.add(arr);
            }
        }

        // 출력
        for (int i = 0; i < studentCount; i++) {
            bw.write(given[i] + " ");
        }
        bw.flush();
    }
}
