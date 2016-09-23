//     delete update

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

  @Test
  public void all_ReturnsListOfAllClients_True(){
    Client firstClient = new Client("Andrew",1);
    firstClient.save();
    Client secondClient = new Client("Jenna",1);
    secondClient.save();
    assertTrue(Client.all().get(0).getName().equals(firstClient.getName()));
    assertTrue(Client.all().get(1).getName().equals(secondClient.getName()));
  }

  @Test
  public void save_SavesClient_true(){
    Client testClient = new Client("Andrew",1);
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void find_FindsClientById_true(){
    Client firstClient = new Client("Andrew",1);
    firstClient.save();
    Client secondClient = new Client("Jenna",1);
    secondClient.save();
    assertTrue(Client.find(secondClient.getId()).equals(secondClient));
  }

  @Test
  public void update_UpdatesClientById_Jenna(){
    Client testClient = new Client("Andrew",1);
    testClient.save();
    testClient.update("Jenna");
    assertEquals("Jenna",Client.find(testClient.getId()).getName());
  }

  @Test
  public void delete_DeletesClientById_null(){
    Client testClient = new Client("Andrew",1);
    testClient.save();
    int testId = testClient.getId();
    testClient.delete();
    assertEquals(null,Client.find(testId));
  }

  @Test
   public void equals_returnsTrueIfNamesAretheSame() {
     Client firstClient = new Client("Andrew",1);
     Client secondClient = new Client("Andrew",1);
     assertTrue(firstClient.equals(secondClient));
   }

}
