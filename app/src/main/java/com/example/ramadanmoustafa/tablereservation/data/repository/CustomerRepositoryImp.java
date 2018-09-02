package com.example.ramadanmoustafa.tablereservation.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.ramadanmoustafa.tablereservation.base.BaseReactiveResponse;
import com.example.ramadanmoustafa.tablereservation.data.entities.Customer;
import com.example.ramadanmoustafa.tablereservation.data.local.CustomerDao;
import com.example.ramadanmoustafa.tablereservation.data.remote.ServiceApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class CustomerRepositoryImp implements CustomerRepository {
    private final ServiceApi mServiceApi;
    private final CustomerDao mCustomerDao;
    private CompositeDisposable mCompositeDisposable;


    @Inject
    public CustomerRepositoryImp(ServiceApi serviceApi, CustomerDao customerDao){
        this.mServiceApi = serviceApi;
        this.mCustomerDao = customerDao;
        mCompositeDisposable = new CompositeDisposable();
    }

    /**
     * Returns data from remote server or locally based on {@param forceUpdate}
     */
    @Override
    public LiveData<BaseReactiveResponse<List<Customer>>> getCustomers(boolean forceUpdate){
        MutableLiveData<BaseReactiveResponse<List<Customer>>> response = new MutableLiveData<>();
        if( !forceUpdate ) { //Fetch from db
            mCompositeDisposable.add(mCustomerDao.getCustomers()
                    .doOnSubscribe(disposable -> response.postValue(new BaseReactiveResponse(true)))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new Consumer<List<Customer>>() {
                                @Override
                                public void accept(List<Customer> customers) throws Exception {

                                    response.setValue(new BaseReactiveResponse<>(customers));
                                }
                            },
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    response.setValue(new BaseReactiveResponse<>(throwable));
                                }
                            }


                    ));

        }
        else { //fetch from remote api
            mCompositeDisposable.add(mServiceApi.getCustomerList()
                    .doOnSubscribe(disposable -> response.postValue(new BaseReactiveResponse(true)))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new Consumer<List<Customer>>() {
                                @Override
                                public void accept(List<Customer> customers) throws Exception {
                                    response.setValue(new BaseReactiveResponse<>(customers));
                                    saveCustomers(customers);

                                }
                            },
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    response.setValue(new BaseReactiveResponse<>(throwable));
                                }
                            }


                    ));
        }


        return response;


    }

    /**
     * saves data to database tables, for offline usage
     * @param customers
     */
    private void saveCustomers(List<Customer> customers) {
        io.reactivex.Observable.just(customers).doOnSubscribe(disposable -> {
            mCustomerDao.deleteAllCustomers();
            Customer[] arr = customers.toArray(new Customer[customers.size()]);


            mCustomerDao.saveCustomers(arr);
        }).
        subscribeOn(Schedulers.io())
                .subscribe(customers1 ->
                Timber.d("data saved to db successfully"));
    }


    @Override
    public void onCleared() {
        if(!mCompositeDisposable.isDisposed())
            mCompositeDisposable.dispose();
    }

}
