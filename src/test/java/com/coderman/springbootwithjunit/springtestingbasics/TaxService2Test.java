package com.coderman.springbootwithjunit.springtestingbasics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TaxService2Test {
    private  TaxService2 taxService2;

    @Before // called before every test. We want the new instance for each test method. We are using
    public void init(){
        TaxBracketService taxBracketService = new TaxBracketService();
        taxService2 = new TaxService2();
        taxService2.setTaxBracketService(taxBracketService);  // Dependency injection
    }

    @Test
    public void brackets(){
    }
}
