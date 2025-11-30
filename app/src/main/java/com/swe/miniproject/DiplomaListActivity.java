package com.swe.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiplomaListActivity extends AppCompatActivity {

    private Map<String, List<String>> departmentCourses = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diploma_list);

        // Initialize department courses
        initializeDepartmentCourses();

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });

        TextView tvTitle = findViewById(R.id.tvDiplomaListTitle);
        ListView lvDiplomaCourses = findViewById(R.id.lvDiplomaCourses);
        LinearLayout llHeader = findViewById(R.id.llHeader);

        String department = getIntent().getStringExtra("department");
        tvTitle.setText(department);

        // Add header image based on department
        ImageView headerImage = new ImageView(this);
        if ("Electrical Engineering".equals(department)) {
            headerImage.setImageResource(R.drawable.eed_header);
        } else if ("Mechanical Engineering".equals(department)) {
            headerImage.setImageResource(R.drawable.med_header);
        } else if ("Computer Information Department".equals(department)) {
            headerImage.setImageResource(R.drawable.cid_header);
        }
        headerImage.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        headerImage.setAdjustViewBounds(true);
        llHeader.addView(headerImage, 0);

        List<String> courses = departmentCourses.get(department);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courses);
        lvDiplomaCourses.setAdapter(adapter);

        lvDiplomaCourses.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCourse = (String) parent.getItemAtPosition(position);
            Intent intent = new Intent(DiplomaListActivity.this, ProgrammeListActivity.class);
            intent.putExtra("programmeTitle", selectedCourse);
            intent.putExtra("programmeList", getDiplomaCourseDetails(selectedCourse));
            startActivity(intent);
        });
    }

    private void initializeDepartmentCourses() {
        departmentCourses.put("Electrical Engineering", new ArrayList<>(Arrays.asList(
                "Diploma of Mechatronics Engineering Technology",
                "Diploma in Engineering Technology (Sustainable Energy & Power Distribution)",
                "Diploma of Electronics Engineering Technology (Computer)",
                "Diploma of Engineering Technology (Instrumentation & Control)",
                "Diploma in Autotronics Engineering Technology"
        )));

        departmentCourses.put("Mechanical Engineering", new ArrayList<>(Arrays.asList(
                "Diploma in Precision Tooling Engineering Technology",
                "Diploma in Engineering Technology (Industrial Design)",
                "Diploma in Industrial Quality Engineering Technology",
                "Diploma in Innovative Product Design Engineering Technology",
                "Diploma of Mechanical Engineering Technology (CNC Precision)",
                "Diploma in Engineering Technology(Machine Tools Maintenance)",
                "Diploma of Mechanical Engineering Technology (Manufacturing)"
        )));

        departmentCourses.put("Computer Information Department", new ArrayList<>(Arrays.asList(
                "Diploma in Software Engineering",
                "Diploma in Cyber Security Technology",
                "Diploma in Creative Multimedia"
        )));
    }

    private String getDiplomaCourseDetails(String courseName) {
        // ... keep your existing switch case method as is ...
        switch (courseName) {
            case "Diploma in Creative Multimedia":
                return "Program Code: (R2/0211/4/0171) (07/30) (MQA/FA 6035)\n"
                        + "Status: Accredited\n"
                        + "Study Mode: Full Time\n"
                        + "Fee: RM 33,324\n"
                        + "Duration: 6 Semester/ 3 Years\n"
                        + "Job Opportunity:\n"
                        + "1. Creative Media Editor\n"
                        + "2. Web Designer\n"
                        + "3. Creative Designer\n"
                        + "4. 2D/3D Animator\n"
                        + "5. Photographer & Videographer\n"
                        + "6. Mobile Apps Developer";
            case "Diploma of Mechatronics Engineering Technology":
                return "Program Code: (R3/0713/4/0041) (07/30) (MQA/FA 14611)\n"
                        + "Status: Accredited\n"
                        + "Study Mode: Full Time\n"
                        + "Fee: RM 33,324\n"
                        + "Duration: 6 Semester/ 3 Years\n"
                        + "Job Opportunity:\n"
                        + "1. Assistant Mechatronics Engineer\n"
                        + "2. Automation Technical Specialist\n"
                        + "3. Automation System Designer\n"
                        + "4. Remote Operating Vehicle (ROV) Supervisor.";
            case "Diploma of Engineering Technology (Instrumentation & Control)":
                return "Program Code: (R3/0741/4/0012) (07/30) (MQA/FA 14612)\n"
                        + "Status: Accredited\n"
                        + "Study Mode: Full Time\n"
                        + "Fee: RM 33,324\n"
                        + "Duration: 6 Semester/ 3 Years\n"
                        + "Job Opportunity:\n"
                        + "1. Field Assistant Engineer\n"
                        + "2. Control & Instrumentation Technologist\n"
                        + "3. Service Assistant Engineer\n"
                        + "4. Process Assistant Engineer";
            case "Diploma of Electronics Engineering Technology (Computer)":
                return "Program Code: (R3/0741/4/0013) (07/30) (MQA/FA 14613)\n"
                        + "Status: Accredited\n"
                        + "Study Mode: Full Time\n"
                        + "Fee: RM 33,324\n"
                        + "Duration: 6 Semester/ 3 Years\n"
                        + "Job Opportunity:\n"
                        + "1. IT Assistant\n"
                        + "2. System Programmer/Analyst/Management\n"
                        + "3. Telecommunication Technologist\n"
                        + "4. Electronics Support Specialist\n"
                        + "5. Technical Support Specialist";
            case "Diploma in Engineering Technology (Sustainable Energy & Power Distribution)":
                return "Program Code: (R2/522/4/0017) (10/27) (MQA/FA 1410)\n"
                        + "Status: Accredited\n"
                        + "Study Mode: Full Time\n"
                        + "Fee: RM 33,324\n"
                        + "Duration: 6 Semester/ 3 Years\n"
                        + "Job Opportunity:\n"
                        + "1. Photovoltaic Project Engineer\n"
                        + "2. Energy Management Manager\n"
                        + "3. Instrument & Power Plant Engineer\n"
                        + "4. Electrical Engineer\n"
                        + "5. Environmental Consultant\n"
                        + "6. R&D Engineer\n"
                        + "7. Sustainable Energy Technologist\n"
                        + "8. Renewable Energy Power Designer";
            case "Diploma in Autotronics Engineering Technology":
                return "Program Code: (R/523/4/0223) (12/24) (MQA/FA 4936)\n"
                        + "Status: Accredited\n"
                        + "Study Mode: Full Time\n"
                        + "Fee: RM 33,324\n"
                        + "Duration: 6 Semester/ 3 Years\n"
                        + "Job Opportunity:\n"
                        + "1. Autotronics Specialist\n"
                        + "2. Production Assistant Technician\n"
                        + "3. Autotronics Assistant Engineer\n"
                        + "4. Research & Development Executive";
            case "Diploma in Engineering Technology (Industrial Design)":
                return "Program Code: (R2/0741/4/0010) (02/30) (MQA/FA 4785}\n"
                        + "Status: Accredited\n"
                        + "Study Mode: Full Time\n"
                        + "Fee: RM 33,324\n"
                        + "Duration: 6 Semester/ 3 Years\n"
                        + "Job Opportunity:\n"
                        + "1. Industrial Designer\n"
                        + "2. Automotive Designer\n"
                        + "3. Product Designer\n"
                        + "4. Furniture Designer\n"
                        + "5. 3D Visualizer";
            case "Diploma in Innovative Product Design Engineering Technology":
                return "Program Code: (R2/0741/4/0006) (01/26) (A 7165)\n"
                        + "Status: Accredited\n"
                        + "Study Mode: Full Time\n"
                        + "Fee: RM 33,324\n"
                        + "Duration: 6 Semester/ 3 Years\n"
                        + "Job Opportunity:\n"
                        + "1. Product Designer\n"
                        + "2. CAD Application Assistant Engineer\n"
                        + "3. Product and Realibility Assistant Engineer\n"
                        + "4. Quality Assurance Executive";
            case "Diploma in Engineering Technology(Machine Tools Maintenance)":
                return "Status: Accredited\n"
                        + "Study Mode: Full Time\n"
                        + "Fee: RM 33,324 (Education Fee Only)\n"
                        + "Duration: 6 Semester/ 3 Years\n"
                        + "Job Opportunity:\n"
                        + "1. Field Service Machine Maintenance\n"
                        + "2. Assistance Engineer\n"
                        + "3. Machine\n"
                        + "- Designer and Retrofitting (Executive)\n"
                        + "- Designer\n"
                        + "- Expert\n"
                        + "- Associate\n"
                        + "- Automation Specialist";
            case "Diploma in Precision Tooling Engineering Technology":
                return "Program Code: (R2/0714/4/0013) (10/25) (A 5005)\n"
                        + "Status: Accredited\n"
                        + "Study Mode: Full Time\n"
                        + "Fee: RM 33,324\n"
                        + "Duration: 6 Semester/ 3 Years\n"
                        + "Job Opportunity:\n"
                        + "1. Production Engineer\n"
                        + "2. Mould and Die Design Engineer\n"
                        + "3. Machinist Expert\n"
                        + "4. Quality Assurance Engineer\n"
                        + "5. Technical Trainer or Consultant";
            case "Diploma in Industrial Quality Engineering Technology":
                return "Status: Accredited\n"
                        + "Study Mode: Full Time\n"
                        + "Fee: RM 33,324\n"
                        + "Duration: 6 Semester/ 3 Years\n"
                        + "Job Opportunity:\n"
                        + "1. QA Assistant Engineer\n"
                        + "2. Quality:\n"
                        + "- Assurance Supervisor\n"
                        + "- Auditor\n"
                        + "- Trainer\n"
                        + "- Management System (QMS) Coordinator\n"
                        + "3. QA/QC Technician\n"
                        + "4. Quality Control\n"
                        + "- Assistant Engineer";
            case "Diploma of Mechanical Engineering Technology (CNC Precision)":
                return "Program Code: (R3/0714/4/0025) (12/27) (MQA/FA 14870)\n"
                        + "Status: Accredited\n"
                        + "Study Mode: Full Time\n"
                        + "Fee: RM 33,324\n"
                        + "Duration: 6 Semester/ 3 Years\n"
                        + "Job Opportunity:\n"
                        + "1. CNC\n"
                        + "- Milling Machinist\n"
                        + "- Milling Programmer\n"
                        + "- Turning Machinist\n"
                        + "- Programmer Programmer";
            case "Diploma of Mechanical Engineering Technology (Manufacturing)":
                return "Program Code: ((R2/0714/4/0011) (02/28) (MQA/FA 14871))\n"
                        + "Status: Accredited\n"
                        + "Study Mode: Full Time\n"
                        + "Fee: RM 33,324 (Education Fee Only)\n"
                        + "Duration: 6 Semester/ 3 Years\n"
                        + "Job Opportunity:\n"
                        + "1. Manufacturing Assistant Engineer\n"
                        + "2. Industrial Assistant Engineer\n"
                        + "3. Automation Technical Assistant\n"
                        + "4. Production Assistant Engineer / Technical Supervisor";
            case "Diploma in Software Engineering":
                return "Program Code: (R/0612/4/0007) (03/28) (MQA/FA 9541)\n"
                        + "Status: Accredited\n"
                        + "Study Mode: Full Time\n"
                        + "Fee: RM 33,324\n"
                        + "Duration: 6 Semester/ 3 Years\n"
                        + "Job Opportunity:\n"
                        + "1. Software\n"
                        + "- Tester\n"
                        + "- System Analysis\n"
                        + "- Quality Assurance Engineer";
            case "Diploma in Cyber Security Technology":
                return "Program Code: (R2/0613/4/0043) (07/26) (MQA/FA 15748)\n"
                        + "Status: Accredited\n"
                        + "Study Mode: Full Time\n"
                        + "Fee: RM 33,324\n"
                        + "Duration: 6 Semester/ 3 Years\n"
                        + "Job Opportunity:\n"
                        + "1. Cybersecurity Analyst\n"
                        + "2. Security Engineer /Architect\n"
                        + "3. Penetration Testing\n"
                        + "4. Security Consultant\n"
                        + "5. Ethical Hacker";
            default:
                return "Details not available.";
        }
    }
}
