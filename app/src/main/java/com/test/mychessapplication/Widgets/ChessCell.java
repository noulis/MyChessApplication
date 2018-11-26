package com.test.mychessapplication.Widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.test.mychessapplication.R;

public class ChessCell extends View {

    public ChessCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ChessCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray customAttributes = context.obtainStyledAttributes(attrs, R.styleable.ChessCell, 0, 0);

        if (customAttributes == null) {
            return;
        }

        boolean hasDarkBackground = customAttributes.getBoolean(R.styleable.ChessCell_hasDarkBackground, true);

        if (hasDarkBackground) {
            this.setBackgroundColor(getResources().getColor(R.color.chessDark, context.getTheme()));
        }
        else {
            this.setBackgroundColor(getResources().getColor(R.color.chessLight, context.getTheme()));
        }

        customAttributes.recycle();
    }

}
