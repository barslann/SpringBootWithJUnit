package com.coderman.springbootwithjunit.springtestingbasics;

import org.assertj.core.util.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaxBracketService {
    public List<String> all(){
        return Lists.newArrayList("LOW","MEDIUM","HIGH");
    }
}
