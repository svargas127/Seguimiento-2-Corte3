package org.carlosYvargas.Application;

import org.carlosYvargas.Application.Services.Service;
import org.carlosYvargas.InfraEstructure.FileAvionRepository;
import org.carlosYvargas.InfraEstructure.FileVueloRepository;
import org.carlosYvargas.Interface.ObjectRepository;

public class Main {

    public static void main(String[] args) {
        ObjectRepository repository = new FileAvionRepository();
        Service service = new Service(repository);


        ObjectRepository repository2 = new FileVueloRepository();
        Service service2 = new Service(repository2);



        Visual visual = new Visual(service, service2);


        visual.iniciar();

    }



}
