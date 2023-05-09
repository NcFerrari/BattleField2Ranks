package business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mapinfo {

    private Short id;

    private String name;

    private Long score;

    private Long time;

    private Long times;

    private Long kills;

    private Long deaths;

    private Short custom;

}