package com.swe.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button btnDiploma = findViewById(R.id.btnDiploma);
        Button btnGapp = findViewById(R.id.btnGapp);
        Button btnGufp = findViewById(R.id.btnGufp);
        Button btnStart = findViewById(R.id.btnStart);
        Button btnEnquiry = findViewById(R.id.btnEnquiry);
        Button btnAboutUs = findViewById(R.id.btnAboutUs);

        btnDiploma.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, DiplomaDepartmentsActivity.class);
            startActivity(intent);
        });

        btnGapp.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, ProgrammeListActivity.class);
            intent.putExtra("programmeTitle", "GAPP Programme");
            intent.putExtra("programmeList", getGappPrograms());
            startActivity(intent);
        });

        btnGufp.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, ProgrammeListActivity.class);
            intent.putExtra("programmeTitle", "GUFP Programme");
            intent.putExtra("programmeList", getGufpPrograms());
            startActivity(intent);
        });

        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, QualificationActivity.class);
            startActivity(intent);
        });

        btnEnquiry.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, EnquiryActivity.class);
            startActivity(intent);
        });

        btnAboutUs.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, AboutUsActivity.class);
            startActivity(intent);
        });
    }

    private String getGappPrograms() {
        return "Mode: Onsite\n"
                + "Intakes: July\n"
                + "Conducted in Malaysia: Yes\n"
                + "Field of Study: Pre-University Programme / University Foundation Studies\n"
                + "Awarding Country: Malaysia\n"
                + "Fees: 40000 / Course / 60000 / Course (international)\n"
                + "Duration: 22-month preparatory programme at GMI (4 semesters), 6-month intensive German Language training at various language centres in Germany\n"
                + "Type: Full-time Study\n"
                + "Level of Study: Pre-University/Foundation Studies\n"
                + "Faculty: Foundation, Pre-University & General Studies Department\n"
                + "Exam Body: Internal Qualification, Malaysia";
    }

    private String getGufpPrograms() {
        return "Mode: Onsite\n"
                + "Intakes: July\n"
                + "Conducted in Malaysia: Yes\n"
                + "Field of Study: Pre-University Programme / University Foundation Studies\n"
                + "Awarding Country: Malaysia\n"
                + "Fees: 17100 / Course / 24150 / Course (international)\n"
                + "Duration: 1 year\n"
                + "Type: Full-time Study\n"
                + "Level of Study: Pre-University/Foundation Studies\n"
                + "Faculty: Foundation, Pre-University & General Studies Department\n"
                + "Exam Body: Internal Qualification, Malaysia";
    }
}
