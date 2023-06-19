package lp.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerHistory {

    private Integer id;

    private Long timestamp;

    private Long time;

    private Long score;

    private Long cmdscore;

    private Long skillscore;

    private Long teamscore;

    private Long kills;

    private Long deaths;

    private Short rank;

}