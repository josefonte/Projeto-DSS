package data;

import business.Circuito;
import business.SegmentoDePista;

import java.sql.*;
import java.util.*;

public class CircuitoDAO implements Map<String , Circuito>{
    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM circuitos")) {
            if(rs.next()) {
                i = rs.getInt(1);
            }
        }
        catch (Exception e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs =
                     stm.executeQuery("SELECT Id FROM circuitos WHERE Num='"+key.toString()+"'")) {
            r = rs.next();
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        Circuito c = (Circuito) value;
        return this.containsKey(c.getNomeCircuito());
    }

    @Override
    public Circuito get(Object key) {
        Circuito c = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM circuitos WHERE NomeCircuito='"+key+"'")) {
            if (rs.next()) {  // A chave existe na tabela
                // Reconstruir a colecção de alunos da turma
                c = new Circuito(rs.getString("NomeCircuito"), rs.getInt("distancia"));
                // Reconstruir a Sala
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return s;
    }

    @Override
    public SegmentoDePista put(String s, SegmentoDePista segmentoDePista) {
        return null;
    }

    @Override
    public Sala put(String key, Sala s) {
        Sala res = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate(
                    "INSERT INTO salas " +
                            "VALUES ('"+s.getNumero()+"', '"+
                            s.getEdificio()+"', "+
                            s.getCapacidade()+")"+
                            "ON DUPLICATE KEY UPDATE Edificio=Values(Edificio), "+
                            "Capacidade=Values(Capacidade)");

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Sala remove(Object o) {
        Sala s = this.get(o);
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            // apagar a turma
            stm.executeUpdate("DELETE FROM salas WHERE Num='" + s.getNumero() + "'");

        }catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return s;
    }

    @Override
    public void putAll(Map<? extends String, ? extends SegmentoDePista> map) {

    }

    @Override
    public void putAll(Map<? extends String, ? extends Sala> map) {
        for(Sala s: map.values()) this.put(s.getNumero(), s);

    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE salas");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<String> keySet() {
        Set<String> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("SELECT Num from salas");
            ResultSet rs = stm.executeQuery("SELECT Num FROM salas");
            while(rs.next()){
                String idt = rs.getString("Num");
                res.add(idt);
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Collection<Sala> values() {
        Collection<Sala> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Num FROM salas")) { // ResultSet com os ids de todas as turmas
            while (rs.next()) {
                String idt = rs.getString("Num"); // Obtemos um id de turma do ResultSet
                Sala s = this.get(idt);                    // Utilizamos o get para construir as turmas uma a uma
                res.add(s);                                 // Adiciona a turma ao resultado.
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Set<Entry<String, Sala>> entrySet() {
        Map<String,Sala> res = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT Num FROM salas")) { // ResultSet com os ids de todas as turmas
            while (rs.next()) {
                String idt = rs.getString("Num"); // Obtemos um id de turma do ResultSet
                Sala s = this.get(idt);                    // Utilizamos o get para construir as turmas uma a uma
                res.put(idt,s);                                 // Adiciona a turma ao resultado.
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res.entrySet();
    }
}