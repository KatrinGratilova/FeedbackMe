package org.katrin.feedbackme.repository.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.katrin.feedbackme.entity.ReviewEntity;
import org.katrin.feedbackme.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    @Transactional
    public UserEntity addReview(long id, ReviewEntity reviewEntity) {
        UserEntity recipient = entityManager.find(UserEntity.class, id);
        if (recipient == null)
            throw new EntityNotFoundException("Recipient not found for id: " + id);

        UserEntity author = entityManager.find(UserEntity.class, reviewEntity.getAuthor().getId());
        if (author == null)
            throw new EntityNotFoundException("Author not found for id: " + reviewEntity.getAuthor().getId());

        reviewEntity.setRecipient(recipient);
        recipient.getReviewsReceived().add(reviewEntity);
        reviewEntity.setAuthor(author);
        author.getReviewsWritten().add(reviewEntity);

        recipient.recalculateAvgRating();

        return entityManager.find(UserEntity.class, recipient.getId());
    }

    @Override
    @Transactional
    public UserEntity updateWithoutReviews(UserEntity userModified) {
        UserEntity userEntity = entityManager.find(userModified.getClass(), userModified.getId());
        if (userEntity == null)
            throw new EntityNotFoundException("User not found for id: " + userModified.getId());

        if (userModified.getName() != null && !userModified.getName().isEmpty())
            userEntity.setName(userModified.getName());
        if (userModified.getEmail() != null && !userModified.getEmail().isEmpty())
            userEntity.setEmail(userModified.getEmail());
        if (userModified.getPhoneNumber() != null && !userModified.getPhoneNumber().isEmpty())
            userEntity.setPhoneNumber(userModified.getPhoneNumber());
        if (userModified.getPassword() != null && !userModified.getPassword().isEmpty())
            userEntity.setPassword(userModified.getPassword());
        if (userModified.getBio() != null)
            userEntity.setBio(userModified.getBio());

        return entityManager.merge(userEntity);
    }
}
