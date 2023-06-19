package lp.jpa.dao;

import lp.business.dto.RoundHistory;

import java.util.List;

public interface RoundHistoryDao {

    void saveOrUpdate(RoundHistory roundHistory);

    RoundHistory getRoundHistory(int id);

    List<RoundHistory> getAllRoundHistory();

    void deleteRoundHistory(RoundHistory roundHistory);

    void deleteRoundHistory(int id);

}