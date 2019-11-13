package com.example.vinaykatariya.numbergame;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Dialog myDialog1,myDialog2;
    private int randomNumber = (int) (Math.random() * 100) + 1;
    private int guess;
    private TextView result;
    private boolean hasWon = false;
    private EditText text;
    private Button button;
    private static int i=10,count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDialog1 = new Dialog(this);
        myDialog2 = new Dialog(this);
        text = (EditText) findViewById(R.id.e);
        button = (Button) findViewById(R.id.b1);
        result = (TextView) findViewById(R.id.t1);

        button.setEnabled(false);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String input = text.getText().toString().trim();
                button.setEnabled(!input.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                count++;
                guess = Integer.parseInt(text.getText().toString());

                if(guess>randomNumber&&i>1)
                {
                    result.setText("It is smaller than "+ String.valueOf(guess));
                    i--;
                    Toast.makeText(MainActivity.this,i+" Guesses left",Toast.LENGTH_SHORT).show();
                }
                else if(guess<randomNumber&&i>1)
                {
                    result.setText("It is greater than "+ String.valueOf(guess));
                    i--;
                    Toast.makeText(MainActivity.this,i+" Guesses left",Toast.LENGTH_SHORT).show();

                }
                else if(guess==randomNumber&&i>=1)
                {
                   hasWon = true;

                    myDialog1.setContentView(R.layout.wiin);
                    TextView textClose,win,retry;
                    textClose = (TextView) myDialog1.findViewById(R.id.close);
                    win = (TextView) myDialog1.findViewById(R.id.wintext);
                    retry = (TextView) myDialog1.findViewById(R.id.retry);
                    win.setText("Hurray You Won!! Total Guesses: "+count);
                    textClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            result.setText("you have 10 Guesses");
                            i=10;
                            count=0;
                            randomNumber = (int) (Math.random() * 100) + 1;
                            text.getText().clear();
                            myDialog1.dismiss();
                            finish();
                            moveTaskToBack(true);
                        }
                    });

                    retry.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            result.setText("you have 10 Guesses");
                            i=10;
                            count=0;
                            randomNumber = (int) (Math.random() * 100) + 1;
                            text.getText().clear();
                            button.setEnabled(false);
                            myDialog1.dismiss();
                        }
                    });
                        myDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        myDialog1.show();

                }
                else
                {
                    button.setEnabled(false);
                    result.setText("You lose !! The number was: "+ String.valueOf(randomNumber));
                    myDialog2.setContentView(R.layout.tryagain);
                    TextView lose,retry,exit;
                    lose = (TextView) myDialog2.findViewById(R.id.lose);
                    retry = (TextView) myDialog2.findViewById(R.id.retry);
                    exit = (TextView) myDialog2.findViewById(R.id.exit);

                    lose.setText("You lose !! The number was: "+ String.valueOf(randomNumber));
                    myDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog2.show();
                    retry.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            result.setText("you have 10 Guesses");
                            i=10;
                            count=0;
                            randomNumber = (int) (Math.random() * 100) + 1;
                            text.getText().clear();
                            button.setEnabled(true);
                            button.setEnabled(false);
                            myDialog2.dismiss();
                        }
                    });

                    exit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            result.setText("you have 10 Guesses");
                            i=10;
                            count=0;
                            randomNumber = (int) (Math.random() * 100) + 1;
                            text.getText().clear();
                            button.setEnabled(true);
                            myDialog2.dismiss();
                            finish();
                            moveTaskToBack(true);
                        }
                    });

                }
            }
        });

    }

}
