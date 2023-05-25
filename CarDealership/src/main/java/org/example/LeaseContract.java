package org.example;

public class LeaseContract extends Contract {
    public LeaseContract(String date, String customer_name, String customer_email, Vehicle vehicle) {
        super(date, customer_name, customer_email, vehicle);
    }

    public LeaseContract(String contractDate, String customerName, String customerEmail, Vehicle vehicle, double v, double v1) {
        super();
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = 0;
        double totalPrice = 0;
        vehiclePrice = getVehicle().getPrice();
        totalPrice = vehiclePrice + (vehiclePrice * 0.07);
        return totalPrice;
    }

    @Override
    public double getMonthlyPayment() {
        return 0;
    }

    @Override
    public String getPersistenceString() {
        return null;
    }
}
