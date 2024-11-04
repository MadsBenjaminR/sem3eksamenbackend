package dat.routes;

import dat.controllers.impl.TripController;
import dat.daos.impl.TripDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class TripRoutes {

    private TripController tripController;

    public TripRoutes(EntityManagerFactory emf) {
        tripController = new TripController(new TripDAO(emf));
    }

    protected EndpointGroup getRoutes() {
        return () -> {
            get("/", tripController::getAll); // Get all trips
            get("/{id}", tripController::getById); // Get a trip by its id
            post("/", tripController::create); // Create a new trip
            put("/{id}", tripController::update); // Update information about a trip
            delete("/{id}", tripController::delete); // Delete a trip
            put("/{tripId}/guides/{guideId}", tripController::addGuideToTrip); // Add a guide to a trip
            post("/populate", tripController::populate); // Populate the database with trips and guides
            get("/guides/{guideId}", tripController::getTripsByGuide);
        };
    }
}
