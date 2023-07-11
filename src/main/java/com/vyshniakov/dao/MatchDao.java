package com.vyshniakov.dao;

import com.vyshniakov.exception.MatchDaoException;
import com.vyshniakov.model.Match;
import com.vyshniakov.model.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class MatchDao {

    private final EntityManagerFactory emf;

    public MatchDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Match match) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Player player1 = getPlayerByNameOrCreateNew(match.getPlayer1().getName(), entityManager);
            Player player2 = getPlayerByNameOrCreateNew(match.getPlayer2().getName(), entityManager);
            updateMatchWithContextPlayers(match, player1, player2);
            entityManager.persist(match);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new MatchDaoException("Error performing save operation", e);
        } finally {
            entityManager.close();
        }
    }

    private void updateMatchWithContextPlayers(Match match, Player player1, Player player2) {
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        if (match.getWinner().getName().equals(player1.getName())) {
            match.setWinner(player1);
        } else {
            match.setWinner(player2);
        }
    }

    private Player getPlayerByNameOrCreateNew(String name, EntityManager entityManager) {
        return entityManager
                .createQuery("SELECT p FROM Player p WHERE p.name =: name", Player.class)
                .setParameter("name", name)
                .getResultStream()
                .findAny()
                .orElseGet(() -> new Player(name)
                );
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
            entityManager.close();
        }
    }
}
