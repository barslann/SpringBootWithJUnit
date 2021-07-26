package com.coderman.springbootwithjunit.springtestingbasics;

import org.junit.Test;

public class MockitoTest {
    @Test
    public void all(){
        // matchers - if this feedback is called with an argument greater than(gt) 4, then return excellent
//        Mockito.when(restaurantService.feedback(AdditionalMatchers.gt(4))).thenReturn("good");

        // to say no matter what the input use Mockito.anyString() Mockito.any()
//        Mockito.when(restaurantService.chefSpecial(Mockito.anyString(),Mockito.any(Ingredient.class))
//                  .thenReturn("special1"); // when called first time
//                  .thenReturn("special2") // when called second time


        //Verify
        //serve methodunun 2 kere cagrildigini kontrol etmek icin
        //Mockito.verify(restaurantService,Mockito.times(2)).serve();

        // captor -> captures actual parameter value which we passed when we call this method!
        //Mockito.verify(restaurantService).order(captor.capture());


    }

}
