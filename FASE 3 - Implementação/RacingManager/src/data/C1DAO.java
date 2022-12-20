package data;
import business.C1;


import java.sql.*;
import java.util.*;
public class C1DAO {
    private static C1DAO singleton = null;
    private C1DAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS C1 (" +
                    "id varchar(10) NOT NULL PRIMARY KEY," +
                    "modelo varchar(45) DEFAULT NULL," +
                    "celindrada int DEFAULT NULL," +
                    "potencia int DEFAULT NULL," +
                    "fiabilidade float DEFAULT NULL," +
                    "pac int DEFAULT NULL," +
                    "TipoPneus ENUM ('Duro','Macio','Chuva)," +
                    "ModoMotor ENUM('Conversador','Normal', 'Agressivo')";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }
        public static C1DAO getInstance() {
            if (C1DAO.singleton == null) {
                C1DAO.singleton = new C1DAO();
            }
            return C1DAO.singleton;
        }

    }

