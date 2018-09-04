package Clases.Principales;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaPrestamos;
import Clases.DataBases.TablaUsuarios;
import Clases.Estados.Estado;

public class Reserva extends Prestamo{

    protected int idDocente;

    public Reserva(int id, String fecha, int idHorario, int idEspacio, int idDocente, Estado estado) {
        super(id,fecha,idEspacio,idHorario,estado);
        this.idHorario=idHorario;
        this.idEspacio = idEspacio;
        this.idDocente = idDocente;
    }


    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }

    public void setHorario(Horario horario) {
        idHorario = horario.getId();
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }
/*Hacer

    public void cancelar(){
        estado.cancelar();
    }*/

    public void setEspacio(Espacio espacio) {
        idEspacio = espacio.getID();
    }

    public boolean guardarPrestamo() {
        return TablaPrestamos.insertReserva(this, DBController.getDB());
    }

    public void setResponsable(Docente responsable) {
        idDocente = responsable.getId();
    }


    public Docente getResponsable() {
        return ((Docente)TablaUsuarios.findUsuario(idDocente,DBController.getDB()));
    }

    public int getIdDocente() {
        return idDocente;
    }
}
