package dat.daos;

import java.util.Set;

public interface ITripGuideDAO<T, ID> {

    void addGuideToTrip(long tripId, long guideId);
    Set<T> getTripsByGuide(long guideId);

}
