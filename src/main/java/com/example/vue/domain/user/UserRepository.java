package com.example.vue.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public User save(User user) {
        em.persist(user);
        return user;
    }

    public List<User> findByEmail(String email) {
        return em.createNamedQuery("findByEmail", User.class).setParameter("email", email).getResultList();
    }

}
