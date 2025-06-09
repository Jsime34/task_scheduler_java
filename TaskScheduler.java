import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void schedule(Task task){
        long delay = Duration.between(LocalDateTime.now(), task.getTime()).toMillis();

        if(delay<0) return;

        scheduler.schedule(()->{
            System.out.println("Reminder of: " + task.getTitle() + " - " + task.getDescription());
        }, delay, TimeUnit.MILLISECONDS);
    }

}
