package com.swe.miniproject;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<CourseRequirement> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });

        // Initialize course requirements (all diplomas use the same requirements)
        courseList = new ArrayList<>();
        courseList.add(new CourseRequirement("GAPP Foundation", "GAPP", "A", "A", "A", "A", "F", "F", "F"));
        courseList.add(new CourseRequirement("GUFP Foundation", "GUFP", "C", "C", "C", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma of Mechatronics Engineering Technology", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma of Engineering Technology (Instrumentation & Control)", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma of Electronics Engineering Technology (Computer)", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma in Engineering Technology (Sustainable Energy & Power Distribution)", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Autotronics Engineering Technology", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma in Engineering Technology (Industrial Design)", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma in Innovative Product Design Engineering Technology", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma in Engineering Technology(Machine Tools Maintenance)", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma in Precision Tooling Engineering Technology", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma in Industrial Quality Engineering Technology", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma of Mechanical Engineering Technology (CNC Precision)", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma of Mechanical Engineering Technology (Manufacturing)", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma in Software Engineering", "Diploma", "E", "E", "E", "C", "F", "C", "F"));
        courseList.add(new CourseRequirement("Diploma in Creative Multimedia", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma in Cyber Security Technology", "Diploma", "E", "E", "E", "C", "F", "C", "F"));

        String[] grades = getResources().getStringArray(R.array.grades_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, grades);

        AutoCompleteTextView etBM = findViewById(R.id.etBM);
        etBM.setAdapter(adapter);
        AutoCompleteTextView etEnglish = findViewById(R.id.etEnglish);
        etEnglish.setAdapter(adapter);
        AutoCompleteTextView etSejarah = findViewById(R.id.etSejarah);
        etSejarah.setAdapter(adapter);
        AutoCompleteTextView etMath = findViewById(R.id.etMath);
        etMath.setAdapter(adapter);
        AutoCompleteTextView etPrinsipPerakaunan = findViewById(R.id.etPrinsipPerakaunan);
        etPrinsipPerakaunan.setAdapter(adapter);
        AutoCompleteTextView etSainsKomp = findViewById(R.id.etSainsKomp);
        etSainsKomp.setAdapter(adapter);
        AutoCompleteTextView etKimia = findViewById(R.id.etKimia);
        etKimia.setAdapter(adapter);

        Button btnCheck = findViewById(R.id.btnCheckEligibility);

        btnCheck.setOnClickListener(v -> {
            String bm = etBM.getText().toString().trim().toUpperCase();
            String english = etEnglish.getText().toString().trim().toUpperCase();
            String sejarah = etSejarah.getText().toString().trim().toUpperCase();
            String math = etMath.getText().toString().trim().toUpperCase();

            if (bm.isEmpty() || english.isEmpty() || sejarah.isEmpty() || math.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill in all required fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            String prinsipPerakaunan = etPrinsipPerakaunan.getText().toString().trim().toUpperCase();
            String sainsKomp = etSainsKomp.getText().toString().trim().toUpperCase();
            String kimia = etKimia.getText().toString().trim().toUpperCase();

            ArrayList<String> eligibleCourses = new ArrayList<>();
            for (CourseRequirement cr : courseList) {
                if (isEligible(bm, english, sejarah, math, prinsipPerakaunan, sainsKomp, kimia, cr)) {
                    eligibleCourses.add(cr.courseName + " (" + cr.programmeType + ")");
                }
            }

            if (eligibleCourses.isEmpty()) {
                Toast.makeText(MainActivity.this, "No eligible courses found based on your results.", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(MainActivity.this, UserResult.class);
                intent.putExtra("eligibleCourses", eligibleCourses);
                intent.putExtra("sourceActivity", "SPM"); // Add this line
                startActivity(intent);
            }
        });
    }

    private boolean isEligible(String bm, String english, String sejarah, String math, String prinsipPerakaunan, String sainsKomp, String kimia, CourseRequirement cr) {
        return compareGrade(bm, cr.minBM) >= 0 &&
                compareGrade(english, cr.minEnglish) >= 0 &&
                compareGrade(sejarah, cr.minSejarah) >= 0 &&
                compareGrade(math, cr.minMath) >= 0 &&
                (cr.minPrinsipPerakaunan.equals("F") || compareGrade(prinsipPerakaunan, cr.minPrinsipPerakaunan) >= 0) &&
                (cr.minSainsKomp.equals("F") || compareGrade(sainsKomp, cr.minSainsKomp) >= 0) &&
                (cr.minKimia.equals("F") || compareGrade(kimia, cr.minKimia) >= 0);
    }

    private int compareGrade(String userGrade, String minGrade) {
        String[] grades = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "E", "F"};
        int userIdx = -1, minIdx = -1;
        for (int i = 0; i < grades.length; i++) {
            if (grades[i].equals(userGrade)) userIdx = i;
            if (grades[i].equals(minGrade)) minIdx = i;
        }

        if (userGrade.isEmpty()) {
            return 0; // Treat empty optional subjects as a "pass"
        }

        if (userIdx == -1) return -1; // Invalid grade is a failure
        if (minIdx == -1) return -1; // Should not happen

        return userIdx <= minIdx ? 1 : -1;
    }
}
