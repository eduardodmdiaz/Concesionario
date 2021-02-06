/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author Edu
 * @version 2.0 from PROG05_Ejerc1
 */
public class Vehiculo {
    /**
     * Clase Vehículo para usar en Principal.
     * tiene como atributos marca, matricula, km, fechaMatriculacion, precio, nombrePropietario y DNI
     * también tiene un atributo descripcion que es un String hecho con algunos de los datos anteriores
     * Como esa descripcion no la generamos por teclado, la generamos en el constructor
     * el atributo descripción tiene una caracteristica especial que he detectado programando
     * como podemos en los distintos menús cambiar algunos de los atributos de los que aparecen
     * en descripcion, he optado por cambiar el getDescripcion() de manera que siempre generamos uno
     * nuevo en el momento en el que el método es llamado, para que no ocurra, por ejemplo,
     * que hemos cambiado los kilómetros recorridos por el coche pero no se modifica la descripcion,
     * de esta manera siempre tendremos la descripcion actualizada cuando llamamos al método.
     * 
     * Todos los atributos están como private, de manera que están encapsulados y solo se puede
     * acceder a ellos mediante sus métodos.
     * 
     */
    private String marca;
    private String matricula;
    private int km;
    private LocalDate fechaMatriculacion;
    private String descripcion;
    private float precio;
    private String nombrePropietario;
    private String DNI;
    /**
     * Constructor de objetos Vehículos
     * Al ser un constructor que solo se llama en la opción 1 del menú, no hallo necesario sobrecargarlo
     * Siempre vamos a llamar al constructor con los mismos parámetros, solo será llamado desde
     * la clase Concesionario
     * @param marca marca del vehiculo
     * @param matricula matricula del vehiculo
     * @param km kilometros recorridos por el vehiculo
     * @param fechaMatriculacion fecha de matriculacion del vehiculo
     * @param precio precio del vehiculo
     * @param nombrePropietario nombre del propietario del vehiculo
     * @param DNI Id del dueño del vehiculo
     */
    public Vehiculo(String marca, String matricula, int km, LocalDate fechaMatriculacion, float precio, String nombrePropietario, String DNI){
        
        this.marca=marca;
        this.matricula=matricula;
        this.km=km;
        this.fechaMatriculacion=fechaMatriculacion;
        this.precio=precio;
        this.nombrePropietario=nombrePropietario;
        this.DNI=DNI;
        String auxDescripcion="Vehículo Marca "+marca+". Matrícula: "+matricula+". Kilómetros del vehículo: "+km+".";
        this.descripcion=auxDescripcion;
                
    }
    /**
     * metodo para obtener los años que tiene el vehículo
     * Usamos para obtenerlo el periodo de tiempo en años que han pasado desde su
     * fecha de matriculacion y la fecha actual
     * @return anios que son los años que han pasado desde la fecha de matriculacion
     */
    public int getAnios(){
        /**
         * 
         * @return numero de años del vehículo, tipo int
         */
        int anios;
        anios = Period.between(fechaMatriculacion, LocalDate.now()).getYears();
        return anios;        
    }
    /**
     * metodo para obtener la marca del vehículo
     * @return un string con el nombre de la marca
     */
    public String getMarca() {
        
        return marca;
    }
    /**
     * metodo para establecer el nombre de la marca
     * @param marca que es el nombre de la marca, tipo String
     */
    public void setMarca(String marca) {
        
        this.marca = marca;
    }
    /**
     * metodo para obtener la matrícula del vehículo
     * @return un string con el numero y letras de la matricula
     */
    public String getMatricula() {
        return matricula;
    }
    /**
     * metodo para establecer la matricula del vehiculo
     * @param matricula la matricula que le pasamos, tipo String
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    /**
     * metodo para obtener los km recorridos actuales del vehiculo
     * @return un int con los km recorridos por el coche
     */
    public int getKm() {
        return km;
    }
    /**
     * metodo para estableces los km recorridos actuales del vehículo, como solo se pueden sumar
     * en este método sumamos a los actuales los km del parametro
     * @param km el numero de kilometros recorridos extras, tipo int
     * @version 2.0 cambiamos que ya no se suman los km, sino que se sustituyen
     */
    public void setKm(int km) {
        
        this.km = km;
    }
    /**
     * metodo para obtener la fecha de matriculacion del vehiculo
     * @return un dato tipo LocalDate con la fecha de matriculacion del vehiculo
     */
    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }
    /**
     * metodo para establecer la fecha de matriculacion del vehiculo
     * @param fechaMatriculacion es la fecha que le pasamos al metodo, tipo LocalDate
     */
    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }
    /**
     * metodo que usamos para obtener la descripcion del vehiculo
     * aqui entra el metodo especial para establecer esta descripcion, queremos que 
     * esta se actualize si se pide, por si se han cambiado algunos datos de los que este
     * campo informa. Por tanto cuando llamamos al método, el mismo método primero 
     * actualiza la descripcion del objeto y luego la devuleve.
     * @return descripcion un String con la descipcion del vehiculo
     */
    public String getDescripcion() {
        
        String auxDescripcion;
        auxDescripcion="Vehículo Marca "+marca+". Matrícula: "+matricula+". Kilómetros del vehículo: "+km+".";
        this.descripcion=auxDescripcion;
        return descripcion;
    }
    /**
     * metodo para establecer la descripcion del vehiculo
     * @param descripcion la descripcion que le pasamos, tipo String
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * metodo para obtener el precio del vehiculo
     * @return precio el precio actual del vehículo, tipo float
     */
    public float getPrecio() {
        return precio;
    }
    /**
     * metodo para establecer el precio del vehiculo
     * @param precio el precio que le pasamos, tipo float
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    /**
     * metodo para obtener el nombre del propietario del vehiculo
     * @return nombrePropietario que es el nombre actual del propietario, tipo String
     */
    public String getNombrePropietario() {
       return nombrePropietario;
    }
    /**
     * metodo para establecer el nombre del propietario del vehiculo
     * @param nombrePropietario el nombre del propietario que queremos que tenga el vehiculo, tipo String
     */
    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }
    /**
     * metodo para obtener el DNI del propietario del vehiculo
     * @return DNI el DNI del propietario actual del vehiculo, tipo String
     */
    public String getDNI() {
        return DNI;
    }
    /**
     * metodo para establecer el DNI del propietario del vehiculo
     * @param DNI el DNI nuevo del propietario que queremos que tenga el vehiculo, tipo String
     */
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    
    
}
