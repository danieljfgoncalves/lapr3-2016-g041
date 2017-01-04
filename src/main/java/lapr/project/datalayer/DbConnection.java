/**
 * Package location for data layer concepts.
 */
package lapr.project.datalayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.driver.OracleDriver;

/**
 * Class responsible for db connection.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class DbConnection {

    /**
     * The database URL.
     */
    private static final String DB_URL = "jdbc:oracle:thin://@gandalf.dei.isep.ipp.pt:1521/pdborcl";

    /**
     * The database user name.
     */
    private static final String DB_USERNAME = "LAPR3_41";

    /**
     * The database password.
     */
    private static final String DB_PASSWORD = "qwerty";

    /**
     * Gets the database connection.
     *
     * @return database connection
     * @throws SQLException cannot connect
     */
    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}
