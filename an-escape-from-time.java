import java.util.Scanner;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Solution {

    // Read inputs, initialize graph
    public static void main(String[] args) {
        // Get inputs N, M, T
        Scanner scan = new Scanner(System.in);
        String[] input1 = scan.nextLine().split(" ");
        int N = Integer.parseInt(input1[0]);
        int M = Integer.parseInt(input1[1]);
        int T = Integer.parseInt(input1[2]);
        
        // Initialize bus stops
        Stop[] stops = new Stop[N];
        List<List<Ride>> rides = new ArrayList<List<Ride>>();
        for(int i = 0; i < stops.length; i++) {
            stops[i] = new Stop(i + 1);
            rides.add(new ArrayList<>());
        }
        
        // Initialize bus rides, separated by departing stop
        for(int i = 0; i < M; i++) {
            String[] rideInfo = scan.nextLine().split(" ");
            rides.get(Integer.parseInt(rideInfo[0]) - 1).add(
                new Ride(stops[Integer.parseInt(rideInfo[0]) - 1], stops[Integer.parseInt(rideInfo[1]) - 1], Integer.parseInt(rideInfo[2]), Integer.parseInt(rideInfo[3])));
        }
        
        // Sort bus rides by departTime
        for(int i = 0; i < rides.size(); i++) {
            Collections.sort(rides.get(i), new Comparator<Ride>() {
                public int compare(Ride r1, Ride r2) {
                    return r1.departTime == r2.departTime ? new Integer(r1.arriveTime).compareTo(r2.arriveTime) : new Integer(r1.departTime).compareTo(r2.departTime);
                }
            });
            stops[i].departingRides = rides.get(i);
        }
        
        // Compute solution
        // for(Stop stop : stops) {
        //     System.out.println(stop + "\n");
        // }
        int solution = stops[0].arrivalAt(stops[N - 1], 0, T, new ArrayList<Stop>());
        if(solution == -1) {
            System.out.print("NO");
        } else {
            System.out.print("YES " + solution);
        }
    }
}

// Stop is a node in the graph, containing a list of departing rides
class Stop {
    public int stopNum;
    public List<Ride> departingRides;

    public Stop(int stopNum) {
        this.stopNum = stopNum;
    }

    // Search for the first arrivalAt a destination stop from this one, under the problem constraints. -1 if no solution.
    public int arrivalAt(Stop destination, int currentTime, int maxTime, List<Stop> visited) {
        boolean debug = false;
        // base case: we're at the destination
        if(this == destination) {
            if(debug) System.out.println("We're at the destination! " + currentTime);
            return currentTime;
        } else {
            // try each of our departing rides in order
            // TODO: convert linear search to binary search
            if(debug) System.out.println("At stop " + this.stopNum + " at " + currentTime);
            for(Ride ride : departingRides) {
                if(debug) System.out.print("Trying ride: " + ride + "... ");
                if(ride.departTime < currentTime) {if(debug) System.out.println("Too late"); continue;}
                if(ride.departTime - currentTime > maxTime) {if(debug) System.out.println("Out of time"); break;}
                if(visited.contains(ride.arriveStop)) {if(debug) System.out.println("Already visited " + visited); continue;}
                if(debug) System.out.println("wheee");
                List<Stop> newVisited = new ArrayList<Stop>(visited);
                newVisited.add(this);
                int solution = ride.arriveStop.arrivalAt(destination, ride.arriveTime, maxTime, newVisited);
                if(solution != -1) return solution;
            }
            
            // nothing found
            return -1;
        }
    }
    
    
    public String toString() {
        return "STOP #" + stopNum + ":\n" + departingRides;
    }
}

// Ride is an edge in the graph, containing the departStop, arriveStop, departTime, arriveTime
class Ride {
    public Stop departStop;
    public Stop arriveStop;
    public int departTime;
    public int arriveTime;

    public Ride(Stop departStop, Stop arriveStop, int departTime, int arriveTime) {
        this.departStop = departStop;
        this.arriveStop = arriveStop;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
    }

    public String toString() {
        return "Ride from STOP #" + departStop.stopNum + " (" + departTime + ") to STOP #" + arriveStop.stopNum + " (" + arriveTime + ")";
    }
}
