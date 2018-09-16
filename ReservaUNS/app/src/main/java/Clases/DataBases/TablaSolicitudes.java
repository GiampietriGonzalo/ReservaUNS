package Clases.DataBases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;
import java.util.Iterator;
import java.util.LinkedList;

import Clases.Estados.Aceptado;
import Clases.Estados.Activo;
import Clases.Estados.Cancelado;
import Clases.Estados.Estado;
import Clases.Estados.Rechazado;
import Clases.Estados.StateController;
import Clases.Principales.Solicitud;
import Clases.Principales.SolicitudAsignacion;
import Clases.Principales.SolicitudReserva;

public class TablaSolicitudes extends Tabla {

    private static final String[] columns={Columns.Id,Columns.IdAutor, Columns.IdHorarios, Columns.Fecha, Columns.IdEspacio, Columns.CapacidadEstimada,Columns.Tipo,Columns.Estado};


    public static LinkedList<Solicitud> findSolicitudesUsuario(int idUsuario, SQLiteDatabase db){

        LinkedList<Solicitud> solicitudes=new LinkedList<Solicitud>();
        JSONObject jsonIdHorarios;
        LinkedList<Integer> idHorarios;
        Estado estado;

        Cursor cursor=db.query("Solicitudes",columns,Columns.IdAutor +" = '"+idUsuario+"'",null,null,null,null);

        try {
            while (!cursor.isClosed() && cursor.moveToNext()) {

                estado=getEstado(cursor);
                jsonIdHorarios = new JSONObject(cursor.getString(2));
                idHorarios = new LinkedList<Integer>();
                Iterator<String> iteratorJsonHorarios = jsonIdHorarios.keys();

                while (iteratorJsonHorarios.hasNext()) {
                    idHorarios.add(jsonIdHorarios.getInt(iteratorJsonHorarios.next()));
                }

                switch (cursor.getString(6)) {

                    case "SolicitudReserva": {
                        solicitudes.addLast(new SolicitudReserva(cursor.getInt(0), cursor.getInt(1), idHorarios, cursor.getString(3), cursor.getInt(4), cursor.getInt(5),estado));
                        break;
                    }

                    case "SolicitudAsignacion": {
                        solicitudes.addLast(new SolicitudAsignacion(cursor.getInt(0), cursor.getInt(1), idHorarios, cursor.getString(3), cursor.getInt(4), cursor.getInt(5),estado));
                        break;
                    }

                } //Fin switch
            } //Fin while
        }
        catch (JSONException e){
            Log.e("E7","Error en JsonObject de idHorarios en findSolicitudesUsuario");
            e.printStackTrace();
        }

        return solicitudes;

    }

    public static Solicitud findSolicitud(int idSolicitud,SQLiteDatabase db){

        Solicitud toReturn=null;
        JSONObject jsonIdHorarios;
        LinkedList<Integer> idHorarios;
        Estado estado;

        Cursor cursor=db.query("Solicitudes",columns,Columns.Id +" = '"+idSolicitud+"'",null,null,null,null);

        try {
            while (!cursor.isClosed() && cursor.moveToNext()) {

                estado=getEstado(cursor);
                jsonIdHorarios = new JSONObject(cursor.getString(3));
                idHorarios = new LinkedList<Integer>();
                Iterator<String> iteratorJsonHorarios = jsonIdHorarios.keys();

                while (iteratorJsonHorarios.hasNext()) {
                    idHorarios.add(jsonIdHorarios.getInt(iteratorJsonHorarios.next()));
                }

                switch (cursor.getString(6)) {

                    case "SolicitudReserva": {
                        toReturn = new SolicitudReserva(cursor.getInt(0), cursor.getInt(1), idHorarios, cursor.getString(3), cursor.getInt(4), cursor.getInt(5),estado);
                        break;
                    }

                    case "SolicitudAsignacion": {
                        toReturn = new SolicitudAsignacion(cursor.getInt(0), cursor.getInt(1), idHorarios, cursor.getString(3), cursor.getInt(4), cursor.getInt(5),estado);
                        break;
                    }

                }//Fin switch
            }
        }
        catch (JSONException e){
            Log.e("E7","Error en JsonObject de idHorarios en findSolicitud");
            e.printStackTrace();
        }

        return toReturn;
    }

    public static boolean eliminarSolicitud(int idSolicitud, SQLiteDatabase db){

        boolean exito=false;

        Cursor cursor=db.query("Solicitudes",columns,Columns.Id +" = '"+idSolicitud+"'",null,null,null,null);

        if (cursor.moveToFirst()){
            exito= db.delete("Solicitudes",Columns.Id + " = ?",new String[]{""+idSolicitud}) > 0;}

        return exito;
    }

