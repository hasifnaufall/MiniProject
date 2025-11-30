package com.swe.miniproject;

public class CourseRequirement {
    public final String courseName;
    public final String programmeType;
    public final String minBM;
    public final String minEnglish;
    public final String minSejarah;
    public final String minMath;
    public final String minPrinsipPerakaunan;
    public final String minSainsKomp;
    public final String minKimia;

    public CourseRequirement(String courseName, String programmeType, String minBM, String minEnglish, String minSejarah, String minMath, String minPrinsipPerakaunan, String minSainsKomp, String minKimia) {
        this.courseName = courseName;
        this.programmeType = programmeType;
        this.minBM = minBM;
        this.minEnglish = minEnglish;
        this.minSejarah = minSejarah;
        this.minMath = minMath;
        this.minPrinsipPerakaunan = minPrinsipPerakaunan;
        this.minSainsKomp = minSainsKomp;
        this.minKimia = minKimia;
    }
}
