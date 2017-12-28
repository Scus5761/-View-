package ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ToggleView extends View {
    private Bitmap switch_background;
    private Bitmap slide_button;
    private Paint paint;
    private boolean mSwitchState = false;
    private float currentX;
    private OnSwitchUpdateListener onSwitchUpdateListener;

    //用于代码创建
    public ToggleView(Context context) {
        super(context);
        init();
    }

    //在xml中使用，可自定义属性
    public ToggleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        String namespace = "http://schemas.android.com/apk/res-auto";
        int switch_background = attrs.getAttributeResourceValue(namespace, "switch_background", -1);
        setSwitchBackgroundResource(switch_background);

        int slide_button = attrs.getAttributeResourceValue(namespace, "slide_button", -1);
        setSlideButtonResource(slide_button);
        attrs.getAttributeBooleanValue(namespace, "switch_state", false);
        init();
    }
    //在xml中使用，可自定义属性，如果自定义样式则执行该构造方法
    public ToggleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        paint = new Paint();

    }


    public void setSwitchBackgroundResource(int background) {
        switch_background = BitmapFactory.decodeResource(getResources(), background);

    }

    public void setSlideButtonResource(int button) {
        slide_button = BitmapFactory.decodeResource(getResources(), button);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(switch_background.getWidth(), switch_background.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(switch_background, 0, 0, paint);

        if (isTouchMode) {
            Float newLeft = currentX - slide_button.getWidth() / 2.0f;
            if (newLeft < 0) {
                newLeft = 0f;
            } else if (newLeft > switch_background.getWidth() - slide_button.getWidth()) {
                newLeft = Float.valueOf(switch_background.getWidth() - slide_button.getWidth());
            }
            canvas.drawBitmap(slide_button, newLeft, 0, paint);

        } else {
            if (mSwitchState) {
                int x = switch_background.getWidth() - slide_button.getWidth();
                canvas.drawBitmap(slide_button, x, 0, paint);
            } else {
                canvas.drawBitmap(slide_button, 0, 0, paint);
            }
        }


       /* if (mSwitchState) {
            int x = switch_background.getWidth() - slide_button.getWidth();
            canvas.drawBitmap(slide_button, x, 0, paint);
        } else {
            canvas.drawBitmap(slide_button, 0, 0, paint);
        }
*/
    }

    boolean isTouchMode = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouchMode = true;
                currentX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                isTouchMode = true;
                currentX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                isTouchMode = false;
                float center = switch_background.getWidth() / 2.0f;
                boolean state = currentX > center;

                if (state != mSwitchState && onSwitchUpdateListener != null) {
                    onSwitchUpdateListener.onStateUpdate(state);
                }
                mSwitchState = state;
                break;
        }

        invalidate();
        return true;
    }

    public void setOnSwitchStateUpdateListener(OnSwitchUpdateListener onSwitchUpdateListener) {
        this.onSwitchUpdateListener = onSwitchUpdateListener;

    }

    public interface OnSwitchUpdateListener {

                void onStateUpdate(boolean state);
    }


}
