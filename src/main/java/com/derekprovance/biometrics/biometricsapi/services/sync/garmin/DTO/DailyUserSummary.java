package com.derekprovance.biometrics.biometricsapi.services.sync.garmin.DTO;

import java.time.LocalDate;

public class DailyUserSummary {
    private Long userProfileId;
    private Double totalKilocalories;
    private Double activeKilocalories;
    private Double bmrKilocalories;
    private Double wellnessKilocalories;
    private Double burnedKilocalories;
    private Double consumedKilocalories;
    private Double remainingKilocalories;
    private Integer totalSteps;
    private Integer netCalorieGoal;
    private Integer totalDistanceMeters;
    private Integer wellnessDistanceMeters;
    private Double wellnessActiveKilocalories;
    private Double netRemainingKilocalories;
    private Long userDailySummaryId;
    private LocalDate calendarDate;
    private String uuid;
    private Integer dailyStepGoal;
    private LocalDate wellnessStartTimeGmt;
    private LocalDate wellnessStartTimeLocal;
    private LocalDate wellnessEndTimeGmt;
    private LocalDate wellnessEndTimeLocal;
    private Long durationInMilliseconds;
    private String wellnessDescription;
    private Integer highlyActiveSeconds;
    private Integer activeSeconds;
    private Integer sedentarySeconds;
    private Integer sleepingSeconds;
    private Boolean includesWellnessData;
    private Boolean includesActivityData;
    private Boolean includesCalorieConsumedData;
    private Boolean privacyProtected;
    private Integer moderateIntensityMinutes;
    private Integer vigorousIntensityMinutes;
    private Float floorsAscendedInMeters;
    private Float floorsDescendedInMeters;
    private Float floorsAscended;
    private Float floorsDescended;
    private Integer intensityMinutesGoal;
    private Integer userFloorsAscendedGoal;
    private Integer minHeartRate;
    private Integer maxHeartRate;
    private Integer restingHeartRate;
    private Integer lastSevenDaysAvgRestingHeartRate;
    private String source;
    private Integer averageStressLevel;
    private Integer maxStressLevel;
    private Integer stressDuration;
    private Integer restStressDuration;
    private Integer activityStressDuration;
    private Integer uncategorizedStressDuration;
    private Integer totalStressDuration;
    private Integer lowStressDuration;
    private Integer mediumStressDuration;
    private Integer highStressDuration;
    private Double stressPercentage;
    private Double restStressPercentage;
    private Double activityStressPercentage;
    private Double uncategorizedStressPercentage;
    private Double lowStressPercentage;
    private Double mediumStressPercentage;
    private Double highStressPercentage;
    private String stressQualifier;
    private Integer measurableAwakeDuration;
    private Integer measurableAsleepDuration;
    private String lastSyncTimestampGMT;
    private Integer minAvgHeartRate;
    private Integer maxAvgHeartRate;

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }

    public Double getTotalKilocalories() {
        return totalKilocalories;
    }

    public void setTotalKilocalories(Double totalKilocalories) {
        this.totalKilocalories = totalKilocalories;
    }

    public Double getActiveKilocalories() {
        return activeKilocalories;
    }

    public void setActiveKilocalories(Double activeKilocalories) {
        this.activeKilocalories = activeKilocalories;
    }

    public Double getBmrKilocalories() {
        return bmrKilocalories;
    }

    public void setBmrKilocalories(Double bmrKilocalories) {
        this.bmrKilocalories = bmrKilocalories;
    }

    public Double getWellnessKilocalories() {
        return wellnessKilocalories;
    }

    public void setWellnessKilocalories(Double wellnessKilocalories) {
        this.wellnessKilocalories = wellnessKilocalories;
    }

    public Double getBurnedKilocalories() {
        return burnedKilocalories;
    }

    public void setBurnedKilocalories(Double burnedKilocalories) {
        this.burnedKilocalories = burnedKilocalories;
    }

    public Double getConsumedKilocalories() {
        return consumedKilocalories;
    }

    public void setConsumedKilocalories(Double consumedKilocalories) {
        this.consumedKilocalories = consumedKilocalories;
    }

    public Double getRemainingKilocalories() {
        return remainingKilocalories;
    }

    public void setRemainingKilocalories(Double remainingKilocalories) {
        this.remainingKilocalories = remainingKilocalories;
    }

    public Integer getTotalSteps() {
        return totalSteps;
    }

    public void setTotalSteps(Integer totalSteps) {
        this.totalSteps = totalSteps;
    }

    public Integer getNetCalorieGoal() {
        return netCalorieGoal;
    }

    public void setNetCalorieGoal(Integer netCalorieGoal) {
        this.netCalorieGoal = netCalorieGoal;
    }

    public Integer getTotalDistanceMeters() {
        return totalDistanceMeters;
    }

    public void setTotalDistanceMeters(Integer totalDistanceMeters) {
        this.totalDistanceMeters = totalDistanceMeters;
    }

    public Integer getWellnessDistanceMeters() {
        return wellnessDistanceMeters;
    }

    public void setWellnessDistanceMeters(Integer wellnessDistanceMeters) {
        this.wellnessDistanceMeters = wellnessDistanceMeters;
    }

    public Double getWellnessActiveKilocalories() {
        return wellnessActiveKilocalories;
    }

    public void setWellnessActiveKilocalories(Double wellnessActiveKilocalories) {
        this.wellnessActiveKilocalories = wellnessActiveKilocalories;
    }

    public Double getNetRemainingKilocalories() {
        return netRemainingKilocalories;
    }

    public void setNetRemainingKilocalories(Double netRemainingKilocalories) {
        this.netRemainingKilocalories = netRemainingKilocalories;
    }

    public Long getUserDailySummaryId() {
        return userDailySummaryId;
    }

    public void setUserDailySummaryId(Long userDailySummaryId) {
        this.userDailySummaryId = userDailySummaryId;
    }

    public LocalDate getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(LocalDate calendarDate) {
        this.calendarDate = calendarDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getDailyStepGoal() {
        return dailyStepGoal;
    }

    public void setDailyStepGoal(Integer dailyStepGoal) {
        this.dailyStepGoal = dailyStepGoal;
    }

    public LocalDate getWellnessStartTimeGmt() {
        return wellnessStartTimeGmt;
    }

    public void setWellnessStartTimeGmt(LocalDate wellnessStartTimeGmt) {
        this.wellnessStartTimeGmt = wellnessStartTimeGmt;
    }

    public LocalDate getWellnessStartTimeLocal() {
        return wellnessStartTimeLocal;
    }

    public void setWellnessStartTimeLocal(LocalDate wellnessStartTimeLocal) {
        this.wellnessStartTimeLocal = wellnessStartTimeLocal;
    }

    public LocalDate getWellnessEndTimeGmt() {
        return wellnessEndTimeGmt;
    }

    public void setWellnessEndTimeGmt(LocalDate wellnessEndTimeGmt) {
        this.wellnessEndTimeGmt = wellnessEndTimeGmt;
    }

    public LocalDate getWellnessEndTimeLocal() {
        return wellnessEndTimeLocal;
    }

    public void setWellnessEndTimeLocal(LocalDate wellnessEndTimeLocal) {
        this.wellnessEndTimeLocal = wellnessEndTimeLocal;
    }

    public Long getDurationInMilliseconds() {
        return durationInMilliseconds;
    }

    public void setDurationInMilliseconds(Long durationInMilliseconds) {
        this.durationInMilliseconds = durationInMilliseconds;
    }

    public String getWellnessDescription() {
        return wellnessDescription;
    }

    public void setWellnessDescription(String wellnessDescription) {
        this.wellnessDescription = wellnessDescription;
    }

    public Integer getHighlyActiveSeconds() {
        return highlyActiveSeconds;
    }

    public void setHighlyActiveSeconds(Integer highlyActiveSeconds) {
        this.highlyActiveSeconds = highlyActiveSeconds;
    }

    public Integer getActiveSeconds() {
        return activeSeconds;
    }

    public void setActiveSeconds(Integer activeSeconds) {
        this.activeSeconds = activeSeconds;
    }

    public Integer getSedentarySeconds() {
        return sedentarySeconds;
    }

    public void setSedentarySeconds(Integer sedentarySeconds) {
        this.sedentarySeconds = sedentarySeconds;
    }

    public Integer getSleepingSeconds() {
        return sleepingSeconds;
    }

    public void setSleepingSeconds(Integer sleepingSeconds) {
        this.sleepingSeconds = sleepingSeconds;
    }

    public Boolean getIncludesWellnessData() {
        return includesWellnessData;
    }

    public void setIncludesWellnessData(Boolean includesWellnessData) {
        this.includesWellnessData = includesWellnessData;
    }

    public Boolean getIncludesActivityData() {
        return includesActivityData;
    }

    public void setIncludesActivityData(Boolean includesActivityData) {
        this.includesActivityData = includesActivityData;
    }

    public Boolean getIncludesCalorieConsumedData() {
        return includesCalorieConsumedData;
    }

    public void setIncludesCalorieConsumedData(Boolean includesCalorieConsumedData) {
        this.includesCalorieConsumedData = includesCalorieConsumedData;
    }

    public Boolean getPrivacyProtected() {
        return privacyProtected;
    }

    public void setPrivacyProtected(Boolean privacyProtected) {
        this.privacyProtected = privacyProtected;
    }

    public Integer getModerateIntensityMinutes() {
        return moderateIntensityMinutes;
    }

    public void setModerateIntensityMinutes(Integer moderateIntensityMinutes) {
        this.moderateIntensityMinutes = moderateIntensityMinutes;
    }

    public Integer getVigorousIntensityMinutes() {
        return vigorousIntensityMinutes;
    }

    public void setVigorousIntensityMinutes(Integer vigorousIntensityMinutes) {
        this.vigorousIntensityMinutes = vigorousIntensityMinutes;
    }

    public Float getFloorsAscendedInMeters() {
        return floorsAscendedInMeters;
    }

    public void setFloorsAscendedInMeters(Float floorsAscendedInMeters) {
        this.floorsAscendedInMeters = floorsAscendedInMeters;
    }

    public Float getFloorsDescendedInMeters() {
        return floorsDescendedInMeters;
    }

    public void setFloorsDescendedInMeters(Float floorsDescendedInMeters) {
        this.floorsDescendedInMeters = floorsDescendedInMeters;
    }

    public Float getFloorsAscended() {
        return floorsAscended;
    }

    public void setFloorsAscended(Float floorsAscended) {
        this.floorsAscended = floorsAscended;
    }

    public Float getFloorsDescended() {
        return floorsDescended;
    }

    public void setFloorsDescended(Float floorsDescended) {
        this.floorsDescended = floorsDescended;
    }

    public Integer getIntensityMinutesGoal() {
        return intensityMinutesGoal;
    }

    public void setIntensityMinutesGoal(Integer intensityMinutesGoal) {
        this.intensityMinutesGoal = intensityMinutesGoal;
    }

    public Integer getUserFloorsAscendedGoal() {
        return userFloorsAscendedGoal;
    }

    public void setUserFloorsAscendedGoal(Integer userFloorsAscendedGoal) {
        this.userFloorsAscendedGoal = userFloorsAscendedGoal;
    }

    public Integer getMinHeartRate() {
        return minHeartRate;
    }

    public void setMinHeartRate(Integer minHeartRate) {
        this.minHeartRate = minHeartRate;
    }

    public Integer getMaxHeartRate() {
        return maxHeartRate;
    }

    public void setMaxHeartRate(Integer maxHeartRate) {
        this.maxHeartRate = maxHeartRate;
    }

    public Integer getRestingHeartRate() {
        return restingHeartRate;
    }

    public void setRestingHeartRate(Integer restingHeartRate) {
        this.restingHeartRate = restingHeartRate;
    }

    public Integer getLastSevenDaysAvgRestingHeartRate() {
        return lastSevenDaysAvgRestingHeartRate;
    }

    public void setLastSevenDaysAvgRestingHeartRate(Integer lastSevenDaysAvgRestingHeartRate) {
        this.lastSevenDaysAvgRestingHeartRate = lastSevenDaysAvgRestingHeartRate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getAverageStressLevel() {
        return averageStressLevel;
    }

    public void setAverageStressLevel(Integer averageStressLevel) {
        this.averageStressLevel = averageStressLevel;
    }

    public Integer getMaxStressLevel() {
        return maxStressLevel;
    }

    public void setMaxStressLevel(Integer maxStressLevel) {
        this.maxStressLevel = maxStressLevel;
    }

    public Integer getStressDuration() {
        return stressDuration;
    }

    public void setStressDuration(Integer stressDuration) {
        this.stressDuration = stressDuration;
    }

    public Integer getRestStressDuration() {
        return restStressDuration;
    }

    public void setRestStressDuration(Integer restStressDuration) {
        this.restStressDuration = restStressDuration;
    }

    public Integer getActivityStressDuration() {
        return activityStressDuration;
    }

    public void setActivityStressDuration(Integer activityStressDuration) {
        this.activityStressDuration = activityStressDuration;
    }

    public Integer getUncategorizedStressDuration() {
        return uncategorizedStressDuration;
    }

    public void setUncategorizedStressDuration(Integer uncategorizedStressDuration) {
        this.uncategorizedStressDuration = uncategorizedStressDuration;
    }

    public Integer getTotalStressDuration() {
        return totalStressDuration;
    }

    public void setTotalStressDuration(Integer totalStressDuration) {
        this.totalStressDuration = totalStressDuration;
    }

    public Integer getLowStressDuration() {
        return lowStressDuration;
    }

    public void setLowStressDuration(Integer lowStressDuration) {
        this.lowStressDuration = lowStressDuration;
    }

    public Integer getMediumStressDuration() {
        return mediumStressDuration;
    }

    public void setMediumStressDuration(Integer mediumStressDuration) {
        this.mediumStressDuration = mediumStressDuration;
    }

    public Integer getHighStressDuration() {
        return highStressDuration;
    }

    public void setHighStressDuration(Integer highStressDuration) {
        this.highStressDuration = highStressDuration;
    }

    public Double getStressPercentage() {
        return stressPercentage;
    }

    public void setStressPercentage(Double stressPercentage) {
        this.stressPercentage = stressPercentage;
    }

    public Double getRestStressPercentage() {
        return restStressPercentage;
    }

    public void setRestStressPercentage(Double restStressPercentage) {
        this.restStressPercentage = restStressPercentage;
    }

    public Double getActivityStressPercentage() {
        return activityStressPercentage;
    }

    public void setActivityStressPercentage(Double activityStressPercentage) {
        this.activityStressPercentage = activityStressPercentage;
    }

    public Double getUncategorizedStressPercentage() {
        return uncategorizedStressPercentage;
    }

    public void setUncategorizedStressPercentage(Double uncategorizedStressPercentage) {
        this.uncategorizedStressPercentage = uncategorizedStressPercentage;
    }

    public Double getLowStressPercentage() {
        return lowStressPercentage;
    }

    public void setLowStressPercentage(Double lowStressPercentage) {
        this.lowStressPercentage = lowStressPercentage;
    }

    public Double getMediumStressPercentage() {
        return mediumStressPercentage;
    }

    public void setMediumStressPercentage(Double mediumStressPercentage) {
        this.mediumStressPercentage = mediumStressPercentage;
    }

    public Double getHighStressPercentage() {
        return highStressPercentage;
    }

    public void setHighStressPercentage(Double highStressPercentage) {
        this.highStressPercentage = highStressPercentage;
    }

    public String getStressQualifier() {
        return stressQualifier;
    }

    public void setStressQualifier(String stressQualifier) {
        this.stressQualifier = stressQualifier;
    }

    public Integer getMeasurableAwakeDuration() {
        return measurableAwakeDuration;
    }

    public void setMeasurableAwakeDuration(Integer measurableAwakeDuration) {
        this.measurableAwakeDuration = measurableAwakeDuration;
    }

    public Integer getMeasurableAsleepDuration() {
        return measurableAsleepDuration;
    }

    public void setMeasurableAsleepDuration(Integer measurableAsleepDuration) {
        this.measurableAsleepDuration = measurableAsleepDuration;
    }

    public String getLastSyncTimestampGMT() {
        return lastSyncTimestampGMT;
    }

    public void setLastSyncTimestampGMT(String lastSyncTimestampGMT) {
        this.lastSyncTimestampGMT = lastSyncTimestampGMT;
    }

    public Integer getMinAvgHeartRate() {
        return minAvgHeartRate;
    }

    public void setMinAvgHeartRate(Integer minAvgHeartRate) {
        this.minAvgHeartRate = minAvgHeartRate;
    }

    public Integer getMaxAvgHeartRate() {
        return maxAvgHeartRate;
    }

    public void setMaxAvgHeartRate(Integer maxAvgHeartRate) {
        this.maxAvgHeartRate = maxAvgHeartRate;
    }
}
