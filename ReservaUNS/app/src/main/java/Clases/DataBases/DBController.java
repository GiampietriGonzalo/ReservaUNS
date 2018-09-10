package Clases.DataBases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.LinkedList;

import Clases.Estados.Aceptado;
import Clases.Estados.Activo;
import Clases.Estados.Cancelado;
import Clases.Estados.Estado;
import Clases.Estados.Rechazado;
import Clases.Principales.Aula;
import Clases.Principales.Edificio;
import Clases.Principales.Espacio;
import Clases.Principales.Horario;
import Clases.Principales.Prestamo;
import Clases.Principales.Solicitud;
import Clases.Principales.Usuario;

/**
 * Created by gonza on 16/07/18.
 */

public class DBController {

    private final static int DB_VERSION= 1;
    private final static String DB_NAME="DB.sqlite";
    private static DBController dbC;
    private DBHelper dbHelper;
    private static SQLiteDatabase sqlDB,sqlDBW;
    private static Context myContext;

    private DBController(Context context) {

        dbHelper = new DBHelper(context);
        myContext=context;

        sqlDB=dbHelper.getReadableDatabase();
        sqlDBW= dbHelper.getWritableDatabase();

    }



    public static SQLiteDatabase getDB() {
        return sqlDB;
    }

    public static DBController getDBController(Context context) {
        if(dbC==null)
            dbC=new DBController(context);
        return dbC;
    }

    public static Usuario findUsuario(int idUsuario){
        return TablaUsuarios.findUsuario(idUsuario,sqlDB);
    }

    public static LinkedList<Espacio> findEspaciosAReservar(String tipo,String nombreEdificioPreferencia, int capacidadEstimada){

        LinkedList<Espacio> espacios;
        int idEdificioPreferencia= TablaEdificios.getIdEdificio(nombreEdificioPreferencia,sqlDB);

        if(nombreEdificioPreferencia.equals("Edificio"))
            espacios=TablaEspacios.findEspaciosAReservarSinEdificioPreferencia(tipo,capacidadEstimada,sqlDB);
        else
            espacios = TablaEspacios.findEspaciosAReservar(tipo, idEdificioPreferencia, capacidadEstimada, sqlDB);

        return espacios;
    }

    public static LinkedList<Edificio> getEdificios(){
        return TablaEdificios.getEdificios(sqlDB);
    }

    public static Espacio findEspacio(int idEspacio){
        return TablaEspacios.findEspacio(idEspacio,sqlDB);
    }


    public Espacio findAula(String nombreAula){

         Aula toReturn=null;

        if (nombreAula!=null && nombreAula!="")
            toReturn = TablaEspacios.findAula(nombreAula, sqlDB);


         return toReturn;

    }

    public static LinkedList<Solicitud> getSolicitudes(){
        return TablaSolicitudes.getSolicitudes(sqlDB);
    }

    public static LinkedList<Prestamo> getPrestamos(){
        return TablaPrestamos.getPrestamos(sqlDB);
    }


    public static boolean actualizarPrestamo(Prestamo prestamo){
        return TablaPrestamos.actualizarPrestamo(prestamo,sqlDBW);
    }

    /**
     * @return True si se ha eliminado la solicitud indicada, false en caso contrario
     * */
    public static boolean cancelarSolicitud(int idSolicitud){
        return TablaSolicitudes.cancelarSolicitud(idSolicitud, sqlDBW);
    }

    public static void actualizarEstadoSolicitud(Solicitud solicitud){
        TablaSolicitudes.actualizarEstado(solicitud,sqlDB);
    }
    
    /**
     * @return True si se ha eliminado el prestamo indicado, false en caso contrario
     * */
    public static boolean cancelarPrestamo(int idPrestamo){
        return TablaPrestamos.eliminarPrestamo(idPrestamo, sqlDBW);
    }

    public static boolean eliminarHorario(int idHorario){
        return TablaHorarios.eliminarHorario(idHorario,sqlDB);
    }


    public static LinkedList<Solicitud> findSolicitudesUsuario(int idUsuario){
        return TablaSolicitudes.findSolicitudesUsuario(idUsuario,sqlDB);
    }


    public static Edificio findEdificio(int idEdificio){
        return TablaEdificios.findEdificio(idEdificio,sqlDB);
    }


    public static LinkedList<Integer> findEspaciosDeEdificio(int idEdificio){
       return TablaEspacios.findEspacios(idEdificio, sqlDB);
    }

    public static Edificio findDepartamento(int idDepartamento){
        return TablaEdificios.findDepartamento(idDepartamento, sqlDB);
    }

    public static boolean insertSolicitudReserva(Solicitud reserva){
        return TablaSolicitudes.insertSolicitudReserva(reserva,sqlDB);
    }

    public static boolean insertSolicitudAsignacion(Solicitud asignacion){
        return TablaSolicitudes.insertSolicitudReserva(asignacion,sqlDB);
    }

    public static boolean insertHorario(Horario h){
        return TablaHorarios.insertHorario(h,sqlDB);
    }

    public static Horario findHorario(int idHorario){
        return TablaHorarios.findHorario(idHorario,sqlDB);
    }

    public static boolean insertReserva(Prestamo prestamo){
        return TablaPrestamos.insertReserva(prestamo,sqlDB);
    }

    public static boolean insertAsignacion(Prestamo prestamo){
        return TablaPrestamos.insertAsignacion(prestamo,sqlDB);
    }

    public static Prestamo findPrestamo(int idPrestamo){
        return TablaPrestamos.findPrestamo(idPrestamo,sqlDB);
    }

    public static Solicitud findSolicitud(int idSolicitud){
        return TablaSolicitudes.findSolicitud(idSolicitud,sqlDB);
    }

    public static boolean insertDocente(Usuario docente){
        return TablaUsuarios.insertDocente(docente,sqlDB);
    }

    public static boolean insertEmpleadoDepartamento(Usuario empleado){
        return TablaUsuarios.insertEmpleadoDepartamento(empleado,sqlDB);
    }

    public static boolean insertEmpleadoSecretaria(Usuario empleado){
        return TablaUsuarios.insertEmpleadoSecretaria(empleado,sqlDB);
    }

    public static LinkedList<Horario> findHorariosEspacio(Espacio e){
        return TablaHorarios.findHorariosEspacio(e,sqlDB,getDBController(myContext));
    }

    public static Usuario verificarLogIn(String mail, String contraseña){
        return TablaUsuarios.verificarLogIn(mail,contraseña,sqlDB);
    }

    public void open(){
        sqlDB =dbHelper.getWritableDatabase();
    }

    public void close(){
        sqlDB.close();
    }


    private class DBHelper extends SQLiteAssetHelper{

        private final Context myContext;
        private SQLiteDatabase myDataBase=null;

        public DBHelper(Context context){
            super(context, DB_NAME,context.getExternalFilesDir(null).getAbsolutePath(),null,DB_VERSION);
            myContext=context;
            //super(context, name, factory, version);
        }


    }


}
