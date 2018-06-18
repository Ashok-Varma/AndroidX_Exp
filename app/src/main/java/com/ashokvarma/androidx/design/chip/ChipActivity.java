package com.ashokvarma.androidx.design.chip;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.ashokvarma.androidx.R;

/**
 * https://material.io/develop/android/components/chip/
 */
public class ChipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chip_act);

        Chip chip1 = findViewById(R.id.chip_1);
        Chip chip2 = findViewById(R.id.chip_2);
        Chip chip3 = findViewById(R.id.chip_3);
        Chip chip4 = findViewById(R.id.chip_4);
        Chip chip5 = findViewById(R.id.chip_5);
        Chip chip6 = findViewById(R.id.chip_6);

        setListeners(chip1);
        setListeners(chip2);
        setListeners(chip3);
        setListeners(chip4);
        setListeners(chip5);
        setListeners(chip6);

        ChipGroup chipGroup = findViewById(R.id.single_select_chip);
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, @IdRes int checkedId) {
                // Handle the checked chip change.
                Chip chip = group.findViewById(checkedId);
                if (chip != null)
                    Toast.makeText(ChipActivity.this, chip.getChipText() + " : " + (chip.isChecked() ? "Checked" : "UnChecked"), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setListeners(final Chip chip) {
        chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(ChipActivity.this, chip.getChipText() + " : " + (b ? "Checked" : "UnChecked"), Toast.LENGTH_SHORT).show();
            }
        });


        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChipActivity.this, chip.getChipText() + " : Close Clicked", Toast.LENGTH_SHORT).show();
                chip.setVisibility(View.GONE);
            }
        });

        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChipActivity.this, chip.getChipText() + " : Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
