class Solution {
public:

    bool isLeapYear(int year) {
        return (year % 400 == 0) ||
               (year % 4 == 0 && year % 100 != 0);
    }

    int getDays(string date) {

        int year = stoi(date.substr(0, 4));
        int month = stoi(date.substr(5, 2));
        int day = stoi(date.substr(8, 2));

        int total = 0;

        // Add days from previous years
        for (int y = 1971; y < year; y++) {
            if (isLeapYear(y))
                total += 366;
            else
                total += 365;
        }

        // Days in each month
        int monthDays[] = {
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };

        // Add days from previous months
        for (int m = 0; m < month - 1; m++) {
            total += monthDays[m];

            if (m == 1 && isLeapYear(year))
                total++;
        }

        // Add current day
        total += day;

        return total;
    }

    int daysBetweenDates(string date1, string date2) {

        int days1 = getDays(date1);
        int days2 = getDays(date2);

        return abs(days1 - days2);
    }
};w