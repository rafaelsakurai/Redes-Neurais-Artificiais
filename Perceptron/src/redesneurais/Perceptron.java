package redesneurais;

import java.util.Arrays;
import util.math.MathX;
import util.random.RandomX;

public class Perceptron {
    public static void main(String[] args) {
        double[] w = treinar();
        analisar(w);
    }
    
    public static double[] treinar() {
        double[][] x = Amostra.getValores();
        double[] d = Amostra.getSaidas();

        double[] w = RandomX.randomUniform(0, 1, 4); //Pesos iniciados aleatoriamente entre 0 e 1.
        System.out.println(Arrays.toString(w));
        double n = 0.01; //Taxa de aprendizagem.
        int epoca = 0;
        boolean erro = false;
        do {
            erro = false;
            for(int k = 0; k < x.length; k++) {
                double u = MathX.sum(MathX.multiply(w, x[k]));
                double y = sinal(u);
                if(y != d[k]) {
                    w = MathX.plus(w, MathX.multiply(x[k], n * (d[k] - y)));
                    erro = true;
                }
            }
            epoca++;
        } while(erro);
        System.out.println(Arrays.toString(w));
        System.out.println("Epoca: " + epoca);
        
        return w;
    }
    
    public static void analisar(double[] w) {
        double[][] x = Amostra.getAmostras();
        for(int i = 0; i < x.length; i++) {
            double u = MathX.sum(MathX.multiply(w, x[i]));
            double y = sinal(u);
            System.out.print(Arrays.toString(x[i]));
            System.out.println(y);
        }
    }
    
    public static double sinal(double u) {
        return u >= 0.0 ? 1.0 : -1.0;
    }
}



/*


30x3 3x1  


3x1 1x3

[0.44907652853644087, 
0.8508834077531339,
0.21597884466273676]

[-0.6508, 0.1097, 4.0009]


[
[-1.0, -0.6508, 0.1097, 4.0009]
[-1.0, -1.4492, 0.8896, 4.4005]
[-1.0, 2.085, 0.6876, 12.071]
[-1.0, 0.2626, 1.1476, 7.7985]
[-1.0, 0.6418, 1.0234, 7.0427]
[-1.0, 0.2569, 0.673, 8.3265][-1.0, 1.1155, 0.6043, 7.4446][-1.0, 0.0914, 0.3399, 7.0677][-1.0, 0.0121, 0.5256, 4.6316][-1.0, -0.0429, 0.466, 5.4323][-1.0, 0.434, 0.687, 8.2287][-1.0, 0.2735, 1.0287, 7.1934][-1.0, 0.4839, 0.4851, 7.485][-1.0, 0.4089, -0.1267, 5.5019][-1.0, 1.4391, 0.1614, 8.5843][-1.0, -0.9115, -0.1973, 2.1962][-1.0, 0.3654, 1.0475, 7.4858][-1.0, 0.2144, 0.7515, 7.1699][-1.0, 0.2013, 1.0014, 6.5489][-1.0, 0.6483, 0.2183, 5.8991][-1.0, -0.1147, 0.2242, 7.2435][-1.0, -0.797, 0.8795, 3.8762][-1.0, -1.0625, 0.6366, 2.4707][-1.0, 0.5307, 0.1285, 5.6883][-1.0, -1.22, 0.7777, 1.7252][-1.0, 0.3957, 0.1076, 5.6623][-1.0, -0.1013, 0.5989, 7.1812][-1.0, 2.4482, 0.9455, 11.2095][-1.0, 2.0149, 0.6192, 10.9263][-1.0, 0.2012, 0.2611, 5.4631]]



*/