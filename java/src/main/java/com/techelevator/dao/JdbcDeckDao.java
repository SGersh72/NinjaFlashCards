package com.techelevator.dao;

import com.techelevator.model.Deck;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDeckDao implements DeckDao {

    public static final int PRIVATE = 1;
    public static final int UNLISTED = 2;
    public static final int PUBLIC = 3;
    private final String FULL_DECK = "SELECT d.deck_id, d.genre, d.deck_status, d.deck_name, d.deck_desc, d.owner_id FROM deck AS d ";

    private final JdbcTemplate jdbcTemplate;
    public JdbcDeckDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public Deck createDeck(Deck deck) {
        String sql = "INSERT INTO deck (owner_id, genre, deck_name, deck_desc, deck_status) VALUES(?, ?, ?, ?, ?) RETURNING deck_id;";
        Long deck_id = null; //default
        try {
            deck_id = jdbcTemplate.queryForObject(sql, Long.class, deck.getOwnerId(), deck.getGenre(),
                    deck.getDeckName(), deck.getDeckDesc(), deck.getStatus());
        } catch (DataAccessException e) {
           // return null; //if exception, return null handle null in controller
        }
        //deck.setDeckId(deck_id);
        //return deck;
        return getDeckById(deck_id);
    }

    @Override
    public Deck updateDeck(Deck deck) {
        String sql = "UPDATE deck SET deck_status = ?, deck_name = ?, deck_desc = ?, genre = ?, owner_id = ? " +
                     "WHERE deck_id = ?;";
        try {
            jdbcTemplate.update(sql, deck.getStatus(), deck.getDeckName(), deck.getDeckDesc(), deck.getGenre(), deck.getOwnerId(), deck.getDeckId());
        } catch (DataAccessException e) {
            return null;
        }
        return deck;
    }

    @Override
    public void deleteDeck(long deckId) {
        String deleteCards = "DELETE FROM card WHERE deck_id = ?;";
        jdbcTemplate.update(deleteCards, deckId);
        String deleteHistory = "DELETE FROM user_deck_history WHERE deck_id = ?;";
        jdbcTemplate.update(deleteHistory, deckId);
        String deleteDeck = "DELETE FROM deck WHERE deck_id = ?;";
        jdbcTemplate.update(deleteDeck, deckId);
    }

    @Override
    public Deck getDeckById(Long deckId) {
        Deck deck = null;
        String sql = FULL_DECK + "WHERE d.deck_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, deckId);
        if (result.next()){
            deck = mapRowToDeck(result);
        }
        return deck;
    }

    @Override
    public List<Deck> getAllDecks() {
        List<Deck> decks = new ArrayList<>();
        SqlRowSet result = jdbcTemplate.queryForRowSet(FULL_DECK);
        while (result.next()){
            decks.add(mapRowToDeck(result));
        }
        return decks;
    }

    @Override
    public List<Deck> getDecksByOwnerId(long ownerId) {
        List<Deck> decks = new ArrayList<>();
        String sql = FULL_DECK +  "WHERE d.owner_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, ownerId);
        while (result.next()){
            decks.add(mapRowToDeck(result));
        }
        return decks;
    }

    @Override
    public List<Deck> getDecksByStatusId(int status) {
        List<Deck> decks = new ArrayList<>();
        String sql = FULL_DECK +  "WHERE d.deck_status = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, status);
        while (result.next()){
            decks.add(mapRowToDeck(result));
        }
        return decks;
    }



    //Get History Methods
    private static final int GREEN = 3;
    private static final int YELLOW = 2;
    private static final int RED = 1;
    private static final int NO_HISTORY = 0;

    @Override
    public int getHistoryUserDeck(long userId, long deckId) {
        String sql = "SELECT score FROM user_deck_history WHERE user_id = ? AND deck_id = ? ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId, deckId);
        if (result.next()){
            return result.getInt("score");
        }
        return NO_HISTORY;
    }

    @Override
    public int updateUserDeckHistory(long userId, long deckId, double scorePercent) {
        String sql = "UPDATE user_deck_history SET score = ? WHERE user_id = ? AND deck_id = ?;";
        int score = getBrainColor(scorePercent);
        jdbcTemplate.update(sql, score, userId, deckId);
        return score;
    }

    @Override
    public int createUserDeckHistory(long userId, long deckId, double scorePercent) {
        String sql = "INSERT INTO user_deck_history(user_id, deck_id, score) VALUES(?, ?, ?);";
        int score = getBrainColor(scorePercent);
        jdbcTemplate.update(sql, userId, deckId, score);
        return score;
    }

    private int getBrainColor(double scorePercent) {
        int score = RED;
        if(scorePercent >= .5)
            score = YELLOW;
        if(scorePercent >=.8)
            score = GREEN;
        return score;
    }

    private Deck mapRowToDeck(SqlRowSet row) {
        Deck deck = new Deck();
        deck.setDeckId(row.getLong("deck_id"));
        deck.setStatus(row.getInt("deck_status"));
        deck.setDeckName(row.getString("deck_name"));
        deck.setDeckDesc(row.getString("deck_desc"));
        deck.setGenre(row.getString("genre"));
        deck.setOwnerId(row.getInt("owner_id"));
        return deck;
    }
}
