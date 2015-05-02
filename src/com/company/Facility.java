package com.company;

import java.awt.*;
import java.awt.geom.Point2D;

abstract class Facility
{
    private final Point2D location;
    public Point2D getLocation()
    {
        return location;
    }

    private final int id;
    public int getId() { return id; }

    Facility(int id, Point2D location)
    {
        this.id = id;
        this.location = location;
    }

    public double distanceToOtherFacilityExact(Facility facility)
    {
        Point2D otherFacilityLocation = facility.getLocation();

        return this.location.distance(otherFacilityLocation);

    }

    static Point2D getFacilityLocationFromStrings(String[] currentLineValues)
    {
        int xLocation = Integer.parseInt(currentLineValues[1]);
        int yLocation = Integer.parseInt(currentLineValues[2]);

        return new Point(xLocation, yLocation);
    }
}
