import java.io.*;
import java.util.StringTokenizer;

public class B2559_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] temperature = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) { // O(N)
            temperature[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for (int i = 0; i < K; i++) { // O(K)
            sum += temperature[i];
        }

        int max = sum;
        for (int i = 1; i < N - K; i++) { // O(N-K)
            final int prev = temperature[i - 1];
            final int next = temperature[i + K - 1];

            sum = sum - prev + next;

            if(sum > max) {
                max = sum;
            }
        }
        bw.write(max + "\n");
        bw.flush();
    }
}
