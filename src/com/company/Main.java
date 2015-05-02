package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main
{

    public static void main(String[] args) throws Exception
    {

        String inputFileName = "C:\\Users\\krystian1201\\Desktop\\Coder Challange\\przyklady\\problem_0.txt";

        List<String> fileLines = readInputFileToStringList(inputFileName);

        //showRawInputFile(fileLines);

        TransportSystem transportSystem = createTransportSystemFromFileLines(fileLines);

        //transportSystem.showSituation();

        transportSystem.calculateRoutes1();

        //transportSystem.showSituation();
    }



    private static List<String> readInputFileToStringList(String inputFileName)
    {
        List<String> fileLines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFileName)))
        {

            String currentLine;

            try
            {
                while ((currentLine = bufferedReader.readLine()) != null)
                {
                    fileLines.add(currentLine);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return fileLines;
    }

    private static void showRawInputFile(List<String> fileLines)
    {
        System.out.println();

        for(String fileLine : fileLines)
        {
            System.out.println(fileLine);
        }

        System.out.println();
    }

    private static TransportSystem createTransportSystemFromFileLines(List<String> fileLines)
    {
        String firstLine = fileLines.get(0);
        String[] firstLineValues = firstLine.split("\\s+");

        final int clientsNumber =  Integer.parseInt(firstLineValues[0]);
        final int warehousesNumber = Integer.parseInt(firstLineValues[1]);
        final int vehiclesNumber = Integer.parseInt(firstLineValues[2]);
        final int vehicleCapacity = Integer.parseInt(firstLineValues[3]);


        //second line is empty
        //line with index 2 is third line
        int lineIndex = 2;

        List<Client> clients = new ArrayList<Client>();

        for(int i=0; i< clientsNumber; i++)
        {
            String fileLine = fileLines.get(lineIndex);
            Client client = Client.createClientFromFileLine(fileLine);
            clients.add(client);

            lineIndex++;
        }

        List<Warehouse> warehouses = new ArrayList<Warehouse>();

        for(int i=0; i< warehousesNumber; i++)
        {
            String fileLine = fileLines.get(lineIndex);
            Warehouse warehouse = Warehouse.createWarehouseFromFileLine(fileLine, vehiclesNumber, vehicleCapacity);
            warehouses.add(warehouse);

            lineIndex++;
        }

        //TransportSystem t = new TransportSystem(clientsNumber, warehousesNumber, vehiclesNumber, vehicleCapacity);

        return new TransportSystem(vehiclesNumber, vehicleCapacity,
                clients, warehouses);
    }




}
