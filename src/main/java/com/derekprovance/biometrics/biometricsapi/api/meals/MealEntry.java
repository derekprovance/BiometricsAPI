package com.derekprovance.biometrics.biometricsapi.api.meals;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class MealEntry {
    private @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) Integer id;

    @NotNull
    private LocalDate date;

    @NotNull
    private String name;

    @NotNull
    private Integer amount;

    @NotNull
    private String unit;

    private Integer calories;

    private Float carbs;

    private Float fat;

    private Float fiber;

    private Float protein;

    private Float sodium;

    @NotNull
    @Column(name="meal_type_id")
    private MealBlock mealBlock;

    @NotNull
    @JsonIgnore
    @Column(unique=true)
    private Long logId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Float getCarbs() {
        return carbs;
    }

    public void setCarbs(Float carbs) {
        this.carbs = carbs;
    }

    public Float getFat() {
        return fat;
    }

    public void setFat(Float fat) {
        this.fat = fat;
    }

    public Float getFiber() {
        return fiber;
    }

    public void setFiber(Float fiber) {
        this.fiber = fiber;
    }

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public Float getSodium() {
        return sodium;
    }

    public void setSodium(Float sodium) {
        this.sodium = sodium;
    }

    public MealBlock getMealBlock() {
        return mealBlock;
    }

    public void setMealBlock(MealBlock mealBlock) {
        this.mealBlock = mealBlock;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }
}
