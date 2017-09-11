package com.company;

import java.io.*;
import java.util.*;


public class PaymentTracker {

    private List<Payment> payments = new ArrayList<>();
    private CurrencyConverterUSD currencyConverterUSD = new CurrencyConverterUSD();

    /**
     * This method start payment tracking and also contain console input.
     */
    public void start(){
        System.out.println("Type name of the file and press ENTER or just continue by pressing ENTER!");
        Scanner scanInput = new Scanner(System.in);
        String dataFromConsole;
        Timer t = new Timer();
        dataFromConsole = scanInput.nextLine();
        if (!dataFromConsole.equals("")) readFromFile(dataFromConsole);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                showPayments();
            }
        }, 60000, 60000);
        do {

            dataFromConsole = scanInput.nextLine();
            String parsedData[] = dataFromConsole.split(" ");
            if (parsedData[0].equals("quit")) break; else
                if (parsedData[0].matches("\\w[A-Z]{2}")) calculate(parsedData); else {
                    System.out.println("Wrong currency format!");

                }

             }while (true);
        t.cancel();
        scanInput.close();
    }

    /**
     * It reads a payments  from file.
     * @param fileName name of the required file
     */
    private void readFromFile(String fileName){
        File file = new File(fileName);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String parsedData[] = line.split(" ");
                calculate(parsedData);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage()+"!");
        } catch (IOException e) {
            System.out.println(e.getMessage()+"!");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }


    }

    /**
     * It calculate payment from console input.
     * @param data line from console input
     */
    private void calculate(String[] data){
        Payment newPayment = new Payment();
        boolean isFound = false;
        for (Payment payment: payments){
            if (payment.getCurrency().equals(data[0])) {
                isFound = true;
                float newAmount = (Float.valueOf(data[1]))+payment.getAmount();
                payments.remove(payment);
                if (newAmount!= 0) {
                    newPayment.setAmount(newAmount);
                    newPayment.setCurrency(data[0]);
                    payments.add(newPayment);
                }
                break;
            }
        }
        if (!isFound) {
            newPayment.setCurrency(data[0]);
            newPayment.setAmount(Float.valueOf(data[1]));
            payments.add(newPayment);
        }
    }

    /**
     * It shows stored payments.
     */
    private void showPayments(){
        currencyConverterUSD.generateExchangeRate();
        System.out.println("----------------");
        for (Payment payment: payments)
        {
            for (ExchangeRate exchangeRate: currencyConverterUSD.getExchangeRates()) {
                if (payment.getCurrency().equals(exchangeRate.getCurrency())){
                    if (!payment.getCurrency().equals("USD"))
                        System.out.println(payment.getCurrency() + " " + String.format("%.0f", payment.getAmount())
                                + " (USD " +
                                (String.format("%.2f",exchangeRate.getRate()*payment.getAmount())).replace(",",".") + ")");
                    else System.out.println(payment.getCurrency() + " " + String.format("%.0f", payment.getAmount()));
                    break;
                }
            }
        }
    }
}
