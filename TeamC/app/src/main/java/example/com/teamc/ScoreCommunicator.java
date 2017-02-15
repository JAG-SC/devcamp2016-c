package example.com.teamc;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ayijk on 2017/02/15.
 */

public class ScoreCommunicator {

    private static RankingService createService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://script.google.com/macros/s/AKfycbxnYsv5nR-ACdCsHq3m82w-4R1ZjtCzIa-YtlyNI2vP8ZXzbq6D/")
                .build();
        RankingService service = retrofit.create(RankingService.class);
        return service;
    }

    public static void getrank(String user_id, int point) {
        createService().exec(Method.getrank.name(), user_id, point).enqueue(new Callback<Void>() {
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

    public static void registrank(String user_id, int point) {
        createService().exec(Method.registrank.name(), user_id, point).enqueue(new Callback<Void>() {
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

    public static void deleteuser(String user_id, int point) {
        createService().exec(Method.deleteuser.name(), user_id, point).enqueue(new Callback<Void>() {
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
    }
}
