class Solution {
public:
    /* Finds the sum of a vector of integers */
    int vecSum(vector<int> vec) {
        int sum = 0;
        for (int num : vec) {
            sum += num;
        }
        return sum;
    }
    
    
    /* This function finds every single contiguous subarray that starts at a given index curIdx */
    void findAllSubArrays(vector<vector<int>>& allSubArrays, vector<int>& nums, vector<int>& curSubArray, int curIdx) {
        if (curIdx == nums.size()) {
            return;
        }
        
        //include
        curSubArray.push_back(nums[curIdx]);
        allSubArrays.push_back(curSubArray);
        findAllSubArrays(allSubArrays, nums, curSubArray, curIdx + 1);
        
        //unchoose
        curSubArray.pop_back();
    }
    
    
    /* Finds the contiguous subarray (containing at least one number) which has the largest 
     * sum and return its sum. 
     */
    int maxSubArray(vector<int>& nums) {
        
        // Create vector of all subarrays
        vector<int> temp;
        vector<vector<int>> allSubArrays;
        for (int i = 0; i < nums.size(); i++) {             // go thru all potential starting indices
            findAllSubArrays(allSubArrays, nums, temp, i);
        }
        
        // Create vector of all sums
        vector<int> allSums;
        for (vector<int> subArr : allSubArrays) {
            allSums.push_back(vecSum(subArr));
        }
        
        // Find max sum
        int maxSum = allSums[0];
        for (int sum : allSums) {
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        return maxSum;
    }
};
