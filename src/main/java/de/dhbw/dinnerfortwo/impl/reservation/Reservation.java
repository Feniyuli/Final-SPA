package de.dhbw.dinnerfortwo.impl.reservation;

import de.dhbw.dinnerfortwo.impl.person.Person;
import de.dhbw.dinnerfortwo.impl.person.PersonTO;
import de.dhbw.dinnerfortwo.impl.table.Tables;
import de.dhbw.dinnerfortwo.impl.table.TablesTO;
import org.springframework.beans.BeanUtils;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private boolean arrive;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "fromTime", nullable = false)
    private Timestamp fromTime;

    @Column(name = "toTime", nullable = false)
    private Timestamp toTime;

    @Column(nullable = false)
    private Date reservationDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "guest", referencedColumnName = "id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tableReserved", referencedColumnName = "id")
    private Tables table;

    public Reservation (){
    }

    public Reservation(long id, boolean arrive, LocalDate date, Timestamp fromTime, Timestamp toTime, Date reservationDate, Person person, Tables table) {
        this.id = id;
        this.arrive = arrive;
        this.date = date;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.reservationDate = reservationDate;
        this.person = person;
        this.table = table;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Tables getTable() {
        return table;
    }

    public void setTable(Tables table) {
        this.table = table;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation reservation = (Reservation) o;
        return getId() == reservation.getId();
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId());
    }

    public ReservationTO toDTO(){
        ReservationTO reservationTO = new ReservationTO();
        BeanUtils.copyProperties( this, reservationTO);

        Person person = this.getPerson();
        PersonTO personTO = person.toDTO();

        Tables tables = this.getTable();
        TablesTO tablesTO = tables.toDTO();

        reservationTO.setPerson(personTO);
        reservationTO.setTable(tablesTO);
        return reservationTO;
    }

    public static Reservation toEntity(ReservationTO reservationTO){
        Reservation reservationToEntity = new Reservation();
        BeanUtils.copyProperties(reservationTO, reservationToEntity);

        PersonTO personTO = reservationTO.getPerson();
        Person person = Person.toEntity(personTO);

        TablesTO tablesTO = reservationTO.getTable();
        Tables tables = Tables.toEntity(tablesTO);

        reservationToEntity.setPerson(person);
        reservationToEntity.setTable(tables);
        return reservationToEntity;

    }
}