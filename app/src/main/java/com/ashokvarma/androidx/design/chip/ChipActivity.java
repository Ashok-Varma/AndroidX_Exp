package com.ashokvarma.androidx.design.chip;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.ashokvarma.androidx.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

/**
 * https://material.io/develop/android/components/chip/
 */
public class ChipActivity extends AppCompatActivity {

    Toast toast;

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
                    showMessage(chip.getChipText() + " : " + (chip.isChecked() ? "Checked" : "UnChecked"));

            }
        });
    }

    void showMessage(String message) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    private void setListeners(final Chip chip) {
        chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showMessage(chip.getChipText() + " : " + (b ? "Checked" : "UnChecked"));
            }
        });


        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage(chip.getChipText() + " : Close Button Clicked");
                chip.setVisibility(View.GONE);
            }
        });

//        chip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showMessage(chip.getChipText() + " : Clicked");
//            }
//        });
    }
}
