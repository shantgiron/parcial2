package rutas;


import modelos.Publicacion;
import modelos.Usuario;
import services.PublicacionServices;
import services.UsuarioServices;
import spark.ModelAndView;
import spark.Session;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.net.PortUnreachableException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class ManejoRutasShant {
    public void rutas() {
        File uploadDir = new File("fotos");
        uploadDir.mkdir();


        //Usuario admin por defecto
        if (new UsuarioServices().getUsuarioByEmail("admin@admin.com") == null) {
            Usuario usuario = new Usuario();
            usuario.setPassword("admin");
            usuario.setNombre("Shantall");
            usuario.setApellido("Giron");
            usuario.setCorreo("admin@admin.com");
            usuario.setLugar_nacimiento("Santiago");
            usuario.setCiudad_residencia("Santiago");
            usuario.setFotoPerfil("/img/badge4.png");
            usuario.setFotoPortada("img/top-header1.jpg");
            usuario.setAdmin(true);

            new UsuarioServices().crearUsuario(usuario);
        }

        get("/login", (request, response) -> {
            Map<String, Object> modelo = new HashMap<>();

            String mensaje = "";

            if (request.queryMap().hasKeys() ) {
                if (request.queryMap("register").booleanValue())
                    mensaje = "Usuario registrado satisfactoriamente.";
            }

            modelo.put("mensaje", mensaje);

            return renderThymeleaf(modelo,"/login");
        });

        post("/login", (request, response) -> {
            boolean iniciarsesion = Boolean.parseBoolean(request.queryParams("iniciarsesion"));

            if (iniciarsesion) {
                String correo = request.queryParams("correo");
                String contrasena = request.queryParams("contrasena");

                Usuario user = new UsuarioServices().autenticarUsuario(correo, contrasena);

                if (user == null) {
                    request.session().invalidate();
                    response.redirect("/login");
                } else {
                    try {
                        if("on".equalsIgnoreCase(request.queryParams("recordar"))){
                            response.cookie("/", "usuario", Long.toString(user.getId()), 7*24*60*60*1000, false);
                            response.cookie("/", "pass", user.getPassword(), 7*24*60*60*1000, false);
                        }else{
                            response.cookie("/", "usuario", Long.toString(user.getId()), 0, false);
                            response.cookie("/", "pass", Long.toString(user.getId()), 0, false);
                        }
                    }catch (Exception e){  }

                    Session session = request.session(true);
                    session.attribute("usuario", user);
                    response.redirect("/index");
                }





            } else {
                Usuario usuario = new Usuario();

                String nombre = request.queryParams("nombre");
                String apellido = request.queryParams("apellido");
                String correo = request.queryParams("correo");
                String contrasena = request.queryParams("contrasena");
                String cumpleanos = request.queryParams("cumpleanos");

                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setCorreo(correo);
                usuario.setPassword(contrasena);
                usuario.setFecha_nacimiento(new SimpleDateFormat("mm/dd/yyyy").parse(cumpleanos));

                usuario.setFotoPerfil("/img/badge3.png");
                usuario.setFotoPortada("/img/top-header1.jpg");
                usuario.setAdmin(true);

                new UsuarioServices().crearUsuario(usuario);

                Usuario user =  UsuarioServices.getInstancia().getUsuarioByEmail(correo);

                Session session = request.session(true);
                session.attribute("usuario", user);
                response.redirect("/inicio");

                response.redirect("/login?register=true");
            }

            return null;
        });

        get("/index", (request, response) -> {
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("publicaciones", new PublicacionServices().listaPublicacion());
            Usuario u = UsuarioServices.getLogUser(request);
            modelo.put("usuario", u);
            return renderThymeleaf(modelo,"/index");
        });

        post("/inicio", (request, response) -> {

            //super importante, para leer los campos ya se se codifican diferente gracias a la imagen
            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            String descripcion = request.queryParams("descripcion");

            Publicacion publicacion = new Publicacion();

            publicacion.setDescripcion(descripcion);
            publicacion.setUsuario(UsuarioServices.getLogUser(request));
            publicacion.setFecha(new Date());

            String img = RutasImagen.guardarImagen("foto", uploadDir, request);

            publicacion.setImg(img);

            publicacion.setMuro_de(UsuarioServices.getLogUser(request).getId());

            if(img != "-1") publicacion.setNaturaleza("FOTO");


            PublicacionServices.getInstancia().crear(publicacion);


            response.redirect("/inicio");

            return "";
        });

        get("/perfil", (request, response) -> {
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("usuario", UsuarioServices.getLogUser(request));

            return renderThymeleaf(modelo,"/perfilUsuario");
        });

        get("/cerrarsesion", (request, response) -> {
            request.session().invalidate();
            response.redirect("/login");
            return null;
        });
        
         /*  post("/borrarPublicacion", (request, response) -> {
            PublicacionServices as = new PublicacionServices();
            Session session = request.session(true);
            int publicacionid = Integer.parseInt(request.queryParams("publicacionid"));
            int usuarioid = (int)( (Usuario)session.attribute("usuario")).getId();
            as.borrarPublicacion(publicacionid, usuarioid);
            response.redirect("/inicio");
            return "";
        });

        get("/editarPublicacion", (request, response)->{
            int id = Integer.parseInt(request.queryParams("id"));
            PublicacionServices as = new PublicacionServices();
            Publicacion publicacion = as.getPublicacion((long) id);
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("registeredUser", getLogUser(request));
            modelo.put("publicacion", publicacion);
            String etiqueta = "";

            return renderThymeleaf(modelo, "/editarPublicacion");
        });


        post("/editarPublicacion", (request, response) -> {
            PublicacionServices as = new PublicacionServices();
            String descripcion = request.queryParams("descripcion");
            String usuario = request.queryParams("usuario");
            String tags = request.queryParams("etiquetas");
            String img = request.queryParams("img")
            long id = (long) Integer.parseInt( request.queryParams("id"));

            as.actualizarPublicacion(descripcion, usuario, tags, id);

            response.redirect("/index");
            return "";
        });


        private Usuario getLogUser(Request request){

            Usuario usuario = new Usuario();
            Session session = request.session(true);

            if(request.cookie("usuario") != null){
                UsuarioServices us = new UsuarioServices();
                usuario = us.getUsuario(Integer.parseInt(request.cookie("usuario")));
                session.attribute("usuario", usuario);
            }

            if(session.attribute("usuario") != null) usuario = session.attribute("usuario");

            return usuario;
        }*/
    }

    // Declaración para simplificar el uso del motor de template Thymeleaf.
    public static String renderThymeleaf(Map<String, Object> model, String templatePath) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
