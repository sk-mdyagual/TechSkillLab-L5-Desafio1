package principles.interfaces;

import resources.Empleado;

@FunctionalInterface
public interface ResultMessage {
    String format(Empleado empleado);
    //String format(String op, int res);

    //Default: Enriquecer la interfaz


    //Static: Actúan como utilitarios
    //static void show(String s){
    //    System.out.println(s);
   // }
}
