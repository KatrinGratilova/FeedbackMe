package org.katrin.feedbackme.repository.User;

import org.katrin.feedbackme.entity.ReviewEntity;
import org.katrin.feedbackme.entity.UserEntity;

public interface UserRepositoryCustom {
    UserEntity addReview(long id, ReviewEntity reviewEntity);

    UserEntity updateWithoutReviews(UserEntity userEntity);
}
