package ru.skillbox.currency.exchange.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.dto.SimpleCurrencyDto;
import ru.skillbox.currency.exchange.entity.Currency;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

  CurrencyDto convertToDto(Currency currency);

  Currency convertToEntity(CurrencyDto currencyDto);

  List<SimpleCurrencyDto> convertToDtoList(List<Currency> currencies);
}
