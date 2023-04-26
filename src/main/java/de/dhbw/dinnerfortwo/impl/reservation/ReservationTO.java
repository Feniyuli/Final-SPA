package de.dhbw.dinnerfortwo.impl.reservation;

import de.dhbw.dinnerfortwo.impl.person.PersonTO;
import de.dhbw.dinnerfortwo.impl.table.TablesTO;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public class ReservationTO {
    private long id;
    private PersonTO person;
    private TablesTO table;
    private boolean arrive;
    private LocalDate date;
    private Timestamp fromTime;
    private Timestamp toTime;
    private Date reservationDate;

    public ReservationTO(){
    }

    public ReservationTO(long id, PersonTO person, TablesTO table, boolean arrive, LocalDate date, Timestamp fromTime, Timestamp toTime, Date reservationDate) {
        this.id = id;
        this.person = person;
        this.table = table;
        this.arrive = arrive;
        this.date = date;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.reservationDate = reservationDate;
    }

    public Timestamp getFromTime() {
        return fromTime;
    }

    public void setFromTime(Timestamp fromTime) {
        this.fromTime = fromTime;
    }

    public Timestamp getToTime() {
        return toTime;
    }

    public void setToTime(Timestamp toTime) {
        this.toTime = toTime;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonTO getPerson() {
        return person;
    }

    public void setPerson(PersonTO person) {
        this.person = person;
    }

    public TablesTO getTable() {
        return table;
    }

    public void setTable(TablesTO table) {
        this.table = table;
    }

    public boolean isArrive() {
        return arrive;
    }

    public void setArrive(boolean arrive) {
        this.arrive = arrive;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
