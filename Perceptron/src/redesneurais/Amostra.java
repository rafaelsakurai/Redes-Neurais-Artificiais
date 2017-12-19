package redesneurais;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Amostra {
    private static final double[][] valores;
    private static final double[] saidas;
    private static final double[][] amostras;
    
    static {
        valores = new double[30][4];
        saidas = new double[30];
        amostras = new double[10][4];
        int cont = 0;
        
        try {
            Scanner s = new Scanner(new File("/Users/rafaelsakurai/Documents/Estudos/Redes Neurais Artificiais/Perceptron/src/treinamento"));
            s.nextLine(); //Pula a primeira linha (cabeçalho).
            while(s.hasNextLine()) {
                String[] dados = s.nextLine().split(";");
                valores[cont][0] = -1;
                valores[cont][1] = Double.valueOf(dados[1]);
                valores[cont][2] = Double.valueOf(dados[2]);
                valores[cont][3] = Double.valueOf(dados[3]);
                saidas[cont] = Double.valueOf(dados[4]);
                cont++;
            }
            
            cont = 0;
            Scanner s2 = new Scanner(new File("/Users/rafaelsakurai/Documents/Estudos/Redes Neurais Artificiais/Perceptron/src/amostras"));
            s2.nextLine(); //Pula a primeira linha (cabeçalho).
            while(s2.hasNextLine()) {
                String[] dados = s2.nextLine().split(";");
                amostras[cont][0] = -1;
                amostras[cont][1] = Double.valueOf(dados[1]);
                amostras[cont][2] = Double.valueOf(dados[2]);
                amostras[cont][3] = Double.valueOf(dados[3]);
                cont++;
            }
            
        } catch (FileNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static double[][] getValores() {
        return valores;
    }

    public static double[] getSaidas() {
        return saidas;
    }

    public static double[][] getAmostras() {
        return amostras;
    }
}
