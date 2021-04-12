package daily;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущьность, описывающая валюту
 */
@Data
@NoArgsConstructor
public class Currency {
    @SerializedName("ID")
    private String id;
    @SerializedName("NumCode")
    private String numCode;
    @SerializedName("CharCode")
    private String charCode;
    @SerializedName("Nominal")
    private int nominal;
    @SerializedName("Name")
    private String name;
    @SerializedName("Value")
    private double value;
    @SerializedName("Previous")
    private double previous;

    /**
     * Возвращает модулб значения, отражающего последнее изменение значения валюты.
     * @return
     */
    public double getAmountOfChange(){
        return Math.abs(value-previous);
    }
}
