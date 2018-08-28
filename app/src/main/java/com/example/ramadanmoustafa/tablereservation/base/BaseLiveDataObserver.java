package com.example.ramadanmoustafa.tablereservation.base;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

/**
 * Base livedata observer to provide detailed calledbacks for different states (onsuccess, onerror, onprogress)
 * Much better than Observer.onChanged
 * @param <R>
 */
public abstract class BaseLiveDataObserver<R extends BaseReactiveResponse> implements Observer<R> {
    @Override
    public void onChanged(@Nullable R apiResponse) {
        if(apiResponse!= null){
            if(apiResponse.isLoading()) {
                onProgress(true);
            }
            else{
               onProgress(false);
                if(apiResponse.isSuccessful())
                    onSuccess(apiResponse);
                else
                    onError(apiResponse.getApiErrorMessage());
            }
        }

    }

    public abstract void onSuccess(@Nullable R data);

    public abstract void onError(@Nullable String data);

    public abstract void onProgress(boolean isShown);
}