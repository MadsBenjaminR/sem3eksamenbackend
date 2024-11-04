package dat.controllers.impl;

import dat.config.Populator;
import dat.daos.impl.TripDAO;
import dat.dtos.TripDTO;
import dat.security.exceptions.ApiException;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

public class TripController {

    private final Logger log = LoggerFactory.getLogger(TripController.class);

    private final TripDAO dao;

    public TripController(TripDAO dao) {
        this.dao = dao;
    }

    public void create(Context ctx) {
        try {
            TripDTO tripDTO = ctx.bodyAsClass(TripDTO.class);
            TripDTO createdTrip = dao.create(tripDTO);
            if (createdTrip != null) {
                ctx.res().setStatus(201); // 201 Created
                ctx.json(createdTrip);
            } else {
                ctx.res().setStatus(400); // 400 Bad Request
            }
        } catch (Exception e) {
            log.error("400 {}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }




    public void getAll(Context ctx) {
        try {
            List<TripDTO> trips = dao.getAll();
            ctx.res().setStatus(200); // 200 OK
            ctx.json(trips);
        } catch (Exception e) {
            log.error("500 {}", e.getMessage());
            throw new ApiException(500, e.getMessage());
        }
    }

    public void getById(Context ctx) {
        try {
            Long id = Long.parseLong(ctx.pathParam("id"));
            TripDTO trip = dao.getById(id);
            if (trip != null) {
                ctx.res().setStatus(200); // 200 OK
                ctx.json(trip);
            } else {
                ctx.res().setStatus(404); // 404 Not Found
            }
        } catch (NumberFormatException e) {
            log.error("400 Invalid ID format: {}", ctx.pathParam("id"));
            ctx.res().setStatus(400); // 400 Bad Request
            throw new ApiException(400, "Invalid ID format");
        } catch (Exception e) {
            log.error("500 {}", e.getMessage());
            throw new ApiException(500, e.getMessage());
        }
    }

    public void update(Context ctx) {
        try {
            TripDTO tripDTO = ctx.bodyAsClass(TripDTO.class);
            TripDTO updatedTrip = dao.update(tripDTO);
            if (updatedTrip != null) {
                ctx.res().setStatus(200); // 200 OK
                ctx.json(updatedTrip);
            } else {
                ctx.res().setStatus(404); // 404 Not Found
            }
        } catch (Exception e) {
            log.error("400 {}", e.getMessage());
            ctx.res().setStatus(400); // 400 Bad Request
            throw new ApiException(400, e.getMessage());
        }
    }

    public void delete(Context ctx) {
        try {
            Long id = Long.parseLong(ctx.pathParam("id"));
            dao.delete(id);
            ctx.res().setStatus(204); // 204 No Content
        } catch (NumberFormatException e) {
            log.error("400 Invalid ID format: {}", ctx.pathParam("id"));
            ctx.res().setStatus(400); // 400 Bad Request
            throw new ApiException(400, "Invalid ID format");
        } catch (Exception e) {
            log.error("500 {}", e.getMessage());
            throw new ApiException(500, e.getMessage());
        }
    }

    public void addGuideToTrip(Context ctx) {
        try {
            long tripId = Long.parseLong(ctx.pathParam("tripId"));
            long guideId = Long.parseLong(ctx.pathParam("guideId"));
            dao.addGuideToTrip(tripId, guideId);
            ctx.res().setStatus(200); // 200 OK
        } catch (NumberFormatException e) {
            log.error("400 Invalid ID format: {}", e.getMessage());
            ctx.res().setStatus(400); // 400 Bad Request
            throw new ApiException(400, "Invalid ID format");
        } catch (Exception e) {
            log.error("500 {}", e.getMessage());
            throw new ApiException(500, e.getMessage());
        }
    }

    public void getTripsByGuide(Context ctx) {
        try {
            long guideId = Long.parseLong(ctx.pathParam("guideId"));
            Set<TripDTO> trips = dao.getTripsByGuide(guideId);
            ctx.res().setStatus(200); // 200 OK
            ctx.json(trips);
        } catch (NumberFormatException e) {
            log.error("400 Invalid guide ID format: {}", e.getMessage());
            ctx.res().setStatus(400); // 400 Bad Request
            throw new ApiException(400, "Invalid guide ID format");
        } catch (Exception e) {
            log.error("500 {}", e.getMessage());
            throw new ApiException(500, e.getMessage());
        }
    }

    public void populate(Context ctx) {
        try {
            Populator.main(new String[]{}); // Call the main method to populate the database
            ctx.res().setStatus(201); // Created
            ctx.json("Database populated successfully");
        } catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }
}
