package safe.jpa.daoimpl;

import safe.dto.Review;
import safe.jpa.dao.ReviewDao;

import java.util.List;

public class ReviewDaoImpl extends EntityManager implements ReviewDao<Review> {

    @Override
    public void saveOrUpdate(Review review) {

    }

    @Override
    public Review getReview(int id) {
        return null;
    }

    @Override
    public List<Review> getAllReview() {
        return null;
    }

    @Override
    public void deleteReview(Review review) {

    }

    @Override
    public void deleteReview(int id) {
    }

}