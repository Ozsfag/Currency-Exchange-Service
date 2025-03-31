package ru.skillbox.currency.exchange.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.dto.SimpleCurrencyDto;
import ru.skillbox.currency.exchange.service.CurrencyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/currency")
public class CurrencyController {
  private final CurrencyService service;

  @GetMapping
  ResponseEntity<List<SimpleCurrencyDto>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping(value = "/{id}")
  ResponseEntity<CurrencyDto> getById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @GetMapping(value = "/convert")
  ResponseEntity<Double> convertValue(
      @RequestParam("value") Long value, @RequestParam("numCode") Long numCode) {
    return ResponseEntity.ok(service.convertValue(value, numCode));
  }

  @PostMapping("/create")
  ResponseEntity<CurrencyDto> create(@RequestBody CurrencyDto dto) {
    return ResponseEntity.ok(service.create(dto));
  }
}
