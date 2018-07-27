package Clases.Principales;

import java.util.LinkedList;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaHorarios;

/**
 * Created by gonza on 16/07/18.
 */

public class Horario {

    protected int id;
    protected String horaInicio;
    protected String horaFin;
    protected int idPrestamo;
    protected LinkedList<String> diasSemana;

    public Horario(int id, String horaInicio, String horaFin, int idPrestamo, LinkedList<String> diasSemana){
        this.id=id;
        this.horaInicio=horaInicio;
        this.horaFin=horaFin;
        this.idPrestamo=idPrestamo;
        this.diasSemana=diasSemana;

        if(this.id==999)
            this.id= TablaHorarios.getNextID(DBController.getDB());

    }

    public boolean guardarHorario(){
        return TablaHorarios.insertHorario(this,DBController.getDB());
    }

    public int getId() {
        return id;
    }

    public LinkedList<String> getDiasSemana() {
        return diasSemana;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public String getHoraInicio() {
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

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setId(int id) {
        this.id = id;
    }
}
