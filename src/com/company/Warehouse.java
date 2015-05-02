package com.company;


import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


public class Warehouse extends Facility
{
    private final List<Vehicle> vehicles;
    public List<Vehicle> getVehicles() { return vehicles; }

    private final List<Client> clientsInArea;
    public List<Client> getClientsInArea() { return clientsInArea; }


    private Warehouse(int id, Point2D location, int vehiclesNumber, int vehicleCapacity)
    {
        super(id, location);
        vehicles = new ArrayList<Vehicle>();

        for (int vehicleID = 1; vehicleID <= vehiclesNumber; vehicleID++)
        {
            vehicles.add(new Vehicle(vehicleID, id, vehicleCapacity));
        }

        clientsInArea = new ArrayList<Client>();
    }

    public static Warehouse createWarehouseFromFileLine(String fileLine, int vehiclesNumber, int vehicleCapacity)
    {
        String[] currentLineValues = fileLine.split("\\s+");

        int facilityID = Integer.parseInt(currentLineValues[0]);
        Point2D facilityLocation = Facility.getFacilityLocationFromStrings(currentLineValues);

        return new Warehouse(facilityID, facilityLocation, vehiclesNumber, vehicleCapacity);

    }

    public void addClientInArea(Client client)
    {
        clientsInArea.add(client);
    }

    public int getCurrentNumberOfClientsInArea()
    {
        return clientsInArea.size();
    }

}
