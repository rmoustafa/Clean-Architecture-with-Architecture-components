package com.example.ramadanmoustafa.tablereservation.data.repository;

import com.example.ramadanmoustafa.tablereservation.data.entities.Table;
import com.example.ramadanmoustafa.tablereservation.data.remote.ServiceApi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.hamcrest.core.Is.is;


@RunWith(MockitoJUnitRunner.class)
public class TableRepositoryImpTest {
    @Mock
    private ServiceApi mserviceApi;
    private TableRepositoryImp tableRepositoryImp;

    @Before
    public void setUp() throws Exception {
        tableRepositoryImp =  new TableRepositoryImp(mserviceApi);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());

    }
    @Test
    public void testMapBooleanToTableItem(){
        Table table = new Table(1, true);
        Assert.assertThat(tableRepositoryImp.mapBooleanToTableItem(true), is(table));
        //Assert.assertEquals(tableRepositoryImp.mapBooleanToTableItem(true), table);
        //Mockito.when().thenReturn()

    }


    @Test
    public void validateGetTableMapRemoteApi() {
        Mockito.doReturn(Observable.just(Arrays.asList(true,false,true))).when(mserviceApi).getTableMap();
        tableRepositoryImp.getTableMap();
        Mockito.verify(mserviceApi).getTableMap();

    }

}