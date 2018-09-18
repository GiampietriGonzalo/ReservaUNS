package Clases.Estados;

/**
 * Created by gonza on 18/09/18.
 */

public class DeBaja extends Estado {



        public DeBaja(){
            super(1);
        }

        public Estado aceptar() {
            return null;
        }

        public Estado cancelar() {
            return null;
        }

        public Estado rechazar() {
            return null;
        }

        public String toString(){return "Dado de baja";}

        public Estado finalizar(){return null;}

        public Estado darDeBaja(){return this;}


}
