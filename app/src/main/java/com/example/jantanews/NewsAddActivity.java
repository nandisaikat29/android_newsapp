package com.example.jantanews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NewsAddActivity extends AppCompatActivity {
    EditText news_add_editText,news_heading_add_editText;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_add);

        news_add_editText = findViewById(R.id.news_add_editText);
        news_heading_add_editText = findViewById(R.id.news_heading_add_editText);
    }

    public void submitMethod(View view) {
        Map<String,Object> data =new HashMap<>();
        if (news_add_editText.getText().toString().isEmpty()
                && news_add_editText.getText().toString().isEmpty()){
            Toast.makeText(this, "please write something", Toast.LENGTH_SHORT).show();
        }else {
            data.put("heading_text",news_heading_add_editText.getText().toString());
            data.put("news_text",news_add_editText.getText().toString());

        }

        db.collection("news_list").document().set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(NewsAddActivity.this, "data added", Toast.LENGTH_SHORT).show();
                Log.d("#success","data added");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("#fail",e.getMessage().toString());
            }
        });

    }
}