package ca.yorku.eecs.mack.healthappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/***
 * Creates a simple username and email to indicate which user is trying the application
 */
public class CreateAccountActivity extends AppCompatActivity {

    public static final String USER = "user";
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText email;
    private Button buttonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        email = findViewById(R.id.editTextEmail);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);

        // Use lambda expression for onClickListener
        buttonCreateAccount.setOnClickListener(this::onClick);
    }



    // Rename method to follow Java conventions
    private void onClick(View v) {
        // Handle button click here
        String username = editTextUsername.getText().toString();
        SharedPreferences.Editor editor = getSharedPreferences(USER, Context.MODE_PRIVATE).edit();
        editor.putString("username", username);
        editor.apply();
        String password = editTextPassword.getText().toString();
        String emailInput = email.getText().toString();
        editor.putString("email", emailInput);
        editor.apply();

        if (username.isEmpty() || password.isEmpty() || emailInput.isEmpty()) {
            Toast.makeText(CreateAccountActivity.this, R.string.fields_cant_be_empty, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CreateAccountActivity.this, R.string.account_created_successfully, Toast.LENGTH_SHORT).show();
        }
    }
}
