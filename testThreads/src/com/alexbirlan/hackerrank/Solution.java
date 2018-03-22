package com.alexbirlan.hackerrank;

import java.util.Scanner;
import java.util.Stack;

public class Solution {

    static int minCost = Integer.MAX_VALUE;

    static int roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        // Complete this function
        if (c_road > c_lib) {
            return n * c_lib;
        }

//        int[][] roadsMap = new int[n][n];
//        for (int i = 0; i < cities.length; i++) {
//            int lCity = cities[i][0];
//            int rCity = cities[i][1];
//
//            roadsMap[lCity-1][rCity-1] = 1;
//        }

        boolean[] visitedCities = new boolean[n];
        Stack<Integer> route = new Stack<>();
        int minCost = 10000;
        int connectedComp = 0;
        for (int i = 0; i < cities.length; i++) {
            if (!visitedCities[i]) {
//                System.out.println("new head start from " + i);
                dfs(i, n, c_lib, c_road, c_lib, roadsMap, visitedCities, route);
                connectedComp++;
            }
        }

//        System.out.println(c_road * (n - connectedComp) + c_lib * connectedComp);


        return (c_road * (n - connectedComp) + c_lib * connectedComp);

//        return minCost;
    }

    static void dfs(int city, int noCities, int c_lib, int c_road, int currCost, int[][] roadsMap, boolean[] visitedCities, Stack<Integer> route) {
        route.push(city);
//        System.out.println(city);
//        System.out.println("|");
//        System.out.println("V");
        visitedCities[city] = true;
        for (int i = 0; i < noCities; i++) {
            if ((roadsMap[city][i] == 1) && !visitedCities[i]) {
                currCost += c_road;
                dfs(i, noCities, c_lib, c_road, currCost, roadsMap, visitedCities, route);
                currCost -= c_road;
            }
        }


//        System.out.print("Route: ");
//        System.out.println(route);
//        for (int i = 0; i < noCities; i++) {
//            Stack<Integer> newRoute = new Stack<>();
//            if (!visitedCities[i]) {
//                System.out.println("new graph from " + i);
//                currCost += c_lib;
//                dfs(i, noCities, c_lib, c_road, currCost, roadsMap, visitedCities, newRoute);
//                currCost -= c_lib;
//            }
//        }
//
//        boolean allChecked = true;
//        for (int i = 0; i < noCities; i++) {
//            if (!visitedCities[i]) {
//                allChecked = false;
//            }
//        }
//        if (allChecked) {
//            System.out.println("Cost: " + currCost);
//            if (currCost < minCost) {
//                minCost = currCost;
//            }
//
//        }
//        route.pop();
//        visitedCities[city] = false;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int n = in.nextInt();
            int m = in.nextInt();
            int c_lib = in.nextInt();
            int c_road = in.nextInt();
            int[][] cities = new int[m][2];
            for(int cities_i = 0; cities_i < m; cities_i++){
                for(int cities_j = 0; cities_j < 2; cities_j++){
                    cities[cities_i][cities_j] = in.nextInt();
                }
            }
            int result = roadsAndLibraries(n, c_lib, c_road, cities);
            System.out.println(result);
        }
        in.close();
    }
}
