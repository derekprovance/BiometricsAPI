package com.derekprovance.biometrics.biometricsapi.api.meals;

import java.util.HashMap;
import java.util.Map;

public enum DayBlock {
    ANYTIME(0),
    BREAKFAST(1),
    MORNING_SNACK(2),
    LUNCH(3),
    AFTERNOON_SNACK(4),
    DINNER(5),
    EVENING_SNACK(6);

    private Integer value;
    private static Map<Integer, DayBlock> map = new HashMap<>();

    public Integer getValue()
    {
        return this.value;
    }

    static {
        for (DayBlock dayBlock : DayBlock.values()) {
            map.put(dayBlock.value, dayBlock);
        }
    }

    public static DayBlock valueOf(Integer dayBlock) {
        return map.get(dayBlock);
    }

    DayBlock(Integer value)
    {
        this.value = value;
    }
}