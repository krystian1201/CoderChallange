package com.company;

import java.util.*;


class TransportSystem
{
    private final List<Client> clients;
    private final List<Warehouse> warehouses;

    private int getClientsNumber() { return clients.size(); }
    private int getWarehousesNumber() { return warehouses.size(); }

    private final int vehiclesNumber;
    //public int getVehiclesNumber() { return vehiclesNumber; }

    private final int vehicleCapacity;
    //public int getVehicleCapacity() { return vehicleCapacity; }


    TransportSystem(int vehiclesN, int vehicleCapacity,
                    List<Client> clients, List<Warehouse> warehouses)
    {

        vehiclesNumber = vehiclesN;
        this.vehicleCapacity = vehicleCapacity;

        this.clients = clients;
        this.warehouses = warehouses;
    }


    public void showSituation()
    {
        System.out.println();
        System.out.println("Number of clients: " + getClientsNumber());
        System.out.println("Number of warehouses: " + getWarehousesNumber());
        System.out.println("Number of vehicles for each warehouse: " + vehiclesNumber);
        System.out.println("Capacity of each vehicle: " + vehicleCapacity);

        System.out.println();

        System.out.println("Clients");

        for (Client client : clients)
        {
            System.out.println("id: " + client.getId() + ", location: " +
                    client.getLocation() + ", demand: " + client.getDemand());
        }

        System.out.println();
        System.out.println("Warehouses");

        for (Warehouse warehouse : warehouses)
        {
            System.out.println("id: " + warehouse.getId() + ", location: " +
                    warehouse.getLocation());


            System.out.println("    Vehicles");

            for (int i = 0; i < vehiclesNumber; i++)
            {
                Vehicle vehicle = warehouse.getVehicles().get(i);

                System.out.println("    id: " + vehicle.getVehicleID() + ", warehouse id: " +
                        vehicle.getWarehouseID());
            }

        }
    }


    public void calculateRoutes1() throws Exception
    {
        //at first - we assume that the number of clients is divisible by
        //the number of warehouses
        int numberOfClientsInArea = getClientsNumber()/getWarehousesNumber();


        for(Warehouse warehouse : warehouses)
        {
            getClientsInWarehouseAreaAllAtOnce(warehouse, numberOfClientsInArea);


            int indexOfClientToVisit = 0;

            for(Vehicle vehicle : warehouse.getVehicles())
            {

                Client clientToVisitInNextStep = warehouse.getClientsInArea().get(indexOfClientToVisit);

                while(vehicle.canVisitClient(clientToVisitInNextStep))
                {
                    Client clientToVisit = warehouse.getClientsInArea().get(indexOfClientToVisit);
                    vehicle.visitClient(clientToVisit);


                    if(indexOfClientToVisit < numberOfClientsInArea - 1)
                    {
                        indexOfClientToVisit++;
                        clientToVisitInNextStep = warehouse.getClientsInArea().get(indexOfClientToVisit);
                    }
                }

            }
        }
    }

    private void getClientsInWarehouseAreaAllAtOnce(Warehouse warehouse, int numberOfClientsInArea)
    {
        List<Client> clientsByDistanceToThisWarehouse =
                getClientsSortedByDistanceToWarehouse(warehouse);

        showClientsList(clientsByDistanceToThisWarehouse, warehouse);

        int indexOfClient = 0;

        while(warehouse.getCurrentNumberOfClientsInArea() < numberOfClientsInArea)
        {
            Client client = clientsByDistanceToThisWarehouse.get(indexOfClient);

            //if the client does not have a warehouse assigned to it
            if(client.getWarehouseID() == null)
            {
                client.setWarehouseID(warehouse.getId());
                warehouse.addClientInArea(client);
            }

            indexOfClient++;
        }
    }

    private List<Client> getClientsSortedByDistanceToWarehouse(Warehouse warehouse)
    {
        List<Client> clientsByDistanceToThisWarehouse = new ArrayList<Client>(clients);

        Comparator<Client> comparatorClientByDemand = new Comparator<Client>()
        {
            @Override
            public int compare(Client client1, Client client2)
            {
                return Double.compare(client1.distanceToOtherFacilityExact(warehouse),
                        client2.distanceToOtherFacilityExact(warehouse));
            }
        };

        clientsByDistanceToThisWarehouse.sort(comparatorClientByDemand);

        return clientsByDistanceToThisWarehouse;
    }



    private void showClientsList(List<Client> clientsList, Warehouse warehouse)
    {
        for (Client client : clientsList)
        {
            System.out.println("id: " + client.getId() + ", location: " +
                    client.getLocation() + ", demand: " + client.getDemand() +
                    ", distance from warehouse " + warehouse.getId() + ": " +
                    client.distanceToOtherFacilityExact(warehouse));
        }

    }
}
