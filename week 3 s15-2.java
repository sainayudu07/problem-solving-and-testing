class Solution {
public:
    vector<string> sortPeople(vector<string>& names, vector<int>& heights) {

        vector<pair<int, string>> people;

        // Store height and name together
        for (int i = 0; i < names.size(); i++) {
            people.push_back({heights[i], names[i]});
        }

        // Sort by height in descending order
        sort(people.begin(), people.end(), [](pair<int, string>& a,
                                              pair<int, string>& b) {
            return a.first > b.first;
        });

        // Store sorted names
        vector<string> result;

        for (auto& person : people) {
            result.push_back(person.second);
        }

        return result;
    }
};