package com.company;

class Vehicle
{
    private final int vehicleID;
    public int getVehicleID() { return vehicleID; }

    private final int warehouseID;
    public int getWarehouseID() { return warehouseID; }

    private int vehicleLoad;

    Vehicle(int vehicleID, int warehouseID, int vehicleLoad)
    {
        this.vehicleID = vehicleID;
        this.warehouseID = warehouseID;
        this.vehicleLoad = vehicleLoad;
    }

    public void visitClient(Client client) throws Exception
    {
        if(client.getDemand() < 1)
        {
            throw new Exception("Client demand less than 1.\n" +
                    "Client must have been visited already " +
                    "or client demand was incorrect at input");
        }

        if(client.getWarehouseID() == null)
        {
            throw new Exception("Client does not have a warehouse assigned to it.");
        }

        if(client.getDemand() > this.vehicleLoad)
        {
            throw new Exception("Vehicle does not have enough load do service the client.");
        }

        this.vehicleLoad -= client.getDemand();
        client.setDemandToZero();

    }

    public boolean canVisitClient(Client client)
    {
        return this.vehicleLoad >= client.getDemand();

    }

}
