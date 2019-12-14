package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //🔴 Create the instance of viewModel
    MainActivityViewModel mainActivityViewModel;

    private TextView tvCount;
    private Button btnCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        /*
        1. 어떤 activity의 view model인지 정의. of()
        2. 실제로 ViewModel을 가져옴. get()
         */
        mainActivityViewModel = ViewModelProviders.of(MainActivity.this).get(MainActivityViewModel.class);
        LiveData<Integer> count = mainActivityViewModel.getInitialCount();
        count.observe(MainActivity.this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                String text = "count: " + integer;
                tvCount.setText(text);
            }
        });

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityViewModel.getCurrentCount();
            }
        });

    }

    private void init(){
        tvCount = findViewById(R.id.tvScore);
        btnCount = findViewById(R.id.btnCount);
    }

}
