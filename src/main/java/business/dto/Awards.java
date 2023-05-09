package business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Awards {

    private Integer id;

    private Integer awd;

    private Long level;

    private Long earned;

    private Long first;

}