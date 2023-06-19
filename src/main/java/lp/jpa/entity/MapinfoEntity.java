package lp.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "mapinfo")
public class MapinfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short idEntity;

    @Column(name = "name")
    private String nameEntity;

    @Column(name = "score")
    private Long scoreEntity;

    @Column(name = "time")
    private Long timeEntity;

    @Column(name = "times")
    private Long timesEntity;

    @Column(name = "kills")
    private Long killsEntity;

    @Column(name = "deaths")
    private Long deathsEntity;

    @Column(name = "custom")
    private Short customEntity;

}