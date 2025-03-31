package ru.skillbox.currency.exchange.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SimpleCurrencyDto {
  private String name;

  private Double value;
}
