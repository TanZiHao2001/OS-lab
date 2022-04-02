package os;

import java.util.Arrays;
import java.util.Scanner;

public class lab1 {

    /**
     * Sample Input:
     * 5
     * 0 2
     * 1 6
     * 2 4
     * 3 9
     * 6 12
     * <p>
     * Input 2:
     * 5
     * 3 6
     * 1 4
     * 5 7
     * 0 1
     * 2 4
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes/jobs: ");
        int n = sc.nextInt();
        System.out.println();
        
        int[] arr_time = new int[n];
        int[] burst_time = new int[n];
        int[] id = new int[n];
        int count = 0;
        
        //input the arrival time and burst time for every process
        while (n > 0) {
            id[count] = count;
            System.out.print("Enter arrival time for process " + (count+1) + ": ");
            arr_time[count] = sc.nextInt();
            System.out.print("Enter burst time for process " + (count+1) + ": ");
            burst_time[count] = sc.nextInt();
            count++;
            n--;
            System.out.println();
        }
        fcfs(id, arr_time, burst_time);
    }

    static void fcfs(int[] id, int[] arr_time, int[] burst_time) {

        //Insertion Sort to sort the process based on **ARRIVAL TIME**
        int n = arr_time.length;
        for (int i = 1; i < n; ++i) {
            int key = arr_time[i];
            int burst_key = burst_time[i];
            int j = i - 1;

            while (j >= 0 && arr_time[j] > key) {
                arr_time[j + 1] = arr_time[j];
                burst_time[j + 1] = burst_time[j];
                j = j - 1;
            }
            arr_time[j + 1] = key;
            burst_time[j + 1] = burst_key;
        }
        System.out.println("The arrival time for each process after sorted:");
        System.out.println(Arrays.toString(arr_time));
        System.out.println("The burst time for each process after sorted:");
        System.out.println(Arrays.toString(burst_time));
        System.out.println();
        
        int[] waiting_time = new int[n];
        int[] turnaround_time = new int[n];
        int[] completion_time = new int[n];
        
        //calculate the completion time for each process
        completion_time[0] = burst_time[0];
        for (int i = 1; i < n; i++) {
            completion_time[i] = completion_time[i - 1] + burst_time[i];
        }
        
        int sum_turnaround_time = 0;
        int sum_waiting_time = 0;
        //calculate the turnaround time and waiting time for each process and sum them up
        for (int i = 0; i < n; i++) {
            turnaround_time[i] = completion_time[i] - arr_time[i];
            waiting_time[i] = turnaround_time[i] - burst_time[i] >= 0 ? turnaround_time[i] - burst_time[i] : 0;
            sum_turnaround_time += turnaround_time[i];
            sum_waiting_time += waiting_time[i];
        }

        System.out.print("Process ID \t Arrival Time \t Burst Time "
                + "\t Completion Time \t Turnaround Time \t Waiting Time");
        System.out.println("");
        for (int i = 0; i < n; i++) {
            System.out.printf("%d \t\t %d \t\t %d \t\t %d \t\t\t %d \t\t\t %d\n",
                    (id[i]+1), arr_time[i], burst_time[i], completion_time[i], turnaround_time[i], waiting_time[i]);
        }
        
        System.out.println();
        System.out.println("The total turnaround time is " + sum_turnaround_time);
        System.out.println("The average turnaround time is " + (double)sum_turnaround_time / n);
        System.out.println("The total waiting time is " + sum_waiting_time);
        System.out.println("The average waiting time is " + (double)sum_waiting_time / n);
    }
}
            