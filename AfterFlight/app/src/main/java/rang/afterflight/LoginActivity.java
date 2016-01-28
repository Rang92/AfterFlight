package rang.afterflight;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseUser;


/**
 * Rang Salih
 * rangsalih@gmail.com
 * 10690972
 * https://www.youtube.com/watch?v=B_FM9cggf8M
 */
public class LoginActivity extends Activity {

    private EditText usernameView;
    private EditText passwordView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        usernameView = (EditText) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);

        loginClicked();
        registerClicked();
    }

    public void loginClicked(){
        // Set up the submit button click handler
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Validate the log in data
                boolean validationError = false;
                StringBuilder validationErrorMessage =
                        new StringBuilder(getResources().getString(R.string.error_intro));
                if (isEmpty(usernameView)) {
                    validationError = true;
                    validationErrorMessage.append(getResources().getString(R.string.error_blank_username));
                }
                if (isEmpty(passwordView)) {
                    if (validationError) {
                        validationErrorMessage.append(getResources().getString(R.string.error_join));
                    }
                    validationError = true;
                    validationErrorMessage.append(getResources().getString(R.string.error_blank_password));
                }
                validationErrorMessage.append(getResources().getString(R.string.error_end));

                // If there is a validation error, display the error
                if (validationError) {
                    Toast.makeText(LoginActivity.this, validationErrorMessage.toString(),
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                // Set up a progress dialog
                final ProgressDialog dlg = new ProgressDialog(LoginActivity.this);
                dlg.setTitle("Please wait.");
                dlg.setMessage("Logging in....");
                dlg.show();
                // Call the Parse login method
                ParseUser.logInInBackground(usernameView.getText().toString(),
                        passwordView.getText()
                        .toString(), new LogInCallback() {

                    @Override
                    public void done(ParseUser user, com.parse.ParseException e) {
                        dlg.dismiss();
                        // if there is an error
                        if (e != null) {
                            // Show the error message
                            Toast.makeText(LoginActivity.this, e.getMessage(),
                                    Toast.LENGTH_LONG).show();// if there isn´t an error
                            // if there isn´t an error
                        } else {
                            // Start an intent for the dispatch activity
                            Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                            intent.putExtra("username", usernameView.getText().toString());
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                    Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }

    public void registerClicked(){
        // Sign up button click handler
        ((Button) findViewById(R.id.register_now)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Starts an intent for the sign up activity
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }
}