package com.dlb.tdd;

public class SellItem
{
    private final LcdScreen _lcdScreen;

    public SellItem(final LcdScreen lcdScreen)
    {
        _lcdScreen = lcdScreen;
    }

    public void onBarcode(final String input)
    {
        final String barcode = getBarCode(input);

        /**
         *  I guess que je pourrais bouger ça dans une méthode ou sa propre classe (genre itemRepository), mais j'ai eu la flemme et je ne penses
         *  pas que c'étais le point de l'exercice anyways
         */
        if(barcode.equals("000002"))
            _lcdScreen.show("2.00$");
        else if(barcode.equals("000001"))
            _lcdScreen.show("1.00$");
        else
            _lcdScreen.show("SNAFU");
    }

    private static String getBarCode(final String input)
    {
        return input.substring(0, input.length() - 2);
    }
}
