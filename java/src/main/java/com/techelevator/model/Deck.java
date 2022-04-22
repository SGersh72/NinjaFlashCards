package com.techelevator.model;

import javax.validation.constraints.NotNull;

public class Deck {

    private long deckId;
    @NotNull
    private long ownerId;
    @NotNull
    private String genre;
    @NotNull
    private String deckName;
    @NotNull
    private String deckDesc;
    @NotNull
    private int status; //1-Private 2-Pending 3-Public

    //DEFAULT CONSTRUCTOR
    public Deck(){}

    //GETTERS
    public long getDeckId() {
        return deckId;
    }
    public long getOwnerId() {
        return ownerId;
    }
    public String getDeckName() {
        return deckName;
    }
    public String getDeckDesc() {
        return deckDesc;
    }
    public int getStatus() {
        return status;
    }
    public String getGenre() { return genre; }

    //SETTERS
    public void setDeckId(long deckId) {
        this.deckId = deckId;
    }
    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }
    public void setDeckDesc(String deckDesc) {
        this.deckDesc = deckDesc;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setGenre(String genre) { this.genre = genre; }
}
