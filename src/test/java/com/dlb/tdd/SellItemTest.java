package com.dlb.tdd;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;

import org.junit.Before;
import org.junit.Test;

public class SellItemTest
{
    private SellItem _sellItem;
    private TestLcdScreen _lcdScreen;

    @Before
    public void setup()
    {
        _lcdScreen = new TestLcdScreen();
        _sellItem = new SellItem(_lcdScreen);
    }

    @Test
    public void whenReceivingCodeBar_thenAssociatedPriceIsDisplay()
    {
        _sellItem.onBarcode("000001\n\r");

        assertThat(_lcdScreen._price, is(equalTo("1.00$")));
    }

    @Test
    public void whenReceivingAnotherCodeBar_thenAssociatedPriceIsDisplay()
    {
        _sellItem.onBarcode("000002\r\n");

        assertThat(_lcdScreen._price, is(equalTo("2.00$")));
    }

    /**
     * C'est pas vraiment un test nécessaire, car j'ai réussi à le faire passer sans changer de code de production, mais je trouves que ça a de la valeur...
     */
    @Test
    public void whenReceivingMultipleCodeBar_thenPriceOfLastCodeBarIsDisplay()
    {
        _sellItem.onBarcode("000002\r\n");
        _sellItem.onBarcode("000001\n\r");

        assertThat(_lcdScreen._price, is(equalTo("1.00$")));
    }

    @Test
    public void whenReceivingInvalidCodeBar_thenErrorIsDisplay()
    {
        _sellItem.onBarcode("Invalid input\n\r");

        assertThat(_lcdScreen._price, is(equalTo("SNAFU")));
    }

    private class TestLcdScreen implements LcdScreen
    {
        private String _price;

        @Override
        public void show(final String price)
        {
            _price = price;
        }
    }
}