package de.dhbw.dinnerfortwo.impl.reservation;

import de.dhbw.dinnerfortwo.impl.person.PersonTO;
import de.dhbw.dinnerfortwo.impl.table.Tables;
import de.dhbw.dinnerfortwo.impl.table.TablesTO;

import java.sql.Date;
import java.sql.Timestamp;

public class ReservationTO {
    private long id;
    private PersonTO person;
    private TablesTO table;
    private boolean arrive;
    private Date date;
    private Timestamp from;
    private Timestamp to;

    public ReservationTO(){
    }

    public ReservationTO (long id, PersonTO person, TablesTO table, boolean arrive, Date date, Timestamp from, Timestamp to){
        this.id = id;
        this.person = person;
        this.table = table;
        this.arrive = arrive;
        this.date = date;
        this.from = from;
        this.to = to;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }

    public Timestamp getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }
}
