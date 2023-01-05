package data;

import business.Piloto;

import java.sql.*;

public class CampeonatoDAO {
    private CampeonatoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()){
            String campeonato = "CREATE TABLE IF NOT EXISTS campeonatos ("+
                    "nome varchar(16) NOT NULL PRIMARY KEY, "+
                    "corridaAtual INT NOT NULL, "+
                    "Categoria ENUM('C1','C2','GT','SC'))";
            stm.executeUpdate(campeonato);
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
            stm.executeUpdate(circuito);
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }
}
