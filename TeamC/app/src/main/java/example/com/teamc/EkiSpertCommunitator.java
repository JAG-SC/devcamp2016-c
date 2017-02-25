package example.com.teamc;

import android.content.Context;

import example.com.teamc.resp.CoursePlain;
import example.com.teamc.resp.Range;
import example.com.teamc.resp.StationResp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by ayijk on 2017/02/14.
 */

public class EkiSpertCommunitator {
    private final EkiSpertService service;
    private final String key;

    public EkiSpertCommunitator(Context context) {
        this.key = context.getString(R.string.ekispert_key);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.ekispert.jp/v1/json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(EkiSpertService.class);
    }

    public void range(int upperLimit, String name, int limit, final RangeListener listener) {
        service.range(key, upperLimit, name, limit).enqueue(new Callback<Range>() {
            @Override
            public void onResponse(Call<Range> call, Response<Range> response) {
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Range> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    public interface RangeListener {
        void onResponse(Range range);

        void onFailure(Throwable throwable);
    }

    public void course_plain(String name, String to, final CoursePlainListener listener) {
        service.course_plain(key, name, to).enqueue(new Callback<CoursePlain>() {
            @Override
            public void onResponse(Call<CoursePlain> call, Response<CoursePlain> response) {
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<CoursePlain> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    public interface CoursePlainListener {
        void onResponse(CoursePlain coursePlain);

        void onFailure(Throwable throwable);
    }

    public void station(int code, final StationListener listener) {
        service.station(key, code).enqueue(new Callback<StationResp>() {
            @Override
            public void onResponse(Call<StationResp> call, Response<StationResp> response) {
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<StationResp> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    public void station(String name, final StationListener listener) {
        try {
            service.station(key, URLEncoder.encode(name, "UTF-8")).enqueue(new Callback<StationResp>() {
                @Override
                public void onResponse(Call<StationResp> call, Response<StationResp> response) {
                    listener.onResponse(response.body());
                }

                @Override
                public void onFailure(Call<StationResp> call, Throwable t) {
                    listener.onFailure(t);
                }
            });
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public interface StationListener {
        void onResponse(StationResp station);

        void onFailure(Throwable throwable);
    }


    private interface EkiSpertService {
        @GET("search/range")
        Call<Range> range(@Query("key") String key, @Query("upperLimit") int upperLimit,
                          @Query("name") String name, @Query("limit") int limit);

        @GET("search/course/plain")
        Call<CoursePlain> course_plain(@Query("key") String key, @Query("from") String from, @Query("to") String to);

        @GET("station")
        Call<StationResp> station(@Query("key") String key, @Query("code") int code);

        @GET("station")
        Call<StationResp> station(@Query("key") String key, @Query("name") String name);
    }
}
