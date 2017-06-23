/*===============================================================================
* Program: K-Means implementation CS 4315 Assignment 2 Point.java
* Programmer: David Torrente (A00652464)
* Date Of Last Edit: 3/01/2016
* Description: This is the class for k-means data points. Typical getters
*   and setters.
===============================================================================*/

public class Point
{

    double xCoordinate;
    double yCoordinate;
    int cluster;

    public void setX(double x)
    {
        xCoordinate = x;
    }
    public void setY(double y)
    {
        yCoordinate = y;
    }
    public void setCluster(int c)
    {
        cluster = c;
    }

    public double getX()
    {
        return xCoordinate;
    }
    public double getY()
    {
        return yCoordinate;
    }
    public int getCluster()
    {
        return cluster;
    }
}
