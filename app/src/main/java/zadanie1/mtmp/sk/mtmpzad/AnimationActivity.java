package zadanie1.mtmp.sk.mtmpzad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zadanie1.mtmp.sk.mtmpzad.utils.Data;
import zadanie1.mtmp.sk.mtmpzas.R;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        Data data = (Data) getIntent().getSerializableExtra("data");
    }
}
