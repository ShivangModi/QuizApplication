package com.example.quizmaster;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {

    private TextView question,noIndicator;
    private FloatingActionButton bookmarkBtn;
    private LinearLayout optionsContainer;
    private Button shareBtn,nextBtn;
    private int count = 0;
    private List<QuestionModel> list;
    private int position = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        question = findViewById(R.id.quastions);
        noIndicator = findViewById(R.id.no_indicator);
        bookmarkBtn = findViewById(R.id.bookmark_btn);
        optionsContainer = findViewById(R.id.options_container);
        shareBtn = findViewById(R.id.share_btn);
        nextBtn = findViewById(R.id.next_btn);

        list = new ArrayList<>();
        list.add(new QuestionModel("Question 1", "A", "B", "C", "D", "A"));
        list.add(new QuestionModel("Question 2", "A", "B", "C", "D", "B"));
        list.add(new QuestionModel("Question 3", "A", "B", "C", "D", "C"));
        list.add(new QuestionModel("Question 4", "A", "B", "C", "D", "D"));
        list.add(new QuestionModel("Question 5", "A", "B", "C", "D", "D"));
        list.add(new QuestionModel("Question 6", "A", "B", "C", "D", "B"));
        list.add(new QuestionModel("Question 7", "A", "B", "C", "D", "C"));
        list.add(new QuestionModel("Question 8", "A", "B", "C", "D", "A"));
        list.add(new QuestionModel("Question 9", "A", "B", "C", "D", "C"));
        list.add(new QuestionModel("Question 10", "A", "B", "C", "D", "A"));

        for (int i=0; i<4;i++){
            optionsContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer((Button)v);
                }
            });
        }

        playAnim(question,0,list.get(position).getQuestion());
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextBtn.setEnabled(false);
                nextBtn.setAlpha(0.7f);
                position++;
                if (position == list.size()){
                    //score activity
                    return;
                }
                count = 0;
                playAnim(optionsContainer,0,optionsContainer.toString());
                playAnim(question,0, list.get(position).getQuestion());
            }
        });

    }

    private void playAnim(final View view,final int value,final String data){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (value == 0 && count < 4) {
                    String option = "";
                    if (count == 0){
                        option = list.get(position).getOptionA();
                    }
                    else if (count == 1){
                        option = list.get(position).getOptionB();
                    }
                    else if (count == 2){
                        option = list.get(position).getOptionC();
                    }
                    else if (count == 3){
                        option = list.get(position).getOptionD();
                    }

                    playAnim(optionsContainer.getChildAt(count),1,option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (value == 0){
                    try {
                        ((TextView)view).setText(data);
                    }catch (ClassCastException ex){
                        ((Button)view).setText(data);
                    }
                    view.setTag(data);
                    playAnim(view,1,data);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

   // @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(Button selectedOptions){
        enableOption(false);
        nextBtn.setEnabled(true);
        nextBtn.setAlpha(1);
        if (selectedOptions.getText().toString().equals(list.get(position).getCorrectAnswer())){
            ///correct
            score++;
            Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                selectedOptions.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CFF4C")));
            }*/
        }else{
            ///incorrect
            Toast.makeText(getApplicationContext(),"Incorrect Answer",Toast.LENGTH_SHORT).show();
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                selectedOptions.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF1A1A")));
                Button correctoption = (Button) optionsContainer.findViewWithTag(list.get(position).getCorrectAnswer());
                correctoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CFF4C")));
            }*/
        }
    }

    public void enableOption(boolean enable){
        for (int i=0;i<4;i++){
            optionsContainer.getChildAt(i).setEnabled(enable);
        }
    }
}