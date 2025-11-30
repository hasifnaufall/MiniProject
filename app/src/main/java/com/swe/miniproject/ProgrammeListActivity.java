package com.swe.miniproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ProgrammeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programme_list);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });

        ConstraintLayout headerLayout = findViewById(R.id.headerLayout);
        TextView tvProgrammeTitle = findViewById(R.id.tvProgrammeTitle);
        LinearLayout llProgrammeDetails = findViewById(R.id.llProgrammeDetails);
        Button btnApply = findViewById(R.id.btnApply);

        String programmeTitle = getIntent().getStringExtra("programmeTitle");
        String programmeList = getIntent().getStringExtra("programmeList");

        // Set the title or hide it
        if (programmeTitle.equals("GAPP Programme") || programmeTitle.equals("GUFP Programme")) {
            tvProgrammeTitle.setVisibility(View.GONE);
        } else {
            tvProgrammeTitle.setText(programmeTitle);
        }

        // Clear previous views, if any
        llProgrammeDetails.removeAllViews();

        // Add header image if it's GAPP or GUFP
        if (programmeTitle.equals("GAPP Programme")) {
            ImageView ivGappHeader = new ImageView(this);
            ivGappHeader.setImageResource(R.drawable.gapp_header);
            ivGappHeader.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            ivGappHeader.setAdjustViewBounds(true);
            llProgrammeDetails.addView(ivGappHeader);
        } else if (programmeTitle.equals("GUFP Programme")) {
            ImageView ivGufpHeader = new ImageView(this);
            ivGufpHeader.setImageResource(R.drawable.gufp_header);
            ivGufpHeader.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            ivGufpHeader.setAdjustViewBounds(true);
            llProgrammeDetails.addView(ivGufpHeader);
        }

        // Split the programmeList string into individual key-value pairs
        String[] lines = programmeList.split("\n");

        for (String line : lines) {
            // Split each line into a key and a value
            String[] parts = line.split(":", 2);
            String key = parts[0].trim();
            String value = parts.length > 1 ? parts[1].trim() : "";

            // Inflate the item_programme_detail.xml layout
            LayoutInflater inflater = LayoutInflater.from(this);
            View detailView = inflater.inflate(R.layout.item_programme_detail, llProgrammeDetails, false);

            // Get the TextViews from the inflated layout
            TextView tvKey = detailView.findViewById(R.id.tvDetailKey);
            TextView tvValue = detailView.findViewById(R.id.tvDetailValue);

            // Set the text for the key and value
            tvKey.setText(key);
            tvValue.setText(value);

            // Add the inflated view to the LinearLayout
            llProgrammeDetails.addView(detailView);
        }

        btnApply.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gmi.vialing.com/oa/login"));
            startActivity(browserIntent);
        });
    }
}
