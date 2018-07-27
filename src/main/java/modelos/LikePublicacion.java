package modelos;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class LikePublicacion implements Serializable {

    @Id
    private long publicacionId;

    @Id
    private long usuarioId;

    private int val;

    public LikePublicacion() {}

    public long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(long publicacionId) {
        this.publicacionId = publicacionId;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
