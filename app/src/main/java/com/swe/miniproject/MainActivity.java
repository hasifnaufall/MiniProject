package com.swe.miniproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private List<CourseRequirement> courseList;
    private LinearLayout subjectsContainer;
    private List<View> subjectViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });

        subjectsContainer = findViewById(R.id.llSpmSubjectsContainer);
        Button btnAddSpmSubject = findViewById(R.id.btnAddSpmSubject);

        // Add three initial subject rows
        addSpmSubjectRow();
        addSpmSubjectRow();
        addSpmSubjectRow();

        btnAddSpmSubject.setOnClickListener(v -> {
            addSpmSubjectRow();
        });

        // Initialize course requirements (all diplomas use the same requirements)
        courseList = new ArrayList<>();
        courseList.add(new CourseRequirement("GAPP Foundation", "GAPP", "A", "A", "A", "A", "F", "F", "F"));
        courseList.add(new CourseRequirement("GUFP Foundation", "GUFP", "C", "C", "C", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma of Mechatronics Engineering Technology", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma of Engineering Technology (Instrumentation & Control)", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma of Electronics Engineering Technology (Computer)", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma in Engineering Technology (Sustainable Energy & Power Distribution)", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
        courseList.add(new CourseRequirement("Diploma in Autotronics Engineering Technology", "Diploma", "E", "E", "E", "C", "F", "F", "F"));
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

            List<String> optionalSubjects = new ArrayList<>();
            List<String> optionalGrades = new ArrayList<>();

            for (View subjectView : subjectViews) {
                AutoCompleteTextView etSubjectName = subjectView.findViewById(R.id.etSpmSubjectName);
                AutoCompleteTextView etSubjectGrade = subjectView.findViewById(R.id.etSpmSubjectGrade);

                String subjectName = etSubjectName.getText().toString().trim();
                String subjectGrade = etSubjectGrade.getText().toString().trim().toUpperCase();

                if (!subjectName.isEmpty() && !subjectGrade.isEmpty()) {
                    optionalSubjects.add(subjectName);
                    optionalGrades.add(subjectGrade);
                }
            }

            // Check for duplicate subjects
            Set<String> uniqueSubjects = new HashSet<>(optionalSubjects);
            if (uniqueSubjects.size() < optionalSubjects.size()) {
                Toast.makeText(MainActivity.this, "Please select unique optional subjects.", Toast.LENGTH_SHORT).show();
                return;
            }

            String prinsipPerakaunan = "F";
            String chemistry = "F";
            String physics = "F";
            String pendidikanQuranSunnah = "F";
            String pendidikanIslam = "F";

            for (int i = 0; i < optionalSubjects.size(); i++) {
                String subject = optionalSubjects.get(i);
                String grade = optionalGrades.get(i);

                if (subject.equals("Prinsip Perakaunan")) {
                    prinsipPerakaunan = grade;
                } else if (subject.equals("Chemistry")) {
                    chemistry = grade;
                } else if (subject.equals("Physics")) {
                    physics = grade;
                } else if (subject.equals("Pendidikan AL-QURAN & AS-SUNNAH")) {
                    pendidikanQuranSunnah = grade;
                } else if (subject.equals("Pendidikan Islam")) {
                    pendidikanIslam = grade;
                }
            }

            ArrayList<String> eligibleCourses = new ArrayList<>();
            for (CourseRequirement cr : courseList) {
                if (isEligible(bm, english, sejarah, math, prinsipPerakaunan, chemistry, physics, pendidikanQuranSunnah, pendidikanIslam, cr)) {
                    eligibleCourses.add(cr.courseName + " (" + cr.programmeType + ")");
                }
            }

            if (eligibleCourses.isEmpty()) {
                Toast.makeText(MainActivity.this, "No eligible courses found based on your results.", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(MainActivity.this, UserResult.class);
                intent.putExtra("eligibleCourses", eligibleCourses);
                intent.putExtra("sourceActivity", "SPM");
                startActivity(intent);
            }
        });
    }

    private void addSpmSubjectRow() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View subjectView = inflater.inflate(R.layout.spm_subject_item, subjectsContainer, false);

        String[] grades = getResources().getStringArray(R.array.grades_array);
        String[] optionalSubjects = getResources().getStringArray(R.array.spm_optional_subjects_array);

        ArrayAdapter<String> gradesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, grades);
        ArrayAdapter<String> subjectsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, optionalSubjects);

        AutoCompleteTextView etSpmSubjectName = subjectView.findViewById(R.id.etSpmSubjectName);
        etSpmSubjectName.setAdapter(subjectsAdapter);
        AutoCompleteTextView etSpmSubjectGrade = subjectView.findViewById(R.id.etSpmSubjectGrade);
        etSpmSubjectGrade.setAdapter(gradesAdapter);

        subjectsContainer.addView(subjectView);
        subjectViews.add(subjectView);
    }

    private boolean isEligible(String bm, String english, String sejarah, String math, String prinsipPerakaunan, String chemistry, String physics, String pendidikanQuranSunnah, String pendidikanIslam, CourseRequirement cr) {
        return compareGrade(bm, cr.minBM) >= 0 &&
                compareGrade(english, cr.minEnglish) >= 0 &&
                compareGrade(sejarah, cr.minSejarah) >= 0 &&
                compareGrade(math, cr.minMath) >= 0 &&
                (cr.minPrinsipPerakaunan.equals("F") || compareGrade(prinsipPerakaunan, cr.minPrinsipPerakaunan) >= 0) &&
                (cr.minSainsKomp.equals("F") || compareGrade(chemistry, cr.minSainsKomp) >= 0) &&
                (cr.minKimia.equals("F") || compareGrade(physics, cr.minKimia) >= 0);
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
