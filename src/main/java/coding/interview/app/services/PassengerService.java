package coding.interview.app.services;

import coding.interview.app.entities.Passenger;
import coding.interview.app.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassengerService{
    private final PassengerRepository passengerRepository;


    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public Optional<Passenger> findById(Long id) {
        return passengerRepository.findById(id);
    }

    public Passenger save(Passenger passenger) {
        return passengerRepository.save(passenger);
    }
}
