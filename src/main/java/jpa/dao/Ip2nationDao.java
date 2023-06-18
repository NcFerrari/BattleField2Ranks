package jpa.dao;

import business.dto.Ip2nation;

import java.util.List;

public interface Ip2nationDao {

    void saveOrUpdate(Ip2nation ip2nation);

    Ip2nation getIp2nation(int id);

    List<Ip2nation> getAllIp2nation();

    void deleteIp2nation(Ip2nation ip2nation);

    void deleteIp2nation(int id);

}