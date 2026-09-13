import java.util.*;

class Solution {
    public boolean containsDuplicate(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {

            // If the number already exists, duplicate found
            if (set.contains(num)) {
                return true;
            }

            set.add(num);
        }

        // No duplicates found
        return false;
    }
}
