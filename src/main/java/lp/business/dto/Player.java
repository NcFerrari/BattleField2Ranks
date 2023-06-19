package lp.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private Integer id;

    private String name;

    private String country;

    private Long time;

    private Long rounds;

    private String ip;

    private Long score;

    private Long cmdscore;

    private Long skillscore;

    private Long teamscore;

    private Long kills;

    private Long deaths;

    private Long captures;

    private Long neutralizes;

    private Long captureassists;

    private Long neutralizeassists;

    private Long defends;

    private Long damageassists;

    private Long heals;

    private Long revives;

    private Long ammos;

    private Long repairs;

    private Long targetassists;

    private Long driverspecials;

    private Long driverassists;

    private Long passengerassists;

    private Long teamkills;

    private Long teamdamage;

    private Long teamvehicledamage;

    private Long suicides;

    private Long killstreak;

    private Long deathstreak;

    private Short rank;

    private Long banned;

    private Long kicked;

    private Long cmdtime;

    private Long sqltime;

    private Long sqmtime;

    private Long lwtime;

    private Long wins;

    private Long losses;

    private Short availunlocks;

    private Short usedunlocks;

    private Long joined;

    private Long rndscore;

    private Long lastonline;

    private Short chng;

    private Short decr;

    private Long mode0;

    private Long mode1;

    private Long mode2;

    private Short permban;

    private String clantag;

}