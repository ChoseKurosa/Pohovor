package com.company;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CurrencyConverterUSD {

    private List<ExchangeRate> exchangeRates = new ArrayList<>();

    /**
     *It generates exchange rate for all possible currencies.
     */
    public void generateExchangeRate(){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int len = 3;
        iterate(chars, len, new char[len], 0);
    }

    /**
     * It iterate through the possible chars and fulfill the List of exchange rates and currencies.
     * @param chars array of possible chars
     * @param len length of the currency
     * @param build array of chars representing current currency
     * @param pos current position
     */
    private void iterate(char[] chars, int len, char[] build, int pos) {
        if (pos == len) {
            String word = new String(build);
            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setCurrency(word);
            Random random = new Random();
            exchangeRate.setRate(0.5f + random.nextFloat()*(2.5f - 0.5f));
            exchangeRates.add(exchangeRate);
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            build[pos] = chars[i];
            iterate(chars, len, build, pos + 1);
        }
    }

    /**
     * It gets the List of the exchange rates.
     * @return List of the exchange rates
     */
    public List<ExchangeRate> getExchangeRates() {
        return exchangeRates;
    }
}
