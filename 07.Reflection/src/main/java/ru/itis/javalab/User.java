package ru.itis.javalab;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private boolean isWorker;

    public User() {
        this.id = 1L;
        this.firstName = "Allaguly";
        this.lastName = "Ahmedov";
        this.isWorker = false;
    }

    public User(Long id, String firstName, String lastName, boolean isWorker) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isWorker = isWorker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isWorker() {
        return isWorker;
    }

    public void setWorker(boolean worker) {
        isWorker = worker;
    }
}

