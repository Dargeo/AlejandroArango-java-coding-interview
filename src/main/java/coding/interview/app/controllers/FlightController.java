package coding.interview.app.controllers;

import coding.interview.app.entities.Flight;
import coding.interview.app.entities.Passenger;
import coding.interview.app.requests.UpdateFlightRequest;
import coding.interview.app.services.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;

    /*
    *Authentication and authorization can be added to ensure that only logged staff can access certain endpoints.
    * this helps to maintain security in between petitions and prevents unauthorized modifications in the DB.
    */


    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    /**
     * Retrieves all available flights.
     @return a list of {@link Flight} objects.
      *         Returns an empty list if no flights exist.
     */
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.findAll();
    }

    /**
     *
     * @param id of the flight to be retrieved
     * @return {@link ResponseEntity} containing:
     *         - {@code 200 OK} and the {@link Flight} if found
     *         - {@code 404 Not Found} if no flight exists with the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        final Optional<Flight> optionalFlight = flightService.findById(id);
        if(optionalFlight.isPresent()) {
            return ResponseEntity.ok(optionalFlight.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *
     * @param id of the flight to be updated
     * @param request an {@link UpdateFlightRequest} containing the new flight details
     @return {@link ResponseEntity} containing:
      *         - {@code 200 OK} and the updated {@link Flight} if the update was successful
      *         - {@code 404 Not Found} if no flight exists with the given ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody UpdateFlightRequest request) {

        final Optional<Flight> optionalFlight = flightService.updateFlight(id,request);
        if(optionalFlight.isPresent()){
            return ResponseEntity.ok(optionalFlight.get());
        }
        return ResponseEntity.notFound().build();
    }


    /**
     * Adds an existing passenger to an existing flight.
     *
     * @param id of the flight to be updated with the new passenger
     * @param passengerId of the passenger to be added
     * @return {@link ResponseEntity} containing:
     *         - {@code 200 OK} and the updated {@link Flight} with the passenger added  if everything was successfull
     *         - {@code 404 Not Found} if either the flight or the passenger does not exist
     */
    @PutMapping("/{id}/passenger/{passengerId}")
    public ResponseEntity<Flight> addPassengerToFlight(@PathVariable Long id, @PathVariable Long passengerId) {
        final Optional<Flight> optionalFlight = flightService.addPassengerToFlight(id,passengerId);
        if(optionalFlight.isPresent()){
            return ResponseEntity.ok(optionalFlight.get());
        }
        return ResponseEntity.notFound().build();
    }


}
