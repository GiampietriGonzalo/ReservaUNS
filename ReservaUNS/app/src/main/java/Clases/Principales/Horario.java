package Clases.Principales;

import java.util.LinkedList;

/**
 * Created by gonza on 16/07/18.
 */

public class Horario {

    protected int id;
    protected String horaInicio;
    protected String horaFin;
    protected LinkedList<String> diasSemana;

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
