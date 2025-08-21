package coding.interview.app.services;

import coding.interview.app.entities.Flight;
import coding.interview.app.entities.Passenger;
import coding.interview.app.repositories.FlightRepository;
import coding.interview.app.repositories.PassengerRepository;
import coding.interview.app.requests.UpdateFlightRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    //Added finals to imports
    private final FlightRepository flightRepository;
    private final PassengerService passengerService;

    //Added finals to parameters.
    public FlightService(final FlightRepository flightRepository,final PassengerService passengerService) {
        this.flightRepository = flightRepository;
        this.passengerService = passengerService;
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public Optional<Flight> findById(final Long id) {
        return flightRepository.findById(id);
    }

    public Flight save(final Flight flight) {
        return flight;
    }

    public Optional<Flight> updateFlight(final Long id, final UpdateFlightRequest request){
        final Optional<Flight> optionalFlight = findById(id);
        if(optionalFlight.isPresent()) {
            final Flight flight = optionalFlight.get();
            flight.setCode(request.code());
            flight.setStatus(request.status());
            flight.setDestination(request.destination());
            flight.setOrigin(request.origin());
            return Optional.of(save(flight));
        }
        return Optional.empty();
    }

    public Optional<Flight> addPassengerToFlight(final Long flightId, final Long passengerId){
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        Optional<Passenger> optionalPassenger = passengerService.findById(passengerId);
        if (optionalFlight.isPresent() && optionalPassenger.isPresent()) {
            Flight flight = optionalFlight.get();
            flight.getPassengers().add(optionalPassenger.get());
            flightRepository.save(flight);
            return Optional.of(flight);
        }
        return Optional.empty();
    }
}
