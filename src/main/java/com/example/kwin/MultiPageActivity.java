package com.example.kwin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MultiPageActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

    }
    // 멘토 옵션 선택
    public void myListener(View target){
        Intent intent = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(intent);
    }
    // 멘티 옵션 선택
    public void myListener1(View target){
        Intent intent = new Intent(getApplicationContext(),
                BmainActivity.class);
        startActivity(intent);
    }

}
