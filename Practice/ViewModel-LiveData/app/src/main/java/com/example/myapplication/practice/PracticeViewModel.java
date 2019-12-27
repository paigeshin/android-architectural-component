package com.example.myapplication.practice;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PracticeViewModel extends ViewModel {

    private int count = 0;
    private MutableLiveData<Integer> mutableCount;

    public MutableLiveData<Integer> initialize(){
        mutableCount.setValue(count);
        return mutableCount;
    }

    public void increment(){
        count++;
        mutableCount.setValue(count);
    }

}
