package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Maps {

    private Integer id;

    private Short mapid;

    private Long time;

    private Long win;

    private Long loss;

    private Long best;

    private Long worst;

}