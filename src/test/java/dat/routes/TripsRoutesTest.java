package dat.routes;

import dat.config.ApplicationConfig;
import dat.config.HibernateConfig;
import dat.daos.impl.TripDAO;
import dat.dtos.TripDTO;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TripsRoutesTest {

    private static Javalin app;
    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
    private static final TripDAO plantDao = new TripDAO(emf);

    private static final String BASE_URL = "http://localhost:7080/api/trips";

    private static Populate populate = new Populate(plantDao, emf);

    private static TripDTO t1, t2, t3;

    private static List<TripDTO> trips;

    @BeforeAll
    static void init() {
        app = ApplicationConfig.startServer(7080, emf);
    }

    @BeforeEach
    void setUp() {
        trips = populate.populate3Trips();
        t1 = trips.get(0);
        t2 = trips.get(1);
        t3 = trips.get(2);
    }

    @AfterEach
    void tearDown() {
        populate.clean();
    }

    @AfterAll
    static void closeDown() {
        ApplicationConfig.stopServer(app);
    }

    @Test
    void testReadTrips() {
        TripDTO plant = given()
                .when()
                .get(BASE_URL + "/plant/{id}", t1.getId())
                .then()
                .statusCode(200)
                .extract()
                .as(TripDTO.class);
        assertEquals(t1, plant);
    }
}
