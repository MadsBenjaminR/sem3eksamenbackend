package dat.routes;

import dat.daos.impl.TripDAO;
import dat.dtos.TripDTO;
import dat.enums.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Populate {

    private static TripDAO tripDAO;
    private static EntityManagerFactory emf;

    public Populate(TripDAO tripDAO, EntityManagerFactory emf) {
        this.tripDAO = tripDAO;
        this.emf = emf;
    }

    public List<TripDTO> populate3Trips() {

        TripDTO trip1, trip2, trip3;

        // Populate data and test objects
        trip1 = new TripDTO(1L, "Trip to the Mountains", 250.0, "08:00", "17:00", "Mountain Base", Category.forest, null);
        trip2 = new TripDTO(2L, "Beach Day", 150.0, "10:00", "15:00", "Beach Front", Category.beach, null);
        trip3 = new TripDTO(3L, "City Tour", 100.0, "09:00", "18:00", "City Center", Category.city, null);

        // Save the data
        trip1 = tripDAO.create(trip1);
        trip2 = tripDAO.create(trip2);
        trip3 = tripDAO.create(trip3);

        return List.of(trip1, trip2, trip3);
    }

    public void clean() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Trip").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
