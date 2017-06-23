/*===============================================================================
* Program: K-Means implementation CS 4315 Assignment 2 - Assignment2.java
* Programmer: David Torrente (A00652464)
* Date Of Last Edit: 3/01/2016
* Description: This is the basic interface for the k-means class.
*   It simply takes two command line arguments and passes them in
*   to the main program.
===============================================================================*/

public class Assignment2
{

    public static void main(String[] args)
    {

        int clusters = Integer.parseInt(args[0]);

        if (clusters < 1)
        {
            System.out.println("Cluster count is invalid. Defaulting to 1.");
            clusters = 1;
        }

        String fileName = args[1];

        KMeans kMeans = new KMeans(clusters, fileName);
        kMeans.run();


    }
}