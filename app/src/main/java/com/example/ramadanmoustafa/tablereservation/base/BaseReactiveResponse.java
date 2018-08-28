package com.example.ramadanmoustafa.tablereservation.base;

import android.support.annotation.Nullable;
import retrofit2.Response;

/**
 * A base class to indicate the different responses of asynchronous operations
 * It Wraps the reactive response get by rxjava observables
 *
 * @param <T>
 */
public class BaseReactiveResponse<T> {

    private final int responseCode;
    @Nullable
    private final T body;
    private final Throwable throwable;
    @Nullable
    private final String errorMessage;

    private final boolean loading;

    private BaseReactiveResponse(int responseCode, T body, boolean loading, Throwable throwable){
        this.responseCode = responseCode;
        this.throwable = throwable;
        this.body = body;
        this.errorMessage = throwable != null ? throwable.getMessage() : null;
        this.loading = loading;
    }

    public BaseReactiveResponse(Throwable error) {
        this( 500, null, false, error);
    }
    public BaseReactiveResponse(boolean loading) {
        this( 200, null, loading, null);
    }
    public BaseReactiveResponse(Response<T> response) {

        this( response.code(), response.body(), false, null);
    }

    public BaseReactiveResponse(T response) {

        this( 200, response, false, null);
    }
    public boolean isSuccessful() {
        return responseCode >= 200 && responseCode < 300;
    }

    public int getResponseCode(){
        return responseCode;
    }

    public T getBody() {
        return body;
    }

    public boolean isLoading() {
        return loading;
    }

    @Nullable
    public String getApiErrorMessage() {
        return errorMessage;
    }
}
