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

    /**
     * Obtiene la instancia única del repositorio
     */
    public static MotoRepository getInstancia() {
        if (instancia == null) {
            instancia = new MotoRepository();
        }
        return instancia;
    }

    /**
     * Carga algunos productos de ejemplo
     */
    private void cargarDatosEjemplo() {
        motos.add(new Moto("2025","BFU 80E","Yamaha","2026"));
    }

    /**
     * Obtiene todos los productos
     */
    public ArrayList<Moto> getMotos() {
        return motos;
    }

    /**
     * Agrega un nuevo producto
     */
    public void agregarMoto(Moto moto) {
        moto.add(moto);
    }

    /**
     * Elimina un producto
     */
    public boolean eliminarMoto(Moto moto) {
        return motos.remove(moto);
    }

    /**
     * Busca un producto por código
     */
    public Moto buscarMotoPorPlaca(String placa) {
        for(Moto m : motos) {
            if(m.getPlaca().equals(placa)) {
                return m;
            }
        }
        return null;
    }
    /**
     * Obtiene la cantidad de productos
     */
    public int getCantidadProductos() {
        return motos.size();
    }
}
