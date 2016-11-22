package nl.groenier.android.firstcanvas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private TextView mTextView;
    private CanvasView mCanvasView;
    private Button mButtonRedraw;

    private int shoesToDraw = 100;

    private Random random = new Random();

    public static List<CanvasObject> objectList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text_view_intro);
        mCanvasView = (CanvasView) findViewById(R.id.canvas_view);
        mButtonRedraw = (Button) findViewById(R.id.button_start_redraw);


//        objectList.add(new CanvasObject(500,200));
//        objectList.add(new CanvasObject(700,600));

        for(int i = 0; i < shoesToDraw; i++) {
            objectList.add(new CanvasObject(random.nextInt(900),random.nextInt(900),random.nextInt(360)));
        }

        mButtonRedraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objectList.clear();
                for(int i = 0; i < shoesToDraw; i++) {
                    objectList.add(new CanvasObject(random.nextInt(900),random.nextInt(900),random.nextInt(360)));
                }
                refreshCanvas();
                Toast.makeText(MainActivity.this, "Refreshed!", Toast.LENGTH_SHORT).show();
            }
        });

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(30);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                objectList.add(new CanvasObject(random.nextInt(2000),random.nextInt(750)));
//                                objectList.add(new CanvasObject(500,500,0));
                                mTextView.setText(Integer.toString(objectList.size()));
                                updateAllCanvasObjects();
                                refreshCanvas();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();

        }

        public void refreshCanvas() {
            mCanvasView.invalidate();
        }

        private void updateAllCanvasObjects(){
            for (CanvasObject item : objectList) {
                updateCanvasObject(item);
            }

//            Iterator<CanvasObject> iter = objectList.iterator();
//
//            while (iter.hasNext()) {
//                CanvasObject object = iter.next();
//
//                int x = object.getX();
//                int y = object.getY();
//
//                if(x < 2000 && y < 750) {
//                    updateCanvasObject(object);
//                }
//                else {
//                    iter.remove();
//                }
//            }

        }

        private void updateCanvasObject(CanvasObject object){

            int x = object.getX();
            int y = object.getY();
            int orientation = object.getOrientation();

//            int transX = random.nextInt(2);
//            int transY = random.nextInt(2);
            int transX = 1;
            int transY = 1;
            int transOrientation = 1;

            object.setX(object.getX() + transX);
            object.setY(object.getY() + transY);
            object.setOrientation(object.getOrientation() + transOrientation);
        }



}
