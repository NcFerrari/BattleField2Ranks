package jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "servers")
public class ServersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idEntity;

    @Column(name = "ip")
    private String ipEntity;

    @Column(name = "prefix")
    private String prefixEntity;

    @Column(name = "name")
    private String nameEntity;

    @Column(name = "port")
    private Integer portEntity;

    @Column(name = "queryport")
    private Integer queryportEntity;

    @Column(name = "lastupdate")
    private LocalDateTime lastupdateEntity;

}