package org.carlosYvargas.Application;

import org.carlosYvargas.Application.Services.Service;
import org.carlosYvargas.Domain.Avion;
import org.carlosYvargas.Domain.Vuelo;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Visual implements Runnable{
    private final Service service;

    public Visual(Service service,Service service2) {
        this.service = service;
    }


    public void iniciar(){
        while (true) {
            try {
                while (true) {
                    int opcion = Integer.parseInt(JOptionPane.showInputDialog("Elija una opcion valida: \n" +
                            "1. Agregar aviones \n" +
                            "2. simular vuelos  \n" +
                            "3.Salir del programa  "));

                    switch (opcion) {
                        case 1 -> generarAviones();
                        case 2 -> run();
                        case 3 -> {
                            return;
                        }
                        default -> JOptionPane.showMessageDialog(null, "Opcion no valida");
                    }

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Dato erroneo ingresado");
            }
        }
    }


    public void generarAviones(){
        String[] opcionesCliente = {"Registrar de forma aleatoria","Registrar de forma manual"};
        int opcion = JOptionPane.showOptionDialog(null, "Desea registrar los aviones de forma aleatoria o hacerlo de forma manual?", "Registro de aviones", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionesCliente, opcionesCliente[0]);
        switch(opcion){
            case 0:
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cantidad de aviones que desee registrar: "));
                if(cantidad > 0){
                    aviones(cantidad);
                }
                else JOptionPane.showMessageDialog(null,"No puede ingresar una cantidad menor a cero");
        }
    }

    @Override
    public synchronized void  run() {

        enum VuelosOrigenAleatorios{
            COLOMBIA,
            EEUU,
            MEXICO,
            PERU,
            RUSIA
        }

        enum VuelosDestinoAleatorios{
            ESPAÑA,
            INDIA,
            ALEMANIA,
            ECUADOR,
            CHINA
        }

        enum VuelosFechasAleatorios{
            DIA1("2024//01/12"),
            DIA2("2024//04/01"),
            DIA3("2024//07/20"),
            DIA4("2024//10/02"),
            DIA5("2024//11/27");

            String fecha;

            VuelosFechasAleatorios(String fecha) {
                this.fecha = fecha;
            }
        }


        System.out.println("Preparando aviones...");
        System.out.println("Agregando combustible...");
        System.out.println("Abordando equipaje...");

        List<Avion> avionesListos = service.listar();


        if (avionesListos.isEmpty()) {
            System.out.println("No hay aviones listos para partir.");
            return;
        }

        Random rand  = new Random();
        Random rand2 = new Random();
        Random rand3 = new Random();

        for (Avion avion : avionesListos) {

            if (avion.getUsado() == 0 ){

                VuelosOrigenAleatorios vueloAsignado = VuelosOrigenAleatorios.values()[rand.nextInt(VuelosOrigenAleatorios.values().length)];
                VuelosDestinoAleatorios vueloLlegada =  VuelosDestinoAleatorios.values()[rand2.nextInt( VuelosDestinoAleatorios.values().length)];
                VuelosFechasAleatorios vueloFecha =  VuelosFechasAleatorios.values()[rand3.nextInt(VuelosFechasAleatorios.values().length)];



                System.out.println("Avion: " + avion.getNombreAvion() + " listo para partir hacia: " + vueloAsignado + " desde: " + vueloLlegada);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.err.println("Error en la simulación de atención: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }

                Vuelo registro = new Vuelo(vueloAsignado.name(),vueloLlegada.name(),vueloFecha.fecha,avion);
                service.registrarObject(registro);

            }  else System.out.println("No hay aviones listos para partir.");


        }

        System.out.println("Vuelos liberados para todos los aviones.");



    }

    public void aviones(int cantidad){

        enum AvionesAleatorios{
            AirbusA320(2,50),
            Boeing737(10,20),
            Antonov225Mriya(1,100),
            Airbus321(7,1500);


            int anios;
            int capacidad;

            AvionesAleatorios(int anios, int capacidad) {
                this.anios = anios;
                this.capacidad = capacidad;
            }
        }


        for (int i = 0; i < cantidad; i++){
            Random rand = new Random();
            int numeroAvion = rand.nextInt(5) -1;


            for(AvionesAleatorios aviones : AvionesAleatorios.values()){
                Avion avion = new Avion();
                if(numeroAvion == aviones.ordinal()){
                    avion.setNombreAvion(aviones.name());
                    avion.setCapacidadAvion(aviones.capacidad);
                    avion.setAniosAvion(aviones.anios);
                    service.registrarObject(avion);
                }

            }



        }




    }



}
