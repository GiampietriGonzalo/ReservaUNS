package Clases.DataBases;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    private static String DB_PATH = "/data/data/Clases.DataBases/databases/";
    private static DBController dbC;
    private DBHelper dbHelper;
    private static SQLiteDatabase sqlDB;



    private DBController(Context context) {
        dbHelper = new DBHelper(context);
        // Esto es para inicializar la BD
        try {
            dbHelper.createDataBase();
            dbHelper.openDataBase();

        } catch (IOException e) {
            System.out.println("Error en create or open database");
        }
        sqlDB=dbHelper.getWritableDatabase();

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
        return TablaEstadosPrestamos.findEstadoPrestamo(idEstadoPrestamo,sqlDB);
    }

    public static boolean insertPrestamoActivo(EstadoPrestamo estado){
        return TablaEstadosPrestamos.insertPrestamoActivo(estado,sqlDB);
    }

    public static boolean insertPrestamoCancelado(EstadoPrestamo estado){
        return TablaEstadosPrestamos.insertPrestamoCancelado(estado,sqlDB);
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




    private class DBHelper extends SQLiteOpenHelper{

        private final Context myContext;
        private SQLiteDatabase myDataBase=null;

        public DBHelper(Context context){
            super(context, DB_NAME,null, DB_VERSION);
            myContext=context;
            //super(context, name, factory, version);
        }

        public void onCreate(SQLiteDatabase db){
            //No se hace nada acá
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


        public void createDataBase() throws IOException {

            boolean dbExist = checkDataBase();

            if (dbExist) {
                // Si existe, no se hace nada
            } else {
                // Llamando a este método se crea la base de datos vacía en la ruta
                // por defecto del sistema de nuestra aplicación por lo que
                // podremos sobreescribirla con nuestra base de datos.
                this.getReadableDatabase();

                try {

                    copyDataBase();

                } catch (IOException e) {

                    throw new Error("Error copiando database");
                }
            }
        }


        private boolean checkDataBase() {

            SQLiteDatabase checkDB = null;

            try {
                String myPath = DB_PATH + DB_NAME;
                checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

            } catch (SQLiteException e) {
                System.out.println("Base de datos no creada todavia");
            }

            if (checkDB != null) {
                checkDB.close();
            }

            return checkDB != null ? true : false;

        }

        private void copyDataBase() throws IOException {

            OutputStream databaseOutputStream = new FileOutputStream("" + DB_PATH + DB_NAME);
            InputStream databaseInputStream;

            byte[] buffer = new byte[1024];
            int length;

            databaseInputStream = myContext.getAssets().open("BD.sql");
            length=databaseInputStream.read(buffer);

            while (length > 0) {
                databaseOutputStream.write(buffer);
            }

            databaseInputStream.close();
            databaseOutputStream.flush();
            databaseOutputStream.close();
        }


        public synchronized void close() {

            if (myDataBase != null)
                myDataBase.close();

            super.close();
        }

        public void openDataBase() throws SQLException {

            // Open the database
            String myPath = DB_PATH + DB_NAME;
            myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }


    }




}
