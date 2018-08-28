package com.example.ramadanmoustafa.tablereservation.di.modules;

import com.example.ramadanmoustafa.tablereservation.BuildConfig;
import com.example.ramadanmoustafa.tablereservation.TableReservationApp;
import com.example.ramadanmoustafa.tablereservation.Utils.NetworkUtils;
import com.example.ramadanmoustafa.tablereservation.data.remote.ServiceApi;
import com.example.ramadanmoustafa.tablereservation.di.scopes.ApplicationScope;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import timber.log.Timber;

@Module
public class NetworkModule {

    private final static int CONNECT_TIMEOUT_IN_SECONDS = 10;
    private final static int READ_TIMEOUT_IN_SECONDS = 60;
    private final static int WRITE_TIMEOUT_IN_SECONDS = 60;
    private final static long CACHE_SIZE_BYTES = 10 * 1024 * 1024;// 10 MB
    private final static String CACHE_FILE_NAME = "okhttp_cache";

    /**
     * provides an Interceptor object to enable logging http request/response,
     * based on the defined log level
     *
     */
    @Provides
    @ApplicationScope
    public static HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        return new HttpLoggingInterceptor(Timber::i).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @ApplicationScope
    public static File provideCacheFile(TableReservationApp context){
        return new File(context.getApplicationContext().getCacheDir(),CACHE_FILE_NAME);

    }


    /**
     * provides a an http cache storage with specific size
     *
     */
    @Provides
    @ApplicationScope
    public static Cache provideCache(File cacheFile){
        return new Cache(cacheFile ,CACHE_SIZE_BYTES);
    }


    /**
     * provides an Interceptor object to enable adding custom headers to http requests
     * It Also enables getting data from cache or server
     * @return
     */
    @Provides
    @ApplicationScope
    public static Interceptor provideNetworkInterceptor(TableReservationApp context){
        //
        return chain -> {
            Request.Builder builder = chain.request().newBuilder();

            Map<String, String> params = NetworkUtils.getAuthorizationHeaders(context.getApplicationContext());
            if ( params != null && params.size() > 0) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue()).build();
                }
            }
            // to be added to work offline
            if (!NetworkUtils.isNetworkAvailable(context)) {
                builder.cacheControl(CacheControl.FORCE_CACHE);
            }

            Response response = chain.proceed(builder.build());

            return response;

        };
    }


    /**
     * provides a custom OkHTPP object to be used a retrofit client
     * it could be used as a standalone http client
     */
    @Provides
    @ApplicationScope
    public static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor,
                                                   Interceptor networkInterceptor, Cache cache) {
        // add certificate here

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(networkInterceptor);


        //Enable logging in debug mode only
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(loggingInterceptor);
        }
        return clientBuilder.build();
    }


    @Provides
    @ApplicationScope
    public static Retrofit provideRetrofit(OkHttpClient httpClient){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @ApplicationScope
    public static ServiceApi getServieceApi(Retrofit retrofit){
        return retrofit.create(ServiceApi.class);
    }

/*
    @Provides
    @ApplicationScope
    public static SharedPreferencesManager sharedPreferences(TableReservationApp context){
        return new SharedPreferencesManager(context);
    }*/

}

