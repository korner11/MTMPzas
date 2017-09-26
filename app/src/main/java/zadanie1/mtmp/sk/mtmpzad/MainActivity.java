package zadanie1.mtmp.sk.mtmpzad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import zadanie1.mtmp.sk.mtmpzas.R;

public class MainActivity extends AppCompatActivity {

    Button btnCacl;
    EditText txtAngle;
    EditText txtSpeed;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCacl = (Button) findViewById(R.id.btnCalc);
        txtAngle = (EditText) findViewById(R.id.txtAngle);
        txtSpeed = (EditText) findViewById(R.id.txtSpeed);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        btnCacl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String angle = txtAngle.getText().toString();
                String speed = txtSpeed.getText().toString();

                if(TextUtils.isEmpty(angle)) {
                    txtAngle.setError(getString(R.string.errorMsgAngle));
                    return;
                }

                if(TextUtils.isEmpty(speed)) {
                    txtSpeed.setError(getString(R.string.errorMsgSpeed));
                    return;
                }

                Intent intent = new Intent(MainActivity.this, TableActivity.class);
                intent.putExtra("angle",angle);
                intent.putExtra("speed",speed);
                intent.putExtra("checkBox",checkBox.isChecked());
                startActivity(intent);
            }
        });
    }


}
