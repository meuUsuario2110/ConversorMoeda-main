package main;
import utilities.ApiRequest;
import utilities.ConverterRegister;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ConverterRegister register;
        String option;
        List<ConverterRegister> listaDeRequisicoes = new ArrayList<>();
        boolean loop = true;

        while(loop){
            System.out.println("""
                    *********************************************
                    
                    Seja bem vindo ao conversor de moedas =)
                    
                    1) Dólar =>> Peso argentino
                    2) Peso argentino =>> Dólar
                    3) Dólar =>> Real brasileiro
                    4) Real brasileiro =>> Dólar
                    5) Dólar =>> Peso colombiano
                    6) Peso colombiano =>> Dólar
                    7) Sair
                    Escolha uma das opções válidas:
                    
                    *********************************************
                    """);

            option = input.nextLine();

            if(option.equals("7") && listaDeRequisicoes.isEmpty()) {
                break;
            }
            else {
                switch(option) {
                    case "1":
                        register = addList("USD", "ARS");
                        if(register != null) {
                            listaDeRequisicoes.add(register);
                        }
                        break;
                    case "2":
                        register = addList("ARS", "USD");
                        if(register != null) {
                            listaDeRequisicoes.add(register);
                        }
                        break;
                    case "3":
                        register = addList("USD", "BRL");
                        if(register != null) {
                            listaDeRequisicoes.add(register);
                        }
                        break;
                    case "4":
                        register = addList("BRL", "USD");
                        if(register != null) {
                            listaDeRequisicoes.add(register);
                        }
                        break;
                    case "5":
                        register = addList("USD", "COP");
                        if(register != null) {
                            listaDeRequisicoes.add(register);
                        }
                        break;
                    case "6":
                        register = addList("COP", "USD");
                        if(register != null) {
                            listaDeRequisicoes.add(register);
                        }
                        break;
                    case "7":
                        try {
                            FileWriter writer = new FileWriter("registros.txt");
                            for(ConverterRegister registro : listaDeRequisicoes) {
                                writer.write(registro.toString());
                            }

                            writer.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        loop = false;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            }
        }
    }

    public static ConverterRegister addList(String primaryCoin, String secondaryCoin) {
        Scanner input = new Scanner(System.in);
        ConverterRegister newRegister = null;
        try {
            System.out.printf("Informe o valor que deseja converter para %s: ", secondaryCoin);
            System.out.println();
            double primaryValue = input.nextDouble(); // valor em dolar
            double conversion = ApiRequest.conversor(primaryCoin, secondaryCoin);
            double secondaryValue = primaryValue * conversion;
            System.out.printf("Valor de %.2f [%s] convertido para [%s]: %.2f",primaryValue, primaryCoin, secondaryCoin, secondaryValue);
            newRegister = new ConverterRegister(primaryCoin, primaryValue, secondaryCoin, secondaryValue, conversion);
            System.out.println();
        } catch(RuntimeException e) {
            System.out.println("Informe um valor válido");
        }
        return newRegister;
    }
}
