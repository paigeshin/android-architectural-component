package com.example.myapplication.practice2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class Practice2Activity extends AppCompatActivity {

    private Practice2ViewModel practice2ViewModel;
    private MutableLiveData<Integer> count;

    private TextView tvCount;
    private Button btnCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice2);
        init();
        initializeViewModel();
        liveDataObserver();
        buttonListener();
    }

    private void init(){
        tvCount = findViewById(R.id.tvCount_from_activity_practice2);
        btnCount = findViewById(R.id.btnCount_from_activity_practice2);
    }

    private void initializeViewModel(){
        practice2ViewModel = ViewModelProviders.of(Practice2Activity.this).get(Practice2ViewModel.class);
        count = practice2ViewModel.initialize();
    }

    private void liveDataObserver(){
        count.observe(Practice2Activity.this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                String text = "count: " + integer;
                tvCount.setText(text);
            }
        });
    }

    private void buttonListener(){
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                practice2ViewModel.increment();
            }
        });
    }

}
