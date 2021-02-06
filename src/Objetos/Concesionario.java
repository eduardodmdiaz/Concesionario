/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.time.LocalDate;

/**
 *La clase concesionario es un array con objetos de tipo Vehiculo en su interior
 * su dimension máxima será de 50, controlamos el numero de Vehiculos en el array con
 * el integer numeroVehiculos
 * @author Edu
 * @version 1.0 of 02/02/2021
 */
public class Concesionario {
    private Vehiculo[] concesionario;
    private int numeroVehiculos;
    /**
     * Constructor de la clase Concesionario, aquí especificamos el numero máximo de 50
     * e inicializamos numeroVehiculos a 0
     */
    public Concesionario(){
        concesionario=new Vehiculo[50];
        numeroVehiculos=0;
    }
    /**
     * metodo para buscar un vehículo teniendo su numero de matrícula dentro del
     * array
     * @param matricula la matricula del vehiculo que le pasamos al método. 
     * @return descripcion un string con la descripcion del vehiculo, será null si no hay
     * vehiculo con esa matricula
     */
    public String buscaVehiculo(String matricula){
        String matriculaAux; //donde alojamos la matricula que sacamos del array en las iteraciones
        String descripcion=null;
        String marca;
        String matriculaResult;
        float precio;
        
        boolean exito=false;
        boolean fin=false;
        int contador=0;
        
        
        while (!exito&&!fin) {            
            matriculaAux=concesionario[contador].getMatricula();
            if (matriculaAux.equals(matricula)){
                
                marca=concesionario[contador].getMarca();
                matriculaResult=concesionario[contador].getMatricula();
                precio=concesionario[contador].getPrecio();
                
                descripcion="Marca: "+marca+" Matricula: "+matriculaResult+" Precio: "+precio;
                
                exito=true;
                }
            contador++;
            fin=(contador==numeroVehiculos);
        }
        return descripcion;
    }
    /**
     * metodo que usamos para insertar vehículo en el array, lo usamos con todos los atributos
     * del objeto vehículo porque será el método que llame al constructor de la clase vehiculo
     * @param marca la marca del vehiculo
     * @param matricula matricula del vehiculo
     * @param km kilometros recorridos por el vehiculo
     * @param fechaMatriculacion fecha de matriculacion del vehiculo
     * @param precio precio del vehiculo
     * @param nombrePropietario nombre del propietario del vehiculo
     * @param DNI ID del dueño del vehiculo
     * @return un int que será 0 si todo ha ido bien, -1 si el concesionario está lleno
     * y -2 si ya existe un vehículo con la matricula del que queremos insertar
     */
    public int insertarVehiculo(String marca, String matricula, int km, LocalDate fechaMatriculacion, float precio, String nombrePropietario, String DNI){
        int salida;
        String descripcion;
        if(numeroVehiculos==50){
            salida=-1;
        } else {
            if (numeroVehiculos==0) {
                salida=0;
                concesionario[numeroVehiculos] = new Vehiculo(marca,matricula,km,fechaMatriculacion,precio,nombrePropietario,DNI);
                numeroVehiculos++;
            } else {
                descripcion=buscaVehiculo(matricula);
                if (descripcion!=null) {
                    salida=-2;
                } else {
                    salida=0;
                    concesionario[numeroVehiculos] = new Vehiculo(marca,matricula,km,fechaMatriculacion,precio,nombrePropietario,DNI);
                    numeroVehiculos++;
                }
            }
        }
        return salida;
    }
    /**
     * metodo que usamos para actualizar los kms de un vehículo, no he querido actualizar
     * los kms de manera que siempre tuvieran que ser mayores que los existentes porque
     * sino tendríamos un problema si alguien del concesionario cometiera un error
     * al hacer la escritura de kms y quisiera corregirlo
     * @param matricula matricula del vehiculo a actualizar
     * @param km kilometros a actualizar
     * @return <code>true</code> si ha podido actualizar los kilometros
     *         <code>false</code> si el vehículo que buscamos no existe en nuestro concesionario
     */
    public boolean actualizaKms(String matricula, int km){
        String descripcionAux;
        boolean resultado;
        int i;
        
        descripcionAux=buscaVehiculo(matricula);
        if(descripcionAux==null){
            resultado=false;
        } else {
            i=obtenerIndiceMatricula(matricula);
            concesionario[i].setKm(km);
            resultado=true;
        }
        return resultado;
    }
    /**
     * Método auxiliar que me he creado para saber que indice dentro del array tiene
     * el vehiculo que tiene una matricula dada
     * @param matricula matricula del vehiculo a buscar
     * @return el indice del vehiculo con esa matricula
     */
    public int obtenerIndiceMatricula(String matricula){
        boolean exito=false;
        String matriculaAux;
        int contador=0;
        int resultado=-1;
        
        while (!exito) {            
            matriculaAux=concesionario[contador].getMatricula();
            if (matriculaAux.equals(matricula)){
                resultado=contador;
                exito=true;
                }
            contador++;
        }
        return resultado;
    }
    /**
     * metodo que lista todos los vehiculos que existen en el array, los listamos por medio de un for
     * que recorre la totalidad del array
     */
    public void listaVehiculos(){
        String datos;
        int numero;
        System.out.println("--------------------------------------------------");
        System.out.println("Lista de todos los vehículos del concesionario.");
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < numeroVehiculos; i++) {
            numero=i+1;
            datos="Vehículo "+numero;
            datos=datos.concat(": Marca: "+concesionario[i].getMarca());
            datos=datos.concat(" Matrícula: "+concesionario[i].getMatricula());
            datos=datos.concat(" Precio: "+concesionario[i].getPrecio());
            datos=datos.concat("€ Kilómetros: "+concesionario[i].getKm());
            datos=datos.concat(" Descripción: "+concesionario[i].getDescripcion());
            System.out.println(datos);
        }
        System.out.println("--------------------------------------------------");
    }
    /**
     * metodo para obtener el numero de vehiculos que tiene el array
     * @return numeroVehiculos, el numero de vehiculos que tenemos en el concesionario
     */
    public int getNumeroVehiculos() {
        return numeroVehiculos;
    }

        
}
