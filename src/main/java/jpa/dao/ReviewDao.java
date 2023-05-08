package jpa.dao;

import java.util.List;

public interface ReviewDao<Review> {

    void saveOrUpdate(Review review);

    Review getReview(int id);

    List<Review> getAllReview();

    void deleteReview(Review review);

    void deleteReview(int id);

}