package example.com.teamc;


import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
import java.util.List;


import example.com.teamc.LivedoorWeatherWebService.ExpertRange.EkispertRange;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;
*/


/**
 * Created by hayatokimura on 2017/02/14.
 */
/*
public class  EkispertWrapper{

    private static EkispertRange INSTANCE;

    public static EkispertRange get() {
        if (INSTANCE == null) {
            INSTANCE = createInstance();
        }
        return INSTANCE;
    }

    private static EkispertRange createInstance() {
        return retrofit().create(EkispertRange.class);
    }

    private static Retrofit retrofit() {
        final String ENDPOINT = "https://ancient-bastion-22709.herokuapp.com";
        return new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}*/


public class EkispertWrapper extends AsyncTask<Void, Void, String> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // doInBackground前処理
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // doInBackground後処理
        MainActivity.str = result;

    }

    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection con = null;
        URL url = null;
        String urlSt = "https://api.ekispert.jp/v1/json/search/range?&upperLimit=10&name=%E6%9D%B1%E4%BA%AC&limit=3key=";

        try {
            // URLの作成
            url = new URL(urlSt);
            // 接続用HttpURLConnectionオブジェクト作成
            con = (HttpURLConnection)url.openConnection();
            // リクエストメソッドの設定
            con.setRequestMethod("POST");
            // リダイレクトを自動で許可しない設定
            con.setInstanceFollowRedirects(false);
            // URL接続からデータを読み取る場合はtrue
            con.setDoInput(true);
            // URL接続にデータを書き込む場合はtrue
            con.setDoOutput(true);

            // 接続
            con.connect(); // ①

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String readInputStream(InputStream in) throws IOException, UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        String st = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        while((st = br.readLine()) != null)
        {
            sb.append(st);
        }
        try
        {
            in.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return sb.toString();
    }

    JSONObject getJSONObj(String st,String obj_name){
        try {

            JSONObject jsonData = new JSONObject(st).getJSONObject(obj_name);
            return jsonData;
        }catch(org.json.JSONException e){
            e.printStackTrace();
            return null;
        }
    }

}

