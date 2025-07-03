package kit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P42586_기능개발 {
    public static void main(String[] args) {
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};

        System.out.println(Arrays.toString(solution(progresses, speeds)));
    }

    private static int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> releases = new LinkedList<>();

        for (int i = 0; i < progresses.length; i++) {
            // 당일 배포가 가능한지 progress % speed
            boolean canSameDayRelease = ((100 - progresses[i]) % speeds[i]) == 0;

            // 기능 배포 예상 일자
            int expected = ((100 - progresses[i]) / speeds[i]);

            if (!canSameDayRelease) { // 당일 배포 시간이 부족하여 다음날 배포
                releases.add(expected + 1);
            } else {
                releases.add(expected);
            }
        }

        int expectedRelease = releases.poll();
        int featureCount = 1;

        List<Integer> features = new ArrayList<>();
        while (!releases.isEmpty()) {
            if (expectedRelease >= releases.peek()) {
                featureCount++;
                releases.poll();
            } else {
                features.add(featureCount); // 배포에 포함된 피처 개수
                featureCount = 1; // 피처 개수 초기화
                expectedRelease = releases.poll(); // 다음 배포때 피처 세팅
            }
        }
        features.add(featureCount); // 마지막 배포

        return features.stream().mapToInt(count -> count).toArray();
    }
}
