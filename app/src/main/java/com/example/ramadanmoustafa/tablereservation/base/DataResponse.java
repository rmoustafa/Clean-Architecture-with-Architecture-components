package com.example.ramadanmoustafa.tablereservation.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A generic class that contains data and status about loading this data.
 * it wraps the data retrieved by repository, whether it comes from api or local
 *
 */
public class DataResponse<T> {

    @Nullable
    private final T data;
    @Nullable
    private final Throwable throwable;
    @NonNull
    public final Status status;

    private DataResponse(@NonNull Status status, @Nullable T data, @NonNull Throwable throwable){
        this.data = data;
        this.throwable = throwable;
        this.status = status;
    }

    public static <T> DataResponse<T> success(@Nullable T data) {

        return new DataResponse<>(Status.SUCCESS, data, null);
    }

    public static <T> DataResponse<T> error(Throwable error) {
        return new DataResponse<>(Status.ERROR, null, error);
    }

    public  static <T> DataResponse<T> loading(@NonNull boolean loading) {
        return new DataResponse<>(Status.LOADING, null, null);
    }

    public T getData() {
        return data;
    }

    public Status getStatus() {
        return status;
    }

    @Nullable
    public String getErrorMessage() {
        return  throwable != null ? throwable.getMessage() : null;
    }

    public enum Status { SUCCESS, ERROR, LOADING }

}
