class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0;

        int currentMax = nums[0];
        int maxSum = nums[0];

        int currentMin = nums[0];
        int minSum = nums[0];

        totalSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            // Maximum normal subarray
            currentMax = Math.max(num, currentMax + num);
            maxSum = Math.max(maxSum, currentMax);

            // Minimum subarray
            currentMin = Math.min(num, currentMin + num);
            minSum = Math.min(minSum, currentMin);

            totalSum += num;
        }

        // All elements are negative
        if (maxSum < 0) {
            return maxSum;
        }

        // Circular maximum = total sum - minimum subarray
        int circularSum = totalSum - minSum;

        return Math.max(maxSum, circularSum);
    }
}
