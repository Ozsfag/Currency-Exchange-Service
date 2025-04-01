package ru.skillbox.currency.exchange.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.skillbox.currency.exchange.client.CurrencyApiClient;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyUpdateService {

  private final CurrencyApiClient client;
  private final CurrencyRepository repository;

  @Scheduled(fixedRate = 3600000)
  public void updateCurrencies() {
    log.info("Starting currency update process.");
    List<Currency> currencies = client.fetchCurrencies();

    currencies.forEach(
        currency ->
            repository
                .findByIsoCharCode(currency.getIsoCharCode())
                .ifPresentOrElse(
                    exisstedCurrency -> {
                      exisstedCurrency.setValue(currency.getValue());
                      exisstedCurrency.setNominal(currency.getNominal());
                      repository.save(exisstedCurrency);
                    },
                    () -> repository.save(currency)));
    log.info("Currency update process completed");
  }
}
