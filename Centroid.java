/*===============================================================================
* Program: K-Means implementation CS 4315 Assignment 2 Centroid.java
* Programmer: David Torrente (A00652464)
* Date Of Last Edit: 3/01/2016
* Description: This is the centroid class. Note that it contains similar code
*   to the point class and a better OOP implementation would have included
*   abstraction. However, this was not the intent of this program and as
*   such this class was intentionally modular without any additional
*   dependencies. It also includes an additional overloaded constructor
*   for various tests and completely random centroid placement.
===============================================================================*/


import java.util.Random;

public class Centroid
{
    double xCoord;
    double yCoord;
    int id;


    //overloaded constructor for completely random cluster centroid
    public Centroid(int id)
    {
        xCoord = new Random().nextInt(100);
        yCoord = new Random().nextInt(100);

        this.id = id;
    }


    //Constructor for centroid to be placed at one of the existing
    //points.
    public Centroid(double xCoord, double yCoord, int id)
    {
        this.xCoord = xCoord;
        this.yCoord = yCoord;

        this.id = id;

    }

    public void setX(double x) { xCoord = x; }
    public void setY(double y) { yCoord = y; }

    public double getX() { return xCoord; }
    public double getY() { return yCoord; }
    public int getID() { return id; }
}
