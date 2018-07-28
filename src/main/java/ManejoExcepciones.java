import static spark.Spark.exception;
import static spark.Spark.get;

public class ManejoExcepciones {


    public void manejoExcepciones(){

        exception(NumberFormatException.class, (e, request, response) -> {
            response.status(500);
            response.body("Error convertiendo un número....");
            e.printStackTrace();
        });

        //otras, ahora la aplicacion nunca estallará :)
        exception(Exception.class, (e, request, response) -> {
            response.status(500);
            response.body(e.getMessage());
            e.printStackTrace();
        });


    }
}