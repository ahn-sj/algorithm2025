import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 ********** input *********
 3
 3
 0 1 0
 1 0 1
 0 1 0
 1 2 3

 ********** output *********
 YES
 */

// cities
// parent
// route
public class B1976_여행가자 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int col = Integer.parseInt(st.nextToken());

        parent = new int[row + 1];
        int[][] conn = new int[row + 1][col + 1]; // 도시들간의 연결 여부

        for (int i = 1; i <= row; i++) {
            st = new StringTokenizer(br.readLine());
            final String[] travelPlan = st.nextToken().split(" ");
            for (int j = 1; j <= col; j++) {
                conn[i][j] = Integer.parseInt(travelPlan[j - 1]);
            }
        }

        System.out.println("conn = " + Arrays.deepToString(conn));

    }

    private static void union(final int left, final int right) {
        int searchedLeft = find(left);
        int searchedRight = find(right);

        if (searchedLeft > searchedRight) {
            parent[searchedLeft] = searchedRight;
        } else {
            parent[searchedRight] = searchedLeft;
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
