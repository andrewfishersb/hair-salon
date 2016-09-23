import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_Instantiates_True(){
    Stylist testStylist = new Stylist("Sean");
    assertTrue(testStylist instanceof Stylist);
  }

  @Test
  public void all_ReturnsListofAllStylists_List(){
    Stylist firstStylist = new Stylist("Sean");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Veronica");
    secondStylist.save();
    assertTrue(Stylist.all().get(0).getName().equals(firstStylist.getName()));
    assertTrue(Stylist.all().get(1).getName().equals(secondStylist.getName()));
  }

  @Test
  public void save_SavesStylists_true(){
    Stylist testStylist = new Stylist("Sean");
    testStylist.save();
    assertTrue(Stylist.all().get(0).equals(testStylist));
  }

  @Test
  public void find_RetrievesStylistById_true(){
    Stylist firstStylist = new Stylist("Sean");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Veronica");
    secondStylist.save();
    assertEquals(Stylist.find(secondStylist.getId()),secondStylist);
  }

  @Test
  public void update_UpdatesStylistInformation_Shaun(){
    Stylist testStylist = new Stylist("Sean");
    testStylist.save();
    testStylist.update("Shaun");
    assertEquals("Shaun",Stylist.find(testStylist.getId()).getName());
  }

  @Test
  public void delete_DeletesAnEntry_True(){
    Stylist testStylist = new Stylist("Sean");
    testStylist.save();
    int testStylistId = testStylist.getId();
    testStylist.delete();
    assertEquals(null,Stylist.find(testStylistId));
  }

  @Test
  public void getClients_retrievesALlClientsFromDatabase_ClientList() {
    Stylist myStylist = new Stylist("Sean");
    myStylist.save();
    Client firstClient = new Client("Andrew", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Jenna", myStylist.getId());
    secondClient.save();
    Client[] Clients = new Client[] { firstClient, secondClient };
    assertEquals(Arrays.asList(Clients),myStylist.getClients());
  }
}
