package com.example.ramadanmoustafa.tablereservation.base;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity <V extends ViewModel> extends DaggerAppCompatActivity{

    @Inject
    protected ViewModelFactory mViewModelFactory;
    protected V mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    protected void showMessage(String message, View view) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    protected void setActionBarTitle(String title){

        getSupportActionBar().setTitle(title);
    }
}
