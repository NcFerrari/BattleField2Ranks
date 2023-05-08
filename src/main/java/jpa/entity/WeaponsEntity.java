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
@Table(name = "weapons")
public class WeaponsEntity {

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

    @Column(name = "time7")
    private Long time7Entity;

    @Column(name = "time8")
    private Long time8Entity;

    @Column(name = "knifetime")
    private Long knifetimeEntity;

    @Column(name = "c4time")
    private Long c4timeEntity;

    @Column(name = "handgrenadetime")
    private Long handgrenadetimeEntity;

    @Column(name = "claymoretime")
    private Long claymoretimeEntity;

    @Column(name = "shockpadtime")
    private Long shockpadtimeEntity;

    @Column(name = "atminetime")
    private Long atminetimeEntity;

    @Column(name = "tacticaltime")
    private Long tacticaltimeEntity;

    @Column(name = "grapplinghooktime")
    private Long grapplinghooktimeEntity;

    @Column(name = "ziplinetime")
    private Long ziplinetimeEntity;

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

    @Column(name = "kills7")
    private Long kills7Entity;

    @Column(name = "kills8")
    private Long kills8Entity;

    @Column(name = "knifekills")
    private Long knifekillsEntity;

    @Column(name = "c4kills")
    private Long c4killsEntity;

    @Column(name = "handgrenadekills")
    private Long handgrenadekillsEntity;

    @Column(name = "claymorekills")
    private Long claymorekillsEntity;

    @Column(name = "shockpadkills")
    private Long shockpadkillsEntity;

    @Column(name = "atminekills")
    private Long atminekillsEntity;

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

    @Column(name = "deaths7")
    private Long deaths7Entity;

    @Column(name = "deaths8")
    private Long deaths8Entity;

    @Column(name = "knifedeaths")
    private Long knifedeathsEntity;

    @Column(name = "c4deaths")
    private Long c4deathsEntity;

    @Column(name = "handgrenadedeaths")
    private Long handgrenadedeathsEntity;

    @Column(name = "claymoredeaths")
    private Long claymoredeathsEntity;

    @Column(name = "shockpaddeaths")
    private Long shockpaddeathsEntity;

    @Column(name = "atminedeaths")
    private Long atminedeathsEntity;

    @Column(name = "ziplinedeaths")
    private Long ziplinedeathsEntity;

    @Column(name = "grapplinghookdeaths")
    private Long grapplinghookdeathsEntity;

    @Column(name = "tacticaldeployed")
    private Long tacticaldeployedEntity;

    @Column(name = "grapplinghookdeployed")
    private Long grapplinghookdeployedEntity;

    @Column(name = "ziplinedeployed")
    private Long ziplinedeployedEntity;

    @Column(name = "fired0")
    private Long fired0Entity;

    @Column(name = "fired1")
    private Long fired1Entity;

    @Column(name = "fired2")
    private Long fired2Entity;

    @Column(name = "fired3")
    private Long fired3Entity;

    @Column(name = "fired4")
    private Long fired4Entity;

    @Column(name = "fired5")
    private Long fired5Entity;

    @Column(name = "fired6")
    private Long fired6Entity;

    @Column(name = "fired7")
    private Long fired7Entity;

    @Column(name = "fired8")
    private Long fired8Entity;

    @Column(name = "knifefired")
    private Long knifefiredEntity;

    @Column(name = "c4fired")
    private Long c4firedEntity;

    @Column(name = "claymorefired")
    private Long claymorefiredEntity;

    @Column(name = "handgrenadefired")
    private Long handgrenadefiredEntity;

    @Column(name = "shockpadfired")
    private Long shockpadfiredEntity;

    @Column(name = "atminefired")
    private Long atminefiredEntity;

    @Column(name = "hit0")
    private Long hit0Entity;

    @Column(name = "hit1")
    private Long hit1Entity;

    @Column(name = "hit2")
    private Long hit2Entity;

    @Column(name = "hit3")
    private Long hit3Entity;

    @Column(name = "hit4")
    private Long hit4Entity;

    @Column(name = "hit5")
    private Long hit5Entity;

    @Column(name = "hit6")
    private Long hit6Entity;

    @Column(name = "hit7")
    private Long hit7Entity;

    @Column(name = "hit8")
    private Long hit8Entity;

    @Column(name = "knifehit")
    private Long knifehitEntity;

    @Column(name = "c4hit")
    private Long c4hitEntity;

    @Column(name = "claymorehit")
    private Long claymorehitEntity;

    @Column(name = "handgrenadehit")
    private Long handgrenadehitEntity;

    @Column(name = "shockpadhit")
    private Long shockpadhitEntity;

    @Column(name = "atminehit")
    private Long atminehitEntity;

}