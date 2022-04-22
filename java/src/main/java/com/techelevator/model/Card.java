package com.techelevator.model;

public class Card {
    private long cardId;
    private String question;
    private String answer;
    private String image;
    private long deckId;

    public Card() {
    }

    public Card(long cardId, String question, String answer, String image, long deckId) {
        this.cardId = cardId;
        this.question = question;
        this.answer = answer;
        this.image = image;
        this.deckId = deckId;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getDeckId() {
        return deckId;
    }

    public void setDeckId(long deckId) {
        this.deckId = deckId;
    }
}
