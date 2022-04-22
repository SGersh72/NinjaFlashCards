package com.techelevator.dao;

import com.techelevator.model.Card;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCardDao implements CardDao{

    private  final JdbcTemplate jdbcTemplate;

    public JdbcCardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Card> getAllCards(long deck_id) {
        List<Card> cardList = new ArrayList<>();

        String sql = "SELECT card_id, question, answer, image_url, deck_id " +
                "FROM card WHERE deck_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, deck_id);
        while (results.next()) {
            cardList.add(mapToRowCard(results));
        }
        return cardList;
    }

    @Override
    public Card addCard(Card card) {
        Long card_id = null;
        String sql = "INSERT INTO card( question, answer, image_url, deck_id) " +
                "VALUES (?, ?, ?, ?) returning card_id";
        try {
            card_id = jdbcTemplate.queryForObject(sql, Long.class, card.getQuestion(), card.getAnswer(), card.getImage(), card.getDeckId());
            System.out.println(card_id);
            card.setCardId(card_id);
        } catch(DataAccessException e){
            System.out.println("got here");
            return null;
        }
        return card;
    }

    @Override
    public Card updateCard(Card card) {
        String sql = "UPDATE card SET question=?, answer=?, image_url=? " +
                "WHERE card_id=?";
        try {
            jdbcTemplate.update(sql, card.getQuestion(), card.getAnswer(), card.getImage(), card.getCardId());
        }catch(DataAccessException e){
            return null;
        }
        return card;
    }

    @Override
    public boolean deleteCard(long card_id) {
        String sql = "DELETE FROM card WHERE card_id=?";
        return jdbcTemplate.update(sql, card_id) == 1;
    }

    private Card mapToRowCard(SqlRowSet results) {
        Card card = new Card();
        card.setCardId(results.getLong("card_id"));
        card.setQuestion(results.getString("question"));
        card.setAnswer(results.getString("answer"));
        card.setImage(results.getString("image_url"));
        card.setDeckId(results.getLong("deck_id"));
        return card;
    }
}
