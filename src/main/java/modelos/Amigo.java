package modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Amigo {

    @Id
    @GeneratedValue
    long id;

    
    private long usuarioid;

    @Column(columnDefinition =  "boolean default false")
    boolean confirmado;



}
