class Solution {
public:

    bool isLeapYear(int year) {
        return (year % 400 == 0) ||
               (year % 4 == 0 && year % 100 != 0);
    }

    string dayOfTheWeek(int day, int month, int year) {

        string days[] = {
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"
        };

        // January 1, 1971 was Friday
        int totalDays = 0;

        // Count complete years from 1971 to year - 1
        for (int y = 1971; y < year; y++) {
            if (isLeapYear(y))
                totalDays += 366;
            else
                totalDays += 365;
        }

        int monthDays[] = {
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };

        // Count complete months
        for (int m = 1; m < month; m++) {
            totalDays += monthDays[m - 1];

            if (m == 2 && isLeapYear(year)) {
                totalDays++;
            }
        }

        // Add days before the given date
        totalDays += day - 1;

        // January 1, 1971 = Friday
        // Friday has index 5
        int index = (5 + totalDays) % 7;

        return days[index];
    }
};