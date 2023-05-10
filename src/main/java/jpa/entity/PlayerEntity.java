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
@Table(name = "player")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idEntity;

    @Column(name = "name")
    private String nameEntity;

    @Column(name = "country")
    private String countryEntity;

    @Column(name = "time")
    private Long timeEntity;

    @Column(name = "rounds")
    private Long roundsEntity;

    @Column(name = "ip")
    private String ipEntity;

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

    @Column(name = "captures")
    private Long capturesEntity;

    @Column(name = "neutralizes")
    private Long neutralizesEntity;

    @Column(name = "captureassists")
    private Long captureassistsEntity;

    @Column(name = "neutralizeassists")
    private Long neutralizeassistsEntity;

    @Column(name = "defends")
    private Long defendsEntity;

    @Column(name = "damageassists")
    private Long damageassistsEntity;

    @Column(name = "heals")
    private Long healsEntity;

    @Column(name = "revives")
    private Long revivesEntity;

    @Column(name = "ammos")
    private Long ammosEntity;

    @Column(name = "repairs")
    private Long repairsEntity;

    @Column(name = "targetassists")
    private Long targetassistsEntity;

    @Column(name = "driverspecials")
    private Long driverspecialsEntity;

    @Column(name = "driverassists")
    private Long driverassistsEntity;

    @Column(name = "passengerassists")
    private Long passengerassistsEntity;

    @Column(name = "teamkills")
    private Long teamkillsEntity;

    @Column(name = "teamdamage")
    private Long teamdamageEntity;

    @Column(name = "teamvehicledamage")
    private Long teamvehicledamageEntity;

    @Column(name = "suicides")
    private Long suicidesEntity;

    @Column(name = "killstreak")
    private Long killstreakEntity;

    @Column(name = "deathstreak")
    private Long deathstreakEntity;

    @Column(name = "rank")
    private Short rankEntity;

    @Column(name = "banned")
    private Long bannedEntity;

    @Column(name = "kicked")
    private Long kickedEntity;

    @Column(name = "cmdtime")
    private Long cmdtimeEntity;

    @Column(name = "sqltime")
    private Long sqltimeEntity;

    @Column(name = "sqmtime")
    private Long sqmtimeEntity;

    @Column(name = "lwtime")
    private Long lwtimeEntity;

    @Column(name = "wins")
    private Long winsEntity;

    @Column(name = "losses")
    private Long lossesEntity;

    @Column(name = "availunlocks")
    private Short availunlocksEntity;

    @Column(name = "usedunlocks")
    private Short usedunlocksEntity;

    @Column(name = "joined")
    private Long joinedEntity;

    @Column(name = "rndscore")
    private Long rndscoreEntity;

    @Column(name = "lastonline")
    private Long lastonlineEntity;

    @Column(name = "chng")
    private Short chngEntity;

    @Column(name = "decr")
    private Short decrEntity;

    @Column(name = "mode0")
    private Long mode0Entity;

    @Column(name = "mode1")
    private Long mode1Entity;

    @Column(name = "mode2")
    private Long mode2Entity;

    @Column(name = "permban")
    private Short permbanEntity;

    @Column(name = "clantag")
    private String clantagEntity;

}