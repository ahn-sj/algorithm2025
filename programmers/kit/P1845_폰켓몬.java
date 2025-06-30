package kit;

import java.util.HashSet;
import java.util.Set;

public class P1845_폰켓몬 {

    public static void main(String[] args) {
        final int[] nums = {3,1,2,3};
        System.out.println(solution(nums));
    }

    public static int solution(int[] nums) {
        Set<Integer> pokemon = new HashSet<>();
        for(int num : nums) {
            pokemon.add(num);
        }

        int maximum = nums.length / 2;
        int max = Math.min(pokemon.size(), maximum);

        return max;
    }
}
