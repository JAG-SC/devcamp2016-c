package example.com.teamc;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SetTextActivity extends AppCompatActivity implements View.OnClickListener {
    private Button start;
    public String station_name;
    public String station_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_text);

        start = (Button) findViewById(R.id.button_start);
        start.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_start:
                TextView textView1 = (TextView) findViewById(R.id.text_station);
                //TextView textView2 = (TextView) findViewById(R.id.text_time);
                station_name = textView1.getText().toString();
                //station_time = textView2.getText().toString();

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("SName", station_name);

                //intent.putExtra("Stime", station_time);
                Log.d("TAG0",station_name);

                int requestCode = 1000;
                //startActivityForResult(intent, requestCode);
                startActivity(intent);
                break;
        }

    }
}
