import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.Statement
import java.text.SimpleDateFormat
import java.util.*

class SQLite{

    private val conexion: Connection
    init {
        conexion = DriverManager.getConnection("jdbc:sqlite:BDDcelulares.db")
    }

    fun crearTablas(){

        val statement: Statement = conexion.createStatement()

        val scriptTablaMarca="""
            CREATE TABLE IF NOT EXISTS Marcas(
            id              INTEGER PRIMARY KEY,
            nombre          TEXT,
            pais            TEXT,
            anioCreacion    INTEGER,
            ventaMillones   REAL 
            );""".trimIndent()

        val scriptTablaCelulares="""
            CREATE TABLE IF NOT EXISTS Celulares(
                idMarca             INTEGER,
                modelo              TEXT,
                almacenamiento      INTEGER,
                es5G                INTEGER,
                precio              REAL,
                fechaLanzamiento    TEXT,
                FOREIGN KEY(idMarca) REFERENCES Marcas(id)                
        );""".trimIndent()
//
        try{
            statement.execute(scriptTablaMarca)
            statement.execute(scriptTablaCelulares)
        }catch (e: Exception){
            println("Tablas no creadas")
        }

    }

    fun insertarMarca(marca: Marca){
        val queryInsert="""
                INSERT INTO Marcas 
                (id, nombre, pais, anioCreacion, ventaMillones)
                VALUES (?, ?, ?, ?, ?);"""
        val statementInsert: PreparedStatement = conexion.prepareStatement(queryInsert)

        statementInsert.setInt(1, marca.id)
        statementInsert.setString(2, marca.nombre)
        statementInsert.setString(3, marca.pais)
        statementInsert.setInt(4, marca.anioCreacion)
        statementInsert.setDouble(5, marca.ventaMillones)
        statementInsert.executeUpdate()
    }

    fun insertarCelular(celular: Celular){
        val queryInsert="""
                INSERT INTO Celulares 
                (idMarca, modelo, almacenamiento, es5G, precio, fechaLanzamiento) 
                VALUES (?, ?, ?, ?, ?, ?);"""
        val statementInsert: PreparedStatement = conexion.prepareStatement(queryInsert)

        statementInsert.setInt(1, celular.idMarca)
        statementInsert.setString(2, celular.modelo)
        statementInsert.setInt(3, celular.almacenamiento)
        statementInsert.setBoolean(4, celular.es5G)
        statementInsert.setDouble(5, celular.precio)
        statementInsert.setString(6, SimpleDateFormat("yyyy-MM-dd").format(celular.fechaLanzamiento))
        statementInsert.executeUpdate()
    }

    fun updateMarca(marca: Marca){
        val queryUpdate=
           "UPDATE Marcas SET nombre=?, pais=?, anioCreacion = ?, ventaMillones = ? WHERE id = ?;"

        val statementUpdate: PreparedStatement = conexion.prepareStatement(queryUpdate)
        statementUpdate.setString(1, marca.nombre)
        statementUpdate.setString(2, marca.pais)
        statementUpdate.setInt(3, marca.anioCreacion)
        statementUpdate.setDouble(4, marca.ventaMillones)
        statementUpdate.setInt(5, marca.id)
        statementUpdate.executeUpdate()
    }

    fun updateCelular(celular: Celular){
        val queryUpdate =
            "UPDATE Celulares SET idMarca = ?, almacenamiento = ?, es5G = ?, precio = ?, fechaLanzamiento = ? WHERE modelo = ?;"

        val statementUpdate: PreparedStatement = conexion.prepareStatement(queryUpdate)
        statementUpdate.setInt(1, celular.idMarca)
        statementUpdate.setInt(2, celular.almacenamiento)
        statementUpdate.setBoolean(3, celular.es5G)
        statementUpdate.setDouble(4, celular.precio)
        statementUpdate.setString(5, SimpleDateFormat("yyyy-MM-dd").format(celular.fechaLanzamiento))
        statementUpdate.setString(6, celular.modelo)
        statementUpdate.executeUpdate()
    }

    fun deleteMarca(id:Int){
        val queryDelete="DELETE FROM Marcas WHERE id=?;"
        val statementDelete: PreparedStatement = conexion.prepareStatement(queryDelete)

        statementDelete.setInt(1,id)
        statementDelete.executeUpdate()
    }

    fun deleteCelular(modelo:String){
        val queryDelete="DELETE FROM Celulares WHERE modelo=?;"
        val statementDelete: PreparedStatement = conexion.prepareStatement(queryDelete)

        statementDelete.setString(1,modelo)
        statementDelete.executeUpdate()
    }

    fun getMarcas(): List<Marca> {
        val queryGet = "SELECT * FROM Marcas;"
        val statementGet = conexion.createStatement()
        val resultSet = statementGet.executeQuery(queryGet)
        val marcas = mutableListOf<Marca>()

        while (resultSet.next()) {
            marcas.add(
                Marca(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("pais"),
                    resultSet.getInt("anioCreacion"),
                    resultSet.getDouble("ventaMillones")
                )
            )
        }

        return marcas
    }

    fun getCelulares(): List<Celular> {
        val queryGet = "SELECT * FROM Celulares;"
        val statementGet = conexion.createStatement()
        val resultSet = statementGet.executeQuery(queryGet)
        val celulares = mutableListOf<Celular>()

        while (resultSet.next()) {
            celulares.add(
                Celular(
                    resultSet.getInt("idMarca"),
                    resultSet.getString("modelo"),
                    resultSet.getInt("almacenamiento"),
                    resultSet.getBoolean("es5G"),
                    resultSet.getDouble("precio"),
                    SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("fechaLanzamiento"))
                )
            )
        }

        return celulares
    }

    fun closeConnection() {
        conexion.close()
    }
}