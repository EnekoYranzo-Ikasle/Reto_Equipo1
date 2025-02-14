package Modelo;

import javax.swing.*;
import java.util.ArrayList;

public class CompeticionDAO {
    private ArrayList<Competicion> listaCompeticiones;
    private ArrayList<Jornada> listaJornadas;

    public CompeticionDAO() {
        listaCompeticiones = new ArrayList<>();
        listaJornadas = new ArrayList<>();
    }

    public void agregarCompeticion(Competicion competicion) {
        listaCompeticiones.add(competicion);
    }

    public boolean agregarJornadaACompeticion(String codCompe, Jornada jornada) {
        Competicion competicion = buscarCompeticion(codCompe);

        if (competicion != null) {
            competicion.agregarJornada(jornada);
            return true;
        }
        return false;
    }

    public void modificarCompeticion(Competicion competicion) {
        boolean continuar = true;

        while(continuar) {
            for(Competicion comp : listaCompeticiones ) {
                if(comp.getCodCompe().equals(competicion.getCodCompe())) {
                    comp.setNombre(competicion.getNombre());
                    comp.setFecha_inicia(competicion.getFechaInicio());
                    comp.setFecha_fin(competicion.getFecha_fin());
                    comp.setEstado(competicion.getEstado());

                    continuar = false;
                }
            }
        }
    }

    public void eliminarCompeticion(Competicion competicion) {
        listaCompeticiones.remove(competicion);
    }

    public Competicion buscarCompeticion(String cod) {
        for(Competicion competicion : listaCompeticiones) {
            if(competicion.getCodCompe().equals(cod)) {
                return competicion;
            }
        }
        return null;
    }
    public void listarCompeticiones() {
        StringBuilder sbCompes = new StringBuilder("Listado de competiciones:\n\n");

        for (Competicion competicion : listaCompeticiones) {
            sbCompes.append("Competición: ").append(competicion.getCodCompe()).append("\n");

            if (competicion.getListaJornadas() != null && !competicion.getListaJornadas().isEmpty()) {
                sbCompes.append("Jornadas:\n");
                for (Jornada jornada : competicion.getListaJornadas()) {
                    sbCompes.append(" - ").append(jornada.toString()).append("\n");
                }
            } else {
                sbCompes.append("Sin jornadas registradas\n");
            }

            sbCompes.append("\n");
        }
        JOptionPane.showMessageDialog(null, sbCompes.toString());
    }

}
