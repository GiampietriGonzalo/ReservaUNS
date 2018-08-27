package Clases.Principales;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaHorarios;

/**
 * Created by gonza on 16/07/18.
 */

public class Horario {

    protected int id;
    protected int horaInicio;
    protected int horaFin;
    protected int idPrestamo;
    protected LinkedList<String> diasSemana;

    public Horario(int id, int horaInicio, int horaFin, int idPrestamo, LinkedList<String> diasSemana){


        this.horaInicio=horaInicio;
        this.horaFin=horaFin;
        this.idPrestamo=idPrestamo;
        this.diasSemana=diasSemana;

        if(id==9999)
            this.id= TablaHorarios.getNextID(DBController.getDB());
        else
            this.id=id;

    }


    public int getId() {
        return id;
    }

    public LinkedList<String> getDiasSemana() {
        return diasSemana;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public void setDiasSemana(LinkedList<String> diasSemana) {
        this.diasSemana = diasSemana;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String horaInicioConFormato(){

        String toReturn;
        if(horaInicio%100==0)
            toReturn=horaInicio/100+":00";
        else
            toReturn=horaInicio/100+":"+horaInicio%100;

        return toReturn;
    }

    public String horaFinConFormato(){

        String toReturn;

        if(horaFin%100==0)
            toReturn=horaFin/100+":00";
        else
            toReturn=horaFin/100+":"+horaFin%100;

        return toReturn;
    }
}
