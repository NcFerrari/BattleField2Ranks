package jpa.entity;

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
@Table(name = "maps")
public class MapsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idEntity;

    @Column(name = "mapid")
    private Short mapidEntity;

    @Column(name = "time")
    private Long timeEntity;

    @Column(name = "win")
    private Long winEntity;

    @Column(name = "loss")
    private Long lossEntity;

    @Column(name = "best")
    private Long bestEntity;

    @Column(name = "worst")
    private Long worstEntity;

}