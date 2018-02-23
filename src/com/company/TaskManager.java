package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class TaskManager {

    Scanner input = new Scanner(System.in); //Scanner instantiation
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    private List<Task> list = new ArrayList<>();


    public void mainMenu() {

        Output output = new Output();

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
            System.out.println("You have no completed tasks.");
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
            System.out.println("You have no tasks that are incomplete");
        }
    } //Views all tasks that have the boolean value "complete" != true

    private void addTaskTitle(Task task) {
        System.out.println("What is the title of your new task?\n");
        try {
            String title = input.nextLine();
            task.setTitle(title);
        } catch (Exception e) {
            System.out.println("Could not process input, please try again.");
            addTaskTitle(task);
        }
    } //Used by addTask() to set the Title of the created object

    private void addTaskDate(Task task) {
        System.out.println("Add a due date:\n");
        System.out.println("What month is your task due? (number 1-12)");
        String monthInput = input.nextLine();
        System.out.println("What day of the month is your task due?");
        String dayInput = input.nextLine();
        System.out.println("What year is your task due?");
        String yearInput = input.nextLine();

        try {
            int y = Integer.parseInt(yearInput);
            int m = Integer.parseInt(monthInput);
            int d = Integer.parseInt(dayInput);

            LocalDate ld = LocalDate.of(y, m, d);
            String dateString = ld.format(dateFormat);
            task.setDueDate(dateString);
        } catch (Exception e) {
            System.out.println("Could not read date format. Please try again.");
            addTaskDate(task);
        }
    } //Used by addTask() to set the due date of the created object

    private void addTaskDetails(Task task) {
        System.out.println("Please describe your task.\n");
        try {
            String details = input.nextLine();
            task.setDetails(details);
        } catch (Exception e) {
            System.out.println("Could not process input, please try again.");
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
        System.out.println("\nWhich task would you like to mark complete?" +
                "\nEnter a number that appears on the list above.\n");

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
            System.out.println("Input does not correspond to a number on the list. Returning to Main menu.\n");
        }

    } //Sets boolean value "complete" of an object in the arraylist equal to true

    private void edit() {
        System.out.println("Which entry would you like to edit?");

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
            System.out.println("Input does not match any Task on the list.");
            edit();
        }

        if (index <= 0 || index >= (list.size() + 1)) {
            System.out.println("Input does not match any task on the list. Please try again");
            edit();
        }

        Task edit = list.get(index - 1);
        System.out.println("What do you want to edit about this entry?" +
                 "\n1. Title" +
                 "\n2. Due Date" +
                 "\n3. Task description" +
                 "\n4. return to the main menu");
         String editNum = input.nextLine();

         switch (editNum) {
             case "1":
                 System.out.println("What do you want to make your new title?");
                 edit.setTitle(input.nextLine());
                 list.set((index - 1), edit);
                 break;
             case "2":
                 System.out.println("What do you want to make your new due date? Use format MM-DD-YYYY");
                 edit.setDueDate(input.nextLine());
                 list.set((index - 1), edit);
                 break;
             case "3":
                 System.out.println("What do you want to set your task description to?");
                 edit.setDetails(input.nextLine());
                 list.set((index - 1), edit);
                 break;
             case "4":
                 mainMenu();
                 break;
             default:
                 System.out.println("Could not read input. Please try again.");
                 editAgain(edit, index);

         }
    } //Prompts user to overwrite variables of the desired object. (title, due date, description)

    private void delete() {
        System.out.println("Which entry do you want to delete? (number)" +
                "\nType \"return\" to return to the main menu");
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
            System.out.println("Could not match input to number on list. Please try again.");
            delete();
        }
    } //Deletes an object from the arraylist

    private void details() {
        System.out.println("Which task do you wish to know more about?");

        int i = 1;
        for (Task task : list) {
            System.out.println(i + ". " + task.getTitle());
            i++;
        }

        int index = input.nextInt();

        Task details = list.get(index - 1);

        System.out.println("Title: " + details.getTitle() +
        "\nDue Date: " + details.getDueDate() +
        "\nDescription: " + details.getDetails() +
        "\nCompletion Date: " + details.getCompleteDate());
    } //Lists off variable values of an object.

    private void editAgain(Task edit, int index) {
        System.out.println("What do you want to edit about this entry?" +
                "\n1. Title" +
                "\n2. Due Date" +
                "\n3. Task description" +
                "\n4. return to the main menu");
        String editNum = input.nextLine();

        switch (editNum) {
            case "1":
                System.out.println("What do you want to make your new title?");
                edit.setTitle(input.nextLine());
                list.set((index - 1), edit);
                break;
            case "2":
                System.out.println("What do you want to make your new due date? Use format MM-DD-YYYY");
                edit.setDueDate(input.nextLine());
                list.set((index - 1), edit);
                break;
            case "3":
                System.out.println("What do you want to set your task description to?");
                edit.setDetails(input.nextLine());
                list.set((index - 1), edit);
                break;
            case "4":
                mainMenu();
                break;
            default:
                //System.out.println("Could not read input. Returning to the main menu");
        }
    } /*Error handling method. if a user fails to select which variable they want to edit inside of edit(),
    it will ask them again, which variable they want to edit, instead of having them select their object again.*/

}
