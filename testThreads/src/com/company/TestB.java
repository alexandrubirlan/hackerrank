package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class TestB {

    /*
    * Complete the function below.
    */
    static int howManyAgentsToAdd(int noOfCurrentAgents, int[][] callsTimes) {

        int[] neededOps = new int[callsTimes.length];
        for (int i = 0; i < callsTimes.length; i++) {
            for (int j = 0; j < callsTimes.length; j++) {
                if ((callsTimes[j][0] >= callsTimes[i][0]) && (callsTimes[j][0]) <= callsTimes[i][1]) {
                    neededOps[i]++;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < neededOps.length; i++) {
            if (neededOps[i] > max) {
                max = neededOps[i];
            }
        }
        return max - noOfCurrentAgents;

    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        final String fileName = "/home/alex/Work/Testing/hyperledger-fabric/testThreads/src/com/company/input";
        BufferedWriter bw = null;
        if (fileName != null) {
            bw = new BufferedWriter(new FileWriter(fileName));
        } else {
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        int res;
        int noOfCurrentAgents;
        noOfCurrentAgents = Integer.parseInt(in.nextLine().trim());

        int callsTimes_rows = 0;
        int callsTimes_cols = 0;
        callsTimes_rows = Integer.parseInt(in.nextLine().trim());
        callsTimes_cols = Integer.parseInt(in.nextLine().trim());

        int[][] callsTimes = new int[callsTimes_rows][callsTimes_cols];
        for (int callsTimes_i = 0; callsTimes_i < callsTimes_rows; callsTimes_i++) {
            for (int callsTimes_j = 0; callsTimes_j < callsTimes_cols; callsTimes_j++) {
                callsTimes[callsTimes_i][callsTimes_j] = in.nextInt();

            }
        }

        if (in.hasNextLine()) {
            in.nextLine();
        }

        res = howManyAgentsToAdd(noOfCurrentAgents, callsTimes);
        bw.write(String.valueOf(res));
        bw.newLine();

        bw.close();
    }
}

