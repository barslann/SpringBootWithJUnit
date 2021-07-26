package com.coderman.springbootwithjunit.springtestingbasics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoPostProcessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TaxService2.class}) // only create TaxService bean instead of whole application context
public class TaxService3Test {

    @Autowired
    private  TaxService2 taxService2;

    @MockBean //dummy/mock service
    TaxBracketService taxBracketService;

    @Before // called before every test. We want the new instance for each test method. We are using
    public void init(){
        //dont need anymore
    }

    @Test
    public void all(){
        //Tell mock service which method to intercept and what response to return
        Mockito.when(taxBracketService.all()).thenReturn(new ArrayList<>());
        final String s = taxService2.getBracket(300);
        assertThat(s).isEqualTo("sd");
    }
}
