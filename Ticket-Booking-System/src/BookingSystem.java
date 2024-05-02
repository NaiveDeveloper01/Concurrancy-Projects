import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BookingSystem {
    private int[][] seats;
    private Lock lock;
    private Random r;

    private volatile int availableSeats;

    public BookingSystem(int[][] seats, Lock lock, int availableSeats) {
        this.seats = seats;
        this.lock = lock;
        r = new Random();
        this.availableSeats = availableSeats;
    }

    public synchronized boolean bookTickets(int ticketNumbers) {
        if(getAvailableSeats() == 0)
        {
            System.out.println("sorry ...All tickets sold out");
            return false;
        }
        else if (getAvailableSeats() < ticketNumbers)
        {
            System.out.println("Less tickets are available");
            return false;
        }

        else {
            int t = ticketNumbers;
            try {
                /*
                 * simulates the booking process
                 * */
                TimeUnit.SECONDS.sleep(r.nextInt(5, 10));

            } catch (InterruptedException interruptedException) {

            }
            for (int row = 0; row < seats.length && ticketNumbers > 0; row++) {
                for (int col = 0; col < seats[row].length && ticketNumbers > 0; col++) {
                    if (seats[row][col] == 0) {
                        seats[row][col] = 1;
                        ticketNumbers--;
                        this.availableSeats--;
                    }
                }
            }
            System.out.println(t + " Booked");
            //this.availableSeats -= t;
            for (int i = 0; i < seats.length; i++)
                System.out.println(Arrays.toString(seats[i]));
            System.out.println();
            return true;
        }
    }

    public int getAvailableSeats() {
        return this.availableSeats;
       /* System.out.println();
        int count = 0;
        for (int row = 0; row < seats.length; row++ )
        {
            for (int col = 0; col < seats[row].length; col++) {
                System.out.print(seats[row][col]);
                if(seats[row][col] == 0)
                    count++;
            }
            System.out.println();
        }*/
        // return count;
    }
}
