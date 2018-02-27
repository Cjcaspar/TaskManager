package com.company;

public class Output {
    public void mainOutput() {
        System.out.println("What would you like to do? " +
                "\n1. add task " +
                "\n2. view all tasks " +
                "\n3. view uncompleted tasks " +
                "\n4. view completed tasks " +
                "\n5. Mark a task as completed " +
                "\n6. remove a task from the Task manager " +
                "\n7. edit a task in the Task Manager " +
                "\n8. Details on a task in the task manager " +
                "\n9. Exit\n");
    }

    public void invalidInput() {
        System.out.println("Could not read input. Please try again");
    }

    public void invalidDate() {
        System.out.println("Could not read date format. Please try again.");
    }

    public void askDate() {
        System.out.println("Add a due date:\n");
        System.out.println("What month is your task due? (number 1-12)");
    }

    public void askDay() {
        System.out.println("What day of the month is your task due?");
    }

    public void askYear() {
        System.out.println("What year is your task due?");
    }

    public void noTask() {
        System.out.println("You have no tasks in this list.\n");
    }

    public void askTitle() {
        System.out.println("What is the new title of your task?\n");
    }

    public void askDetails() {
        System.out.println("Please describe your task.\n");
    }

    public void askIndex() {
        System.out.println("\nSelect a task from the list. \n");
    }

    public void editList() {
        System.out.println("What do you want to edit about this entry?" +
                "\n1. Title" +
                "\n2. Due Date" +
                "\n3. Task description" +
                "\n4. return to the main menu");
    }

    public void askDateFormatted() {
        System.out.println("What do you want to make your new due date? Use format MM-DD-YYYY");
    }

    public void cancel() {
        System.out.println("Type \"return\" to return to the main menu");
    }

    public void getDetails(Task details) {
        System.out.println("Title: " + details.getTitle() +
                "\nDue Date: " + details.getDueDate() +
                "\nDescription: " + details.getDetails() +
                "\nCompletion Date: " + details.getCompleteDate());
    }
}
