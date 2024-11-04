package dat.entities;

import dat.dtos.GuideDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guide")
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;    // Jeg har valgt at lave en String her således man kan have landekode foran hvis dette bliver nødvendigt

    @Column(name = "years_of_experience", nullable = false)
    private double yearsOfExperience; // jeg har her valgt at det skal være en double hvis en guide for eksempel har 2.5 års erfaring


    @OneToMany(mappedBy = "guide", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Trip> trips = new HashSet<>();

    public Guide(String firstName, String lastName, String email, String phone, double yearsOfExperience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.yearsOfExperience = yearsOfExperience;
    }

    public Guide(GuideDTO guideDto){
        this.id = guideDto.getId();
        this.firstName = guideDto.getFirstName();
        this.lastName = guideDto.getLastName();
        this.email = guideDto.getEmail();
        this.phone = guideDto.getPhone();
        this.yearsOfExperience = guideDto.getYearsOfExperience();
    }

    public void setTrips(Set<Trip> trips) {
        if (trips != null) {
            this.trips = trips;
            for (Trip trip : trips) {
                trip.setGuide(this);
            }
        }
    }

}