    public static LinkedList<Solicitud> getSolicitudes(SQLiteDatabase db){

        LinkedList<Solicitud> solicitudes= new LinkedList<Solicitud>();
        Solicitud aux=null;
        JSONObject jsonIdHorarios;
        LinkedList<Integer> idHorarios;
        Estado estado;

        Cursor cursor=db.query("Solicitudes",columns,null,null,null,null,null);

        try {

            while (!cursor.isClosed() && cursor.moveToNext()) {

                estado=getEstado(cursor);
                jsonIdHorarios = new JSONObject(cursor.getString(2));
                idHorarios= new LinkedList<Integer>();
                Iterator<String> iteratorJsonHorarios=jsonIdHorarios.keys();

                while(iteratorJsonHorarios.hasNext()) {
                    idHorarios.add(jsonIdHorarios.getInt(iteratorJsonHorarios.next()));
                }

                switch (cursor.getString(6)) {

                    case "SolicitudReserva": {
                        aux = new SolicitudReserva(cursor.getInt(0), cursor.getInt(1), idHorarios, cursor.getString(3), cursor.getInt(4), cursor.getInt(5),estado);
                        break;
                    }

                    case "SolicitudAsignacion": {
                        aux = new SolicitudAsignacion(cursor.getInt(0), cursor.getInt(1), idHorarios, cursor.getString(3), cursor.getInt(4), cursor.getInt(5),estado);

                    }

                }
                if(aux!=null)
                    solicitudes.addLast(aux);
            }
        }
        catch (JSONException e){
            Log.e("E7","Error en JsonObject de idHorarios en getSolicitudes");
            e.printStackTrace();
        }


        return  solicitudes;

    }

    private static ContentValues guardarValores(Solicitud solicitud){

        JSONObject idHorarios= new JSONObject();
        int i=0;
        ContentValues values= new ContentValues();

        try {

            for (int idHorario : solicitud.getHorarios()) {
                idHorarios.put("" + i, idHorario);
                i++;
            }

            values.put("Id",solicitud.getId());
            values.put("IdAutor",solicitud.getIdAutor());
            values.put("IdHorarios",idHorarios.toString());
            values.put("Fecha",solicitud.getFecha());
            values.put("IdEspacio",solicitud.getIdEspacio());
            values.put("CapacidadEstimada",solicitud.getCapacidadEstimada());
            values.put("Tipo","SolicitudReserva");
            values.put("Estado",solicitud.getEstadoString());

        }
        catch (JSONException e){
            Log.e("E7","Error en JsonObject de idHorarios en insertSoliciudReserva");
            e.printStackTrace();
        }

        return values;
    }


    public static boolean insertSolicitudReserva(Solicitud solicitud, SQLiteDatabase db){


        ContentValues values= guardarValores(solicitud);

        return db.insert("Solicitudes",null,values) >0 ;
    }

    public static boolean insertSolicitudAsignacion(Solicitud solicitud, SQLiteDatabase db){

        JSONObject idHorarios= new JSONObject();
        int i=0;
        ContentValues values= new ContentValues();

        try {

            for (int idHorario : solicitud.getHorarios()) {
                idHorarios.put("" + i, idHorario);
                i++;
            }

            values.put("Id",solicitud.getId());
            values.put("IdAutor",solicitud.getIdAutor());
            values.put("IdHorarios",idHorarios.toString());
            values.put("Fecha",solicitud.getFecha());
            values.put("IdEspacio",solicitud.getIdEspacio());
            values.put("CapacidadEstimada",solicitud.getCapacidadEstimada());
            values.put("Tipo","SolicitudAsignacion");
        }
        catch (JSONException e){
            Log.e("E7","Error en JsonObject de idHorarios en insertSoliciudAsignacion");
            e.printStackTrace();
        }

        return db.insert("Solicitudes",null,values) >0 ;
    }

    public static int getNextID(SQLiteDatabase db){
        return getNextID(db,"Solicitudes",columns);
    }

    public static Estado getEstado(Cursor cursor) {

        Estado estado = null;

        switch (cursor.getString(7)) {

            case "Aceptado": {
                estado = StateController.getEstadoAceptado();
                break;
            }

            case "Cancelado": {
                estado = StateController.getEstadoCancelado();
                break;
            }

            case "Rechazado": {
                estado = StateController.getEstadoRechazado();
                break;
            }

            case "Activo": {
                estado = StateController.getEstadoActivo();
                break;
            }


        }
        return estado;
    }


    public static boolean actualizarEstado(Solicitud solicitud, SQLiteDatabase db){
        return db.update("Solicitudes",guardarValores(solicitud),Columns.Id +" = "+solicitud.getId(),null) >0;
    }

    private static class Columns implements BaseColumns {

        public static final String Id= "Id";
        public static final String IdAutor = "IdAutor";
        public static final String IdHorarios = "IdHorarios";
        public static final String Fecha = "Fecha";
        public static final String IdEspacio = "IdEspacio";
        public static final String CapacidadEstimada = "CapacidadEstimada";
        public static final String Tipo= "Tipo";
        public static final String Estado="Estado";
    }


}
