package zadanie1.mtmp.sk.mtmpzad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import zadanie1.mtmp.sk.mtmpzad.utils.Data;
import zadanie1.mtmp.sk.mtmpzas.R;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Data data = (Data) getIntent().getSerializableExtra("data");
        GraphView graph = (GraphView) findViewById(R.id.graph);

        DataPoint[] points = new DataPoint[data.getX().size()];

        for (int i = 0; i < data.getX().size(); i++) {
            points[i]= new DataPoint(data.getX().get(i),data.getY().get(i));
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
        graph.addSeries(series);
    }
}
