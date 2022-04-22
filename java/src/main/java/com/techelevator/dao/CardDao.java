package com.techelevator.dao;

import com.techelevator.model.Card;

import java.util.List;

public interface CardDao {

    List<Card> getAllCards(long deck_id);

    Card addCard(Card card);

    Card updateCard(Card card);

    boolean deleteCard(long card_id);
}
