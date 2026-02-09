package dao;

import util.DBConnection;
import java.sql.CallableStatement;
import java.sql.Connection;

public class JuegoDAO {

    public void guardarJuego(
            int numSecreto,
            int numJugador,
            int intentos,
            String resultado
    ) throws Exception {

        Connection con = DBConnection.getConnection();
        CallableStatement cs = con.prepareCall(
                "{ call SP_GUARDAR_JUEGO(?,?,?,?) }"
        );

        cs.setInt(1, numSecreto);
        cs.setInt(2, numJugador);
        cs.setInt(3, intentos);
        cs.setString(4, resultado);

        cs.execute();
        cs.close();
        con.close();
    }
}
