package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class TaskManager {

    Scanner input = new Scanner(System.in); //Scanner instantiation
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    private List<Task> list = new ArrayList<>();
    Output output = new Output();

    public void mainMenu() {



        output.mainOutput();
        String choice = input.nextLine();

        switch (choice) {
            case "1":
                addTask(list);
                mainMenu();
                break;
            case "2":
                viewAll();
                mainMenu();
                break;
            case "3":
                viewIncomplete();
                mainMenu();
                break;
            case "4":
                viewCompleted();
                mainMenu();
                break;
            case "5":
                markComplete();
                mainMenu();
                break;
            case "6":
                delete();
                mainMenu();
                break;
            case "7":
                edit();
                mainMenu();
                break;
            case "8":
                details();
                mainMenu();
                break;
            case "9":
                System.exit(0);
            default:
                output.invalidInput();
                mainMenu();
                break;
        }

    } //Asks user what they would like to do.

    private void addTask(List<Task> task) {
        Task newTask = new Task(" ", " ", " ", false, " ");
        addTaskTitle(newTask);
        addTaskDate(newTask);
        addTaskDetails(newTask);
        task.add(newTask);
    } //Adds a task object to the arraylist.

    private void viewAll() {
        for (Task task : list) {
            System.out.println((list.indexOf(task)+1) + ". " + task.getTitle());
        }

       if (list.size() <= 0) {
           System.out.println("There are no tasks on record.");
        }
    } //Views all task objects in the arraylist

    private void viewCompleted() {
        int i = 1;
        for (Task task : list) {
            if (task.isComplete()) {
                System.out.println(i + ". " + task.getTitle());
                i++;
            }
        }

        if (i == 1) {
            output.noTask();
        }
    } //Views all tasks that have the boolean value "complete" == true

    private void viewIncomplete() {
        int i = 1;
        for (Task task : list) {
            if (!task.isComplete()) {
                System.out.println(i + ". " + task.getTitle());
                i++;
            }
        }
        if (i == 1) {
            output.noTask();
        }
    } //Views all tasks that have the boolean value "complete" != true

    private void addTaskTitle(Task task) {
        output.askTitle();
        try {
            String title = input.nextLine();
            task.setTitle(title);
        } catch (Exception e) {
            output.invalidInput();
            addTaskTitle(task);
        }
    } //Used by addTask() to set the Title of the created object

    private void addTaskDate(Task task) {

        output.askDate();
        String monthInput = input.nextLine();

        output.askDay();
        String dayInput = input.nextLine();

        output.askYear();
        String yearInput = input.nextLine();

        try {
            int y = Integer.parseInt(yearInput);
            int m = Integer.parseInt(monthInput);
            int d = Integer.parseInt(dayInput);

            LocalDate ld = LocalDate.of(y, m, d);
            String dateString = ld.format(dateFormat);
            task.setDueDate(dateString);
        } catch (Exception e) {
            output.invalidDate();
            addTaskDate(task);
        }
    } //Used by addTask() to set the due date of the created object

    private void addTaskDetails(Task task) {
        output.askDetails();
        try {
            String details = input.nextLine();
            task.setDetails(details);
        } catch (Exception e) {
            output.invalidInput();
            addTaskDetails(task);
        }
    } //Used by addTask() to set the description of the created object

    private void markComplete() {
        List<Task> inComplete = new ArrayList<>();
        for (Task task : list) {
            if (!task.isComplete()) {
                inComplete.add(task);
            }
        }
        int i = 1;
        for (Task task : inComplete) {
            System.out.println(i + ". " + task.getTitle());
        }

        output.askIndex();

        try {
            String indexString = input.nextLine();
            int index = (Integer.parseInt(indexString) - 1);
            Task complete = inComplete.get(index);

            String completeTitle = complete.getTitle();

            for (Task task : list) {
                if (task.getTitle().equals(completeTitle)) {
                    task.setComplete(true);
                    task.setCompleteDate(LocalDate.now().format(dateFormat));
                }
            }
            System.out.println();
        }catch (Exception e) {
            output.invalidInput();
            markComplete();
        }

    } //Sets boolean value "complete" of an object in the arraylist equal to true

    private void edit() {

        output.askIndex();

        int i = 1;
        for (Task task : list) {
            System.out.println(i + ". " + task.getTitle());
            i++;
        }
        int index = -5;
        String indexString = input.nextLine();
        try {
            index = Integer.parseInt(indexString);
        } catch (Exception e) {
            output.invalidInput();
            edit();
        }

        if (index <= 0 || index >= (list.size() + 1)) {
            output.invalidInput();
            edit();
        }

        Task edit = list.get(index - 1);

        output.editList();

         String editNum = input.nextLine();

         switch (editNum) {
             case "1":
                 output.askTitle();
                 edit.setTitle(input.nextLine());
                 list.set((index - 1), edit);
                 break;
             case "2":
                 output.askDateFormatted();
                 edit.setDueDate(input.nextLine());
                 list.set((index - 1), edit);
                 break;
             case "3":
                 output.askDetails();
                 edit.setDetails(input.nextLine());
                 list.set((index - 1), edit);
                 break;
             case "4":
                 mainMenu();
                 break;
             default:
                 output.invalidInput();
                 editAgain(edit, index);
                 break;

         }
    } //Prompts user to overwrite variables of the desired object. (title, due date, description)

    private void delete() {
        output.askIndex();
        output.cancel();
        int i = 1;
        for (Task task : list) {
            System.out.println(i + ". " + task.getTitle());
            i++;
        }
        String indexString = input.nextLine();
        if (indexString.equals("return")) {
            return;
        }
        try {
            int index = Integer.parseInt(indexString);
            list.remove(index - 1);
        } catch (Exception e) {
            output.invalidInput();
            delete();
        }
    } //Deletes an object from the arraylist

    private void details() {
        output.askIndex();

        int i = 1;
        for (Task task : list) {
            System.out.println(i + ". " + task.getTitle());
            i++;
        }

        int index = input.nextInt();

        Task details = list.get(index - 1);

        output.getDetails(details);
    } //Lists off variable values of an object.

    private void editAgain(Task edit, int index) {
       output.editList();
       String editNum = input.nextLine();

        switch (editNum) {
            case "1":
                output.askTitle();
                edit.setTitle(input.nextLine());
                list.set((index - 1), edit);
                break;
            case "2":
                output.askDateFormatted();
                edit.setDueDate(input.nextLine());
                list.set((index - 1), edit);
                break;
            case "3":
                output.askDetails();
                edit.setDetails(input.nextLine());
                list.set((index - 1), edit);
                break;
            case "4":
                mainMenu();
                break;
            default:
                output.invalidInput();
                editAgain(edit, index);
                break;
        }
    } /*Error handling method. if a user fails to select which variable they want to edit inside of edit(),
    it will ask them again, which variable they want to edit, instead of having them select their object again.*/
}
