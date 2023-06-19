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
@Table(name = "round_history")
public class RoundHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idEntity;

    @Column(name = "timestamp")
    private Long timestampEntity;

    @Column(name = "mapid")
    private Integer mapidEntity;

    @Column(name = "time")
    private Long timeEntity;

    @Column(name = "team1")
    private Short team1Entity;

    @Column(name = "team2")
    private Short team2Entity;

    @Column(name = "tickets1")
    private Long tickets1Entity;

    @Column(name = "tickets2")
    private Long tickets2Entity;

    @Column(name = "pids1")
    private Long pids1Entity;

    @Column(name = "pids1_end")
    private Long pids1EndEntity;

    @Column(name = "pids2")
    private Long pids2Entity;

    @Column(name = "pids2_end")
    private Long pids2EndEntity;

}