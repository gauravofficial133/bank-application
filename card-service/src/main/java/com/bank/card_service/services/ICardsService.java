package com.bank.card_service.services;

import com.bank.card_service.dtos.CardsDto;

public interface ICardsService {
    void createCard(String mobileNumber);
    CardsDto getCardByMobileNumber(String mobileNumber);
    boolean updateCard(CardsDto cardsDto);
    boolean deleteCard(String mobileNumber);
}
