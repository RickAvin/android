package com.example.testandroid.Basicos;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getSupportActionBar().hide();

        }catch (Exception e){e.printStackTrace();}
        setContentView(getLayout());
    }
    @LayoutRes
    public abstract int getLayout();
}
