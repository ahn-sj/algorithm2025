import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 ******** input ******
 4 2 10
 7 4 5 6
 */

public class B13335_트럭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int truckCount = Integer.parseInt(st.nextToken());  // 트럭의 개수
        int bridgeLength = Integer.parseInt(st.nextToken()); // 다리의 길이
        int bridgeMaxWeight = Integer.parseInt(st.nextToken()); // 다리의 최대하중

        st = new StringTokenizer(br.readLine());
        Queue<Integer> trucks = new LinkedList<>();
        for (int i = 0; i < truckCount; i++) {
            trucks.add(Integer.parseInt(st.nextToken()));
        }

        Queue<Integer> bridges = new LinkedList<>();
        for (int i = 0; i < bridgeLength; i++) {
            bridges.add(0);
        }

        int minTime = 0; // result
        int currentWeight = 0; // 현재 다리의 하중

        // 1. while(!bridge.isEmpty) -> 다리의 길이만큼 초기값 세팅 필요
        while (!bridges.isEmpty()) {
            // 2.    currentWeight += consume() -> 하중 더하기, 단위시간 증가
            minTime++;
            currentWeight -= bridges.poll(); // 3번째 이상의 트럭인 경우에 expect가 예상한걸로 계산되려면 - -> + 로 되어야 함

            //트럭이 비어있으면 건너뜀
            if(trucks.isEmpty()) {
                continue;
            }

            // 3.    if(예상최대무게 > 다리의 무게)   -> 순회만 시킴
            // 예상최대무게 = current + peek()
            int expectMaxWeight = currentWeight + trucks.peek();
            if (bridgeMaxWeight < expectMaxWeight) {
                bridges.add(0);
            } else { // 4.    else(예상최대무게 <= 다리의 무게)   -> produce()
                Integer obj = trucks.poll();
                currentWeight += obj;
                bridges.add(obj);
            }
        }
        System.out.println(minTime);
    }
}
