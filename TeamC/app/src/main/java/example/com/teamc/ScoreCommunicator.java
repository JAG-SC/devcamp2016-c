package example.com.teamc;

import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import example.com.teamc.resp.RankTable;

/**
 * Created by ayijk on 2017/02/15.
 */

public class ScoreCommunicator {

    private static RankingService createService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://script.google.com/macros/s/AKfycbxPnDvfmlwduo7AIQ-KfMl5sqeWJZNERBRNk2zqMk1kT17PsxQ0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RankingService service = retrofit.create(RankingService.class);
        return service;
    }

    public void getrank(String user_id,final GetRankListener listener) {
        createService().getrank(Method.getrank.name(), user_id).enqueue(new Callback<RankTable>() {
            @Override
            public void onResponse(Call<RankTable> call, Response<RankTable> response) {
                // TODO: 2017/02/15
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<RankTable> call, Throwable t) {
                // TODO: 2017/02/15
                listener.onFailure(t);
            }
        });
    }
    public interface GetRankListener {
        void onResponse(RankTable ranktable);

        void onFailure(Throwable throwable);
    }

    public void registrank(String user_id, int point,final RegistRankListener listener) {
        createService().registrank(Method.registrank.name(), user_id,"ranking", point).enqueue(new Callback<RankTable>() {
            @Override
            public void onResponse(Call<RankTable> call, Response<RankTable> response) {
                // TODO: 2017/02/15
            }

            @Override
            public void onFailure(Call<RankTable> call, Throwable t) {
                // TODO: 2017/02/15
            }
        });
    }

    public interface RegistRankListener {
        void onResponse(RankTable ranktable);

        void onFailure(Throwable throwable);
    }

    public void deleteuser(String user_id, int point) {
        createService().deleteuser(Method.deleteuser.name(), user_id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // TODO: 2017/02/15

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // TODO: 2017/02/15
            }
        });
    }

    private enum Method {
        getrank,
        registrank,
        deleteuser
    }

    private interface RankingService {
        @GET("exec")
        Call<Void> exec(@Query("method_type") String method_type, @Query("user_id") String user_id, @Query("point") int point);
        @GET("exec")
        Call<RankTable> registrank(@Query("method_type") String method_type, @Query("user_id") String user_id,@Query("rank_name") String rank_name, @Query("point") int point);
        @GET("exec")
        Call<Void> deleteuser(@Query("method_type") String method_type, @Query("user_id") String user_id);
        @GET("exec")
        Call<RankTable> getrank(@Query("method_type") String method_type, @Query("rank_name") String rank_name);
    }
}
