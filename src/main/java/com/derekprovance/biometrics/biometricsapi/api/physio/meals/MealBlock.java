package com.derekprovance.biometrics.biometricsapi.api.physio.meals;

import java.util.HashMap;
import java.util.Map;

public enum MealBlock {
    ANYTIME(0),
    BREAKFAST(1),
    MORNING_SNACK(2),
    LUNCH(3),
    AFTERNOON_SNACK(4),
    DINNER(5),
    EVENING_SNACK(6);

    private Integer value;
    private static final Map<Integer, MealBlock> map = new HashMap<>();

    public Integer getValue()
    {
        return this.value;
    }

    static {
        for (MealBlock mealBlock : MealBlock.values()) {
            map.put(mealBlock.value, mealBlock);
        }
    }

    public static MealBlock valueOf(Integer mealBlock) {
        return map.get(mealBlock);
    }

    MealBlock(Integer value)
    {
        this.value = value;
    }
}