package business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servers {

    private Long id;

    private String ip;

    private String prefix;

    private String name;

    private Integer port;

    private Integer queryport;

    private LocalDateTime lastupdate;

}