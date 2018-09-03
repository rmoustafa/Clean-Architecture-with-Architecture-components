package com.example.ramadanmoustafa.tablereservation.base;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

/**
 * Base livedata observer to provide detailed calledbacks for different states (onsuccess, onerror, onprogress)
 * Much better than Observer.onChanged
 * @param <R>
 */
public abstract class BaseLiveDataObserver<R extends DataResponse> implements Observer<R> {
    @Override
    public void onChanged(@Nullable R response) {
        if(response!= null){
            switch (response.getStatus()){
                case LOADING:
                    onProgress(true);
                    break;
                case SUCCESS:
                    onSuccess(response);
                    break;
                case ERROR:
                    onError(response.getErrorMessage());
                    break;
                    default:
                        break;
            }
        }

    }

    public abstract void onSuccess(@Nullable R data);

    public abstract void onError(@Nullable String data);

    public abstract void onProgress(boolean isShown);
}