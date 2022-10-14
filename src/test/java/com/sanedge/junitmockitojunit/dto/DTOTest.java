package com.sanedge.junitmockitojunit.dto;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.meanbean.test.BeanTester;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DTOTest {

    @Test
    @DisplayName("Tests all Dto's getter setter")
    void testAllDto() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(CalculatorDto.class);
        beanTester.testBean(PropertyDto.class);
    }
}
