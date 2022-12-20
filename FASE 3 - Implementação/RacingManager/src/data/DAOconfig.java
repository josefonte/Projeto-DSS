package data;
public class DAOconfig {
    static final String USERNAME = "jfc";                       // Actualizar
    static final String PASSWORD = "jfc";                       // Actualizar
    private static final String DATABASE = "turmas4l";          // Actualizar
    private static final String DRIVER = "jdbc:mariadb";        // Usar para MariaDB
    //private static final String DRIVER = "jdbc:mysql";        // Usar para MySQL
    static final String URL = DRIVER+"://localhost:3306/"+DATABASE;
}
