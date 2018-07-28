package services;

import modelos.Album;

public class AlbumServices extends GestionDb<Album> {

    private static AlbumServices instancia;

    public AlbumServices() {
        super(Album.class);
    }

    public static AlbumServices getInstancia() {
        if (instancia == null) {
            instancia = new AlbumServices();
        }
        return instancia;
    }
}
