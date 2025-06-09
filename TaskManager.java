import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final String filePath = "tasks.ser";
    private List<Task> tasks = new ArrayList<>();

    public TaskManager(){
        this.tasks = loadTasksFromFile();
    }

    public void AddTask(Task task){
        tasks.add(task);
        saveTasksToFile();
    }

    public List<Task> getTasks(){
        tasks.sort((t1,t2) -> t1.getTime().compareTo(t2.getTime()));
        return tasks;
    }

    public void deleteTask(int index){
        if (index>=0 && index<tasks.size()){
            tasks.remove(index);
            saveTasksToFile();
        }
    }

    private void saveTasksToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("There was an error while trying to save :/ : " + e.getMessage());
        }
    }

    private List<Task> loadTasksFromFile() {
        File file = new File(filePath);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Task>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks :(. Starting fresh.");
            return new ArrayList<>();
        }
    }
}
