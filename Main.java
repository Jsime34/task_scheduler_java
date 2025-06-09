import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();
        TaskScheduler scheduler = new TaskScheduler();

        while (true) { 
            System.out.println("\n1. Add task \n2. View tasks \n3. Delete task \n4. Exit");
            System.out.print("Choose: ");
            String input = scanner.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!!!! Enter a number, please.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Description: ");
                    String desc = scanner.nextLine();

                    System.out.print("DateTime (yyyy-MM-ddTHH:mm): ");
                    try {
                        LocalDateTime time = LocalDateTime.parse(scanner.nextLine());
                        Task task = new Task(title, desc, time);
                        manager.AddTask(task);
                        scheduler.schedule(task);
                        System.out.println("Task scheduled.");
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Try again.");
                    }
                }

                case 2 -> {
                    int i = 0;
                    for (Task task : manager.getTasks()) {
                        System.out.println(i++ + ". " + task);
                    }
                    if (i == 0) System.out.println("No tasks were found.");
                }

                case 3 -> {
                    System.out.print("Index to delete: ");
                    try {
                        int index = Integer.parseInt(scanner.nextLine());
                        if (index < 0 || index >= manager.getTasks().size()) {
                            System.out.println("Invalid index!");
                        } else {
                            manager.deleteTask(index);
                            System.out.println("Task was successfully deleted");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number!");
                    }
                }

                case 4 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                }

                default -> System.out.println("Invalid option. Try 1-4.");
            }
        }
    }
}
