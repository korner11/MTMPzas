package zadanie1.mtmp.sk.mtmpzad.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;

import zadanie1.mtmp.sk.mtmpzas.R;


/**
 * Created by z003r0bf on 29. 9. 2017.
 */

public class AnimatedView extends android.support.v7.widget.AppCompatImageView {
    private Context mContext;
    int x = -1;
    int y = -1;
    private int xVelocity = 10;
    private int yVelocity = 5;
    private Handler h;
    private final int FRAME_RATE = 30;

    int i=0;
    Data data;

    public void setData(Data data) {
        this.data = data;
    }

    public AnimatedView(Context context, AttributeSet attrs)  {
        super(context, attrs);
        mContext = context;
        h = new Handler();
    }
    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };
    protected void onDraw(Canvas c) {
        BitmapDrawable ball = (BitmapDrawable) mContext.getDrawable(R.drawable.ball);
        if(i<data.getX().size()) {
            x = data.getX().get(i) ;
            y = data.getY().get(i) ;
            i++;
        }

        c.translate(0,c.getHeight());   // reset where 0,0 is located
        c.scale(1,-1);
        c.drawBitmap(ball.getBitmap(), x * 4, y * 4, null);
        h.postDelayed(r, FRAME_RATE);
    }
}
