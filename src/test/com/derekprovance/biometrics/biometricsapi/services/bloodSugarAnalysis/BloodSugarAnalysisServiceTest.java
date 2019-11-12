package com.derekprovance.biometrics.biometricsapi.services.bloodSugarAnalysis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class BloodSugarAnalysisServiceTest {
    @DisplayName("Single test successful")

    @Test
    void analyze() {
        System.out.println("foobar");
        assertTrue(true);
    }

    @Test
    void poop() {
        assertEquals("foobar", "freeman");
    }
}