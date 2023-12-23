package ca.yorku.eecs.mack.healthappdemo;

import static ca.yorku.eecs.mack.healthappdemo.HealthAppDemo.PREFS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

/***
 * Daily Diary log/survey
 * Shown as third view with 5 questions for the user to click answers from spinner
 * Counts the number of clicks and checks the number of trials (5 max, else use can log their input as normal)
 * Considers the time it took to complete the survey until clicking next button for the next activity
 * - if trial is less than 5 or more than 5, next activity launches back to home screen
 * - if trial is = 5, next activity is the results page
 */
public class DailyDiary_Spinner extends Activity {
    private TextView dayOfWeek;
    private long startTimeSBtn; // to store the start time
    private double elapsedTime; // to store elapsed time
    private int clickCount = 0;
    private int count;
    private static final String PREFS3 = "List";
    private static final String TIME_KEY = "time";
    private static final String CLICK_COUNT_KEY = "click_count";
    private Spinner spinner1, spinner2, spinner3, spinner4, spinner5;
    private Button nextButton;
    private DecimalFormat df = new DecimalFormat("#.##");
    String moodsList[] = {"Happy", "Content", "Neutral", "Sad", "Stressed", "Anxious", "Other"};
    String mindsList[] = {"Brain fog", "Stressed", "Focused", "Distracted", "Calm", "Motivated", "Unproductive"};
    String excercisesList[] = {"Running", "Walking", "Cycling", "Weight Lifting", "Yoga", "Sports", "Other"};
    private ToneGenerator toneGenerator = new ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dailydiary_spinner);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle b = getIntent().getExtras();
        assert b != null;
        count = b.getInt("count");
        dayOfWeek = findViewById(R.id.paramLab);
        if (count <= 5){
            dayOfWeek.setText(""+count+"/5");
        }
        else{
            dayOfWeek.setText(" ");
        }
        //check if selected
        // Initialize Spinners and Button
        spinner1 = findViewById(R.id.paramOrderOfControl);
        spinner1.setSelection(0);
        spinner2 = findViewById(R.id.paramGain);
        spinner2.setSelection(0);
        spinner3 = findViewById(R.id.paramPathType);
        spinner3.setSelection(0);
        spinner4 = findViewById(R.id.paramPathWidth);
        spinner4.setSelection(0);
        spinner5 = findViewById(R.id.NumLapsWidth);
        spinner5.setSelection(0);

        nextButton = findViewById(R.id.next);

        // Set up OnItemSelectedListener for each Spinner
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner3.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner4.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner5.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        // Record the start time when the activity is created
        startTimeSBtn = System.currentTimeMillis();

    }
    private class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // Check if all Spinners have a selection
            clickCount++;
            toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP);
            boolean allSelected = isAllSpinnersSelected();
            // Enable or disable the Next button based on the selection status
            nextButton.setEnabled(allSelected);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Handle the case where nothing is selected (if needed)
        }
    }

    private boolean isAllSpinnersSelected() {
        // Check if all Spinners have a selection
        return spinner1.getSelectedItemPosition() > 0 &&
                spinner2.getSelectedItemPosition() > 0 &&
                spinner3.getSelectedItemPosition() > 0 &&
                spinner4.getSelectedItemPosition() > 0 &&
                spinner5.getSelectedItemPosition() > 0;
    }

    public void onNextButtonClick(View view) {
        if(count <= 5){
            saveData();
            saveCounter();
        }

        Intent intent;
        if (count == 5) {
            intent = new Intent(this, Results.class);
        } else {
            intent = new Intent(this, HealthAppDemo.class);
        }
        startActivity(intent);
    }

    private void saveData(){
        long endTime = System.currentTimeMillis();
        // Calculate the elapsed time
        elapsedTime = (endTime - startTimeSBtn)/1000.0;
        String time = df.format(elapsedTime);
        SharedPreferences.Editor editor = getSharedPreferences(PREFS3, Context.MODE_PRIVATE).edit();
        editor.putString(TIME_KEY+count, time);
        editor.apply();
        editor.putInt(CLICK_COUNT_KEY+count, clickCount);
        editor.apply();
    }

    private void saveCounter() {
        SharedPreferences prefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        // Update the counter in SharedPreferences
        SharedPreferences.Editor editor = prefs.edit();
        int newcount = count;
        newcount++;
        editor.putInt("counter", newcount);
        editor.apply();
    }
}
