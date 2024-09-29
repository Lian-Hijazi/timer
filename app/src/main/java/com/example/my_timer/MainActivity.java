package com.example.my_timer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button start,stop;
    TextView tvCount;
    int second=0,minute=0;
    Thread counterThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCount=findViewById(R.id.count);

    }

    public void reset (View view)
    {
        minute=0;
        second=0;
        tvCount.setText("00:00 ");


    }

    public void startCount(View view){
        counterThread=new Thread(()->{
            try {
                while (true) {
                    if(second==59) {
                        second = 0;
                        minute++;
                    }
                    else {
                        second++;
                    }
                    if(second>9) {
                        if (minute > 9) {
                            tvCount.setText(minute + ":" + second);
                        } else
                            tvCount.setText("0" + minute + ":" + second);
                    }
                    else {
                        if (minute > 9)
                            tvCount.setText(minute + ":0" + second);

                        else
                            tvCount.setText("0" + minute + ":0" + second);
                    }

                    Thread.sleep(1000);
                }
            }
            catch(Exception e) {
            }

        });
        counterThread.start();
    }
    public void stopCount(View view){
        counterThread.interrupt();
    }

}