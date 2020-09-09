package com.theseuntaylor.aadproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import com.theseuntaylor.aadproject.fragments.LearningLeadersFragment;
import com.theseuntaylor.aadproject.R;
import com.theseuntaylor.aadproject.fragments.SkillIqLeadersFragment;
import com.theseuntaylor.aadproject.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        sectionsPagerAdapter.addFragment(new LearningLeadersFragment(), "Learning Leaders");
        sectionsPagerAdapter.addFragment(new SkillIqLeadersFragment(), "Skill IQ Leaders");

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Button submitButton = findViewById(R.id.goto_submit_project);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitProject();
            }
        });
    }

    public void SubmitProject() {
        // make post request to send information
        Intent submitProjectIntent = new Intent(getApplicationContext(), SubmitProjectActivity.class);
        startActivity(submitProjectIntent);
    }
}