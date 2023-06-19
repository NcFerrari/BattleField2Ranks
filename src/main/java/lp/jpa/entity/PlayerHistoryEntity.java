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
@Table(name = "player_history")
public class PlayerHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idEntity;

    @Column(name = "timestamp")
    private Long timestampEntity;

    @Column(name = "time")
    private Long timeEntity;

    @Column(name = "score")
    private Long scoreEntity;

    @Column(name = "cmdscore")
    private Long cmdscoreEntity;

    @Column(name = "skillscore")
    private Long skillscoreEntity;

    @Column(name = "teamscore")
    private Long teamscoreEntity;

    @Column(name = "kills")
    private Long killsEntity;

    @Column(name = "deaths")
    private Long deathsEntity;

    @Column(name = "rank")
    private Short rankEntity;

}