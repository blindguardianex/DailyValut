package daily;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * Приложение берет текущий курс валют по ссылке @link https://www.cbr-xml-daily.ru/daily_json.js
 * и выводит 5 валют, номинал которых изменился в большей степени (по модулю)
 */
@Slf4j
public class MainApp {
    private static final String REPOSITORY = "https://www.cbr-xml-daily.ru/daily_json.js";
    private static final int MOST_CHANGE_COUNT = 5;
    private static Map<Double, Currency>amountChange = new HashMap<>();
    private static Valute valute;

    public static void main(String[] args) throws IOException, InterruptedException {
        getValute();
        initAmountChange();
        List<Currency> mostChangesValut = getMostChangedValut(MOST_CHANGE_COUNT);
        printMostChangesValut(mostChangesValut);
    }

    /**
     * Получает перечень валют
     * @see MainApp#getJsonValute()
     * @see daily.MainApp#getValuteFromJson(String)
     * @throws IOException
     * @throws InterruptedException
     */
    private static void getValute() throws IOException, InterruptedException {
        String jsonValute = getJsonValute();
        valute = getValuteFromJson(jsonValute);
    }

    /**
     * Возвращает список валют, претерпевших наибольшие изменения
     * @param size - размер возвращаемого списка
     * @return
     */
    private static List<Currency> getMostChangedValut(int size){
        List<Currency> mostChanged = amountChange.entrySet().stream()
                                            .sorted((x, y)->{
                                                    return x.getKey()<y.getKey() ? 1 :
                                                            x.getKey()>y.getKey() ? -1 :
                                                                0;
                                            })
                                            .limit(size)
                                            .map(Map.Entry::getValue)
                                            .collect(toList());
        return mostChanged;
    }

    /**
     * Выводит список валют, претерпевших наибольшие изменения, в консоль
     * @param mostChanges - список валют, претерпевших наибольшие изменения
     */
    private static void printMostChangesValut(List<Currency>mostChanges){
        for (int i = 0; i<mostChanges.size(); i++){
            System.out.println(String.format("%d) Наименование валюты: %s, величина изменения: %f", i+1, mostChanges.get(i).getName(), mostChanges.get(i).getAmountOfChange()));
        }
    }

    /**
     * Заполняет ассоциативный массив amountChange. Ключом является размер последнего
     * изменения валюты, а значением - сама валюта. Может быть вызван после инициализации поля valute
     * @see MainApp#amountChange
     * @see MainApp#valute
     */
    private static void initAmountChange(){
        Objects.requireNonNull(valute, "Перечень валют не установлен!");
        Iterator<Currency>itr = valute.iterator();
        while (itr.hasNext()){
            Currency currency = itr.next();
            amountChange.put(currency.getAmountOfChange(),currency);
        }
    }

    /**
     * Получает перечень валют из репозитория в формате JSON
     * по протоколу HTTP
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private static String getJsonValute() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(REPOSITORY);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * Парсит JSON-строку, полученную из репозитория, в объект Valute
     * @see Valute
     * @param jsonValute
     * @return
     */
    private static Valute getValuteFromJson(String jsonValute){
        JsonObject obj = JsonParser.parseString(jsonValute).getAsJsonObject();
        String valuteJson = obj.get("Valute").toString();
        Gson gson = new Gson();
        return gson.fromJson(valuteJson, Valute.class);
    }
}

