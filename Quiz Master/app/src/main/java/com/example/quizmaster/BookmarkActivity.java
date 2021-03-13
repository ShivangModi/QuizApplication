package com.example.quizmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.quizmaster.QuestionsActivity.FILE_NAME;
import static com.example.quizmaster.QuestionsActivity.KEY_NAME;

public class BookmarkActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<QuestionModel> bookmarksList;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        recyclerView = findViewById(R.id.rv_bookmark);

        preferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        gson = new Gson();

        getBookmarks();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

       /* List<QuestionModel> list =  new ArrayList<>();
        list.add(new QuestionModel(1,"Who is Founder of Tesla?","","","","","Elon Musk"));
        list.add(new QuestionModel(1,"Who is Founder of Tesla?","","","","","Elon Musk"));
        list.add(new QuestionModel(1,"Who is Founder of Tesla?","","","","","Elon Musk"));
        list.add(new QuestionModel(1,"Who is Founder of Tesla?","","","","","Elon Musk"));
        list.add(new QuestionModel(1,"Who is Founder of Tesla?","","","","","Elon Musk"));
        list.add(new QuestionModel(1,"Who is Founder of Tesla?","","","","","Elon Musk"));
*/
        BookmarksAdapter adapter = new BookmarksAdapter(bookmarksList);
        recyclerView.setAdapter(adapter);
    }

    protected void onPause() {
        super.onPause();
        storeBookmarks();
    }

    private void getBookmarks(){
        String json = preferences.getString(KEY_NAME,"");

        Type type = new TypeToken<List<QuestionModel>>(){}.getType();

        bookmarksList = gson.fromJson(json,type);

        if (bookmarksList == null){
            bookmarksList = new ArrayList<>();
        }
    }

    private void storeBookmarks(){
        String json = gson.toJson(bookmarksList);
        editor.putString(KEY_NAME,json);
        editor.commit();

    }

}