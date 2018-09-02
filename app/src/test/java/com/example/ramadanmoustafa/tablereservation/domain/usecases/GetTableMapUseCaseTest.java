package com.example.ramadanmoustafa.tablereservation.domain.usecases;

import com.example.ramadanmoustafa.tablereservation.data.repository.TableRepositoryImp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetTableMapUseCaseTest {

    private GetTableMapUseCase getTableMapUseCase;
    @Mock private TableRepositoryImp mocktableRepositoryImp;

    @Before
    public void setUp() throws Exception {
        getTableMapUseCase = new GetTableMapUseCase(mocktableRepositoryImp);

    }

    @Test
    public void testGetTableMap() {
        getTableMapUseCase.getTableMap();
        verify(mocktableRepositoryImp).getTableMap();
    }
}