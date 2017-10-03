package ra.sumbayak.beftif.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

import okhttp3.*;
import ra.sumbayak.beftif.api.models.Category;
import ra.sumbayak.beftif.api.models.News;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

import static ra.sumbayak.beftif.Constant.*;

public class ApiInterface {
    
    public static class Builder {
        public static Interface build (Context context) {
            Retrofit.Builder builder = new Retrofit.Builder ()
                .baseUrl (API_BASE_URL)
                .addConverterFactory (GsonConverterFactory.create ());
            
            SharedPreferences sp = context
                .getSharedPreferences (SPNAME, Context.MODE_PRIVATE);
            
            if (sp.contains (SPKEY_TOKEN)) {
                final String token = sp.getString (SPKEY_TOKEN, "Null");
                
                builder.client (new OkHttpClient.Builder ()
                    .addInterceptor (new Interceptor () {
                        @Override
                        public Response intercept (@NonNull Chain chain) throws IOException {
                            return chain.proceed (chain.request ()
                                                       .newBuilder ()
                                                       .addHeader (AUTH_HEADER_NAME, TOKEN_PREFIX + token)
                                                       .build ());
                        }
                    })
                    .build ());
            }
            
            return builder.build ().create (Interface.class);
        }
    }
    
    public interface Interface {
        @GET (" ") Call<JsonObject> test ();
        @GET ("news") Call<List<News>> newsByCategory (@Query ("category__name") String category);
        @GET ("news") Call<List<News>> newsByOrdering (@Query ("ordering") String ordering);
        @GET ("news/{id}") Call<News> retrieveNews (@Path ("id") int id);
        @GET ("categories") Call<List<Category>> categories ();
    }
}
