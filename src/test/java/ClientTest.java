import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Client_Instantiates_True(){
    Client testClient = new Client("Andrew",1);
    assertTrue(testClient instanceof Client);
  }
}
