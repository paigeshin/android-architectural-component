package com.example.myapplication.practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class PracticeActivity extends AppCompatActivity {

    private TextView tvCount;
    private Button btnCount;

    private PracticeViewModel practiceViewModel;
    private MutableLiveData<Integer> mutableCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        init();
        initializeViewModel();
        eventListener();
    }

    private void init(){
        tvCount = findViewById(R.id.tvCount_from_practice);
        btnCount = findViewById(R.id.btnCount_from_practice);
    }

    private void initializeViewModel(){
        practiceViewModel = ViewModelProviders.of(PracticeActivity.this).get(PracticeViewModel.class);
        mutableCount = practiceViewModel.initialize();
        mutableCount.observe(PracticeActivity.this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                String text = "count: " + integer;
                tvCount.setText(text);
            }
        });
    }

    private void eventListener(){
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                practiceViewModel.increment();
            }
        });
    }

}
