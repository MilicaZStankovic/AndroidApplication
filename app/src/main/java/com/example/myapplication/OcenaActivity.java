package com.example.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.myapplication.R.*;

public   class OcenaActivity extends AppCompatActivity  {
    TextView rateCount,showRating;
    EditText review;
    Button submit;
    RatingBar ratingBar;
    float rateValue;String temp;


    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_ocena);
        submit = findViewById(id.button);
        final MediaPlayer media = MediaPlayer.create(this, R.raw.music);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                media.start();
            }
        });

        rateCount = findViewById(id.utisak);
        ratingBar = findViewById(id.rateCount);
        review = findViewById(id.editTextTextPersonName);

        showRating = findViewById(id.id);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {


            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateValue = ratingBar.getRating();
                if(rateValue <= 1 && rateValue > 0)
                    rateCount.setText("Bad" + rateValue + "/5");
                else if (rateValue <= 2 && rateValue >1)
                    rateCount.setText("Ok" + rateValue + "/5");
                else if (rateValue <= 3 && rateValue >2)
                    rateCount.setText("Good" + rateValue + "/5");
                else if (rateValue <= 4 && rateValue >3)
                    rateCount.setText("Very Good" + rateValue + "/5");
                else if (rateValue <= 5&& rateValue >4)
                    rateCount.setText("Best-Thank you so much" + rateValue + "/5");


            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                temp = rateCount.getText().toString();
                showRating.setText("Your Rating: \n" + temp +"\n" + review.getText());
                review.setText("");
                ratingBar.setRating(0);
                rateCount.setText("");

            }
        });


    }


}
