/*===============================================================================
* Program: K-Means implementation CS 4315 Assignment 2 KMeans.java
* Programmer: David Torrente (A00652464)
* Date Of Last Edit: 3/01/2016
* Description: This is the main content of the k-means implementation
*   all k-means algorithms are included in this class.
===============================================================================*/

import java.io.File;
import java.util.Formatter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class KMeans
{

    private int clusters;

    private String fileName;

    private  ArrayList<Point> points;

    private Centroid centroid[];

    private boolean completed;

    public KMeans (int clusters, String fileName)
    {
        this.clusters = clusters;
        this.fileName = fileName;

        completed = false;

        points = new ArrayList<>();
        centroid = new Centroid[clusters];


    }

    public void run()
    {
        System.out.println("Reading data from file");
        readData();

        System.out.println("Setting up initial centroids");
        setCentroids();

        System.out.println("Reclustering data.");
        while(!completed)
        {
            completed = true;
            System.out.println("inloop");
            adjustMembership();
            repositionCenter(clusters);
        }

        System.out.println("Writing to file.");
        printToFile();

        showCentroids();

        System.out.println("Run complete.");
    }

    private void setCentroids()
    {

        double xPos;
        double yPos;

        for (int iter = 0; iter < clusters; iter++)
        {
            xPos = points.get( new Random().nextInt(points.size())).getX();
            yPos = points.get( new Random().nextInt(points.size())).getY();
            centroid[iter] = new Centroid(xPos, yPos, iter);
        }

    }

    private void readData()
    {

        Scanner inFile = null;

        Point point;

        try
        {
            inFile = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException FNFE)
        {
            System.err.println("File not found. Aborting program.");
            System.exit(1);
        }

        while (inFile.hasNext())
        {
            point = new Point();
            point.setX(inFile.nextInt());
            point.setY(inFile.nextInt());
            points.add(point);
        }

        inFile.close();
    }

    private void printToFile()
    {
        Formatter outputFile = null;
        try
        {
            outputFile = new Formatter("output.txt");
        }
        catch ( FileNotFoundException FNF)
        {
            System.out.println("There was an error creating the output file");
            System.out.println("Program terminating");
            System.exit(1);
        }

        for (int pointIter = 0; pointIter < points.size(); pointIter++)
        {
            String newLine = System.getProperty("line.separator");
            outputFile.format("%s %s" , (int)points.get(pointIter).getX(), " ");
            outputFile.format("%s %s", (int)points.get(pointIter).getY(), " ");
            outputFile.format("%s %s", (points.get(pointIter).getCluster()+1), newLine);
        }
            outputFile.close();

    }

    //This method will take all cluster members and average the x and y position.
    //It then moves the cluster centroid to the new position.
    private void repositionCenter( int centroidAmount )
    {
        double prevXPosition[] = new double[centroidAmount];
        double prevYPosition[] = new double[centroidAmount];

        double xPosition[] = new double[centroidAmount];
        double yPosition[] = new double[centroidAmount];
        int memberCount[] = new int[centroidAmount];

        //Initialize all three to 0.
        for(int initializer = 0; initializer < centroidAmount; initializer++)
        {
            xPosition[initializer] = 0;
            yPosition[initializer] = 0;
            memberCount[initializer] = 0;
        }

        //Add in all coordinates for cluster members. The value is the sum after this point.
        //While looping through, the member count is also increased.
        for(int iter = 0; iter < points.size(); iter++)
        {
            xPosition[points.get(iter).getCluster()] += points.get(iter).getX();
            yPosition[points.get(iter).getCluster()] += points.get(iter).getY();
            memberCount[points.get(iter).getCluster()]++;
        }

        //Adjust the actual position by averaging all of the values
        for(int avgIter = 0; avgIter < centroidAmount; avgIter++)
        {
            prevXPosition[avgIter] = centroid[avgIter].getX();
            prevYPosition[avgIter] = centroid[avgIter].getY();

            if (memberCount[avgIter] > 0)
            {
                centroid[avgIter].setX(xPosition[avgIter] / memberCount[avgIter]);
                centroid[avgIter].setY(yPosition[avgIter] / memberCount[avgIter]);
            }
        }

        for(int checkIter = 0; checkIter < centroidAmount; checkIter++)
        {
            if (prevXPosition[checkIter] != centroid[checkIter].getX()
                    || prevYPosition[checkIter] != centroid[checkIter].getY())
                completed = false;
        }
        //At this point, the centroids are repositioned.
    }

    private void adjustMembership()
    {
        double distance;

        //Compare distance and assign point to nearest cluster.
        //Note that the inner loop starts @ 1 as the point is initialized to 0 already.
        for(int pointIter = 0; pointIter < points.size(); pointIter++)
        {
            //Assign membership to first cluster and set distance.
            points.get(pointIter).setCluster(0);
            distance = distance(points.get(pointIter), centroid[0]);

            for (int centroidIter = 1; centroidIter < clusters; centroidIter++)
            {
                if (distance > distance(points.get(pointIter), centroid[centroidIter]))
                {
                    distance = distance(points.get(pointIter), centroid[centroidIter]);
                    points.get(pointIter).setCluster(centroidIter);
                }
            }
        }
    }

    private double distance(Point p, Centroid c)
    {
        //While the distance could have been returned without squaring, it is squared in order
        //to be consistent with what would typically be considered the distance formula.
        return  Math.sqrt(Math.pow(p.getX()-c.getX(), 2)+ Math.pow(p.getY()-c.getY(),2));
    }

    private void showCentroids()
    {
        System.out.println("Final centroid locations are:");

        for (int centroidIter = 0; centroidIter < clusters; centroidIter++)
        {
            System.out.println(centroid[centroidIter].getID()+1);
            System.out.println((int)centroid[centroidIter].getX());
            System.out.println((int)centroid[centroidIter].getY());
        }
    }


}


