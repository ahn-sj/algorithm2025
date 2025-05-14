import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
******** input ******
 7 8
 0 1 3
 1 1 7
 0 7 6
 1 7 1
 0 3 7
 0 4 2
 0 1 1
 1 1 1

******** output ******
 NO
 NO
 YES
*/

// 탐색 & 확인 연산이므로 유니온 파인드로 접근
// 경로 압축(path compression)이 정확히 어떠한 최적화가 되는지 조금 더 분석 필요
public class B1717_집합의_표현 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = Integer.parseInt(st.nextToken());
        int loop = Integer.parseInt(st.nextToken());

        parent = new int[count + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < loop; i++) {
            st = new StringTokenizer(br.readLine());
            int ordinal = Integer.parseInt(st.nextToken()); // 0: union, 1: find
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            // ------ case 1 -------
            // 1. union(ordinal: 0)인 경우 parent[left], parent[right]를 찾음
            if (ordinal == 0) {
                int searchedLeft = find(left);
                int searchedRight = find(right);

                // 2. parent[left], parent[right]의 value를 비교
                // 3. 비교한 결과중에 value의 값이 큰 parent[x]에 작은 값의 value를 넣음
                if (searchedLeft > searchedRight) {
                    parent[searchedLeft] = searchedRight;
                } else {
                    parent[searchedRight] = searchedLeft;
                }
            }

            // ------ case 2 -------
            // 1. find(ordinal: 1)인 경우 parent[left], parent[right]의 루트 로드를 찾음
            // 2. 두 값이 동일한지 확인하여 같다면 YES, 다르다면 NO를 응답
            if (ordinal == 1) {
                int searchedLeft = find(left);
                int searchedRight = find(right);

                String result = searchedLeft == searchedRight ? "YES" : "NO";
                System.out.println(result);
            }
        }
    }

    /**
     * 경로 압축을 적용한 Find 함수
     *
     * @param value 검색 대상
     * @return 루트 노드의 value
     */
    private static int find(final int value) {
        if (parent[value] == value) {
            return value;
        }
        return parent[value] = find(parent[value]);
    }
}
