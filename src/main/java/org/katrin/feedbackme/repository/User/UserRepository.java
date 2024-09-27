package org.katrin.feedbackme.repository.User;

import org.katrin.feedbackme.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {
    UserEntity findByEmail(String email);

    List<UserEntity> findByName(String name);
}
