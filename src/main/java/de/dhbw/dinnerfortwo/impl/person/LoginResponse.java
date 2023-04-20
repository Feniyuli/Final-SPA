package de.dhbw.dinnerfortwo.impl.person;

public class LoginResponse {
    private boolean success;
    private PersonTO person;

    public LoginResponse(boolean success, PersonTO person) {
        this.success = success;
        this.person = person;
    }

    public void setPerson(PersonTO person) {
        this.person = person;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public PersonTO getPerson() {
        return person;
    }
}
