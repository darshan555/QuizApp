package com.example.quiz_app;

import static com.example.quiz_app.MainActivity.listOfQ;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Collections;
import java.util.List;

public class dashboardActivity extends AppCompatActivity {

    private ProgressBar quizTimer;
    List<Modelclass> allQuesList;
    Modelclass modelclass;
    Button nextButton;
    int index = 0;
    TextView question,optiona,optionb,optionc,optiond;
    CardView carda,cardb,cardc,cardd;
    int correctCount = 0;
    int wrongCount = 0;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Hooks();

        allQuesList = listOfQ;
        Collections.shuffle(allQuesList);
        modelclass = listOfQ.get(index);

        carda.setBackgroundColor(getResources().getColor(R.color.white));
        cardb.setBackgroundColor(getResources().getColor(R.color.white));
        cardc.setBackgroundColor(getResources().getColor(R.color.white));
        cardd.setBackgroundColor(getResources().getColor(R.color.white));

        nextButton.setClickable(false);
        // Set up CountDownTimer
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int progress = (int) millisUntilFinished;
                quizTimer.setProgress(progress);
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(dashboardActivity.this);
//                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.timeout_dialog);

                dialog.findViewById(R.id.tryAgainBTN).setOnClickListener(v -> {
                    Intent intent = new Intent(dashboardActivity.this,MainActivity.class);
                    startActivity(intent);
                });
                dialog.show();
            }
        }.start();
        setAllData();
    }

    private void setAllData() {
        question.setText(modelclass.getQuestion());
        optiona.setText(modelclass.getoA());
        optionb.setText(modelclass.getoB());
        optionc.setText(modelclass.getoC());
        optiond.setText(modelclass.getoD());
        quizTimer.setMax(20000); // 20 seconds in milliseconds
        quizTimer.setProgress(20000); // Start from maximum value
        countDownTimer.cancel();
        countDownTimer.start();
        enableButton();
    }

    private void Hooks() {

        quizTimer = findViewById(R.id.quizTimer);
        question = findViewById(R.id.questionTV);
        optiona = findViewById(R.id.optionaTV);
        optionb = findViewById(R.id.optionbTV);
        optionc = findViewById(R.id.optioncTV);
        optiond = findViewById(R.id.optiondTV);

        carda = findViewById(R.id.optionaCV);
        cardb = findViewById(R.id.optionbCV);
        cardc = findViewById(R.id.optioncCV);
        cardd = findViewById(R.id.optiondCV);
        nextButton = findViewById(R.id.nextBTN);
    }

    public void Correct(CardView cardView){
        cardView.setBackgroundColor(getResources().getColor(R.color.green));

        nextButton.setOnClickListener(v -> {
            correctCount++;
            index++;
            modelclass= listOfQ.get(index);
            resetColor();
            setAllData();
        });

    }

    public void Wrong(CardView cardView){
        cardView.setBackgroundColor(getResources().getColor(R.color.red));
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongCount++;
                if (index<listOfQ.size()-1){
                    index++;
                    modelclass = allQuesList.get(index);
                    setAllData();
                    resetColor();
                }else {
                    GameWon();
                }
            }
        });

    }

    private void GameWon() {
        Intent intent = new Intent(dashboardActivity.this,WonActivity.class);
        intent.putExtra("correct",correctCount);
        intent.putExtra("wrong",wrongCount);
        startActivity(intent);
    }

    public void enableButton(){
        carda.setClickable(true);
        cardb.setClickable(true);
        cardc.setClickable(true);
        cardd.setClickable(true);
    }

    public void disableButton(){
        carda.setClickable(false);
        cardb.setClickable(false);
        cardc.setClickable(false);
        cardd.setClickable(false);
    }
    public void resetColor(){
        carda.setBackgroundColor(getResources().getColor(R.color.white));
        cardb.setBackgroundColor(getResources().getColor(R.color.white));
        cardc.setBackgroundColor(getResources().getColor(R.color.white));
        cardd.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void cardAclick(View view) {
        disableButton();
        nextButton.setClickable(true);
        if (modelclass.getoA().equals(modelclass.getAns())) {
            carda.setCardBackgroundColor(getResources().getColor(R.color.green));
            Correct(carda);
        } else {
            Wrong(carda);
        }
    }

    public void cardBclick(View view) {
        disableButton();
        nextButton.setClickable(true);
        if (modelclass.getoB().equals(modelclass.getAns())) {
            cardb.setCardBackgroundColor(getResources().getColor(R.color.green));
            Correct(cardb);
        } else {
            Wrong(cardb);
        }
    }

    public void cardCclick(View view) {
        disableButton();
        nextButton.setClickable(true);
        if (modelclass.getoC().equals(modelclass.getAns())) {
            cardc.setCardBackgroundColor(getResources().getColor(R.color.green));
            Correct(cardc);
        } else {
            Wrong(cardc);
        }
    }

    public void cardDclick(View view) {
        disableButton();
        nextButton.setClickable(true);
        if (modelclass.getoD().equals(modelclass.getAns())) {
            cardd.setCardBackgroundColor(getResources().getColor(R.color.green));
            Correct(cardd);
        } else {
            Wrong(cardd);
        }
    }

}
