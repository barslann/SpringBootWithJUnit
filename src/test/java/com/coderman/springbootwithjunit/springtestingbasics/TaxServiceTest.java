package com.coderman.springbootwithjunit.springtestingbasics;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
public class TaxServiceTest {
    private TaxService taxService = new TaxService();

    @Test
    public void brackets(){
        final String taxBracket = taxService.getBracket(500);
        assertThat(taxBracket).isEqualTo("LOW");
    }

    @Test
    public void allBrackets(){
        final List<String> allBrackets = taxService.allBrackets();
        assertThat(allBrackets).isNotEmpty();
        assertThat(allBrackets).contains("LOW","MEDIUM","HIGH");
    }

    @Test
    public void all(){
        assertThat("mic check 1 2 3 mic check")
                .startsWith("mic check")
                .endsWith("mic check")
                .contains("1","2","3");

        final List<String> people = Lists.newArrayList("tom","mary","elisa");

        assertThat(people)
                .doesNotContainNull()
                .containsAnyOf("mary","tom","tim")
                .doesNotContain("kiwi");


        Throwable throwable = catchThrowable(this::brackets);
        assertThat(throwable)
                .hasRootCauseInstanceOf(IOException.class)
                .hasMessage("not today");
    }
}
