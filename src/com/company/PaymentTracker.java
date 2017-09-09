package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PaymentTracker {

    private List<Payment> payments = new ArrayList<>();
    private String dataFromConsole;

    public void start(){
        Scanner scanInput = new Scanner(System.in);
        do {
            dataFromConsole = scanInput.nextLine();
            String parsedData[] = dataFromConsole.split(" ");
            if (parsedData[0].equals("readfile")) readFromFile(parsedData[1]); else
                if (parsedData[0].matches("\\w[A-Z]{2}")) calculate(parsedData); else {
                    System.out.println("wrong format!!");
                    showPayments();
                }

        }while (!dataFromConsole.equals("quit"));
        scanInput.close();
    }

    private void readFromFile(String fileName){

    }

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

    private void showPayments(){
        for (Payment payment: payments)
        {
            System.out.println(payment.getCurrency()+" "+payment.getAmount());
        }
    }

}
