/*TODO :
    1/ Create The Quest Array : list<quest>  : Done
    2/ add current quest position for the user : Done
    3/ display current position data for the user : Done
    4/ Listen to user Action on The Data : Done
    5/ Verify user Choice : Done
    6/ Show the User Choice result and the Next Button : Done
    7/ Increment the User Score : Done
    8/ Increment the Quest Position : Done
    9/ Repeat until Quest End : Done
    10/ Display the user Score And a message => another Activity ;

*/
package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class QuizzActivity extends AppCompatActivity {

    CountDownTimer C_Timer ;
    ProgressBar progressBar ;
    TextView quest;
    Button Btn_1 ;
    Button Btn_2 ;
    Button Btn_3 ;
    Button Next_btn ;
    TextView score;
    TextView nbreQuest;


    private QuestModel[] questList = new QuestModel[3] ;
    private  int _CurrentQuest  ;
    private int _score = 0  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        Btn_1  = findViewById(R.id.Btn_1);
        Btn_2  = findViewById(R.id.Btn_2);
        Btn_3  = findViewById(R.id.Btn_3);
        Next_btn = findViewById(R.id.next);
        quest = findViewById(R.id.textView) ;
        score = findViewById(R.id.score) ;
        nbreQuest = findViewById(R.id.nbreQuest) ;
        progressBar = findViewById(R.id.progressBar) ;

        _CurrentQuest = 0 ;
        score.setText("score:"+_score);

        C_Timer = new CountDownTimer(10000 , 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int progress = (int) (millisUntilFinished/1000);
                progressBar.setProgress(progressBar.getMax()-progress);
            }

            @Override
            public void onFinish() {
                setBtnDsable();
                alertDialog("No Time Left","Be Faster Next Time");
            }
        };

        initQuest() ;
        displayContent(_CurrentQuest) ;

        Btn_1.setOnClickListener(new View.OnClickListener() { // button click listener
            @Override
            public void onClick(View v) { // onclick event
                String selectedOpt = (String) Btn_1.getText();
                verifyUserRes(Btn_1,selectedOpt) ;
            }
        });

        Btn_2.setOnClickListener(new View.OnClickListener() { // button click listener
            @Override
            public void onClick(View v) { // onclick event
                String selectedOpt = (String) Btn_2.getText();
                verifyUserRes(Btn_2,selectedOpt);
            }
        });

        Btn_3.setOnClickListener(new View.OnClickListener() { // button click listener
            @Override
            public void onClick(View v) { // onclick event
                String selectedOpt = (String) Btn_3.getText();
                verifyUserRes(Btn_3,selectedOpt);
            }
        });

        Next_btn.setOnClickListener(new View.OnClickListener() { // button click listener
            @Override
            public void onClick(View v) { // onclick event
                nextAction();
            }
        });

    }

    public void initQuest(){
        questList[0] = new QuestModel(
                "Inside which HTML element do we put the JavaScript?",
                "<script>>","<head>","<link>","<script>>") ;
        questList[1] = new QuestModel(
                "which HTML element do we Use For Tables ?",
                "<li>","<ul>","<table>","<table>") ;
        questList[2] = new QuestModel(
                "we can use id with ?",
                "Any element in the document","only one element","we can't use it","only one element") ;

    }

    public void displayContent(int pos){
        setBtnEnable();
        C_Timer.start() ;
        int val = pos + 1 ;
        nbreQuest.setText(val +" / "+ questList.length);
        quest.setText(questList[pos].getQuestion());
        Btn_1.setText(questList[pos].getOpt1());
        Btn_2.setText(questList[pos].getOpt2());
        Btn_3.setText(questList[pos].getOpt3());
    }

    public void verifyUserRes(Button btn, String selectedOpt){
        setBtnDsable();
        C_Timer.cancel(); //doesn't work
        if(selectedOpt == questList[_CurrentQuest].getCurrectAns()){
            btn.setBackgroundColor(getResources().getColor(R.color.green));
            _score+=10 ;
            score.setText("score:"+_score);
        }else{
            btn.setBackgroundColor(getResources().getColor(R.color.red));
            alertDialog("Wrong Answer","The Correct Answer Is \n \t"+ questList[_CurrentQuest].getCurrectAns());
        }
    }

    public void nextAction(){
        _CurrentQuest++ ;

        if(_CurrentQuest == questList.length){
            Intent i = new Intent(getApplicationContext(),Congrats_Page.class);
            i.putExtra("score" , Integer.toString(_score) ) ;
            i.putExtra("name",getIntent().getStringExtra("name"));
            startActivity(i);

        }else{
            displayContent(_CurrentQuest) ;
        }

    }

    private void setBtnDsable(){
        Btn_1.setEnabled(false);
        Btn_2.setEnabled(false);
        Btn_3.setEnabled(false);
        Next_btn.setEnabled(true);
    }

    private void setBtnEnable(){
        Btn_1.setEnabled(true);
        Btn_2.setEnabled(true);
        Btn_3.setEnabled(true);
        Next_btn.setEnabled(false);

        Btn_1.setBackgroundColor(getResources().getColor(R.color.purple_200));
        Btn_2.setBackgroundColor(getResources().getColor(R.color.purple_200));
        Btn_3.setBackgroundColor(getResources().getColor(R.color.purple_200));
    }

    private void alertDialog(String title,String text) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this); // dialog instance
        dialog.setMessage(text); // message merged with user input
        dialog.setTitle(title) ;
        dialog.setPositiveButton("Oups !",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alertDialog=dialog.create(); // create the dialog
        alertDialog.show(); //show the dialog
    }
}