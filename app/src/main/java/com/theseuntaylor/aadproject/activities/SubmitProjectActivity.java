package com.theseuntaylor.aadproject.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.theseuntaylor.aadproject.R;
import com.theseuntaylor.aadproject.utilities.AlertDialog;

public class SubmitProjectActivity extends AppCompatActivity {

    private TextInputLayout firstNameInputLayout, lastNameInputLayout, emailInputLayout, githubLinkInputLayout;
    private Button submitProjectButton;

    AlertDialog alertDialog = new AlertDialog();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_project);

        FindViews();

        submitProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidateFields()) {
                    String firstName = firstNameInputLayout.getEditText().getText().toString().trim();
                    String lastName = lastNameInputLayout.getEditText().getText().toString().trim();
                    String email = emailInputLayout.getEditText().getText().toString().trim();
                    String githubLink = githubLinkInputLayout.getEditText().getText().toString().trim();

                    alertDialog.CreateDialog(SubmitProjectActivity.this,
                            "Are you sure?", "Submit Project",
                            firstName, lastName, email, githubLink
                    ).show();
                }
            }
        });
    }



    private void FindViews() {
        firstNameInputLayout = findViewById(R.id.first_name_text_input_layout);
        lastNameInputLayout = findViewById(R.id.last_name_text_input_layout);
        githubLinkInputLayout = findViewById(R.id.github_link_text_input_layout);
        emailInputLayout = findViewById(R.id.email_text_input_layout);
        submitProjectButton = findViewById(R.id.submit_project_button);
    }

    private boolean ValidateFields() {
        if (!ValidateEmail() | !ValidateGithubLink() | !ValidateFirstName() | !ValidateLastName()){
            return false;
        }
        return true;
    }

    private boolean ValidateLastName() {
        String lastName = lastNameInputLayout.getEditText().getText().toString().trim();
        if (lastName.isEmpty()) {
            lastNameInputLayout.setError("Please put in your last name");
            return false;
        }
        lastNameInputLayout
                .setError(null);
        return true;
    }

    private boolean ValidateFirstName() {
        String firstName = firstNameInputLayout.getEditText().getText().toString().trim();
        if (firstName.isEmpty()) {
            firstNameInputLayout.setError("Please put in your first name");
            return false;
        }
        firstNameInputLayout.setError(null);
        return true;
    }

    private boolean ValidateGithubLink() {
        String githubLink = githubLinkInputLayout.getEditText().getText().toString().trim();
        if (githubLink.isEmpty()) {
            githubLinkInputLayout.setError("This field cannot be empty, don't move mad");
            return false;
        }
        githubLinkInputLayout.setError(null);
        return true;
    }

    private boolean ValidateEmail() {
        String email = emailInputLayout.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            emailInputLayout.setError("Please put in your email");
            return false;
        }
        emailInputLayout.setError(null);
        return true;
    }
}
