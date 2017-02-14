package example.com.teamc;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;


public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    static String str;
    private GoogleMap mMap;
    private TextView hpText;    //プレイヤーHP表示
    private boolean flag = false;

    private TextView hpText;    //プレイヤーHP表示
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // load Player
        ImageView imageView1 = (ImageView)findViewById(R.id.image_player);
        imageView1.setImageResource(R.drawable.player_figure);

        // Buttons
        Button firstChoiceButton = (Button)findViewById(R.id.first_choice_button);
        Button secondChoiceButton = (Button)findViewById(R.id.second_choice_button);
        Button thirdChoiceButton = (Button)findViewById(R.id.third_choice_button);

        // TextView
        // HP Text
        hpText = (TextView) findViewById(R.id.hp_text);

        // リスナーをボタンに登録
        firstChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        secondChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        thirdChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(new OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //Toast.makeText(getApplicationContext(), "タップ位置\n緯度：" + latLng.latitude + "\n経度:" + latLng.longitude, Toast.LENGTH_LONG).show();

                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(latLng.latitude, latLng.longitude);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                EkispertWrapper ek = new EkispertWrapper();
                ek.execute();

            }
        });

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
