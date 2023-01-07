package data;

import business.*;

import java.sql.*;
import java.time.LocalTime;
import java.util.*;

public class CampeonatoDAO implements Map<String , Campeonato> {
    private static CampeonatoDAO singleton = null;

    private CampeonatoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()){
            String campeonato = "CREATE TABLE IF NOT EXISTS campeonatos ("+
                    "nome varchar(16) NOT NULL PRIMARY KEY, "+
                    "corridaAtual INT NOT NULL, "+
                    "Categoria ENUM('C1','C2','GT','SC'))";
            stm.executeUpdate(campeonato);
            String corridas ="";
            stm.executeUpdate(corridas);
            String circuito ="CREATE TABLE IF NOT EXISTS circuitos ("+
                    "nome varchar(45) NOT NULL PRIMARY KEY,"+
                    "distancia float(10) NOT NULL, " +
                    "volta int NOT NULL)";
            stm.executeUpdate(circuito);
            String segmentos = "CREATE TABLE IF NOT EXISTS segmentos ("+
                    "id int NOT NULL PRIMARY KEY AUTO_INCREMENT,"+
                    "indice int NOT NULL,"+
                    "gdu int NOT NULL,"+
                    "distancia float NOT NULL"+
                    "nome ENUM('CURVA','RETA','CHICANE'),"+
                    "FOREIGN KEY (nomecircuito) references circuitos(nome))";
            stm.executeUpdate(segmentos);
            String carros = "CREATE TABLE IF NOT EXISTS carros (" +
                    "id varchar(10) NOT NULL PRIMARY KEY," +
                    "categoria varchar(10) NOT NULL,"+
                    "modelo varchar(45) NOT NULL," +
                    "marca varchar(45) NOT NULL,"+
                    "celindrada int NOT NULL," +
                    "potencia int NOT NULL," +
                    "fiabilidade float DEFAULT NULL," +
                    "pac int NOT NULL," +
                    "TipoPneus ENUM ('Duro','Macio','Chuva')," +
                    "ModoMotor ENUM('Conversador','Normal', 'Agressivo')," +
                    "potenciaHibrido int DEFAULT NULL," +
                    "taxaDeteorizacao int DEFAULT NULL)";
            stm.executeUpdate(carros);
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
            String pilotos = "CREATE TABLE IF NOT EXISTS Piloto (" +
                    "nome varchar(45) NOT NULL PRIMARY KEY," +
                    "sva int NOT NULL,"+
                    "cts int NOT NULL)";;
            stm.executeUpdate(pilotos);
            String utilizadores="CREATE TABLE IF NOT EXISTS utilizadores (" +
                    "nomeUtilizador varchar(45) NOT NULL PRIMARY KEY," +
                    "pontosRanking int NOT NULL,"+
                    "tipoUtilizador ENUM ('Admin','Jogador','Convidado','Bot')";
            stm.executeUpdate(utilizadores);

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static CampeonatoDAO getInstance() {
        if (CampeonatoDAO.singleton == null) {
            CampeonatoDAO.singleton = new CampeonatoDAO();
        }
        return CampeonatoDAO.singleton;
    }

    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM campeonatos")) {
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
             ResultSet rs = stm.executeQuery("SELECT Nome FROM campeonatos WHERE Nome='"+key.toString()+"'")) {
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
        Campeonato c = (Campeonato) value;
        return this.containsKey(c.getNomeCampeonato());
    }

    @Override
    public Campeonato get(Object key) {
        Campeonato c = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM campeonatos WHERE nome='"+key+"'")) {
            if (rs.next()) {  // A chave existe na tabela
                // Reconstruir a os segmentos de pista
                //reconstruir circuito
                String nomeCampeonato=rs.getString("nome");
                int corridaAtual=rs.getInt("corridaAtual");
                List<Corrida> corridas = getCorridas(key.toString(), stm);
                Map<String, Participante> campParticipantes = getParticipantes(key.toString(),stm);
                c = new Campeonato(nomeCampeonato,corridaAtual,corridas, campParticipantes);
            }
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return c;
    }

    private Map<String, Participante> getParticipantes(String key, Statement stm) throws SQLException {
        Map<String,Participante> r = new HashMap<>();
        // preencher com o conteúdo da base de dados
        try (ResultSet rsa = stm.executeQuery("SELECT * FROM participantes WHERE campeonatoNome='"+key+"'")) {
            while (rsa.next()) {
                List<LocalTime> tempos = getTemposParticipante(rsa.getInt("tempos"), stm);
                Carro carro = getCarroParticipante(rsa.getInt("carro_id"), stm);
                Utilizador utilizador = getUtilizadorParticipante(rsa.getString("utilizador_nome"));
                Participante participante = new Participante(rsa.getInt("idParticipante"), rsa.getInt("pontuacao"), rsa.getInt("afinacoesRestantes"), rsa.getInt("voltasTotais"), rsa.getInt("localizacaoPista"),tempos,carro,utilizador);
                r.put((Integer.toString(rsa.getInt("idParticipante"))),participante);
            }
        }
        return r;
    }

    private Utilizador getUtilizadorParticipante(String utilizadorNome) throws SQLException{
        return null;
    }

    private Carro getCarroParticipante(int carroId, Statement stm) throws SQLException{
        return null;
    }

    private List<LocalTime> getTemposParticipante(int tempos, Statement stm) throws SQLException{
        return null;
    }

    private List<Corrida> getCorridas(String toString, Statement stm) {
        return null;
    }

    @Override
    public Campeonato put(String s, Campeonato campeonato) {
        return null;
    }

    @Override
    public Campeonato remove(Object key) {
        Campeonato c = this.get(key);
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()){
            // apagar a segmentos
            stm.executeUpdate("DELETE FROM participantes WHERE nome_campeonato='"+key+"'");
            // apagar circuito
            stm.executeUpdate("DELETE FROM campeonatos WHERE nome='"+key+"'");
        } catch (Exception e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return c;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Campeonato> map) {

    }

    @Override
    public void clear() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            stm.executeUpdate("TRUNCATE participantes");
            stm.executeUpdate("TRUNCATE campeonatos");
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<Campeonato> values() {
        return null;
    }

    @Override
    public Set<Entry<String, Campeonato>> entrySet() {
        return null;
    }
}
