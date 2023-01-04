package data;
public class DAOconfig {
    static final String USERNAME = "admin";                       // Actualizar
    static final String PASSWORD = "admin";                       // Actualizar
    private static final String DATABASE = "racing";          // Actualizar
    private static final String DRIVER = "jdbc:mariadb";        // Usar para MariaDB
    //private static final String DRIVER = "jdbc:mysql";        // Usar para MySQL
    static final String URL = DRIVER+"://localhost:3307/"+DATABASE;
}
