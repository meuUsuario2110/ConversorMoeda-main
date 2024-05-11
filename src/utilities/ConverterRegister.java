package utilities;

import java.time.LocalDateTime;

public class ConverterRegister {
    private String primaryCoin;
    private double primaryValue;
    private String secondaryCoin;
    private double secondaryValue;
    private double actualExchangeRate;
    private String dateTimeRequest;

    public ConverterRegister(String primaryCoin, double primaryValue, String secondaryCoin, double secondaryValue, double actualExchangeRate) {
        this.primaryCoin = primaryCoin;
        this.primaryValue = primaryValue;
        this.secondaryCoin = secondaryCoin;
        this.secondaryValue = secondaryValue;
        this.actualExchangeRate = actualExchangeRate;
        this.dateTimeRequest = dateTime();
    }

    private String dateTime() {
        String actualTime = String.valueOf(LocalDateTime.now());
        String[] arrStr = actualTime.split("\\.");
        return arrStr[0].replace("T", " ");
    }

    @Override
    public String toString() {
        String value = String.format("""
                        Cotação Solicitada: %s - %s
                        Valor atual cotação %s: %f
                        Valor de conversão de %s solicitada: %.2f
                        Valor convertido para %s: %.2f
                        Data e hora da solicitação: %s
                        
                        """, this.primaryCoin, this.secondaryCoin, this.primaryCoin, this.actualExchangeRate,
                        this.primaryCoin, this.primaryValue, this.secondaryCoin, this.secondaryValue, this.dateTimeRequest);
        return value;
    }

}
