package com.company;

import com.sun.istack.internal.Nullable;
import java.awt.geom.Point2D;


public class Client extends Facility
{
    private int demand;
    public int getDemand()
    {
        return demand;
    }


    private Integer warehouseID;
    @Nullable
    public Integer getWarehouseID() { return warehouseID; }
    public void setWarehouseID(int warehouseID) { this.warehouseID = warehouseID; }


    private Client(int id, Point2D location, int demand)
    {
        super(id, location);
        this.demand = demand;
    }


    public static Client createClientFromFileLine(String fileLine)
    {
        String[] currentLineValues = fileLine.split("\\s+");

        int facilityID = Integer.parseInt(currentLineValues[0]);
        Point2D facilityLocation = Facility.getFacilityLocationFromStrings(currentLineValues);
        int demand = Integer.parseInt(currentLineValues[3]);

        return new Client(facilityID, facilityLocation, demand);

    }

    public void setDemandToZero()
    {
        this.demand = 0;
    }
}
