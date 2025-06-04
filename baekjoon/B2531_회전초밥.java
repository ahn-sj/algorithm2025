import java.io.*;
import java.util.StringTokenizer;

/**
 * 1. 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공
 * 2. 각 고객에게 초밥의 종류 하나가 쓰인 쿠폰을 발행하고, 1번 행사에 참가할 경우 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료로 제공한다. 만약 이 번호에 적혀진 초밥이 현재 벨트 위에 없을 경우, 요리사가 새로 만들어 손님에게 제공한다.
 * -> 초밥의 종류 하나가 쓰인 쿠폰을 발행하고 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료로 제공
 * --> 할인 행사에 참여하여 가능한 한 다양한 종류의 초밥을 먹으려고 할 때 손님이 먹을 수 있는 초밥 가짓수의 최댓값을 구하시오
 *
 * N: 접시의 수
 * d: 초밥의 가짓수
 * k: 연속해서 먹는 접시의 수
 * c: 쿠폰 번호
 *
 * 고려사항: 벨트로 이어져있기 때문에 '마지막 접시 -> 첫 번째 접시'에 대해서도 생각
 *
 * 솔루션:
 * - 슬라이딩 윈도우 & 나머지 연산 (원형)
 * - 쿠폰 처리
 */
public class B2531_회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시의 수
        int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] sushi = new int[N];
        int[] eaten = new int[d + 1]; // 1-based

        int count = 0;

        // 회전 초밥 벨트에 초밥을 제공
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }
        eaten[c]++; // 가장 먼저 행사 쿠폰 초밥을 무조건 먹음
        count++; // 행사 초밥을 먹음 == 초밥 가짓수 증가

        for (int i = 0; i < k; i++) {
            final int sushiId = sushi[i];
            if(eaten[sushiId] == 0) { // 아직 안먹은 종류의 초밥인 경우 초밥 가짓수 증가
                count++;
            }
            eaten[sushiId]++;
        }

        // result init
        int max = count;

        for (int i = 1; i < N; i++) {
            final int previous = sushi[i - 1];

            // 중복 탐색을 막기 위해(N^2 -> N) 다시 순회하여 계산할 필요가 없기 때문
            if(--eaten[previous] == 0) { // 1) 먹은거 원복
                count--; // 2) 중복되어 먹은게 없다면 먹은 개수도 원복
            }
            final int index = i + k - 1;
            final int position = index % N;
            final int next = sushi[position];

            if(++eaten[next] == 1) { // 1) 먹음 처리
                count++; // 2) 중복되어 먹은게 없다면 먹음 개수 증가
            }

            if(count > max) { // 최댓값 구하기
                max = count;
            }
        }
        bw.write(max + "\n");
        bw.flush();
    }
}
