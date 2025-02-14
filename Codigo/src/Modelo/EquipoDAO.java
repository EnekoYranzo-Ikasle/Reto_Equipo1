package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EquipoDAO {
    private final ArrayList<Equipo> listaEquipos;

    // Constructor:
    public EquipoDAO() {
        this.listaEquipos = new ArrayList<>();
        listaEquipos.add(new Equipo("1234", "Team A", LocalDate.of(2000, 5, 15)));
        listaEquipos.add(new Equipo("E2", "Team B", LocalDate.of(2005, 8, 23)));
        listaEquipos.add(new Equipo("E3", "Team C", LocalDate.of(2010, 3, 10)));
        listaEquipos.add(new Equipo("E4", "Team D", LocalDate.of(2015, 12, 1)));
//        listaEquipos.add(new Equipo("E5", "Team E", LocalDate.of(2020, 7, 7)));
    }

    // Funciones:
    public List<Equipo> obtenerEquipos() {
        return listaEquipos;
    }

    public void altaEquipo(Equipo equipo) {
        listaEquipos.add(equipo);
    }

    public void bajaEquipo(Equipo equipo) {
        listaEquipos.remove(equipo);
    }

    private Optional<Equipo> buscarEquipoPorCod(String idEquipo) {
        Optional<Equipo> buscarEquip = listaEquipos.stream().filter(equipoABuscar ->
                        equipoABuscar.getCodEquipo().equals(idEquipo))
                .findFirst();
        return buscarEquip;
    }

    public Equipo buscarEquipoPorNombre(String nombre) {
        return listaEquipos.stream()
                .filter(e -> e.getNombreEquipo().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public Equipo obtenerEquipo(String idEquipo) {
        Optional<Equipo> buscarEquip = buscarEquipoPorCod(idEquipo);
        return buscarEquip.orElse(null);
    }

    public void agregarJugador(Jugador jugador) {
        String idEquipo = jugador.getEquipo().getCodEquipo();

        Optional<Equipo> buscarEquipo = buscarEquipoPorCod(idEquipo);

        buscarEquipo.get().getListaJugadores().add(jugador);
    }

    public void eliminarJugador(Jugador jugador, String idEquipo) {
        Optional<Equipo> buscarEquip = buscarEquipoPorCod(idEquipo);
        buscarEquip.get().bajaJugador(jugador);
    }

    // Verificaciones:
    public Boolean verificarCodigo(String codEquipo) {
        boolean verificacion;

        Optional<Equipo> buscarEquipo = listaEquipos.stream().filter(equipo -> equipo.getNombreEquipo().equals(codEquipo)).findFirst();

        if (buscarEquipo.isPresent())
            verificacion = false;
        else
            verificacion = true;
        return verificacion;
    }

    public Boolean verificarNombre(String nombre) {
        boolean verificacion;

        Optional<Equipo> buscarEquipo = listaEquipos.stream().filter(equipo -> equipo.getNombreEquipo().equals(nombre)).findFirst();

        if (buscarEquipo.isPresent())
            verificacion = false;
        else
            verificacion = true;
        return verificacion;
    }
}

