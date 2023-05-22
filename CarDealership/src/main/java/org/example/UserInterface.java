package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    // public static Scanner scanner = new Scanner(System.in);
    DealerShip dealerShip;
    private void init(){
        DealerShipFileManager fileManager = new DealerShipFileManager();
        this.dealerShip = fileManager.getDealership();
    }

    public void display() {
        ////Load the dealership data
        init();

        Scanner scanner = new Scanner(System.in);
        int input;
        do {



            System.out.println("\n \n Main Menu:");
            //1 Display the menu
            System.out.println("Display by Price [1]");
            System.out.println("Display by Make and Model [2]");
            System.out.println("Display by Year [3]");
            System.out.println("Display by Color [4]");
            System.out.println("Display by Mileage [5]");
            System.out.println("Display by Type [6]");
            System.out.println("Display All vehicles [7]");
            System.out.println("Add vehicle [8]");
            System.out.println("Remove vehicle [9]");
            System.out.println("Exit [0]");
            input = Integer.parseInt(scanner.nextLine());

            //2 Read user's command
            //3 switch statement that calls the correct process() method

            switch (input) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> getAllVehicleRequest();
                case 8 -> AddVehicleRequest();
                case 9 -> RemoveVehicleRequest();
                case 0 -> {
                    System.out.println("Have a nice day :)");
                    System.exit(0);
                }
                default -> System.out.println("Please enter a valid option");


            }
        } while (input != 0);


    }

    public void displayVehicles(ArrayList<Vehicle> inventory){

        for(Vehicle v :inventory) {
            System.out.printf("%-20d %-7d %-15s %-15s %-10s %-10s %-20d %-20.2f \n",
                    v.getVin(),
                    v.getYear(),
                    v.getMake(),
                    v.getModel(),
                    v.getVehicleType(),
                    v.getColor(),
                    v.getOdometer(),
                    v.getPrice());
        }

    }
    public void processGetByPriceRequest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a minimum price");
        int minprice = scanner.nextInt();
        System.out.println("Please enter a maximum price");
        int maxprice = scanner.nextInt();
        this.dealerShip.getVehiclesByPrice(minprice, maxprice);
    }

    public void processGetByMakeModelRequest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the make");
        String make = scanner.nextLine();
        System.out.println("Enter the model ");
        String model = scanner.nextLine();
        this.dealerShip.getVehiclesByMakeModel(make,model);

    }

    public void processGetByYearRequest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the minimum Year of the vehicle");
        int minYear = scanner.nextInt();
        System.out.println("Enter the maximum Year of the vehicle");
        int maxYear = scanner.nextInt();
        this.dealerShip.getVehiclesByYear(minYear, maxYear);



    }

    public void processGetByColorRequest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the color of the vehicle");
        String color = scanner.nextLine();
        this.dealerShip.getVehiclesByColor(color);


    }

    public void processGetByMileageRequest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the minimum mileage of the vehicle");
        int min = scanner.nextInt();
        System.out.println("Please enter the maximum mileage of the vehicle");
        int max = scanner.nextInt();
        this.dealerShip.getVehiclesByMileage(min, max);
    }

    public void processGetByVehicleTypeRequest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the type of the vehicle");
        String type = scanner.nextLine();
        this.dealerShip.getVehiclesByType(type);



    }

    public void getAllVehicleRequest(){
        ArrayList list = (ArrayList) dealerShip.getAllVehicles();
        displayVehicles(list);

    }
    public void AddVehicleRequest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the vin of the vehicle: ");
        int vin = scanner.nextInt();
        System.out.println("Please enter the year of the vehicle: ");
        int year = scanner.nextInt();
        System.out.println("Please enter the make of the vehicle: ");
        String make = scanner.next();
        System.out.println("Please enter the model of the vehicle: ");
        String model = scanner.next();
        System.out.println("Please enter the type of the vehicle: ");
        String vehicleType = scanner.next();
        System.out.println("Please enter the color of the vehicle: ");
        String color = scanner.next();
        System.out.println("Please enter the odometer of the vehicle: ");
        int odometer = scanner.nextInt();
        System.out.println("Please enter the price of the vehicle: ");
        double price = scanner.nextDouble();
        System.out.println(" \n Vehicle added successfully. Thank You!");
        Vehicle vehicle =  new Vehicle(vin, year, make, model, vehicleType,color,odometer, price);
        dealerShip.addVehicle(vehicle);

        DealerShipFileManager DFM = new DealerShipFileManager();
        DFM.saveDealership(dealerShip);


    }

    public void RemoveVehicleRequest(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the vin of the vehicle that you would like to remove: ");
        int vin = scanner.nextInt();
        Vehicle vehicleEntered = null;
        for(Vehicle v: dealerShip.getAllVehicles())
            if (v.getVin() == vin) {
                vehicleEntered = v;
                break;
            } if (vehicleEntered != null){
            dealerShip.removeVehicle(String.valueOf(vehicleEntered));
            System.out.println("Vehicle removed successfully. Thank You!");
            DealerShipFileManager DFM = new DealerShipFileManager();
            DFM.saveDealership(dealerShip);
        }else{
            System.out.println("No match");
        }
    }
}
