package com.randy073.findmehere;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

/**
 * Activity which displays a login screen to the user.
 */
public class SignUpActivity extends Activity {
  // UI references.
  private EditText usernameEditText;
  private EditText passwordEditText;
  private EditText passwordAgainEditText;
  // [START declare_auth]
  private FirebaseAuth mAuth;
  // [END declare_auth]

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_signup);

    mAuth = FirebaseAuth.getInstance();



    // Set up the signup form.
    usernameEditText = (EditText) findViewById(R.id.username_edit_text);

    passwordEditText = (EditText) findViewById(R.id.password_edit_text);
    passwordAgainEditText = (EditText) findViewById(R.id.password_again_edit_text);
    passwordAgainEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == R.id.edittext_action_signup ||
            actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
          signup();
          return true;
        }
        return false;
      }
    });

    // Set up the submit button click handler
    Button mActionButton = (Button) findViewById(R.id.action_button);
    mActionButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        signup();
      }
    });
  }

  private void signup() {
    String username = usernameEditText.getText().toString().trim();
    String password = passwordEditText.getText().toString().trim();
    String passwordAgain = passwordAgainEditText.getText().toString().trim();

    // Validate the sign up data
    boolean validationError = false;
    StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
    if (username.length() == 0) {
      validationError = true;
      validationErrorMessage.append(getString(R.string.error_blank_username));
    }
    if (password.length() == 0) {
      if (validationError) {
        validationErrorMessage.append(getString(R.string.error_join));
      }
      validationError = true;
      validationErrorMessage.append(getString(R.string.error_blank_password));
    }
    if (!password.equals(passwordAgain)) {
      if (validationError) {
        validationErrorMessage.append(getString(R.string.error_join));
      }
      validationError = true;
      validationErrorMessage.append(getString(R.string.error_mismatched_passwords));
    }
    validationErrorMessage.append(getString(R.string.error_end));

    // If there is a validation error, display the error
    if (validationError) {
      Toast.makeText(SignUpActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
          .show();
      return;
    }

    // Set up a progress dialog
    showProgressDialog();

    // Set up a new user
    createAccount(username, password);
  }
  private void showProgressDialog(){
    final ProgressDialog dialog = new ProgressDialog(SignUpActivity.this);
    dialog.setMessage(getString(R.string.progress_signup));
    dialog.show();
  }
  private void createAccount(String email, String password) {

    // [START create_user_with_email]
    mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {


                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                  Toast.makeText(SignUpActivity.this, "Sign up failed",
                          Toast.LENGTH_SHORT).show();
                }
                return;

              }
            });
    // [END create_user_with_email]
  }

}
