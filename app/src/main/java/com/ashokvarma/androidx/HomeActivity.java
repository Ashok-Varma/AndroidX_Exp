package com.ashokvarma.androidx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ashokvarma.androidx.design.chip.ChipActivity;
import com.ashokvarma.androidx.recyclerview.selection.RecyclerSelectionActivity;
import com.ashokvarma.androidx.slice.SliceActivity;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_act);

        findViewById(R.id.home_button_recycler_view_selection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, RecyclerSelectionActivity.class));
            }
        });

        findViewById(R.id.home_button_design_chips).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ChipActivity.class));
            }
        });

        findViewById(R.id.home_button_silce).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SliceActivity.class));
            }
        });

        findViewById(R.id.home_button_crash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer.parseInt("Thanos");
            }
        });
    }
}
