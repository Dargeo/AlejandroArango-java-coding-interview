package coding.interview.app.repositories;

import coding.interview.app.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository  extends JpaRepository<Passenger, Long> {
}
