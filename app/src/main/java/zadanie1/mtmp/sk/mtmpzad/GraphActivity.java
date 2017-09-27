package zadanie1.mtmp.sk.mtmpzad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Array;

import zadanie1.mtmp.sk.mtmpzad.utils.Data;
import zadanie1.mtmp.sk.mtmpzas.R;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Data data = (Data) getIntent().getSerializableExtra("data");
        createGraph(data);
    }

    private void createGraph(Data data) {
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.setTitle(getResources().getString(R.string.graphTitle));

        // activate horizontal scrolling
        graph.getViewport().setScrollable(true);
        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(80);
        // set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(80);
        //set label titles
        graph.getGridLabelRenderer().setVerticalAxisTitle(getResources().getString(R.string.graphY));
        graph.getGridLabelRenderer().setHorizontalAxisTitle(getResources().getString(R.string.graphX));

        DataPoint[] points = new DataPoint[data.getX().size()];

        for (int i = 0; i < data.getX().size(); i++) {
            points[i]= new DataPoint(data.getX().get(i),data.getY().get(i));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
        graph.addSeries(series);
    }
}
