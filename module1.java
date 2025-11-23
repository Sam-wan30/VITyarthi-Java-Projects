import java.util.*;
import java.io.*;

// ================== TASK CLASS ==================
class Task {
    private String title;
    private String subject;
    private int difficulty;
    private String dueDate;
    private boolean completed;

    public Task(String title, String subject, int difficulty, String dueDate) {
        this.title = title;
        this.subject = subject;
        this.difficulty = difficulty;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public String getTitle() { return title; }
    public String getSubject() { return subject; }
    public int getDifficulty() { return difficulty; }
    public String getDueDate() { return dueDate; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean c) { this.completed = c; }

    public String serialize() {
        return title + "|" + subject + "|" + difficulty + "|" + dueDate + "|" + (completed ? "1" : "0");
    }

    public static Task deserialize(String line) {
        String[] p = line.split("\\|");
        Task t = new Task(p[0], p[1], Integer.parseInt(p[2]), p[3]);
        t.setCompleted(p[4].equals("1"));
        return t;
    }
}

// ================== FILE STORAGE ==================
class FileStorage {
    private String filename;

    public FileStorage(String filename) {
        this.filename = filename;
    }

    public void save(List<Task> tasks) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Task t : tasks) pw.println(t.serialize());
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        File f = new File(filename);
        if (!f.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null)
                list.add(Task.deserialize(line));
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
        return list;
    }
}

// ================== TASK MANAGER ==================
class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private FileStorage storage;

    public TaskManager(FileStorage storage) {
        this.storage = storage;
    }

    public void addTask(Task t) { tasks.add(t); }
    public List<Task> getTasks() { return tasks; }

    public boolean markComplete(int id) {
        if (id < 0 || id >= tasks.size()) return false;
        tasks.get(id).setCompleted(true);
        return true;
    }

    public void loadTasks() { tasks = storage.load(); }
    public void saveTasks() { storage.save(tasks); }
}

// ================== SUGGESTION ENGINE ==================
class SuggestionEngine {
    public Task suggestTask(List<Task> tasks) {
        return tasks.stream()
            .filter(t -> !t.isCompleted())
            .sorted((a, b) -> {
                if (a.getDifficulty() != b.getDifficulty())
                    return b.getDifficulty() - a.getDifficulty();
                return a.getDueDate().compareTo(b.getDueDate());
            })
            .findFirst()
            .orElse(null);
    }
}

// ================== ANALYTICS MODULE ==================
class AnalyticsModule {
    public int getCompletionRate(List<Task> tasks) {
        if (tasks.size() == 0) return 0;
        long done = tasks.stream().filter(Task::isCompleted).count();
        return (int)((done * 100) / tasks.size());
    }
}

// ================== MAIN APPLICATION ==================
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager tm = new TaskManager(new FileStorage("tasks.txt"));
        SuggestionEngine se = new SuggestionEngine();
        AnalyticsModule am = new AnalyticsModule();

        tm.loadTasks();

        while (true) {
            System.out.println("\n=== Smart Study Planner ===");
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Mark Task Complete");
            System.out.println("4. Suggest Task");
            System.out.println("5. Analytics");
            System.out.println("6. Save & Exit");
            System.out.print("Choose: ");

            int ch = Integer.parseInt(sc.nextLine());

            switch (ch) {
                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Subject: ");
                    String subject = sc.nextLine();
                    System.out.print("Difficulty (1-5): ");
                    int diff = Integer.parseInt(sc.nextLine());
                    System.out.print("Due date (YYYY-MM-DD): ");
                    String due = sc.nextLine();
                    tm.addTask(new Task(title, subject, diff, due));
                    System.out.println("Task added!");
                    break;

                case 2:
                    List<Task> tasks = tm.getTasks();
                    if (tasks.isEmpty()) System.out.println("No tasks available.");
                    else {
                        System.out.println("ID | Title | Subject | Difficulty | Due | Done");
                        for (int i = 0; i < tasks.size(); i++) {
                            Task t = tasks.get(i);
                            System.out.printf("%d | %s | %s | %d | %s | %s\n",
                                    i, t.getTitle(), t.getSubject(), t.getDifficulty(), t.getDueDate(), t.isCompleted() ? "Yes" : "No");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Task ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    if (tm.markComplete(id)) System.out.println("Marked complete.");
                    else System.out.println("Invalid task ID.");
                    break;

                case 4:
                    Task s = se.suggestTask(tm.getTasks());
                    if (s == null) System.out.println("No pending tasks.");
                    else System.out.println("Suggested: " + s.getTitle() + " (" + s.getSubject() + ")");
                    break;

                case 5:
                    int rate = am.getCompletionRate(tm.getTasks());
                    System.out.println("Completion Rate: " + rate + "%");
                    break;

                case 6:
                    tm.saveTasks();
                    System.out.println("Saved. Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
