package zadanie1.mtmp.sk.mtmpzad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import zadanie1.mtmp.sk.mtmpzad.utils.Calculator;
import zadanie1.mtmp.sk.mtmpzad.utils.Data;
import zadanie1.mtmp.sk.mtmpzas.R;

public class TableActivity extends AppCompatActivity {

    TableLayout tableLayout;
    Button btnGraph;
    Button btnAnimation;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        btnGraph = (Button) findViewById(R.id.btnGraph);
        btnAnimation = (Button) findViewById(R.id.btnView);
        init();

        btnGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TableActivity.this, GraphActivity.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });

        btnAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TableActivity.this, AnimationActivity.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });
    }

    public void init() {

        Intent intent = getIntent();
        String strAngle = intent.getExtras().getString("angle");
        String strSpeed = intent.getExtras().getString("speed");

        double angle = Double.valueOf(strAngle);
        double speed = Double.valueOf(strSpeed);

        Boolean cBoxActivated = (Boolean)intent.getExtras().get("checkBox");

        Log.d("ACTIVE", cBoxActivated.toString());

        if(!cBoxActivated) {
            data = Calculator.calculate(angle, speed);
            createTable(data);
        }
        else{
            Log.d("SERVER","SERVER");
        }
    }

    private void createTable(Data data) {
        tableLayout = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);

        TextView tv0 = new TextView(this);
        tv0.setText(" Čas ");
        tbrow0.addView(tv0);

        TextView tv1 = new TextView(this);
        tv1.setText(" X ");
        tbrow0.addView(tv1);

        TextView tv2 = new TextView(this);
        tv2.setText(" Y ");
        tbrow0.addView(tv2);

        tableLayout.addView(tbrow0);

        for (int i = 0; i <data.getX().size(); i++) {

            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText("" + String.format("%.2f",data.getTimes().get(i)));
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);

            TextView t2v = new TextView(this);
            t2v.setText(""+data.getX().get(i));
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);

            TextView t3v = new TextView(this);
            t3v.setText("" + data.getY().get(i));
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);

            tableLayout.addView(tbrow);
        }
    }


}
