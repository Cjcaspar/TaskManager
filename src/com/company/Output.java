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
}
