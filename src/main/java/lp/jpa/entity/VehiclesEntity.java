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
@Table(name = "vehicles")
public class VehiclesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idEntity;

    @Column(name = "time0")
    private Long time0Entity;

    @Column(name = "time1")
    private Long time1Entity;

    @Column(name = "time2")
    private Long time2Entity;

    @Column(name = "time3")
    private Long time3Entity;

    @Column(name = "time4")
    private Long time4Entity;

    @Column(name = "time5")
    private Long time5Entity;

    @Column(name = "time6")
    private Long time6Entity;

    @Column(name = "timepara")
    private Long timeparaEntity;

    @Column(name = "kills0")
    private Long kills0Entity;

    @Column(name = "kills1")
    private Long kills1Entity;

    @Column(name = "kills2")
    private Long kills2Entity;

    @Column(name = "kills3")
    private Long kills3Entity;

    @Column(name = "kills4")
    private Long kills4Entity;

    @Column(name = "kills5")
    private Long kills5Entity;

    @Column(name = "kills6")
    private Long kills6Entity;

    @Column(name = "deaths0")
    private Long deaths0Entity;

    @Column(name = "deaths1")
    private Long deaths1Entity;

    @Column(name = "deaths2")
    private Long deaths2Entity;

    @Column(name = "deaths3")
    private Long deaths3Entity;

    @Column(name = "deaths4")
    private Long deaths4Entity;

    @Column(name = "deaths5")
    private Long deaths5Entity;

    @Column(name = "deaths6")
    private Long deaths6Entity;

    @Column(name = "rk0")
    private Long rk0Entity;

    @Column(name = "rk1")
    private Long rk1Entity;

    @Column(name = "rk2")
    private Long rk2Entity;

    @Column(name = "rk3")
    private Long rk3Entity;

    @Column(name = "rk4")
    private Long rk4Entity;

    @Column(name = "rk5")
    private Long rk5Entity;

    @Column(name = "rk6")
    private Long rk6Entity;

}