package jpa.dao;

import java.util.List;

public interface VersionDao<Version> {

    void saveOrUpdate(Version version);

    Version getVersion(int id);

    List<Version> getAllVersion();

    void deleteVersion(Version version);

    void deleteVersion(int id);

}