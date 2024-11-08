package org.carlosYvargas.Domain;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Vuelos")
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String origen;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private String fechaSalida;

    @ManyToOne
    @JoinColumn(name = "avion_id")
    private Avion avion;


    public Vuelo(String origen, String destino, String fechaSalida, Avion avion) {
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.avion = avion;
    }

    public Vuelo() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    @Override
    public String toString() {
        return "Informacion del vuelo: " +
                "ID del vuelo = |" + id + "|\t" +
                "Origen|" + origen + "|\t" +
                "Destino |" + destino + "|\t" +
                "Fecha de salida |" + fechaSalida + "|\t" +
                "Avion asignado |" + avion.getNombreAvion() + "|\t";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return id == vuelo.id && Objects.equals(origen, vuelo.origen) && Objects.equals(destino, vuelo.destino) && Objects.equals(fechaSalida, vuelo.fechaSalida) && Objects.equals(avion, vuelo.avion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, origen, destino, fechaSalida, avion);
    }
}
