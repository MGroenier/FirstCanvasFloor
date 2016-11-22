package nl.groenier.android.firstcanvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Martijn on 16/11/2016.
 */

public class CanvasSurfaceView extends SurfaceView {

    private SurfaceHolder holder;
    private Bitmap bmp;

    public CanvasSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) { }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas canvas = holder.lockCanvas();
                if (canvas != null) {
                    draw(canvas);
                    holder.unlockCanvasAndPost(canvas);
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

        });
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(this.bmp, 25, 25, null);
    }

}