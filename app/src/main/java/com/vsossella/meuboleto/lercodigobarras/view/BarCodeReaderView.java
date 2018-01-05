package com.vsossella.meuboleto.lercodigobarras.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.widget.FrameLayout;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * Created by vsossella on 01/04/17.
 */

public class BarCodeReaderView extends ViewFinderView {

    public BarCodeReaderView(Context context, Display display){
        super(context);
        setLaserColor(Color.RED);
        setRectangleSize(display);
    }

    private void setRectangleSize(Display display) {
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        setLayoutParams(new FrameLayout.LayoutParams(width, height));
    }

    @Override
    public synchronized void updateFramingRect() {
        super.updateFramingRect();
    }

    @Override
    public void onDraw(Canvas canvas) {
        drawLaser(canvas);
        drawTradeMark(canvas);
    }
    private void drawTradeMark(Canvas canvas) {
    }
}