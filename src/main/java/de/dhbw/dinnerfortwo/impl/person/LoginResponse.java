package de.dhbw.dinnerfortwo.impl.person;

public class LoginResponse {
    private boolean success;
    private Person person;

    public LoginResponse(boolean success, Person person) {
        this.success = success;
        this.person = person;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
