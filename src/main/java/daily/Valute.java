package daily;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Сущность, представляющая список всех известных репозиторию валют.
 */
@Data
@NoArgsConstructor
public class Valute implements Iterable{
    Set<Currency>currencies;

    private Currency AUD;
    private Currency AZN;
    private Currency GBP;
    private Currency AMD;
    private Currency BYN;
    private Currency BGN;
    private Currency BRL;
    private Currency HUF;
    private Currency HKD;
    private Currency DKK;
    private Currency USD;
    private Currency EUR;
    private Currency INR;
    private Currency KZT;
    private Currency CAD;
    private Currency KGS;
    private Currency CNY;
    private Currency MDL;
    private Currency NOK;
    private Currency PLN;
    private Currency RON;
    private Currency XDR;
    private Currency SGD;
    private Currency TJS;
    private Currency TRY;
    private Currency TMT;
    private Currency UZS;
    private Currency UAH;
    private Currency CZK;
    private Currency SEK;
    private Currency CHF;
    private Currency ZAR;
    private Currency KRW;
    private Currency JPY;

    /**
     * Инициализирует список валют
     * @see Valute#currencies
     */
    private void initCurrencies(){
        currencies = new HashSet<>();
        currencies.add(AUD);
        currencies.add(AZN);
        currencies.add(GBP);
        currencies.add(AMD);
        currencies.add(BYN);
        currencies.add(BGN);
        currencies.add(BRL);
        currencies.add(HUF);
        currencies.add(HKD);
        currencies.add(DKK);
        currencies.add(USD);
        currencies.add(EUR);
        currencies.add(INR);
        currencies.add(KZT);
        currencies.add(CAD);
        currencies.add(KGS);
        currencies.add(CNY);
        currencies.add(MDL);
        currencies.add(NOK);
        currencies.add(PLN);
        currencies.add(RON);
        currencies.add(XDR);
        currencies.add(SGD);
        currencies.add(TJS);
        currencies.add(TRY);
        currencies.add(TMT);
        currencies.add(UZS);
        currencies.add(UAH);
        currencies.add(CZK);
        currencies.add(SEK);
        currencies.add(CHF);
        currencies.add(ZAR);
        currencies.add(KRW);
        currencies.add(JPY);
    }

    @Override
    public Iterator iterator() {
        initCurrencies();
        return currencies.iterator();
    }
}
