package dat.config;

import dat.entities.Guide;
import dat.entities.Trip;
import dat.enums.Category;
import jakarta.persistence.EntityManagerFactory;

import java.util.HashSet;
import java.util.Set;

public class Populator {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("tour");

        Set<Trip> beachTrips = getBeachTrips();
        Set<Trip> cityTrips = getCityTrips();
        Set<Trip> forestTrips = getForestTrips();
        Set<Trip> lakeTrips = getLakeTrips();
        Set<Trip> seaTrips = getSeaTrips();
        Set<Trip> snowTrips = getSnowTrips();

        try (var em = emf.createEntityManager()) {

            em.getTransaction().begin();

            Guide guide1 = new Guide("John", "Doe", "JohnDoe@gmail.com", "+4511223344", 2.5);
            Guide guide2 = new Guide("Jane", "Doe", "JaneDoe@gmail.com", "+4599887766", 3.5);
            Guide guide3 = new Guide("HC", "Andersen", "HCAnderson@hotmail.com", "+4512345678", 4.5);
            Guide guide4 = new Guide("AP", "Moeller", "Richman@ritchyritch.dk", "+4532165487", 5.0);
            Guide guide5 = new Guide("Jens", "Jensen", "Jensen@jsenen.jensen", "+4545454545", 4.0);
            Guide guide6 = new Guide("Lars", "Larsen", "JYST@JYST.dk", "+4588888888", 3.0);

            guide1.setTrips(beachTrips);
            guide2.setTrips(cityTrips);
            guide3.setTrips(forestTrips);
            guide4.setTrips(lakeTrips);
            guide5.setTrips(seaTrips);
            guide6.setTrips(snowTrips);

            // Persist guides, which will also persist trips due to cascade
            em.persist(guide1);
            em.persist(guide2);
            em.persist(guide3);
            em.persist(guide4);
            em.persist(guide5);
            em.persist(guide6);

            em.getTransaction().commit();
        }

    }

    private static Set<Trip> getBeachTrips() {
        Set<Trip> trips = new HashSet<>();
        trips.add(new Trip("Svanemoellen Strand", 100.5, "11:00", "15:00", "Svanemoellen station", Category.beach));
        trips.add(new Trip("Amager Strandpark", 200.5, "10:00", "16:00", "Amager Strand station", Category.beach));
        trips.add(new Trip("Hornbaek Strand", 300.5, "09:00", "17:00", "Hornbaek Strand station", Category.beach));
        return trips;
    }

    private static Set<Trip> getCityTrips() {
        Set<Trip> trips = new HashSet<>();
        trips.add(new Trip("Copenhagen", 100.5, "11:00", "15:00", "Copenhagen station", Category.city));
        trips.add(new Trip("Aarhus", 200.5, "10:00", "16:00", "Aarhus station", Category.city));
        trips.add(new Trip("Odense", 300.5, "09:00", "17:00", "Odense station", Category.city));
        return trips;
    }

    private static Set<Trip> getForestTrips() {
        Set<Trip> trips = new HashSet<>();
        trips.add(new Trip("Rold Skov", 100.5, "11:00", "15:00", "Rold Skov station", Category.forest));
        trips.add(new Trip("Gribskov", 200.5, "10:00", "16:00", "Gribskov station", Category.forest));
        trips.add(new Trip("Mols Bjerge", 300.5, "09:00", "17:00", "Mols Bjerge station", Category.forest));
        return trips;
    }

    private static Set<Trip> getLakeTrips() {
        Set<Trip> trips = new HashSet<>();
        trips.add(new Trip("Arresø", 100.5, "11:00", "15:00", "Arresø station", Category.lake));
        trips.add(new Trip("Furesø", 200.5, "10:00", "16:00", "Furesø station", Category.lake));
        trips.add(new Trip("Silkeborg Søerne", 300.5, "09:00", "17:00", "Silkeborg Søerne station", Category.lake));
        return trips;
    }

    private static Set<Trip> getSeaTrips() {
        Set<Trip> trips = new HashSet<>();
        trips.add(new Trip("Skagen", 100.5, "11:00", "15:00", "Skagen station", Category.sea));
        trips.add(new Trip("Løkken", 200.5, "10:00", "16:00", "Løkken station", Category.sea));
        trips.add(new Trip("Hirtshals", 300.5, "09:00", "17:00", "Hirtshals station", Category.sea));
        return trips;
    }

    private static Set<Trip> getSnowTrips() {
        Set<Trip> trips = new HashSet<>();
        trips.add(new Trip("Himmelbjerget", 100.5, "11:00", "15:00", "Rold Skov station", Category.snow));
        trips.add(new Trip("Toppen af Danmark", 200.5, "10:00", "16:00", "Gribskov station", Category.snow));
        trips.add(new Trip("HOFOR skibakken", 300.5, "09:00", "17:00", "Mols Bjerge station", Category.snow));
        return trips;
    }
}
