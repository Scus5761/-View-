package com.itheima.mobile.toggleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ui.ToggleView;
import ui.ToggleView.OnSwitchUpdateListener;

public class MainActivity extends AppCompatActivity {

    private ToggleView tv_toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_toggle = (ToggleView) findViewById(R.id.tv_toggle);
      /*  tv_toggle.setSwitchBackgroundResource(R.drawable.switch_background);
        tv_toggle.setSlideButtonResource(R.drawable.slide_button);*/

        tv_toggle.setOnSwitchStateUpdateListener(new OnSwitchUpdateListener() {
            @Override
            public void onStateUpdate(boolean state) {
                Toast.makeText(MainActivity.this, "state:" + state, Toast.LENGTH_SHORT).show();

            }
        });

       /* tv_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 == 0) {
                    tv_toggle.setswitchState(true);
                } else {
                    tv_toggle.setswitchState(false);
                }
                count++;
                tv_toggle.invalidate();
            }
        });*/

       /* tv_toggle.setOnTouchListener(new View.OnTouchListener() {
            float startX;
            float newX;
            float distance;


            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        Log.d("MainActivity", "----------****" + startX);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        newX = event.getX();
                        Log.d("MainActivity", "----------****" + newX);

                        break;

                    case MotionEvent.ACTION_UP:

                        distance = newX - startX;
                        Log.d("MainActivity", "----------****" + distance);
                        if (distance > 0f) {
                            tv_toggle.setswitchState(true);
                        } else {
                            tv_toggle.setswitchState(false);
                        }
                        tv_toggle.invalidate();
                        break;
                }
                return true;
            }
        });*/
    }
}
