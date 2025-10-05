package principles;

import principles.interfaces.ResultMessage;

class Calculator {

    //Funciones puras
    public static int sumar(int n1, int n2){
        return n1 + n2;
    }

    public static int restar(int n1, int n2){
        return n1 - n2;
    }

    public static int multiplicar(int n1, int n2){
        return n1 * n2;
    }

    //Recursión
    public static int factorial(int n1){
        return (n1 == 0)? 1: n1 * factorial(n1);
    }

    public static void main(String[] args) {
        int suma = Calculator.sumar(3,4);
        int resta = Calculator.restar(14,4);
        int mult = Calculator.multiplicar(5,6);

        int fact = Calculator.factorial(6);

        System.out.println("La suma es: "+suma);
        System.out.println("La resta es: "+resta);
        System.out.println("La multiplicación es: "+mult);
        System.out.println("El factorial es: "+fact);

        //To-do: Uso de la interfaz funcional Result Message para mostrar prints

    }
}
