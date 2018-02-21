package com.company;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Calendar;

public class TaskManager {

    Scanner input = new Scanner(System.in);
    LocalDate timeNow = LocalDate.now();
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    private List<Task> list = new ArrayList<>();


    public void mainMenu() {

        /*
        What would you like to do?
        1. add task
        2. view all tasks
        3. view uncompleted tasks
        4. view completed tasks
        5. Mark a task as completed
        6. remove a task from the Task manager
        7. edit a task in the Task Manager
        8. Details on a task in the task manager
        9. Exit
         */

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
                System.out.println("Could not read input. Please try again");
                mainMenu();
                break;
        }

    }

    private void addTask(List<Task> task) {
        Task newTask = new Task(" ", " ", " ", false, " ");
        addTaskTitle(newTask);
        addTaskDate(newTask);
        addTaskDetails(newTask);
    }

    private void viewAll() {
        for (Task title : list) {
            System.out.println((list.indexOf(title)+1) + ". " + title);
        }
    }

    private void viewCompleted() {
        int i = 1;
        for (Task task : list) {
            if (task.isComplete()) {
                System.out.println(i + ". " + task.getTitle());
                i++;
            }
        }
    }

    private void viewIncomplete() {
        int i = 1;
        for (Task task : list) {
            if (!task.isComplete()) {
                System.out.println(i + ". " + task.getTitle());
                i++;
            }
        }
    }

    private void addTaskTitle(Task task) {
        System.out.println("What is the title of your new task?\n");
        try {
            String title = input.nextLine();
            task.setTitle(title);
        } catch (Exception e) {
            System.out.println("Could not process input, please try again.");
            addTaskTitle(task);
        }
    }

    private void addTaskDate(Task task) {
        System.out.println("Add a due date:\n");
        System.out.println("What month is your task due? (number 1-12)");
        String monthInput = input.nextLine();
        System.out.println("What day of the month is your task due?");
        String dayInput = input.nextLine();
        System.out.println("What year is your task due?");
        String yearInput = input.nextLine();

        int y = Integer.parseInt( yearInput );
        int m = Integer.parseInt( monthInput );
        int d = Integer.parseInt( dayInput );

        LocalDate ld = LocalDate.of(y, m, d);

        String dateString = ld.format(dateFormat);

        task.setDueDate(dateString);

    }

    private void addTaskDetails(Task task) {
        System.out.println("Please describe your task.\n");
        try {
            String details = input.nextLine();
            task.setDetails(details);
        } catch (Exception e) {
            System.out.println("Could not process input, please try again.");
            addTaskDetails(task);
        }
    }

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
                "\nEnter a number that appears on the list above.");
        int index = -1;
        do {
            index = (input.nextInt() - 1);
        } while (index > inComplete.size() || index <= 0);

        Task complete = inComplete.get(index);

        String completeTitle = complete.getTitle();

        for (Task task : list) {
            if (task.getTitle().equals(completeTitle)) {
                task.setComplete(true);
                task.setCompleteDate(timeNow.format(dateFormat));
            }
        }

    }

    private void edit() {
        System.out.println("Which entry would you like to edit?");

        int i = 1;
        for (Task task : list) {
            System.out.println(i + ". " + task.getTitle());
            i++;
        }

        int index = input.nextInt();

        Task edit = list.get(index - 1);
        System.out.println("What do you want to edit about this entry?" +
                 "\n1. Title" +
                 "\n2. Due Date" +
                 "\n3. Task description" +
                 "\n4. return to the main menu");
         int editNum = input.nextInt();

         switch (editNum) {
             case 1:
                 System.out.println("What do you want to make your new title?");
                 edit.setTitle(input.nextLine());
                 list.set((index - 1), edit);
                 break;
             case 2:
                 System.out.println("What do you want to make your new due date? Use format MM-DD-YYYY");
                 edit.setDueDate(input.nextLine());
                 list.set((index - 1), edit);
                 break;
             case 3:
                 System.out.println("What do you want to set your task description to?");
                 edit.setDetails(input.nextLine());
                 list.set((index - 1), edit);
                 break;
             case 4:
                 mainMenu();
                 break;
             default:
                 System.out.println("Could not read input. Returning to the main menu");
         }
    }

    private void delete() {
        System.out.println("Which entry do you want to delete?");
        int i = 1;
        for (Task task : list) {
            System.out.println(i + ". " + task.getTitle());
            i++;
        }

        int index = input.nextInt();
        list.remove(index - 1);
    }

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
        " \nDue Date: " + details.getDueDate() +
        "\nDescription: " + details.getDetails() +
        "\nCompletion Date: " + details.getCompleteDate());
    }







}
