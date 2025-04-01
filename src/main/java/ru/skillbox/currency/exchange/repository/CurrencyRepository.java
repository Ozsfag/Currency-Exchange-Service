package ru.skillbox.currency.exchange.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.currency.exchange.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

  Currency findByIsoNumCode(Long isoNumCode);

  Optional<Currency> findByIsoCharCode(String isoCharCode);
}
