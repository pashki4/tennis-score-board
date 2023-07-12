package com.vyshniakov.dao;

import com.vyshniakov.exception.MatchDaoException;
import com.vyshniakov.model.Match;
import com.vyshniakov.model.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.List;
import java.util.function.Function;

public class MatchDao {

    public static final int RECORDS_ON_PAGE = 5;
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

    public List<Match> findAllMatchesPagination(int pageNumber) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.unwrap(Session.class).setDefaultReadOnly(true);

        entityManager.getTransaction().begin();
        try {
            TypedQuery<Match> query = entityManager.createQuery("SELECT m FROM Match m ORDER BY m.id", Match.class);
            query.setFirstResult((pageNumber - 1) * RECORDS_ON_PAGE);
            query.setMaxResults(RECORDS_ON_PAGE);
            List<Match> result = query.getResultList();
            entityManager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new MatchDaoException("Error performing findAllMatches", e);
        } finally {
            entityManager.close();
        }
    }

    public List<Match> findAllMatchesPaginationFilterByPlayerName(int page, String playerName) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.unwrap(Session.class).setDefaultReadOnly(true);
        entityManager.getTransaction().begin();
        try {
            TypedQuery<Match> query = entityManager.createQuery("SELECT m FROM Match m " +
                    "WHERE m.player1.name =: playerName OR m.player2.name =: playerName ORDER BY m.id", Match.class);
            query.setFirstResult((page - 1) * RECORDS_ON_PAGE);
            query.setMaxResults(RECORDS_ON_PAGE);
            query.setParameter("playerName", playerName);
            List<Match> result = query.getResultList();
            entityManager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new MatchDaoException("Error performing findAllMatches", e);
        } finally {
            entityManager.close();
        }
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
