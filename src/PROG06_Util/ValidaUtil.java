/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROG06_Util;

import java.time.LocalDate;
import java.util.regex.*;
/**
 *Esta clase la usaremos para nuestras validaciones, es una clase estatica 
 * con todos los metodos para validar
 * @author Edu
 * @version 2.0 de la 1.0 de PROG05_Ejerc1.ValidaUtil
 */
public class ValidaUtil {
    /**
     * Atributo constante con la cadena que nos marca la letra del DNI
     */
    private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
    /**
     * método estatico que valida si los km son validos o no
     * @param km el numero que queremos comprobar, tipo int
     * @return <code> verdadero </code> si el dato km es mayor que cero
     *         <code> falso </code> si el dato km es menor o igual a cero
     */
    public static boolean validaKm(int km){
        boolean validez=true;
        if (km<=0) {
            validez=false;
        }
        return validez;
    }
    /**
     * metodo que comprueba si la fecha de matriculacion es anterior a la actual.
     * @param fechaMatriculacion fecha de matriculacion a validar
     * @return <code> verdadero </code> si la fecha es correcta y es anterior a la actual
     *         <code> falso </code> si la fecha es incorrecta y es posterior a la actual
     */
    public static boolean validaFecha(LocalDate fechaMatriculacion){
        boolean fechaValida;
        
        LocalDate fechaHoy=LocalDate.now();
        fechaValida=fechaMatriculacion.isAfter(fechaHoy);
        return !fechaValida;
    }
    /**
     * el metodo calcula la letra que deberia tener el DNI dado
     * @param dni dni del que calcular la letra
     * @return letra la letra correspondiente a ese numero, tipo char
     */
    private static char calcularLetraNIF(int dni) {
        
        char letra;
        // Cálculo de la letra NIF
        letra = LETRAS_DNI.charAt(dni % 23);
        // Devolución de la letra NIF
        return letra;
    }
    /**
     * metodo que extrae la letra del nif introducido
     * @param nif el nif del que extraer la letra
     * @return letra, un char con la letra en mayúsucula
     */
    private static char extraerLetraNIF(String nif) {
        char letra = nif.charAt(nif.length() - 1);
        
        //return letra;
        return Character.toUpperCase(letra); //Devolvemos su equivalente en mayúscula para poder comparar con las letras de la cadena LETRAS_DNI
    }
    /**
     * metodo que extrae el numero del nif introducido
     * @param nif el nif del que extraer el numero
     * @return numero, el numero del nif, tipo int
     */
    private static int extraerNumeroNIF(String nif) {
        
        int numero = Integer.parseInt(nif.substring(0, nif.length() - 1));
        return numero;
    }
    /**
     * método que da excepciones si el nif no es valido, siempre da la misma excepcion
     * de modo que en Principal no sabremos por cual de las 3 formas no es valido, solo
     * sabremos que no lo es, el metodo no devuelve nada, por tanto si no salta excepcion sabremos que funciona
     * bien
     * @param nif dni a validar
     * @throws Exception cuando la letra del NIF no coresponde con la que deberia tener
     */
    public static void validarNIF(String nif) throws Exception {
        
        char letra_calculada;
        char letra_leida;
        int dni_leido;

        if (nif == null) {  // El parámetro debe ser un objeto no vacío
            
            throw new Exception();

        } else if (nif.length() < 8 || nif.length() > 9) {    // La cadena debe estar entre 8(7+1) y 9(8+1) caracteres
            
            throw new Exception();

        } else {
            letra_leida = extraerLetraNIF(nif);    // Extraemos la letra de NIF (letra)
            dni_leido = extraerNumeroNIF(nif);  // Extraemos el número de DNI (int)
            letra_calculada = calcularLetraNIF(dni_leido);  // Calculamos la letra de NIF a partir del número extraído
            if (letra_leida != letra_calculada) {   // Comparamos la letra extraída con la calculada
                throw new Exception ();
            }
        }
        
    }
    /**
     * Método que nos sirve para saber si el id introducido es DNI, NIE o no es ninguno de los dos
     * de manera que devuelve 1 si es un DNI, devuelve 2 si es un NIE o devuelve 0 si no es ninguno
     * de los y es un error
     * @param id Id a identificar si es DNI o NIE o ninguno
     * @return 1 si es DNI, 2 si es NIE, 0 si no es ninguno de los dos
     */
    public static int validarDNIoNIE(String id){
        int resultado;
        Pattern nie=Pattern.compile("[XYxy]+[0-9]{7}[A-Za-z]");
        Pattern dni=Pattern.compile("[0-9]{8}[A-Za-z]");
        Matcher mNie=nie.matcher(id);
        Matcher mDni=dni.matcher(id);
        
        
        if (mNie.matches()){
            resultado=2;
        } else {
            if (mDni.matches()) {
                try {
                   validarNIF(id);
                   resultado=1;
                   
                } catch (Exception e) {
                   resultado=0;
                }
                
            } else {
                resultado=0;
            }
        }
        return resultado;
    }
    /**
     * Método que sive para validar la matricula, si cumple con el parametro NNNNLLL
     * será valido y sino no
     * @param matricula matricula a validar
     * @return <code> true </code> si es valida
     *         <code> false </code> si no es valida
     */
    public static boolean validarMatricula(String matricula){
        boolean valido;
        Pattern p=Pattern.compile("[0-9]{4}[A-Z]{3}");
        Matcher m=p.matcher(matricula);
        valido = m.matches();
        return valido;
    }
    /**
     * Metodo que sirve para validar el nombre, un nombre es valido cuando contiene
     * menos de 40 caracteres y cuando tiene al menos 1 nombre y 2 apellidos, no contemplamos
     * nombres compuestos
     * @param nombre nombre a validar
     * @throws Exception cuando tiene menos de 3 palabras la cadena
     */
    public static void validarNombre(String nombre) throws Exception{
        int longitud;
        String apellidos;
        int i,j;
        
        longitud=nombre.length();
        if (longitud>40){throw new Exception();}
        
        i=nombre.indexOf(" ");
        apellidos=nombre.substring(i+1);
        j=apellidos.indexOf(" ");
        
        if(i==-1){throw new Exception();}
        if(j==-1){throw new Exception();}
        
    }
    
}
