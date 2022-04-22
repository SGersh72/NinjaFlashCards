package com.techelevator.controller;

import com.techelevator.dao.DeckDao;
import com.techelevator.dao.JdbcDeckDao;
import com.techelevator.model.Deck;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PreAuthorize("permitAll()")
public class DeckController {

    private DeckDao deckDao;

    //CONSTRUCTOR
    public DeckController(DeckDao deckDao){
        this.deckDao = deckDao;

    }

    @RequestMapping(path="/deck", method = RequestMethod.POST)
    public Deck createDeck(@RequestBody Deck deck){
        return deckDao.createDeck(deck);
    }

    @RequestMapping(path="/deck/{id}", method = RequestMethod.GET)
    public Deck getDeckByDeckId(@PathVariable long id) { return deckDao.getDeckById(id); }

    @RequestMapping(path = "/deck/myDecks/{userId}", method = RequestMethod.GET)
    public List<Deck> getDeckByUserId(@PathVariable long userId) {
        return deckDao.getDecksByOwnerId(userId);
    }
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(path = "/deck", method = RequestMethod.GET)
    public List<Deck> getAllDecks(){
        //Maybe never have access to all decks?
        List<Deck> decks = deckDao.getAllDecks();
        return decks;
    }
    @RequestMapping(path = "/deck/public", method = RequestMethod.GET)
    public List<Deck> getPublicDecks() {
        return deckDao.getDecksByStatusId(JdbcDeckDao.PUBLIC);
    }

    @ResponseStatus(value= HttpStatus.OK)
    @RequestMapping(path = "/deck/{id}", method = RequestMethod.PUT)
    public Deck updateDeck(@RequestBody Deck deck) {
        return deckDao.updateDeck(deck);
    }

    @ResponseStatus(value= HttpStatus.OK)
    @RequestMapping(path = "/deck/{id}", method = RequestMethod.DELETE)
    public void deleteDeck(@PathVariable Long id) {
        deckDao.deleteDeck(id);
    }



    //Score History Endpoints

    //@ResponseStatus(value= HttpStatus.OK)
    @RequestMapping(path="/deck/{deckId}/history/{userId}", method = RequestMethod.GET)
    public int getHistoryUserDeck(@PathVariable long userId, @PathVariable long deckId) {
        return deckDao.getHistoryUserDeck(userId, deckId);
    }

    //@ResponseStatus(value= HttpStatus.OK)
    @RequestMapping(path = "/deck/{deckId}/history/{userId}", method = RequestMethod.PUT) //Updating and Inserting with this endpoint
    public int updateUserDeckHistory(@PathVariable long userId, @PathVariable long deckId, @RequestParam(required = true, name = "score") double scorePercent) {
        return deckDao.updateUserDeckHistory(userId, deckId, scorePercent);
    }
    @RequestMapping(path = "/deck/{deckId}/history/{userId}", method = RequestMethod.POST) //Updating and Inserting with this endpoint
    public int createUserDeckHistory(@PathVariable long userId, @PathVariable long deckId, @RequestParam(required = true, name = "score") double scorePercent) {
        return deckDao.createUserDeckHistory(userId, deckId, scorePercent);
    }

}
