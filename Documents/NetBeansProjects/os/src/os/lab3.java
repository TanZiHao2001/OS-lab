/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;
import java.util.Scanner;
/**
 *
 * @author User
 */
public class lab3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //create a hard disk with 200 blocks of memory, 0 = available, 1 = stored
        int[] block_memory = new int[200];
        
        System.out.println("Welcome! Please enter the starting block and also the file length to store a file.");
        System.out.println();
        System.out.print("Enter the starting block: ");
        int start_block = sc.nextInt();
        System.out.print("Enter the length of file: ");
        int file_length = sc.nextInt();
        System.out.println();
        
        while(true){
            boolean available_blocks = true;
            for(int i = start_block; i < start_block + file_length; i++){
                if(block_memory[i] != 0){
                    available_blocks = false;
                    break;
                }
            }
            if(available_blocks){
                for(int i = start_block; i < start_block + file_length; i++){
                    block_memory[i] = 1;
                    System.out.println("Block " + i + " is now stored");
                }
                System.out.println("\nThe file is now stored inside the disk");
                System.out.println();
            }
            else{
                System.out.println("\nThe file is not stored due to some blocks allocated with other files.");
                System.out.println();
            }
            
            System.out.println("This is the memory allocation of the hard disk thus far:");
            for(int i = 0; i < block_memory.length; i++){
                if((i + 1) % 20 == 0){
                    System.out.print(block_memory[i] + "\n");
                }
                else{
                    System.out.print(block_memory[i] + " ");
                }
            }
            System.out.println();
            
            System.out.println("Do you wish to store more files? Press Y/y to continue, press N/n to stop.");
            System.out.print("Enter your choice: ");
            char choice = sc.next().charAt(0);
            System.out.println();
            
            if(choice != 'y' && choice != 'Y'){
                break;
            }
            else{
                System.out.print("Enter the new starting block: ");
                start_block = sc.nextInt();
                System.out.print("Enter the new file length: ");
                file_length = sc.nextInt();
            }
        }
    }
}
