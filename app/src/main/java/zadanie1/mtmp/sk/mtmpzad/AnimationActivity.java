package zadanie1.mtmp.sk.mtmpzad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import zadanie1.mtmp.sk.mtmpzad.utils.AnimatedView;
import zadanie1.mtmp.sk.mtmpzad.utils.Data;
import zadanie1.mtmp.sk.mtmpzas.R;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Data data = (Data) getIntent().getSerializableExtra("data");
        AnimatedView view = (AnimatedView)findViewById(R.id.anim_view);
        view.setData(data);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
