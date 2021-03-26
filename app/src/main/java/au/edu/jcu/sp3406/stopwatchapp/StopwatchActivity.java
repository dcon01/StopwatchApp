package au.edu.jcu.sp3406.stopwatchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {
    private Stopwatch stopwatch;
    private Handler handler;
    private boolean isRunning;
    private TextView display;
    private Button toggle;
    private int speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        toggle = findViewById(R.id.toggle);

        speed = 1000;

        isRunning = false;
        if (savedInstanceState == null) {
            stopwatch = new Stopwatch();
        } else {
            stopwatch = new Stopwatch(savedInstanceState.getString("value"));
            boolean running = savedInstanceState.getBoolean("running");
            speed = savedInstanceState.getInt("speed");
            if (running) {
                enableStopwatch();
            }
        }

        updateToggleLabel();
    }

    private void enableStopwatch() {
        isRunning = true;
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    stopwatch.tick();
                    display.setText(stopwatch.toString());
                    handler.postDelayed(this, speed);
                }
            }
        });
    }
    private void disableStopwatch() {
        isRunning = false;
    }

    public void settingsClicked(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    private void updateToggleLabel(){
        toggle.setText(isRunning ? "stop": "start");
    }

    public void buttonClicked(View view) {
        if (!isRunning) {
            enableStopwatch();
        } else {
            disableStopwatch();
        }

        updateToggleLabel();
        disableStopwatch();
    }


}