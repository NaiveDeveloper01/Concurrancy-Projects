import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter file number : ");
        }
        catch (NumberFormatException e)
        {
            throw new RuntimeException();
        }
        int num = Integer.parseInt(sc.nextLine());
        List<Callable<Boolean>> taskList = new ArrayList<>();
        int task = 0;
        File file = null;
        while (task < num){
            try {
                System.out.println("Enter file name : ");
                String fileName = sc.nextLine();
                System.out.println("Enter file size : ");
                int fileSize = Integer.parseInt(sc.nextLine());
                file = new File(fileName,fileSize);
            }catch (NumberFormatException numberFormatException)
            {
                System.out.println("Please enter valid data ");
            }

            File finalFile = file;
            taskList.add(()->{
                int i = 0;
                int completed = 0;
                while ( finalFile != null && i <= finalFile.getFileSize())
                {
                    try {

                        /*
                        * simulating the delay of downloading
                        * */
                        TimeUnit.SECONDS.sleep(random.nextInt(1,6));

                        /*
                         * file download simulation based on internet speed
                         * */
                        completed = random.nextInt(1,5);

                        i += completed;

                        finalFile.incrementDownloadPercentage(completed);
                    }
                    catch (InterruptedException interruptedException)
                    {
                        System.out.println("");
                    }
                    System.out.printf("%s download percentage : %.2f %n%n", finalFile.getFilename(), finalFile.getDownloadPercent());
                }
               // System.out.println(""+file.getFilename()+" Downloaded successfully");
                return true;
            });
            task++;
        }

        try (ExecutorService executorService = Executors.newFixedThreadPool(num)){
            executorService.invokeAll(taskList);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}