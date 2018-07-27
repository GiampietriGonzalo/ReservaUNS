package Clases.Principales;

import Clases.DataBases.DBController;
import Clases.DataBases.TablaEstadosReservas;
import Clases.DataBases.TablaPrestamos;
import Clases.DataBases.TablaUsuarios;
import Clases.Estados.EstadoPrestamo;
import Clases.Estados.PrestamoActivo;

public class Reserva extends Prestamo{

    protected int idDocente;

    public Reserva(int id, String descripcion,String fecha, int idHorario, int idEspacio, int idDocente) {
        super(id,descripcion,fecha,idEspacio,idHorario);
        this.idHorario=idHorario;
        this.idEspacio = idEspacio;
        this.idDocente = idDocente;
        EstadoPrestamo aux = new PrestamoActivo(TablaEstadosReservas.getNextID(DBController.getDB()),id);
        this.idEstado = aux.getId();
    }


    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }

    public void setHorario(Horario horario) {
        idHorario = horario.getId();
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
