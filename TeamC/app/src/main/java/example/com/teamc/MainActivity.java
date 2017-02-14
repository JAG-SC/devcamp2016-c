package example.com.teamc;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.wallet.firstparty.GetBuyFlowInitializationTokenResponse;

import example.com.teamc.resp.Range;
import example.com.teamc.resp.StationResp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    static String str;
    private GoogleMap mMap;

    private TextView hpText;    //プレイヤーHP表示
    private int hp=100;
    private String cityname="東京";
    private Button firstChoiceButton;
    private Button secondChoiceButton;
    private Button thirdChoiceButton;
    private int citycode1;
    private int citycode2;
    private int citycode3;
    private float lon;
    private float lat;
    private LatLng latlng;
    private int i;
    private int j;
    private int l;
    private int k;//検索結果のヒット数
    private int stock[]= new int[3];
    private boolean flag;

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
        firstChoiceButton = (Button)findViewById(R.id.first_choice_button);
        secondChoiceButton = (Button)findViewById(R.id.second_choice_button);
        thirdChoiceButton = (Button)findViewById(R.id.third_choice_button);

        // TextView
        // HP Text
        hpText = (TextView) findViewById(R.id.hp_text);
        hpText.setText(String.valueOf(hp));



        EkiSpertCommunitator communitator = new EkiSpertCommunitator(MainActivity.this);
        communitator.range(20, cityname, 5, new EkiSpertCommunitator.RangeListener() {
            @Override
            public void onResponse(Range range) {
                //Toast.makeText(MainActivity.this, "" + range.ResultSet.Point[0].Station.Name, Toast.LENGTH_LONG).show(); // <- ここは用途によって変えてください
                //1～3のボタンに取得した都市名を代入
                k= range.ResultSet.Point.length;

                List<Integer> ijl = new ArrayList<Integer>();
                while ( ijl.size() < 3 ){
                    int tmp = new Random().nextInt(k);
                    if(!ijl.contains(tmp)){
                        ijl.add(tmp);
                    }
                }
                int i = ijl.get(0);
                int j = ijl.get(1);
                int l = ijl.get(2);
                firstChoiceButton.setText(range.ResultSet.Point[i].Station.Name);
                citycode1 = Integer.valueOf(range.ResultSet.Point[i].Station.code);

                secondChoiceButton.setText(range.ResultSet.Point[j].Station.Name);
                citycode2=Integer.valueOf(range.ResultSet.Point[j].Station.code);

                thirdChoiceButton.setText(range.ResultSet.Point[l].Station.Name);
                citycode3=Integer.valueOf(range.ResultSet.Point[l].Station.code);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e("onFailure", throwable.getMessage());
            }

        });



        // リスナーをボタンに登録
        firstChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Buttonの名前(都市名)をcitynameに代入
                cityname = String.valueOf(firstChoiceButton.getText());
                EkiSpertCommunitator communitator = new EkiSpertCommunitator(MainActivity.this);

                //stationAPIを使用して新しく取得した都市名の座標に地図を移動
                communitator.station(citycode1, new EkiSpertCommunitator.StationListener() {
                    @Override
                    public void onResponse(StationResp station) {
                        //緯度と経度を取得
                        lat = Float.valueOf(station.ResultSet.Point.GeoPoint.lati_d);
                        lon = Float.valueOf(station.ResultSet.Point.GeoPoint.longi_d);
                        latlng = new LatLng(lat,lon);
                        //マップの移動
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,13));
                    }

                    @Override
                    public void onFailure(Throwable throwable) {

                    }
                });

                //rangeAPIを使用して値を取得
                communitator.range(20, cityname, 5, new EkiSpertCommunitator.RangeListener() {
                    @Override
                    public void onResponse(Range range) {
                        //Toast.makeText(MainActivity.this, "" + range.ResultSet.Point[0].Station.Name, Toast.LENGTH_LONG).show(); // <- ここは用途によって変えてください
                        //1～3のボタンに取得した都市名を代入
                        k= range.ResultSet.Point.length;

                        List<Integer> ijl = new ArrayList<Integer>();
                        while ( ijl.size() < 3 ){
                            int tmp = new Random().nextInt(k);
                            if(!ijl.contains(tmp)){
                                ijl.add(tmp);
                            }
                        }
                        int i = ijl.get(0);
                        int j = ijl.get(1);
                        int l = ijl.get(2);
                        firstChoiceButton.setText(range.ResultSet.Point[i].Station.Name);
                        citycode1 = Integer.valueOf(range.ResultSet.Point[i].Station.code);

                        secondChoiceButton.setText(range.ResultSet.Point[j].Station.Name);
                        citycode2=Integer.valueOf(range.ResultSet.Point[j].Station.code);

                        thirdChoiceButton.setText(range.ResultSet.Point[l].Station.Name);
                        citycode3=Integer.valueOf(range.ResultSet.Point[l].Station.code);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("onFailure", throwable.getMessage());
                    }

                });
            }
        });



        secondChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Buttonの名前(都市名)をcitynameに代入
                cityname = String.valueOf(firstChoiceButton.getText());
                EkiSpertCommunitator communitator = new EkiSpertCommunitator(MainActivity.this);

                //stationAPIを使用して新しく取得した都市名の座標に地図を移動
                communitator.station(citycode2, new EkiSpertCommunitator.StationListener() {
                    @Override
                    public void onResponse(StationResp station) {
                        //緯度と経度を取得
                        lat = Float.valueOf(station.ResultSet.Point.GeoPoint.lati_d);
                        lon = Float.valueOf(station.ResultSet.Point.GeoPoint.longi_d);
                        latlng = new LatLng(lat,lon);
                        //マップの移動
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,13));
                    }

                    @Override
                    public void onFailure(Throwable throwable) {

                    }
                });

                //rangeAPIを使用して値を取得
                communitator.range(20, cityname, 5, new EkiSpertCommunitator.RangeListener() {
                    @Override
                    public void onResponse(Range range) {
                        //Toast.makeText(MainActivity.this, "" + range.ResultSet.Point[0].Station.Name, Toast.LENGTH_LONG).show(); // <- ここは用途によって変えてください
                        //1～3のボタンに取得した都市名を代入
                        k= range.ResultSet.Point.length;

                        List<Integer> ijl = new ArrayList<Integer>();
                        while ( ijl.size() < 3 ){
                            int tmp = new Random().nextInt(k);
                            if(!ijl.contains(tmp)){
                                ijl.add(tmp);
                            }
                        }
                        int i = ijl.get(0);
                        int j = ijl.get(1);
                        int l = ijl.get(2);
                        firstChoiceButton.setText(range.ResultSet.Point[i].Station.Name);
                        citycode1 = Integer.valueOf(range.ResultSet.Point[i].Station.code);

                        secondChoiceButton.setText(range.ResultSet.Point[j].Station.Name);
                        citycode2=Integer.valueOf(range.ResultSet.Point[j].Station.code);

                        thirdChoiceButton.setText(range.ResultSet.Point[l].Station.Name);
                        citycode3=Integer.valueOf(range.ResultSet.Point[l].Station.code);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("onFailure", throwable.getMessage());
                    }

                });
            }
        });


        thirdChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Buttonの名前(都市名)をcitynameに代入
                cityname = String.valueOf(firstChoiceButton.getText());
                EkiSpertCommunitator communitator = new EkiSpertCommunitator(MainActivity.this);

                //stationAPIを使用して新しく取得した都市名の座標に地図を移動
                communitator.station(citycode3, new EkiSpertCommunitator.StationListener() {
                    @Override
                    public void onResponse(StationResp station) {
                        //緯度と経度を取得
                        lat = Float.valueOf(station.ResultSet.Point.GeoPoint.lati_d);
                        lon = Float.valueOf(station.ResultSet.Point.GeoPoint.longi_d);
                        latlng = new LatLng(lat,lon);
                        //マップの移動
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,13));
                    }

                    @Override
                    public void onFailure(Throwable throwable) {

                    }
                });

                //rangeAPIを使用して値を取得
                communitator.range(20, cityname, 5, new EkiSpertCommunitator.RangeListener() {
                    @Override
                    public void onResponse(Range range) {
                        //Toast.makeText(MainActivity.this, "" + range.ResultSet.Point[0].Station.Name, Toast.LENGTH_LONG).show(); // <- ここは用途によって変えてください
                        //1～3のボタンに取得した都市名を代入
                        k= range.ResultSet.Point.length;

                        List<Integer> ijl = new ArrayList<Integer>();
                        while ( ijl.size() < 3 ){
                            int tmp = new Random().nextInt(k);
                            if(!ijl.contains(tmp)){
                                ijl.add(tmp);
                            }
                        }
                        int i = ijl.get(0);
                        int j = ijl.get(1);
                        int l = ijl.get(2);

                        firstChoiceButton.setText(range.ResultSet.Point[i].Station.Name);
                        citycode1 = Integer.valueOf(range.ResultSet.Point[i].Station.code);

                        secondChoiceButton.setText(range.ResultSet.Point[j].Station.Name);
                        citycode2=Integer.valueOf(range.ResultSet.Point[j].Station.code);

                        thirdChoiceButton.setText(range.ResultSet.Point[l].Station.Name);
                        citycode3=Integer.valueOf(range.ResultSet.Point[l].Station.code);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("onFailure", throwable.getMessage());
                    }

                });
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
        //LatLng sydney = new LatLng(-34, 151);
        //tokyoの座標　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　
        latlng = new LatLng(35.678083,139.770444);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,13));
    }
}
