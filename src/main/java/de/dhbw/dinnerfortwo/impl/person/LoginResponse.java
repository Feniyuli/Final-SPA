package de.dhbw.dinnerfortwo.impl.person;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class LoginResponse {
    private boolean success;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
