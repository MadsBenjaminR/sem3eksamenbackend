package dat.routes;

import dat.security.routes.SecurityRoutes;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {

    private final TripRoutes tripRoutes;

    private final SecurityRoutes authRoute = new SecurityRoutes();


    public Routes(EntityManagerFactory emf){
        tripRoutes = new TripRoutes(emf);
    }
    


    public EndpointGroup getRoutes() {
        return () -> {
            path("/trips", tripRoutes.getRoutes());
            path("/auth", authRoute.getSecurityRoutes());

         /*   //fordi vi finder users favorite spices og cuisines ghkjvhbkjblkjkibnkjn
            path("/users", favoriteRoute.getRoutes());
            path("/cuisines", cuisineRoute.getRoutes());


          */
        };
    }
}