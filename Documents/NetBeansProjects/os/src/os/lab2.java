/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import java.util.Scanner;

/**
 *
 * @author chunfang
 */
public class lab2 {
    
//5
//13
//7
//9
//4
//5    
    static void computeTime(int num, int[][] arr)
    {
        
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++){
                if(arr[j][1] > arr[j+1][1]){
                        int temp = arr[j+1][1];
                        arr[j+1][1] = arr[j][1];
                        arr[j][1] = temp;
                }
            }
        }
        
        arr[0][2] = arr[0][1];  //completion time for first process = first process's burst time
        //waiting time for first process is simply 0 so no need to calculate for it
        arr[0][4] = arr[0][2];  // turnaround time for first process is equal to completion time because we assume the arrival time for every processes are 0
        
        for (int i = 1; i < arr.length; i++){
            arr[i][2] = arr[i-1][2] + arr[i][1]; //completion time for the next process is the completion time for previous + burst time for current
            arr[i][4] = arr[i][2];               //turnaround time is the same as completion time
            arr[i][3] = arr[i][4] - arr[i][1];   //waiting time is turnaround time minus burst time
        }
    }
  
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of Process: ");
        int number = sc.nextInt();
        int[][] arr = new int[number][5];
        System.out.println();
        
        for (int i = 0; i < number; i++) {
            System.out.println("Please enter burst time for process number " + (i+1) + ":");
            System.out.print("Burst Time: ");
            arr[i][1] = sc.nextInt();
        }

        computeTime(number, arr);
        int sumWait = 0;
        int sumTurn = 0;
        System.out.println();
        System.out.println(
            "Process ID\tArrival Time\tBurst"
            + " Time\tCompletion Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < number; i++) {
            sumWait += arr[i][3];
            sumTurn += arr[i][4];
            System.out.printf("%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n", (i+1),
                0, arr[i][1], arr[i][2], arr[i][3], arr[i][4]);
        }
        
        System.out.println();
        System.out.println("The average time for waiting time is: " + (double)sumWait / number);
        System.out.println("The average time for turnaround time is: " + (double)sumTurn / number);
        
        
    }
}
