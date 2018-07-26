package rutas;

import jdk.nashorn.internal.ir.RuntimeNode;

import modelos.Usuario;
import services.UsuarioServices;
import spark.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import static spark.Spark.*;

public class RutasImagen {

public void rutas(){
    File uploadDir = new File("fotos");
    uploadDir.mkdir();//por si el folder no esta

    staticFiles.externalLocation("fotos");


    get("/foto/perfil/", (req, res) ->
            "<form method='post' enctype='multipart/form-data'>" // note the enctype
                    + "    <input type='file' name='foto' accept='.png'>" // make sure to call getPart using the same "name" in the post
                    + "    <button>Upload picture</button>"
                    + "</form>"
    );


    post("/foto/perfil/", (req, res) -> {
        String img = guardarImagen("foto", uploadDir, req);
        Usuario usr = UsuarioServices.getLogUser(req);
        usr.setFotoPerfil(img);
        UsuarioServices.getInstancia().actualizarUsuario(usr);
        res.redirect("/perfil?usuario=" + usr.getId());
        return "";
    });

    post("/foto/portada/", (req, res) -> {
        String img = guardarImagen("foto", uploadDir, req);
        Usuario usr = UsuarioServices.getLogUser(req);
        usr.setFotoPortada(img);
        UsuarioServices.getInstancia().actualizarUsuario(usr);
        res.redirect("/perfil?usuario=" + usr.getId() );
        return "";
    });

}


    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }


    public static String guardarImagen(String campo, File uploadDir,  Request req) throws IOException, ServletException {

        Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");

        req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

        try (InputStream input = req.raw().getPart(campo).getInputStream()) {

            System.out.println(req.raw().getPart(campo).getSubmittedFileName());

            if(req.raw().getPart(campo).getSubmittedFileName().isEmpty()){
                return "-1";
            }
            Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (ServletException e) {
            e.printStackTrace();
            return "-1";
        }

        return "/" + tempFile.getFileName().toString();
    }

}
