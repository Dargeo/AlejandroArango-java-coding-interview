package coding.interview.app.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String code;
    String origin;
    String destination;
    String status;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "flight_id"),
            name = "flight_passengers",
            inverseJoinColumns = @JoinColumn(name = "passenger_id")
    )
    List<Passenger> passengers;

    public Flight(Long id, String code, String origin, String destination, String status) {
        this.id = id;
        this.code = code;
        this.origin = origin;
        this.destination = destination;
        this.status = status;
    }

    public Flight() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
