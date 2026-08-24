class Solution {
public:

    bool isLeapYear(int year) {
        return (year % 400 == 0) ||
               (year % 4 == 0 && year % 100 != 0);
    }

    int dayOfYear(string date) {

        int year = stoi(date.substr(0, 4));
        int month = stoi(date.substr(5, 2));
        int day = stoi(date.substr(8, 2));

        int monthDays[] = {
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };

        int total = day;

        // Add days of previous months
        for (int i = 0; i < month - 1; i++) {
            total += monthDays[i];
        }

        // Add one extra day for February in leap year
        if (month > 2 && isLeapYear(year)) {
            total++;
        }

        return total;
    }
};