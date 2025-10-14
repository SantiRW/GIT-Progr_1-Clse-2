package com.example.preparcial14102025.Repositorios;

import com.example.preparcial14102025.Modelos.Moto;
import java.util.ArrayList;

public class MotoRepository {
    private static MotoRepository instancia;
    private ArrayList<Moto> motos;

    private MotoRepository() {
        motos = new ArrayList<>();
        cargarDatosEjemplo();
    }

    public static MotoRepository getInstancia() {
        if (instancia == null) {
            instancia = new MotoRepository();
        }
        return instancia;
    }

    private void cargarDatosEjemplo() {
        motos.add(new Moto("2025", "BFU 80E", "Yamaha", "2026"));
    }

    public ArrayList<Moto> getMotos() {
        return motos;
    }

    public void agregarMoto(Moto moto) {
        motos.add(moto);
    }

    public boolean eliminarMoto(Moto moto) {
        return motos.remove(moto);
    }

    public Moto buscarMotoPorPlaca(String placa) {
        for(Moto m : motos) {
            if(m.getPlaca().equals(placa)) {
                return m;
            }
        }
        return null;
    }

    public int getCantidadProductos() {
        return motos.size();
    }
}