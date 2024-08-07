package com.example.besafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GetStartedPage extends AppCompatActivity {

    private Handler handler;
    private Runnable imageChangeRunnable;
    private int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_page);

        Button introGetStartedButton=findViewById(R.id.introGetStartedButton);

        ImageView introGirlImage = findViewById(R.id.introGirlImage);
        TextView introText=findViewById(R.id.introText);
        ImageView introSlideCircle1 = findViewById(R.id.introSlideCircle1);
        ImageView introSlideCircle2  = findViewById(R.id.introSlideCircle2);
        ImageView introSlideCircle3  = findViewById(R.id.introSlideCircle3);
        ImageView introSlideCircle4  = findViewById(R.id.introSlideCircle4);

        handler=new Handler();

        imageChangeRunnable=new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(GetStartedPage.this, Integer.toString(count), Toast.LENGTH_SHORT).show();

                if(count%4==0)
                {
                    introGirlImage.setImageResource(R.drawable.girl1);
                    introSlideCircle1.setImageResource(R.drawable.circle);
                    introSlideCircle4.setImageResource(R.drawable.loading_circle);
                    introText.setText("Call for help anytime\nwith customisable SOS number");

                }
                else if(count%4==1)
                {
                    introGirlImage.setImageResource(R.drawable.girl2);
                    introSlideCircle2.setImageResource(R.drawable.circle);
                    introSlideCircle1.setImageResource(R.drawable.loading_circle);
                    introText.setText("Send your location \nto a custom contact list");
                }
                else if(count%4==2)
                {
                    introGirlImage.setImageResource(R.drawable.girl3);
                    introSlideCircle3.setImageResource(R.drawable.circle);
                    introSlideCircle2.setImageResource(R.drawable.loading_circle);
                    introText.setText("Feeling unsafe?\nFind safe places near you instantly");
                }
                else if(count%4==3)
                {
                    introGirlImage.setImageResource(R.drawable.girl4);
                    introSlideCircle4.setImageResource(R.drawable.circle);
                    introSlideCircle3.setImageResource(R.drawable.loading_circle);
                    introText.setText("Access to the \nlocal helpline number");
                }

                handler.postDelayed(this,3000);
                count++;

            }
        };

        handler.postDelayed(imageChangeRunnable,0);


        introGetStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetStartedPage.this, HomePage.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(imageChangeRunnable);
        super.onDestroy();
    }
}