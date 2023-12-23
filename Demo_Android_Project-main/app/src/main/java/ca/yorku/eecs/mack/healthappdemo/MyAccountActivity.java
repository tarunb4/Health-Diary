// MyAccountActivity.java
package ca.yorku.eecs.mack.healthappdemo;

import static ca.yorku.eecs.mack.healthappdemo.CreateAccountActivity.USER;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
/***
 * Retrieves data if the user has logged in and showcases the users username and email
 * Additional info is app details and its version
 */
public class MyAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        // Display information about the application
        displayAppInformation();

        // Display user-specific information
        displayUserInfo();
    }

    private void displayAppInformation() {
        // Retrieve application information (you can replace these with your actual data)
        String version = "1.0";
        String developer = "Group Alpha";

        // Display application information in TextViews
        TextView versionTextView = findViewById(R.id.textViewVersion);
        versionTextView.setText("Version: " + version);

        TextView developerTextView = findViewById(R.id.textViewDeveloper);
        developerTextView.setText("Developer: " + developer);
    }

    private void displayUserInfo() {
        // Retrieve user-specific information from SharedPreferences (replace with your actual data retrieval)

        // Display user-specific information in TextViews
        String user = getUsername();
        TextView usernameTextView = findViewById(R.id.textViewUserName);
        usernameTextView.setText("Username: " + user);
        String email = getEmail();
        TextView emailTextView = findViewById(R.id.textViewEmail);
        emailTextView.setText("Email: " + email);

        // Add more TextViews for additional user information
    }
    private String getUsername(){
        SharedPreferences prefs = getSharedPreferences(USER, Context.MODE_PRIVATE);
        return prefs.getString("username", "John Doe"); // the default value
    }
    private String getEmail(){
        SharedPreferences prefs = getSharedPreferences(USER, Context.MODE_PRIVATE);
        return prefs.getString("email", "john.doe@email.com"); // the default value
    }
}
