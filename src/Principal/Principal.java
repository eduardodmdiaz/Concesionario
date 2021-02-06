package Principal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Objetos.*;
import PROG06_Util.ValidaUtil;
import java.time.LocalDate;
import java.util.Scanner;

       
/**
 * Clase principal desde la que leemos desde el teclado y se encarga de usar los métodos 
 * de las clases de validacion y de la clase Concesionario, no usa la clase Vehiculo
 * @author Edu
 * @version 1.0 del 02-02-2021
 */
public class Principal {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int opcion;
        Scanner lectura = new Scanner (System.in);/**Aquí guardaremos la lectura de la linea auxiliarmente*/
        Concesionario conc=new Concesionario();
        boolean salida=false;
        String matriculaEntrada;
        boolean validaMatricula;
        int kmActualizar;
        String datos;
        int controladorInsercion;
        /**
         * Usamos un do-while para seguir mostrando el menu hasta que ponemos a true la
         * variable salir, que significa que hemos usado la opcion 5
         */
        do {            
            opcion=menu();
            switch (opcion){
                case 1:
                    /**
                     * La opción 1 es para insertar un vehículo en el concesionario
                     * desde este metodo controlamos si el metodo opcionUno(conc) nos da -2,
                     * será que el vehículo identificado con esa matricula ya existe, si da
                     * -1 es que el concesionario esta lleno y si da 0 sera que ha sido insertado con exito.
                     */
                    controladorInsercion=opcionUno(conc);
                    if (controladorInsercion==-2) {
                        System.out.println("La matrícula para ese vehículo ya existe");
                    } else {
                        if (controladorInsercion==-1) {
                            System.out.println("El concesionario ya está lleno, no se puede insertar un nuevo vehículo");
                        } else {
                            System.out.println("Vehículo insertado con éxito");
                        }
                    }
                    break;
                case 2:
                    /**
                     * Con esta opcion primero miramos si existen ya vehiculos en el concesionario
                     * y si ya los tiene, imprime una lista de los vehiculos en el 
                     * concesionario.
                     */
                    if(conc.getNumeroVehiculos()==0){
                        System.out.println("El concesionario aún no tiene vehículos.");
                    } else{
                        conc.listaVehiculos();
                    }
                    break;
                case 3:
                    /**
                     * Con esta opcion miramos si existen vehiculos en el concesionario
                     * y si ya los tiene, entonces pedimos la matricula y buscamos el vehiculo
                     * que tiene esa matricula en el concesionario, si no hay lo mostramos por pantalla
                     * y si lo hay, mostramos sus datos por pantalla.
                     */
                    if(conc.getNumeroVehiculos()==0){
                        System.out.println("El concesionario aún no tiene vehículos.");
                    } else{
                        System.out.println("Introduzca la matrícula del vehículo a buscar");
                        matriculaEntrada=lectura.nextLine();
                        datos=conc.buscaVehiculo(matriculaEntrada);
                        if (datos==null) {
                            System.out.println("No existe vehículo con la matrícula introducida");
                        } else {
                            System.out.println(datos);
                        }
                    }
                    break;
                case 4:
                    /**
                     * Con esta opcion miramos si existen vehiculos en el consecionario
                     * y si ya los tiene, entonces pedimos la matricula del vehiculo a 
                     * modificar los kms, verificamos si es valida, si no lo es seguimos
                     * pidiendo hasta que lo sea, una vez lo es,pedimos los kms que queremos 
                     * nuevos, si hay un vehiculo con esa matricula, los cambiamos
                     * si no hay vehiculo con esa matricula, lo mostramos por pantalla.
                     */
                    if(conc.getNumeroVehiculos()==0){
                        System.out.println("El concesionario aún no tiene vehículos.");
                    } else{
                        do {                        
                            System.out.println("--------------------------------------");
                            System.out.println("Introduzca la matrícula del vehículo a modificar");
                            matriculaEntrada=lectura.nextLine();
                            validaMatricula=ValidaUtil.validarMatricula(matriculaEntrada);
                            if (!validaMatricula) {
                                System.out.println("La matrícula no es válida, vuelva a intentarlo.");
                            } else {
                                System.out.println("Matrícula válida");
                            }
                        } while (!validaMatricula);
                        System.out.println("Introduzca el numero de km para actualizar al vehículo");
                        kmActualizar=lectura.nextInt();
                        lectura.nextLine();
                        validaMatricula=conc.actualizaKms(matriculaEntrada, kmActualizar);
                        if (!validaMatricula) {
                            System.out.println("El vehículo no se encuentra en el concesionario. No se ha podido actualizar");
                        }
                    }
                    
                    break;
                case 5:
                    /**
                     * Esta es la opcion para dar la salida del menú, lo hacemos poniendo
                     *  a true el boolean que controla el do-while.
                     */
                    salida=true;
                    break;
            }
            
        } while (!salida);
        
        
    }
    /**
     * Método que se encarga de convertir los datos introducidos por el usuario
     * en una fecha del tipo LocalDate, controla la excepcion que puede dar si los tipos
     * de la fecha tienen errores y la vuelve a lanzar 
     * @param anioMatriculacion año de matriculacion del vehiculo
     * @param mesMatriculacion mes de matriculacion del vehiculo
     * @param diaMatriculacion dia de matriculacion del vehiculo
     * @return el objeto LocalDate de los atributos que metemos
     * @throws Exception si tienen errores los datos de la fecha
     */
    private static LocalDate conversorFecha (int anioMatriculacion, int mesMatriculacion, int diaMatriculacion) throws Exception{
        /**
         * 
         * @param anioMatriculacion es el año introducido por el usuario, tipo int
         * @param mesMatriculacion es el mes introducido por el usuario, tipo int
         * @param diaMatriculacion es el dia introducido por el usuario, tipo int
         * @return date la fecha formada por los parámetros, de tipo LocalDate
         */
        LocalDate date;
        try {
            date=LocalDate.of(anioMatriculacion, mesMatriculacion, diaMatriculacion);
        } catch (Exception e){
            throw new Exception();
            
        }
        
        return date;
    }
    /**
     * Método que sirve para mostrar el menú del sistema, creado por tener más claridad,
     * se encarga de imprimir el menú por pantalla y se leer la entrada por teclado de la opcion
     * seleccionada, luego devuelve el valor de esa eleccion en tipo int.
     * @return opcion es la opcion elegida por el usuario, tipo int
     */
    private static int menu(){
        
        Scanner lectura = new Scanner (System.in);/**Aquí guardaremos la lectura de la linea auxiliarmente*/
        int opcion;
        
        System.out.println("--------------------------------");
        System.out.println("Bienvenido al concesionario:");
        System.out.println("--------------------------------");
        System.out.println("1. Nuevo vehículo.");
        System.out.println("2. Listar Vehículos.");
        System.out.println("3. Buscar Vehículo.");
        System.out.println("4. Modificar kms del Vehículo.");
        System.out.println("5. Salir.");
        System.out.println("--------------------------------");
        System.out.println("Escoja su opción:");
        opcion=lectura.nextInt();
        lectura.nextLine();
        return opcion;
    }
    /**
     * Es un metodo creado para que el switch tenga mejor visibilidad, se encarga de pedir los datos 
     * y de capturarlos, llama a los validadores y luego inserta el vehiculo en el array, le pasamos 
     * de parametro nuestro objeto concesionario para que pueda llamar al metodo que tiene la clase
     * para insertar el vehiculo, devuelve -1 si el concesionario esta lleno, -2 si el coche ya
     * existe por su matricula en el concesionario y 0 si se inserta el vehiculo con exito
     * @param conc concesionario sobre el que insertar vehiculo
     * @return int que nos diga si el conc esta lleno (-1) si ya hay un coche con esa matricula (-2) o si ha sido bien insertado (0)
     */
    private static int opcionUno(Concesionario conc){
        Scanner lectura = new Scanner (System.in);/**Aquí guardaremos la lectura de la linea auxiliarmente*/
        /**
         * A partir de aquí están los atributos locales del método que serán los que formen el
         * objeto coche, todos introducidos por el usuario a excepcion de fechaMatriculacion
         * que es un atributo formado mediante el método conversorFecha con los datos introducidos
         * por el usuario
         */
        String marca;
        String matricula;
        int km;
        int anio;
        int mes;
        int dia;
        LocalDate fechaMatriculacion;
        float precio;
        String nombre;
        String id;
        int resultadoID;
        /**
         * A partir de aquí tenemos los atributos que nos darán un control sobre la validez
         * de los datos introducidos por el usuario
         * 
         * Las validaciones se hacen en la Clase ValidaUtil,
         * validoKm nos indicará usando el metodo ValidaUtil.ValidaKm(km) si los km son válidos
         * validoFecha nos indicara usando el metodo ValidaUtil.validaFecha(fechaMatriculacion) si la fecha es valida
         * Este metodo tambien llamará a ValidaUtil.validarDNIoNIE(id) Siempre que un dato no 
         * es valido, de una manera u otra, seguirá pidiendo datos hasta llegar a uno valido
         * @return un int que nos dice si el concesionario esta lleno, si existe ya el vehiculo
         * con esa matricula o si ha sido insertado con exito
         */
        boolean validoKm;
        boolean validoMatricula;
        boolean validoFecha;
        int resultadoMetodo;
        boolean validoNombre;
        boolean validoID;
        
        System.out.println("Creación de nuevo vehículo");
        System.out.println("Introduzca la Marca del vehículo");
        marca=lectura.nextLine();
        /**
         * Aquí comienza la parte donde pedimos la matrícula del vehiculo y controlamos que sea
         * valida con el metodo validarMatricula(matricula) de manera que si no es valida seguiremos
         * en el bucle do-while hasta que consigamos una fecha valida.
         */
        do {
        System.out.println("Introduzca la matrícula del vehículo");
        matricula=lectura.nextLine();
        validoMatricula=ValidaUtil.validarMatricula(matricula);
            if (!validoMatricula) {
                System.out.println("La matrícula no es válida, vuelva a intentarlo.");
            } else {
                System.out.println("Matrícula válida");
            }
        } while (!validoMatricula);
        /**
         * Aquí comienza la parte donde pedimos los km, para saber si los km son validos
         * llamaremos al metodo ValidaUtil.validaKm(km) que nos dará un boolean que 
         * guardamos auxiliarmente en validoKm, si ese boolean es false, entonces los
         * km no son válido y por tanto seguimos dentro del bloque do-while para seguir
         * pidiendo datos hasta que sean validos.
         */
        do {            
            System.out.println("Introduzca los kilómetros recorridos por el vehículo");
            km=lectura.nextInt();
            lectura.nextLine();
            validoKm=ValidaUtil.validaKm(km);
            if (!validoKm) { 
                System.out.println("Los kilómetros no son válidos");
            }else{
                System.out.println("Kilómetros validados");
            }
        } while (!validoKm);
        
        /**
         * Aquí comenzamos a pedir los datos de matriculacion del Vehiculo, primero los almacenamos
         * llamamos al metodo conversorFecha(anio,mes,dia) que nos puede dar una excepcion si la fecha
         * no tiene el formato valido para que sea transformada en un objeto LocalDate, si la fecha
         * no es valida, el do-while seguirá pidiendola hasta tener una válida, cuando es válida
         * la guardamos en fechaMatriculacion de manera que tenemos la fecha
         * esa fecha la mandamos a ValidaUtil.validaFecha(fechaMatriculacion) de manera que obtenemos otro 
         * boolear auxiliar que almacenamos en validoFecha, como en el caso anterior
         * si ese boolean es false, volvemos a mandar la señal de no salir del do-while y seguir pidiendo datos.
         */
        do {            
            System.out.println("Introduzca el año de matriculación");
            anio=lectura.nextInt();
            lectura.nextLine();
            System.out.println("Introduzca el mes de matriculación");
            mes=lectura.nextInt();
            lectura.nextLine();
            System.out.println("Introduzca el dia de matriculación");
            dia=lectura.nextInt();
            lectura.nextLine();
            try {
                fechaMatriculacion=conversorFecha(anio,mes,dia);
                validoFecha=ValidaUtil.validaFecha(fechaMatriculacion);
            } catch (Exception e) {
                fechaMatriculacion=null;
                validoFecha=false;
            }
            if (!validoFecha) { 
                System.out.println("La fecha no es válida");
            }else{
                System.out.println("Fecha de matriculación válida");
            }
        } while (!validoFecha);
        /**
         * Aquí pedimos el precio del vehículo, no controlamos si es valido.
         */
        System.out.println("Introduzca el precio del vehículo");
        precio=lectura.nextFloat();
        lectura.nextLine();
        /**
         * Comenzamos a pedir el nombre del propietario y para eso llamamos a validarNombre(nombre)
         * que nos lanza una excepcion si no es valido que capturamos, seguimos pidiendo
         * datos por pantalla hasta que tengamos uno que nos sirva.
         */
        do {            
            System.out.println("Introduzca el nombre del propietario");
            nombre=lectura.nextLine();
            try {
                ValidaUtil.validarNombre(nombre);
                System.out.println("El nombre es válido");
                validoNombre=true;
            } catch (Exception e) {
                System.out.println("El nombre no es valido");
                validoNombre=false;
            }
        } while (!validoNombre);
        
        /**
         * Aquí comenzamos a pedir el dato del DNI del propietario,
         * Una vez leido el dato, lo hacemos validar con ValidaUtil.validarDNIoNIE(id), de manera
         * que, al ser un metodo int, nos da 1 si es DNI valido, 2 si es NIE y 0 si no es nada de eso
         * lo mostramos por pantalla, si no es valido, seguirá pidiendo el dato hasta que
         * lo sea.
         */
        do {            
            System.out.println("Introduzca el DNI o NIE del propietario");
            id=lectura.nextLine();
            resultadoID=ValidaUtil.validarDNIoNIE(id);
            if (resultadoID==1) {
                System.out.println("El Identificador es un DNI válido");
                validoID=true;
            } else {
                if (resultadoID==2) {
                    System.out.println("El Identificador es un NIE válido");
                    validoID=true;
                } else {
                    System.out.println("No es un DNI ni es un NIE");
                    validoID=false;
                }
            }
        } while (!validoID);
        
        /**
         * Como siempre que hay algún error en los datos leidos volvemos a pedirlos en el metodo
         * significa que si hemos llegado a esta linea de codigo, es porque no hay ningun
         * error en los datos del metodo y por tanto, todos los datos son validos, por
         * tanto llamamos al constructor de la clase Concesionario y creamos nuestro Concesionario conc,
         * El método devuelve un int que nos dice el caso que ha ocurrido al insertar el 
         * vehiculo en el array, 
         */
        resultadoMetodo=conc.insertarVehiculo(marca, matricula, km, fechaMatriculacion, precio, nombre, id);
        return resultadoMetodo;
    }   
}
