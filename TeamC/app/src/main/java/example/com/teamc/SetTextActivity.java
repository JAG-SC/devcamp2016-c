package example.com.teamc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import example.com.teamc.resp.RankTable;

public class SetTextActivity extends AppCompatActivity implements View.OnClickListener {
    private Button start;
    public String station_name;
    public String station_time;

    private TableLayout tableRayout;
    private TableRow rankTableRow; //ランキング表示用テーブルラウ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_text);

        start = (Button) findViewById(R.id.button_start);
        start.setOnClickListener(this);

        /*ランキングの取得と表示*/
        tableRayout = (TableLayout) findViewById(R.id.table_layout);
        rankTableRow = (TableRow) getLayoutInflater().inflate(R.layout.table_row,null);

    }

    @Override
    public void onResume() {
        super.onResume();

        //ランキング表示
        ScoreCommunicator scorecom = new ScoreCommunicator();
        scorecom.getrank("ranking",new ScoreCommunicator.GetRankListener(){
            @Override
            public void onResponse(RankTable ranktable) {
                //TODO:addViewで複数要素追加すると落ちるので対策
                String user_id_text = "";
                String point_text = "";
                for(int i=0;i<ranktable.Rank.length;++i) {
                    user_id_text+=ranktable.Rank[i].user_id+"\n";
                    point_text+=Integer.toString(ranktable.Rank[i].point)+"\n";
                }
                ViewGroup parent = (ViewGroup)findViewById(R.id.table_layout);
                if ( parent != null ) { //念のため子要素を削除して落ちるのを防ぐ
                    parent.removeView(rankTableRow);
                }
                TextView rankUserIdTextView = (TextView)rankTableRow.findViewById(R.id.rank_user_id);
                rankUserIdTextView.setText(user_id_text);
                TextView rankPointTextView = (TextView)rankTableRow.findViewById(R.id.rank_point);
                rankPointTextView.setText(point_text);
                tableRayout.addView(rankTableRow,new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            }
            @Override
            public void onFailure(Throwable throwable) {
                Log.e("onFailure", throwable.getMessage());
            }
        });
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
