package com.ashokvarma.androidx.slice;

import android.os.Bundle;

import com.ashokvarma.androidx.R;

import androidx.appcompat.app.AppCompatActivity;

// install https://github.com/googlesamples/android-SliceViewer/releases
public class SliceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slice_act);
    }
}
