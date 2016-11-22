package nl.groenier.android.firstcanvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Martijn on 16/11/2016.
 */

public class CanvasView extends View {

    private int x = 500;
    private int y = 500;
    private Bitmap footImage = BitmapFactory.decodeResource(getResources(), R.drawable.shoe_sole_left);

    //private List<CanvasObject> objectList = new ArrayList();

    // This constructor will be used when the view is created programmatically
    public CanvasView(Context context){
        super(context);
    }

    // This constructor will be used when the view is created through the XML layout
    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int radius = 16;

        int width = getWidth();
        int height = getHeight();

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors
        paint.setColor(Color.parseColor("#CD5C5C"));

//        Random r = new Random();
//
//        canvas.save();
//        canvas.rotate(r.nextInt(360));
//        canvas.drawRect(50, 60, 80, 100, paint);
//        canvas.restore();

        for (CanvasObject item : MainActivity.objectList) {
//            canvas.drawCircle(item.getX(), item.getY(), radius, paint);

            Matrix matrix = new Matrix();

            // rotate around (0,0)
            //rotator.postRotate(90);

            // or, rotate around x,y
            // NOTE: coords in bitmap-space!
            matrix.postRotate(item.getOrientation(), footImage.getWidth()/2, footImage.getHeight()/2);

            int xTranslate = 200;
            int yTranslate = 200;
            matrix.postTranslate(item.getX(), item.getY());

            canvas.drawBitmap(footImage,matrix,paint);
        }

    }



}