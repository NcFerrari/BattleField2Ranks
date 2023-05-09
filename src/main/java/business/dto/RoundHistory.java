package business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundHistory {

    private Long id;

    private Long timestamp;

    private Integer mapid;

    private Long time;

    private Short team1;

    private Short team2;

    private Long tickets1;

    private Long tickets2;

    private Long pids1;

    private Long pids1End;

    private Long pids2;

    private Long pids2End;

}