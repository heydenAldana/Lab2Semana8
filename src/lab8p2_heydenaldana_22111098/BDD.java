package lab8p2_heydenaldana_22111098;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class BDD 
{
    // atributos para base de datos
    private File cestrella, cjugador, cpartida;
    private RandomAccessFile rpartida, restrella, rjugador;
    
    /*
     * RPARTIDA (String nombre, String fechacreacion
     * RESTRELLA (int distancia, String descripcion, Strign nombre)
     * RJUGADOR (double velocidad, String nombre)
    */
    
    // Crear una partida con dos jugadores y dos estrellas
    public void crearPrimerRegistro() throws IOException
    {
        try
        {
            // crea las carpetas y registra
            cpartida = new File("/partidas");
            if(cpartida.mkdirs())
            {
                rpartida = new RandomAccessFile("/partidas/partida.nin", "rw");
                rpartida.seek(rpartida.length());
                rpartida.writeUTF("Ciudad Morioh");
            }
            
            cestrella = new File("/estrellas");
            if(cestrella.mkdirs())
            {
                restrella = new RandomAccessFile("/estrellas/estrella.nin", "rw");           
                restrella.seek(restrella.length());
                restrella.writeInt(1000);
                restrella.writeUTF("La bella estrella de queen");
                restrella.writeUTF("Killer Queen");
                restrella.writeInt(2000);
                restrella.writeUTF("La bella estrella de la vida: queen");
                restrella.writeUTF("Life Queen");
            }
            
            cjugador = new File("/jugadores");
            if(cjugador.mkdirs())
            {
                rjugador = new RandomAccessFile("/jugadores/player.nin", "rw");           
                rjugador.seek(rjugador.length());
                rjugador.writeDouble(25);
                rjugador.writeUTF("James");
                rjugador.writeDouble(55);
                rjugador.writeUTF("Josuke"); 
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR:\n" + e);
        }
    }
    
    
    // crear partida
    public boolean crearPartida(String nombre) throws IOException
    {
        try
        {
            if(verificarNombrePartida(nombre))
                return false;
            rpartida = new RandomAccessFile("/partidas/partida.nin", "rw");
            rpartida.seek(rpartida.length());
            rpartida.writeUTF(nombre);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            rpartida.writeUTF(dtf.format(LocalDateTime.now()));
            return true;
        } catch (IOException e) {
            
        }
        return false;
    }
    
    
    // ----------------------------- VERIFICADORES DE REGISTROS ------------- //
    
    private boolean verificarNombrePartida(String nombre) throws IOException
    {
        try
        {
            rpartida = new RandomAccessFile("/partidas/partida.nin", "rw");
            rpartida.seek(0);
            for (int i = 0; i < rpartida.length(); i++) 
            {
                if(rpartida.readUTF().equals(nombre))
                    return true;
                rpartida.readUTF();
            }
        } catch (IOException e) {
            
        }
        return false;
    }
    
//    
//    private boolean verificarNombreEstrella(String nombreestrella) throws IOException
//    {
//        try
//        {
//            restrella = new RandomAccessFile("/partidas/estrella.nin", "rw");
//            restrella.seek(0);
//            for (int i = 0; i < restrella.length(); i++) 
//            {
//                if(restrella.readUTF().equals(nombreestrella))
//                    return true;
//                restrella.readUTF();
//            }
//        } catch (IOException e) {
//            
//        }
//        return false;
//    }
//    
//    
//    private boolean verificarNombreJugador(String nombre) throws IOException
//    {
//        try
//        {
//            rjugador = new RandomAccessFile("/partidas/player.nin", "rw");
//            rjugador.seek(0);
//            for (int i = 0; i < rjugador.length(); i++) 
//            {
//                rjugador.readDouble();
//                rjugador.readUTF();
//                if(rjugador.readUTF().equals(nombre))
//                    return true;
//            }
//        } catch (IOException e) {
//            
//        }
//        return false;
//    }
//    
//    
    // -------------------------------------------------------------------------- //
    
    // editar partida
    public boolean editarPartida(String nombre, String nombrem) throws IOException
    {
        try
        {
            rpartida = new RandomAccessFile("/partidas/partida.nin", "rw");
            rpartida.seek(0);
            for (int i = 0; i < rpartida.length(); i++) 
            {
                if(rpartida.readUTF().equals(nombre))
                {
                    long aqui = rpartida.getFilePointer();
                    rpartida.seek(aqui);
                    rpartida.writeUTF(nombrem);
                    return true;
                }
                rpartida.readUTF();
            }
        } catch (IOException e) {
            
        }
        return false;
    }
    
    
    // eliminar partida
    public boolean eliminarPartida(String nombre) throws IOException
    {
        try
        {
            rpartida = new RandomAccessFile("/partidas/partida.nin", "rw");
            rpartida.seek(0);
            for (int i = 0; i < rpartida.length(); i++) 
            {
                if(rpartida.readUTF().equals(nombre))
                {
                    long aqui = rpartida.getFilePointer();
                    rpartida.seek(aqui);
                    rpartida.writeUTF("");
                    rpartida.writeUTF("");
                    return true;
                }
                rpartida.readUTF();
            }
        } catch (IOException e) {
            
        }
        return false;
    }
    
    
    // agregar estrella a x partida
    public boolean agregarEstrellaaPartida(String nombrepartida, int distancia, String descripcion, String nombreestrella) throws IOException
    {
        try
        {
            if(verificarNombrePartida(nombrepartida))
            {
                restrella = new RandomAccessFile("/estrellas/estrella.nin", "rw");           
                restrella.seek(0);
                for (int i = 0; i < restrella.length(); i++) 
                {
                    restrella.readInt();
                    restrella.readUTF();
                    if(restrella.readUTF().equals(nombreestrella))
                        return false;
                }
                
                // lo agrega
                restrella.seek(restrella.length());
                restrella.writeInt(distancia);
                restrella.writeUTF(descripcion);
                restrella.writeUTF(nombreestrella);
            }
            else 
            {
                return false;
            }
        } catch (IOException e) {
            
        }
        
        return true;
    }
    
    
    // agregar jugador a x partida
    public boolean agregarJugadoraPartida(String nombrepartida, double velocidad, String nombrejugador) throws IOException
    {
        try
        {
            if(verificarNombrePartida(nombrepartida))
            {
                rjugador = new RandomAccessFile("/jugadores/player.nin", "rw");           
                rjugador.seek(0);
                for (int i = 0; i < rjugador.length(); i++) 
                {
                    rjugador.readDouble();
                    if(rjugador.readUTF().equals(nombrejugador))
                        return false;
                }
                
                // lo agrega
                rjugador.seek(rjugador.length());
                rjugador.writeDouble(velocidad);
                rjugador.writeUTF(nombrejugador);
            }
            else 
            {
                return false;
            }
        } catch (IOException e) {
            
        }
        
        return true;
    }
    
    
    // Esta funcion obtiene un arraylist de los elementos de partida
    // para los combobox
    private ArrayList<String> obtenerTotalPartidas() throws IOException
    {
        ArrayList<String> totalpartidas =  new ArrayList<String>();
        try
        {
            
            rpartida = new RandomAccessFile("/partidas/partida.nin", "rw");
            rpartida.seek(0);
            for (int i = 0; i < rpartida.length(); i++) 
            {
                totalpartidas.add(rpartida.readUTF());
                rpartida.readUTF();
            }
        } catch (IOException e) {
            
        }
        return totalpartidas;
    }
    
    
    // Esta funcion obtiene un arraylist de los elementos de estrella
    // para los combobox
    private ArrayList<String> obtenerTotalEstrellas() throws IOException
    {
        ArrayList<String> totalestrellas =  new ArrayList<String>();
        try
        {
            
            restrella = new RandomAccessFile("/estrellas/estrella.nin", "rw");
            restrella.seek(0);
            for (int i = 0; i < restrella.length(); i++) 
            {
                restrella.readInt();
                restrella.readUTF();
                totalestrellas.add(restrella.readUTF());
            }
        } catch (IOException e) {
            
        }
        return totalestrellas;
    }
    
    
    // Esta funcion obtiene un arraylist de los elementos de estrella
    // para los combobox
    private ArrayList<String> obtenerTotalJugadores() throws IOException
    {
        ArrayList<String> totalply =  new ArrayList<String>();
        try
        {
            
            rjugador = new RandomAccessFile("/jugadores/player.nin", "rw");
            rjugador.seek(0);
            for (int i = 0; i < rjugador.length(); i++) 
            {
                rjugador.readDouble();
                totalply.add(restrella.readUTF());
            }
        } catch (IOException e) {
            
        }
        return totalply;
    }
    
    
    
    // cargar combobox de partidas
    private JComboBox cb_partida, cb_estrella, cb_jugador;
    
    public void rellenacbpartida(JComboBox cb_partida) throws IOException
    {
        ArrayList<String> cosas = obtenerTotalPartidas();
        cb_partida.setModel(new DefaultComboBoxModel(cosas.toArray()));
        cosas.clear();
    }
    
    public void rellenacbestrella(JComboBox cb_estrella) throws IOException
    {
        ArrayList<String> cosas = obtenerTotalEstrellas();
        cb_estrella.setModel(new DefaultComboBoxModel(cosas.toArray()));
        cosas.clear();
    }
    
    public void rellenacbjugadores(JComboBox cb_jugador) throws IOException
    {
        ArrayList<String> cosas = obtenerTotalJugadores();
        cb_jugador.setModel(new DefaultComboBoxModel(cosas.toArray()));
        cosas.clear();
    }
}
