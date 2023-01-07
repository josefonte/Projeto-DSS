package data;

import business.*;
import jdk.jshell.execution.Util;

import java.sql.*;
import java.util.*;

public class UtilizadorDAO implements Map<String, Utilizador> {
    private static UtilizadorDAO singleton = null;
    private UtilizadorDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String participantes = "CREATE TABLE IF NOT EXISTS participantes (" +
                    "idParticipante int NOT NULL PRIMARY KEY," +
                    "pontuacao int NOT NULL,"+
                    "afinacoesRestantes int NOT NULL," +
                    "voltasTotais int NOT NULL,"+
                    "localizacaoPista int NOT NULL," +
                    "tempos int NOT NULL FOREIGN KEY," +
                    "campeonato_nome VARCHAR(45) NOT NULL FOREIGN KEY," +
                    "utilizador_nome VARCHAR(45) NOT NULL FOREIGN KEY," +
                    "carro_id int NOT NULL FOREIGN KEY," +
                    "piloto_nome VARCHAR(45) NOT NULL FOREIGN KEY," +
                    "corrida_id int NOT NULL FOREIGN KEY," +
                    "corrida_circuito_nome VARCHAR(45) NOT NULL FOREIGN KEY)";
            stm.executeUpdate(participantes);
            String sql = "CREATE TABLE IF NOT EXISTS utilizadores (" +
                    "nomeUtilizador varchar(45) NOT NULL PRIMARY KEY," +
                    "pontosRanking int NOT NULL,"+
                    "tipoUtilizador ENUM ('Admin','Jogador','Convidado','Bot')";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }
    public static UtilizadorDAO getInstance() {
        if (UtilizadorDAO.singleton == null) {
            UtilizadorDAO.singleton = new UtilizadorDAO();
        }
        return UtilizadorDAO.singleton;
    }

    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM utilizadores")) {
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
                     stm.executeQuery("SELECT Id FROM utilizadores WHERE id='"+key.toString()+"'")) {
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
        Carro c = (Carro) value;
        return this.containsKey(c.getId());
    }

    @Override
    public Utilizador get(Object key) {
        Utilizador u = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM utilizadores WHERE id='" + key + "'")) {
            if (rs.next()) {
                    u = new Utilizador(rs.getString("nomeUtilizador"), Enum.valueOf(TipoUtilizador.class, rs.getString("tipoUtilizador")));
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return u;
    }

    @Override
    public Utilizador put(String s, Utilizador utilizador) {
        Utilizador res;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            stm.executeUpdate(
                    "INSERT INTO utilizadores " +
                            "VALUES ('"+ utilizador.getNomeUtilizador()+ "', '"+
                            utilizador.getPontosRanking()+"', "+
                            utilizador.getTipoutiliador()+") ");
            res = get(s);
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Utilizador remove(Object o) {
        Utilizador u = this.get(o);
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            // apagar a turma
            stm.executeUpdate("DELETE FROM utilizadores WHERE nome='" + u.getNomeUtilizador() + "'");
            stm.executeUpdate("DELETE FROM participantes WHERE utilizador_nome='"+ u.getNomeUtilizador() +"'");

        }catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return u;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Utilizador> map) {
        for(Utilizador u: map.values()) this.put(u.getNomeUtilizador(), u);
    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            for(Utilizador p: this.values()) stm.executeUpdate("TRUNCATE participantes WHERE utilizador_nome='"+p.getNomeUtilizador()+"'");
            stm.executeUpdate("TRUNCATE utilizadores");
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
            stm.executeUpdate("SELECT nomeUtilizador from utilizadores");
            ResultSet rs = stm.executeQuery("SELECT nomeUtilizador FROM utilizadores");
            while(rs.next()){
                String idt = rs.getString("nomeUtilizador");
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
    public Collection<Utilizador> values() {
        Collection<Utilizador> res = new HashSet<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT nomeUtilizador FROM utilizadores")) { 
            while (rs.next()) {
                String idt = rs.getString("nomeUtilizador");
                Utilizador u = this.get(idt);                    
                res.add(u);                                
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Set<Entry<String, Utilizador>> entrySet() {
        Map<String,Utilizador> res = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT nomeUtilizador FROM utilizadores")) { 
            while (rs.next()) {
                String idt = rs.getString("nomeUtilizador"); 
                Utilizador u = this.get(idt);                    
                res.put(idt,u);                                 
            }
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res.entrySet();
    }
}
