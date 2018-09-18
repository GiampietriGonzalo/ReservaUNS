package Clases.Estados;

public abstract class Estado {

    int id;
    Estado miEstado;

    protected Estado(int id){
        this.id=id;
    }

    public abstract Estado cancelar();
    public abstract Estado aceptar();
    public abstract Estado rechazar();
    public abstract String toString();
    public abstract Estado finalizar();
    public abstract Estado darDeBaja();

}
