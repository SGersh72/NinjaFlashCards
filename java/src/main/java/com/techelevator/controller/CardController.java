package com.techelevator.controller;

import com.techelevator.dao.CardDao;
import com.techelevator.model.Card;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PreAuthorize("permitAll()")
public class CardController {
    private CardDao cardDao;

    public CardController(CardDao cardDao) {
        this.cardDao = cardDao;
    }


    @RequestMapping(path = "/deck/{id}/card", method = RequestMethod.GET)
    public List<Card> getAllCard(@PathVariable long id) {
        return cardDao.getAllCards(id);
    }


    @RequestMapping(path = "/deck/{deck-id}/card", method = RequestMethod.POST)
    public Card addCard(@RequestBody Card card) {
        return cardDao.addCard(card);
    }


    @RequestMapping(path = "/deck/{deck-id}/card/{card-id}", method = RequestMethod.PUT)
    public Card updateCard(@RequestBody Card card) {
        return cardDao.updateCard(card);
    }


    @RequestMapping(path = "/deck/{deck-id}/card/{id}", method = RequestMethod.DELETE)
    public boolean deleteCard(@PathVariable long id) {
        return cardDao.deleteCard(id);
    }
}
