package dat.daos.impl;

import dat.daos.IDAO;
import dat.daos.ITripGuideDAO;
import dat.dtos.TripDTO;
import dat.entities.Trip;
import dat.entities.Guide;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TripDAO implements IDAO<TripDTO, Long>, ITripGuideDAO<TripDTO, Long> {

    private final EntityManagerFactory emf;

    public TripDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public TripDTO create(TripDTO tripDTO) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Trip trip = new Trip();
            // Convert DTO to Entity
            trip.setName(tripDTO.getName());
            trip.setPrice(tripDTO.getPrice());
            trip.setStartTime(tripDTO.getStartTime());
            trip.setEndTime(tripDTO.getEndTime());
            trip.setStartPosition(tripDTO.getStartPosition());
            trip.setCategory(tripDTO.getCategory());
            // Persist the trip entity
            em.persist(trip);
            em.getTransaction().commit();
            return new TripDTO(trip); // Convert back to DTO to return
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<TripDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Trip> query = em.createQuery("SELECT t FROM Trip t", Trip.class);
            List<Trip> trips = query.getResultList();
            // Convert entities to DTOs
            return trips.stream().map(TripDTO::new).collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    @Override
    public TripDTO getById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Trip trip = em.find(Trip.class, id);
            return trip != null ? new TripDTO(trip) : null; // Convert to DTO
        } finally {
            em.close();
        }
    }

    @Override
    public TripDTO update(TripDTO tripDTO) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Trip trip = em.find(Trip.class, tripDTO.getId());
            if (trip != null) {
                // Update fields
                trip.setName(tripDTO.getName());
                trip.setPrice(tripDTO.getPrice());
                trip.setStartTime(tripDTO.getStartTime());
                trip.setEndTime(tripDTO.getEndTime());
                trip.setStartPosition(tripDTO.getStartPosition());
                trip.setCategory(tripDTO.getCategory());
                em.getTransaction().commit();
                return new TripDTO(trip); // Convert back to DTO
            } else {
                em.getTransaction().rollback();
                return null; // Trip not found
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Trip trip = em.find(Trip.class, id);
            if (trip != null) {
                em.remove(trip);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void addGuideToTrip(long tripId, long guideId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Trip trip = em.find(Trip.class, tripId);
            Guide guide = em.find(Guide.class, guideId);
            if (trip != null && guide != null) {
                trip.setGuide(guide); // Set the guide for the trip
                guide.getTrips().add(trip); // Update guide's trip list
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Set<TripDTO> getTripsByGuide(long guideId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Trip> query = em.createQuery("SELECT t FROM Trip t WHERE t.guide.id = :guideId", Trip.class);
            query.setParameter("guideId", guideId);
            List<Trip> trips = query.getResultList();
            // Convert entities to DTOs
            return trips.stream().map(TripDTO::new).collect(Collectors.toSet());
        } finally {
            em.close();
        }
    }
}
