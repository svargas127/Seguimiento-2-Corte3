package org.carlosYvargas.Domain;


import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Aviones")
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String nombreAvion;

    @Column(nullable = false)
    private int capacidadAvion;

    @Column(nullable = false)
    private int aniosAvion;

    @OneToMany(mappedBy = "avion")
    private List<Vuelo> vuelos;

    @Transient
    private int usado;

    @Transient
    private boolean usadoBase;



    public Avion(String nombreAvion, int capacidadAvion, int aniosAvion) {
        this.nombreAvion = nombreAvion;
        this.capacidadAvion = capacidadAvion;
        this.aniosAvion = aniosAvion;
    }

    public Avion() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreAvion() {
        return nombreAvion;
    }

    public void setNombreAvion(String nombreAvion) {
        this.nombreAvion = nombreAvion;
    }

    public int getCapacidadAvion() {
        return capacidadAvion;
    }

    public void setCapacidadAvion(int capacidadAvion) {
        this.capacidadAvion = capacidadAvion;
    }

    public int getAniosAvion() {
        return aniosAvion;
    }

    public void setAniosAvion(int aniosAvion) {
        this.aniosAvion = aniosAvion;
    }


    public int getUsado() {
        return usado;
    }

    public void setUsado(int usado) {
        this.usado = usado;
    }

    public boolean isUsadoBase() {
        return usadoBase;
    }

    public void setUsadoBase(boolean usadoBase) {
        this.usadoBase = usadoBase;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }






    @Override
    public String toString() {
        return "Informacion del avion: " +
                "ID del avion = |" + id + "|\t" +
                "Modelo del avion |" + nombreAvion + "|\t" +
                "Años de vida del avion |" + aniosAvion + "|\t" +
                "Capacidad de pasajeros |" + capacidadAvion + "|\t" +
                "Vuelo asignado |" + vuelos + "|\t";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avion avion = (Avion) o;
        return id == avion.id && capacidadAvion == avion.capacidadAvion && aniosAvion == avion.aniosAvion && Objects.equals(nombreAvion, avion.nombreAvion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreAvion, capacidadAvion, aniosAvion);
    }
}
