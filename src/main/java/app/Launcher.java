package app;

import app.game.Game;

import java.util.HashSet;
import java.util.Set;

public class Launcher {

    public static void main(String[] args) {
//        new Game("smallpigex", 800, 600);
        System.out.println(solution(new int[]{4, 9, 8, 4}, 8));

    }

    public static boolean solution(int[] nums, int target) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            int diff = target - num;
            if(numSet.contains(diff)) {
                return true;
            } else {
                numSet.add(num);
            }
        }
        return false;

    }
}
