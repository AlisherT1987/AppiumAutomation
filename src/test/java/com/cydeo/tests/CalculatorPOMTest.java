package com.cydeo.tests;

import com.cydeo.pages.CalculatorPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorPOMTest {

    CalculatorPage calculatorPage = new CalculatorPage();

    /**
     * 5 * 8 = 40
     */
    @Test
    public void multiplyTest() throws InterruptedException {
        //calculatorPage.clickSingleDigit(5);
        Thread.sleep(2000);
        calculatorPage.nine.click();
        calculatorPage.multiply.click();
        calculatorPage.clickSingleDigit(8);
        calculatorPage.equals.click();
        String result = calculatorPage.result.getText();

        System.out.println("result = " + result);

        assertEquals(40, Integer.parseInt(result) );
    }

}
