package org.example;
import java.lang.*;
import java.text.DecimalFormat;

public  class SalesContract extends Contract {
    private double salesTax;
    //private double recordingFee;
    private double processingFee;
    private boolean financed;
    public SalesContract(String date, String customer_name, String customer_email, Vehicle vehicleSold) {
        super(date, customer_name, customer_email, vehicleSold);
    }

    public SalesContract(String contractDate, String customerName, String customerEmail, Vehicle vehicle, double v, double v1, double v2, boolean financeOption) {
        super();
    }

    public double getSalesTax() {
        return salesTax;
    }

    public double getRecordingFee() {
        return 100;
    }

    public double getProcessingFee() {

        return processingFee;
    }

    public boolean isFinanced() {
        return financed;
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice= 0;
        double totalPrice=0;
        Vehicle vehicleSold = null;
        vehiclePrice= vehicleSold.getPrice();

        if(vehiclePrice < 10000){
            totalPrice = vehiclePrice +295;
        }else{
            totalPrice = vehiclePrice + 495;
        }
        totalPrice = totalPrice +100;
        totalPrice = totalPrice +(vehiclePrice * 0.05);

        return totalPrice;


    }

    @Override
    public double getMonthlyPayment() {
        Vehicle vehicleSold = null;
        double vehiclePrice= vehicleSold.getPrice();
        double p= vehiclePrice;
        double r = 0;
        double t=0;
        double n = 0;
        if(vehiclePrice >= 10000){
            r=0.0425;
            t=4;
            n=12;
        }
        double ron= r / n;
        double numerator= p * ron;
        double oneplusoverN= 1 + ron;
        double power= -t *n;
        double denominator= 1- Math.pow(oneplusoverN, power);
        double payment = numerator/ denominator;
 return payment;

    }

    @Override
    public String getPersistenceString() {
        DecimalFormat df = new DecimalFormat(".00");
        StringBuilder sb = new StringBuilder();

        sb.append("SALE|")
                .append(getDate()).append("|")
                .append(getCustomer_name()).append("|")
                .append(getCustomer_email()).append("|")
                .append(getVehicle()).append("|")
                .append(df.format(getSalesTax())).append("|")
                .append(df.format(getRecordingFee())).append("|")
                .append(df.format(getProcessingFee())).append("|")
                .append(df.format(getTotalPrice())).append("|")
                .append(isFinanced() ? "Y" : "N").append("|")
                .append(df.format(getMonthlyPayment()));
        return sb.toString();
    }
}