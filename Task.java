import java.io.Serializable;
import java.time.LocalDateTime;

public class Task implements Serializable{
    private static final long serialVersionUID = 1L;
    private String title;
    private String description;
    private LocalDateTime time;

    public Task(String title, String description, LocalDateTime time){
        this.title = title;
        this.description = description;
        this.time = time;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public LocalDateTime getTime(){
        return time;
    }

    @Override
    public String toString(){
        return "[" + time + "]" + title + ": " + description;
    }
}