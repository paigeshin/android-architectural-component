package com.example.myapplication.practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class PracticeActivity extends AppCompatActivity {

    private PracticeViewModel practiceViewModel;
    private LiveData<Integer> mutableCount;
    private TextView tvCount;
    private Button btnCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        init();
        viewModelHandler();
        countObserver();
        countListener();
    }

    //view binding
    private void init(){
        tvCount = findViewById(R.id.tvCount_from_activity_practice);
        btnCount = findViewById(R.id.btnCount_from_activity_practice);
    }

    private void viewModelHandler(){
        practiceViewModel = ViewModelProviders.of(PracticeActivity.this).get(PracticeViewModel.class);
        mutableCount = practiceViewModel.initialize();
    }

    private void countObserver(){
        mutableCount.observe(PracticeActivity.this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                String text = "Count: " + integer;
                tvCount.setText(text);
            }
        });
    }

    private void countListener(){
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                practiceViewModel.increment();
            }
        });
    }

}
