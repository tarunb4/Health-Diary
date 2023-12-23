package ca.yorku.eecs.mack.healthappdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

/***
 * Daily Diary log/survey
 * Shown as second view with 5 questions for the user to slide answers from seekbar
 * Counts the number of clicks and checks the number of trials (5 max, else use can log their input as normal)
 * Considers the time it took to complete the survey until clicking next button for the next activity
 */
public class DailyDiary_Slider extends Activity {
    private TextView dayOfWeek;
    private SeekBar water, screenTime, sleep,energy, mindfulness;
    private Button nextBtn;
    private long startTimeSBtn; // to store the start time
    private double elapsedTime; // to store elapsed time
    private int clickCount = 0;
    private int count;
    private static final String PREFS_NAME2 = "Slider";
    private static final String TIME_KEY = "time";
    private static final String CLICK_COUNT_KEY = "click_count";
    private DecimalFormat df = new DecimalFormat("#.##");
    private ToneGenerator toneGenerator = new ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dailydiary_slide_btn);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle b = getIntent().getExtras();
        assert b != null;
        count = b.getInt("count");
        dayOfWeek = findViewById(R.id.paramLabelOrder);
        if (count <= 5){
            dayOfWeek.setText(""+count+"/5");
        }
        else{
            dayOfWeek.setText(" ");
        }
        //check if selected
        water = findViewById(R.id.sliderSeekBar);
        screenTime = findViewById(R.id.sliderSeekBar2);
        sleep = findViewById(R.id.sliderSeekBar1);
        energy = findViewById(R.id.sliderSeekBar3);
        mindfulness = findViewById(R.id.sliderSeekBar4);
        nextBtn = findViewById(R.id.next);
        // Record the start time when the activity is created
        startTimeSBtn = System.currentTimeMillis();

        water.setOnSeekBarChangeListener(seekBarChangeListener);
        screenTime.setOnSeekBarChangeListener(seekBarChangeListener);
        sleep.setOnSeekBarChangeListener(seekBarChangeListener);
        energy.setOnSeekBarChangeListener(seekBarChangeListener);
        mindfulness.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    // Listener for SeekBars
    private final SeekBar.OnSeekBarChangeListener seekBarChangeListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // Check if at least one SeekBar is not at position zero
                    clickCount++;
                    toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP);
                    boolean isAnySeekBarNotAtZero = water.getProgress() > 0 && screenTime.getProgress() > 0
                            && sleep.getProgress() > 0 && energy.getProgress() > 0 && mindfulness.getProgress() > 0;
                    // Enable the "Next" button if SeekBar is not at position zero
                    nextBtn.setEnabled(isAnySeekBarNotAtZero);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            };
    public void onNextButtonClick(View view) {
        saveData();
        Intent intent;
        Bundle b = new Bundle();
        intent = new Intent(this, DailyDiary_Spinner.class);
        b.putInt("count", count);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void saveData(){
        long endTime = System.currentTimeMillis();
        // Calculate the elapsed time
        elapsedTime = (endTime - startTimeSBtn)/1000.0;
        String time = df.format(elapsedTime);
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME2, Context.MODE_PRIVATE).edit();
        editor.putString(TIME_KEY+count, time);
        editor.apply();
        editor.putInt(CLICK_COUNT_KEY+count, clickCount);
        editor.apply();
    }
}
