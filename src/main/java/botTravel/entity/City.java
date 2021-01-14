package botTravel.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cities", schema = "bot-travel")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "city")
    private String city;

    @Column(name = "city_info")
    private String cityInfo;

}
