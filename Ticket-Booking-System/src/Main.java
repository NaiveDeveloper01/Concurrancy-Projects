import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        int [][] seats = {
                {0,0,0},
                {0,0,0},
                {0,0,0}
        };
        Lock lock = new ReentrantLock();
        BookingSystem bookingSystem = new BookingSystem(seats,lock,9);

        Scanner sc = new Scanner(System.in);

        try (ExecutorService executorService = Executors.newCachedThreadPool()){
            while (true)
            {
                if(bookingSystem.getAvailableSeats() == 0)
                {
                    System.out.println("All tickets sold out");
                    executorService.shutdown();
                    break;
                }
                System.out.println("Do you want to book a ticket ? (Y/N)");
                String s = sc.nextLine();
                if(s.equals("Y")||s.equals("y"))
                {
                    System.out.println("Please enter the number of seats : ");
                    int count = Integer.parseInt(sc.nextLine());

                    Callable <Boolean> task = ()->{
                        return bookingSystem.bookTickets(count);
                        // System.out.println("Booked by "+Thread.currentThread().getName()+" "+bookingSystem.bookTickets(count));
                    };
                   //Future<Boolean> bookedStatus =  executorService.submit(task);
                }
                else {
                    executorService.shutdown();
                    break;
                }
            }
        }

        for (int i = 0; i < seats.length; i++) {
            System.out.println(Arrays.toString(seats[i]));
        }

    }
}