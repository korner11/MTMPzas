package zadanie1.mtmp.sk.mtmpzad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
            sendRequest(strAngle,strSpeed);

        }
    }

    private void sendRequest(String angle, String speed){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:8080/calculator?angle="+angle+"&speed="+speed;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       Log.d(" RESPOSNE","Response is: "+ response);
                        try {
                            data = parseResponce(response);
                            createTable(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("ERROR","JSON ERROR");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR","That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public Data parseResponce(String response) throws JSONException {
        JSONObject responseObject = new JSONObject(response);
        JSONArray arrayXs =  responseObject.getJSONArray("x");
        JSONArray arrayYs =  responseObject.getJSONArray("y");
        JSONArray arrayTimes =  responseObject.getJSONArray("times");
        Double endTime = responseObject.getDouble("endTime");
        Double maxDistance = responseObject.getDouble("maxDistance");

        List<Integer> xs = parseJSONarray(arrayXs);
        List<Integer> ys = parseJSONarray(arrayYs);
        List<Double> times = parseJSONarrayD(arrayTimes);
        return new Data(xs,ys,times,endTime,maxDistance);
    }

    private List<Double> parseJSONarrayD(JSONArray array){
        // Create an int array to accomodate the numbers.
        Double[] numbers = new Double[array.length()];

        // Extract numbers from JSON array.
        for (int i = 0; i < array.length(); ++i) {
            numbers[i] = array.optDouble(i);
        }
        return Arrays.asList(numbers);
    }

    private List<Integer> parseJSONarray(JSONArray array){
        // Create an int array to accomodate the numbers.
        Integer[] numbers = new Integer[array.length()];

        // Extract numbers from JSON array.
        for (int i = 0; i < array.length(); ++i) {
            numbers[i] = array.optInt(i);
        }
        return Arrays.asList(numbers);
    }

    private void createTable(Data data) {
        tableLayout = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);

        TextView tv0 = new TextView(this);
        tv0.setText(" Čas ");
        formatTxtView(tv0);
        tbrow0.addView(tv0);

        TextView tv1 = new TextView(this);
        formatTxtView(tv1);
        tv1.setText(" X ");
        tbrow0.addView(tv1);

        TextView tv2 = new TextView(this);
        formatTxtView(tv2);
        tv2.setText(" Y ");
        tbrow0.addView(tv2);

        tableLayout.addView(tbrow0);

        for (int i = 0; i <data.getX().size(); i++) {

            TableRow tbrow = new TableRow(this);

            TextView t1v = new TextView(this);
            formatTxtView(t1v);
            t1v.setText("" + String.format("%.2f",data.getTimes().get(i)));
            tbrow.addView(t1v);

            TextView t2v = new TextView(this);
            formatTxtView(t2v);
            t2v.setText(""+data.getX().get(i));
            tbrow.addView(t2v);

            TextView t3v = new TextView(this);
            formatTxtView(t3v);
            t3v.setText("" + data.getY().get(i));
            tbrow.addView(t3v);

            tableLayout.addView(tbrow);
        }
    }
    public void formatTxtView(TextView view){
        view.setTextAppearance(this,android.R.style.TextAppearance_Large);
       // view.setPadding(60,40,40,60);
        view.setBackground(getDrawable(R.drawable.cell_shape));
        view.setGravity(Gravity.CENTER);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
