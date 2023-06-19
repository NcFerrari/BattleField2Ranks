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
@Table(name = "awards")
public class AwardsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idEntity;

    @Column(name = "awd")
    private Integer awdEntity;

    @Column(name = "level")
    private Long levelEntity;

    @Column(name = "earned")
    private Long earnedEntity;

    @Column(name = "first")
    private Long firstEntity;

}