package com.ashokvarma.androidx.support;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Class description
 *
 * @author ashok
 * @version 1.0
 * @since 17/06/18
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int halfSpace;
    private boolean padVertical = true;
    private boolean padHorizontal = true;
    private boolean recyclerPaddingSet = false;

    public SpacesItemDecoration(int space) {
        this.halfSpace = space / 2;
    }

    public SpacesItemDecoration(@NonNull Context context, @DimenRes int spaceResId) {
        this(context.getResources().getDimensionPixelSize(spaceResId));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (!recyclerPaddingSet) {
            parent.setPadding(
                    padVertical ? (halfSpace + parent.getPaddingLeft()) : parent.getPaddingLeft(),
                    padHorizontal ? halfSpace + parent.getPaddingTop() : parent.getPaddingTop(),
                    padVertical ? halfSpace + parent.getPaddingRight() : parent.getPaddingRight(),
                    padHorizontal ? halfSpace + parent.getPaddingBottom() : parent.getPaddingBottom());
            parent.setClipToPadding(false);
            recyclerPaddingSet = true;
        }

        outRect.set(
                padVertical ? halfSpace : 0,
                padHorizontal ? halfSpace : 0,
                padVertical ? halfSpace : 0,
                padHorizontal ? halfSpace : 0);
    }

    public SpacesItemDecoration setPadHorizontal(boolean padHorizontal) {
        this.padHorizontal = padHorizontal;
        return this;
    }

    public SpacesItemDecoration setPadVertical(boolean padVertical) {
        this.padVertical = padVertical;
        return this;
    }
}