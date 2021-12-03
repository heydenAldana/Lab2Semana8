package lab8p2_heydenaldana_22111098;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class BDD 
{
    // atributos para base de datos
    private ArrayList<Partida> partida = new ArrayList<Partida>();
    private ArrayList<Estrella> estrella; 
    private ArrayList<Jugador> player;
    
    
    // Crear una partida con dos jugadores y dos estrellas
    public void crearPrimerRegistro()
    {
        String p = "Ciudad Morioh";
        partida.add(new Partida(p));
        partida.get(0).setStar(1000, "En un valle enrome con una cuidad\n"
                    + "diminuta pero autosustentable, se\n"
                    + "esconde un misterio, o mejor dicho,\n"
                    + " un caso sin resolver por \nmucho tiempo.", "Star Queen");
        partida.get(0).setStar(800, "Un lugar repleto de hongos, de todo\n"
                    + "tipo, todas las posibilidades en este\n"
                    + "lugar pueden existir, incluso lo ilogico.", "Roca Champiñon");
        partida.get(0).setPly(20, "James");
        partida.get(0).setPly(30, "Toad");
    }
    
    
    // crear partida
    public boolean crearPartida(String nombre)
    {
        for (Partida p : partida) 
        {
            if(p.getNombre().equals(nombre))
                return false;
        }
        partida.add(new Partida(nombre));
        return true;
    }
    
    // editar partida
    public boolean editarPartida(String nombre, String nombrem)
    {
        for (Partida p : partida) 
        {
            if(p.getNombre().equals(nombre))
            {
                p.setNombre(nombrem);
                return true;
            }  
        }
        return false;
    }
    
    // eliminar partida
    public boolean eliminarPartida(String nombre)
    {
        for (int i = 0; i < partida.size(); i++) 
        {
            if(partida.get(i).getNombre().equals(nombre))
            {
                partida.remove(i);
                return true;
            }
        }
        return false;
    }
    
    
    // agregar estrella a x partida
    public boolean agregarEstrellaaPartida(String nombrepartida, int distancia, String descripcion, String nombreestrella)
    {
        for (Partida p : partida) 
        {
            if(p.getNombre().equals(nombrepartida))
            {
                // verifica si hay una estrella con igual nombre
                for (Estrella e : estrella) 
                {
                    if(e.getNombre().equals(nombreestrella))
                        return false;
                }
                p.setStar(distancia, descripcion, nombreestrella);
                return true;
            }
        }
        // no existe dicha partida con ese nombre
        return false;
    }
    
    
    // agregar jugador a x partida
    public boolean agregarJugadoraPartida(String nombrepartida, double velocidad, String nombrejugador)
    {
        for (Partida p : partida) 
        {
            if(p.getNombre().equals(nombrepartida))
            {
                // verifica si hay un jugador con igual nombre
                for (Jugador j : player) 
                {
                    if(j.getNombre().equals(nombrejugador))
                        return false;
                }
                p.setPly(velocidad, nombrejugador);
                return true;
            }
        }
        // no existe dicha partida con ese nombre
        return false;
    }
    
    
    // cargar combobox de partidas
    private JComboBox cb_partida;
    public void rellenacbpartida(JComboBox cb_partida)
    {
        ArrayList<String> cosas = new ArrayList<String>();
        
        for (int i = 0; i < partida.size(); i++) 
        {
            cosas.add(partida.get(i).getNombre());
        }
        
        cb_partida.setModel(new DefaultComboBoxModel(cosas.toArray()));
        cosas.clear();
    }
    
    
    // cargar jTable con elementos de la partida 
}
