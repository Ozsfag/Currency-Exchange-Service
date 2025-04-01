package ru.skillbox.currency.exchange.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.skillbox.currency.exchange.entity.Currency;

@Slf4j
@Component
public class CurrencyApiClient {

  @Value("${currency.api.url}")
  private String apiUrl;

  public List<Currency> fetchCurrencies() {
    try {
      HttpURLConnection connection = createConnection(apiUrl);
      connection.setRequestMethod("GET");

      try (InputStream xml = connection.getInputStream()) {
        JAXBContext jaxbContext = JAXBContext.newInstance(CurrencyList.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        CurrencyList currencyList = (CurrencyList) unmarshaller.unmarshal(xml);
        return currencyList.getCurrencies();
      } catch (JAXBException e) {
        throw new RuntimeException(e);
      }

    } catch (IOException e) {
      log.error("Error fetching currencies from API: ", e);
      throw new RuntimeException("Failed to fetch currencies", e);
    }
  }

  public HttpURLConnection createConnection(String url) throws IOException {
    return (HttpURLConnection) new URL(url).openConnection();
  }

  @XmlRootElement(name = "ValCurs")
  @XmlAccessorType(XmlAccessType.FIELD)
  @RequiredArgsConstructor
  @Getter
  private static class CurrencyList {
    @XmlElement(name = "Value")
    private final List<Currency> currencies;
  }
}
