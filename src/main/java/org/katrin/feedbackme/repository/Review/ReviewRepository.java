package org.katrin.feedbackme.repository.Review;

import org.katrin.feedbackme.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>, ReviewRepositoryCustom {
    List<ReviewEntity> getByRecipientId(Long recipientId);

    List<ReviewEntity> getByAuthorId(Long senderId);
}
