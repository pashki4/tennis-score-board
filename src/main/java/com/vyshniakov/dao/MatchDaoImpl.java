package com.vyshniakov.dao;

import com.vyshniakov.exception.MatchDaoException;
import com.vyshniakov.model.Match;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class MatchDaoImpl {
    private final EntityManagerFactory emf;

    public MatchDaoImpl() {
        emf = Persistence.createEntityManagerFactory("h2TennisScore");
    }

    public void save(Match match) {
        performWithinTx(entityManager -> entityManager.persist(match));
    }

    public List<Match> findMatchesByPlayerId(Long id) {
        return performReturningWithinTx(entityManager
                -> entityManager.createQuery("SELECT m FROM Match m WHERE m.player1.id =: id " +
                        "OR m.player2.id =: id", Match.class)
                .setParameter("id", id)
                .getResultList());
    }

    public List<Match> findAllMatches() {
        return performReturningWithinTx(entityManager
                -> entityManager.createQuery("SELECT m FROM Match m", Match.class)
                .getResultList()
        );
    }

    private void performWithinTx(Consumer<EntityManager> consumer) {
        performReturningWithinTx(entityManager -> {
            consumer.accept(entityManager);
            return null;
        });
    }

    private <T> T performReturningWithinTx(Function<EntityManager, T> function) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            T result = function.apply(entityManager);
            entityManager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new MatchDaoException("Error performing dao operation", e);
        } finally {
            entityManager.clear();
        }
    }
}
