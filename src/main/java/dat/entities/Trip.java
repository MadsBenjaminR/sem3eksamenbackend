package dat.entities;

import dat.dtos.TripDTO;
import dat.enums.Category;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "start_time", nullable = false)
    private String startTime;

    @Column(name = "end_time", nullable = false)
    private String endTime;

    @Column(name = "start_position", nullable = false)
    private String startPosition;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guide_id", nullable = false)
    private Guide guide;

    public Trip(String name, double price, String startTime, String endTime, String startPosition, Category category) {
        this.name = name;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startPosition = startPosition;
        this.category = category;
    }

    public Trip(TripDTO tripDto){
        this.id = tripDto.getId();
        this.name = tripDto.getName();
        this.price = tripDto.getPrice();
        this.startTime = tripDto.getStartTime();
        this.endTime = tripDto.getEndTime();
        this.startPosition = tripDto.getStartPosition();
        this.category = tripDto.getCategory();
    }

    public void setGuide(Guide guide) {
        this.guide = guide; // Set the guide for this trip
        if (guide != null && !guide.getTrips().contains(this)) {
            guide.getTrips().add(this); // Add this trip to the guide's set of trips if not already present
        }
    }
}
