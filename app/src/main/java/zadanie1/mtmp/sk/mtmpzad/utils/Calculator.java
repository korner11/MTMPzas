package zadanie1.mtmp.sk.mtmpzad.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by z003r0bf on 26. 9. 2017.
 */

public class Calculator {

    public static Data calculate(double speed, double angle){



        List<Integer> xs= new ArrayList<>();
        List<Integer> ys= new ArrayList<>();
        List<Double> times = new ArrayList<>();

        angle = Math.toRadians(angle);
        double g = 9.81, t;
        int x,y;
        double endTime = (2*speed * Math.sin(angle)) / g;
        double maxDistance = ((speed*speed)/g) * Math.sin(2*angle);

        for ( t=0; t < endTime; t=t+0.2) {
                x =  (int)(speed*t*Math.cos(angle));
                y = (int)((speed*t*Math.sin(angle) - (0.5 * g * t * t)));
                xs.add(x);
                ys.add(y);
                times.add(t);
            Log.d("LINE",t +" "+ x + " " + y );
        }
        Data data = new Data(xs,ys,times,endTime,maxDistance);
        return data;
    }

}
