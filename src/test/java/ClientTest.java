import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public Client_Instantiates_True(){
    Client testClient = new Client("Andrew");
    assertTrue(testClients instanceof Client);
  }
