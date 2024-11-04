package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.GuideDTO;
import dat.entities.Guide;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.stream.Collectors;

public class GuideDAO implements IDAO<GuideDTO, Long> {

    private final EntityManagerFactory emf;

    public GuideDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public GuideDTO create(GuideDTO guideDTO) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Guide guide = new Guide();
            // Convert DTO to Entity
            guide.setFirstName(guideDTO.getFirstName());
            guide.setLastName(guideDTO.getLastName());
            guide.setEmail(guideDTO.getEmail());
            guide.setPhone(guideDTO.getPhone());
            guide.setYearsOfExperience(guideDTO.getYearsOfExperience());
            // Persist the guide entity
            em.persist(guide);
            em.getTransaction().commit();
            return new GuideDTO(guide); // Convert back to DTO to return
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<GuideDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Guide> query = em.createQuery("SELECT g FROM Guide g", Guide.class);
            List<Guide> guides = query.getResultList();
            // Convert entities to DTOs
            return guides.stream().map(GuideDTO::new).collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    @Override
    public GuideDTO getById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Guide guide = em.find(Guide.class, id);
            return guide != null ? new GuideDTO(guide) : null; // Convert to DTO
        } finally {
            em.close();
        }
    }

    @Override
    public GuideDTO update(GuideDTO guideDTO) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Guide guide = em.find(Guide.class, guideDTO.getId());
            if (guide != null) {
                // Update fields
                guide.setFirstName(guideDTO.getFirstName());
                guide.setLastName(guideDTO.getLastName());
                guide.setEmail(guideDTO.getEmail());
                guide.setPhone(guideDTO.getPhone());
                guide.setYearsOfExperience(guideDTO.getYearsOfExperience());
                em.getTransaction().commit();
                return new GuideDTO(guide); // Convert back to DTO
            } else {
                em.getTransaction().rollback();
                return null; // Guide not found
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
            Guide guide = em.find(Guide.class, id);
            if (guide != null) {
                em.remove(guide);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
