package com.example.ramadanmoustafa.tablereservation.domain.usecases;

import com.example.ramadanmoustafa.tablereservation.data.repository.CustomerRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetCustomersUseCaseTest {

    private GetCustomersUseCase getCustomersUseCase;
    @Mock private CustomerRepository mockCustomerRepository;

    @Before
    public void setUp() throws Exception {
        getCustomersUseCase = new GetCustomersUseCase(mockCustomerRepository);

    }

    @Test
    public void testGetCustomers() {
        getCustomersUseCase.getCustomers(true);
        verify(mockCustomerRepository).getCustomers(true);
        //  CustomerRepository repository = Mockito.mock(CustomerRepository.class);
       // Mockito.when(repository.getCustomers()).thenReturn(io.reactivex.Observable.just(generatedCustomersList()));
    }
}