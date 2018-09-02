package com.example.ramadanmoustafa.tablereservation.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.VisibleForTesting;

import com.example.ramadanmoustafa.tablereservation.data.entities.Table;
import com.example.ramadanmoustafa.tablereservation.base.BaseReactiveResponse;
import com.example.ramadanmoustafa.tablereservation.data.remote.ServiceApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TableRepositoryImp implements TableRepository{

    private int id =0;
    private ServiceApi mServiceApi;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public TableRepositoryImp(ServiceApi serviceApi/*, CustomerDB customerDB*/){
        this.mServiceApi = serviceApi;
//        this.customerDBObject = customerDB;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public LiveData<BaseReactiveResponse<List<Table>>> getTableMap() {

        //TODO: check in cache/db/remote
        MutableLiveData<BaseReactiveResponse<List<Table>>> response = new MutableLiveData<>();
        mCompositeDisposable.add(mServiceApi.getTableMap()
                .flatMap(list -> Observable.fromIterable(list)
                .map(this::mapBooleanToTableItem)
                .toList()
                .toObservable())
                .doOnSubscribe(disposable -> response.postValue(new BaseReactiveResponse(true)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Table>>() {
                               @Override
                               public void accept(List<Table> tableMap) throws Exception {
                                   response.setValue(new BaseReactiveResponse<>(tableMap));
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable e) throws Exception {
                                response.setValue(new BaseReactiveResponse<>(e));
                            }
                        }
                    ));

        return response;
    }

    @VisibleForTesting
     Table mapBooleanToTableItem(boolean isAvailable){
        Table table = new Table(++id, isAvailable);
        return table;
    }
}
