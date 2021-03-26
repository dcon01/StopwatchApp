package au.edu.jcu.sp3406.stopwatchapp;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Stopwatch {
    private int hours, minutes, seconds;

    Stopwatch() {hours = minutes = seconds = 0; }

    Stopwatch(String representation) {
        String[] elements = representation.split(":");
        this.hours = Integer.parseInt(elements[0]);
        this.minutes = Integer.parseInt(elements[1]);
        this.seconds = Integer.parseInt(elements[2]);
    }


    void tick(){
        ++seconds;
        if (seconds > 59){
            seconds = 0;
            ++minutes;
            if (minutes > 59) {
                minutes = 0;
                ++hours;
            }
        }
    }

    @NonNull
    @Override
    public String toString() {
        Locale locale = Locale.getDefault();
        return String.format(locale, "%02d:%02d:%02d", hours, minutes, seconds);
    }
}


