package Clases.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorTreeAdapter;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import java.util.ArrayList;
import java.util.LinkedList;

import Clases.Estados.EstadoPrestamo;
import Clases.Estados.EstadoSolicitud;
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
    private final static String DB_NAME="DB";
    private static DBController dbC;
    private DBHelper dbHelper;
    private static SQLiteDatabase sqlDB;

    private DBController(Context context) {
        dbHelper = new DBHelper(context);
        sqlDB=dbHelper.getWritableDatabase();
    }

    public static SQLiteDatabase getDB() {
        return sqlDB;
    }

    public  DBController getDbC(Context context) {
        if(dbC==null)
            dbC=new DBController(context);
        return dbC;
    }

    public static Usuario findUsuario(int idUsuario){
        return TablaUsuarios.findUsuario(idUsuario,sqlDB);
    }


    public static LinkedList<Espacio> findEspaciosAReservar(String tipo,String nombreEdificioPreferencia, int capacidadEstimada){

        LinkedList<Espacio> espacios= new LinkedList<Espacio>();
        int idEdificioPreferencia= TablaEdificios.getIdEdificio(nombreEdificioPreferencia,sqlDB);

        //si idEdificioPreferencia = 9999 --> no se ingresó edificio de preferencia
        if(idEdificioPreferencia==9999)
            espacios= TablaEspacios.findEspaciosAReservarSinEdificioPreferencia(tipo,capacidadEstimada,sqlDB);
        else
            espacios= TablaEspacios.findEspaciosAReservar(tipo,idEdificioPreferencia,capacidadEstimada,sqlDB);

        return espacios;
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

    public static EstadoPrestamo findEstadoPrestamo(int idEstadoPrestamo){
        return TablaEstadosReservas.findEstadoPrestamo(idEstadoPrestamo,sqlDB);
    }

    public static boolean insertPrestamoActivo(EstadoPrestamo estado){
        return TablaEstadosReservas.insertPrestamoActivo(estado,sqlDB);
    }

    public static boolean insertPrestamoCancelado(EstadoPrestamo estado){
        return TablaEstadosReservas.insertPrestamoCancelado(estado,sqlDB);
    }

    public static Edificio findEdificio(int idEdificio){
        return TablaEdificios.findEdificio(idEdificio,sqlDB);
    }

    public static EstadoSolicitud findEstadoSolicitud(int idEstadoSolicitud){
        return TablaEstadosSolicitud.findEstadoSolicitud(idEstadoSolicitud,sqlDB);
    }

    public static boolean insertSolicitudActiva(EstadoSolicitud estado){
        return TablaEstadosSolicitud.insertSolicitudActiva(estado,sqlDB);
    }

    public static boolean insertSolicitudAceptada(EstadoSolicitud estado){
        return TablaEstadosSolicitud.insertSolicitudAceptada(estado,sqlDB);
    }

    public static boolean insertSolicitudRechazada(EstadoSolicitud estado){
        return TablaEstadosSolicitud.insertSolicitudRechazada(estado,sqlDB);
    }

    public static boolean insertSolicitudCancelada(EstadoSolicitud estado){
        return TablaEstadosSolicitud.insertSolicitudCancelada(estado,sqlDB);
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

    public void open(){
        sqlDB =dbHelper.getWritableDatabase();
    }

    public void close(){
        sqlDB.close();
    }




    private class DBHelper extends SQLiteAssetHelper{


        public DBHelper(Context context){
            super(context, DB_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DB_VERSION);
            //super(context, name, factory, version);
        }





        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //db.execSQL("DROP TABLE IF EXISTS " + ProductServiceTable.getName());
            //db.execSQL("DROP TABLE IF EXISTS " + CommercesTable.getName());
            //db.execSQL("DROP TABLE IF EXISTS " + CityTable.getName());
            //db.execSQL("DROP TABLE IF EXISTS " + SectorTable.getName());
            // db.execSQL("drop table if exists " +  CommercesAuxTable.getName());
            //db.execSQL("drop table if exists " + UsersTable.getName());

            //onCreate(db);   // Llamamos al método onCreate para volver a crear las tablas.
        }

        /*public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }*/


    }

}
