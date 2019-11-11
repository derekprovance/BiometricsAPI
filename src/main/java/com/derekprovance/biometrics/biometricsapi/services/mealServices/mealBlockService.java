package com.derekprovance.biometrics.biometricsapi.services.mealServices;

import com.derekprovance.biometrics.biometricsapi.api.physio.mealLog.MealBlock;

import java.time.LocalDateTime;

public class mealBlockService {

    public static MealBlock getMealBlock(LocalDateTime dateTime) {
        int hour = dateTime.getHour();

        MealBlock block;
        if(breakfastTime(hour)) {
            block = MealBlock.BREAKFAST;
        } else if (lunchTime(hour)) {
            block = MealBlock.LUNCH;
        } else if (dinnerTime(hour)) {
            block = MealBlock.DINNER;
        } else if (afternoonSnackTime(hour)) {
            block = MealBlock.AFTERNOON_SNACK;
        } else if (eveningSnackTime(hour)) {
            block = MealBlock.EVENING_SNACK;
        } else {
            block = MealBlock.ANYTIME;
        }

        return block;
    }

    private static boolean breakfastTime(int hour) {
        return hour >= 9 && hour < 11;
    }

    private static boolean lunchTime(int hour) {
        return hour >= 11 && hour < 13;
    }

    private static boolean afternoonSnackTime(int hour) {
        return hour >= 13 && hour < 17;
    }

    private static boolean dinnerTime(int hour) {
        return hour >= 17 && hour < 19;
    }

    private static boolean eveningSnackTime(int hour) {
        return hour >= 19 && hour < 24;
    }

}
