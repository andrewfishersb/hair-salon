import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public Stylist_Instantiates_True(){
    Stylist testStylist = new Stylist("Sean");
    assertTrue(testStylists instanceof Stylist);
  }

  @Test
  public void all_ReturnsListofAllStylists_List(){
    Stylist testStylist = new Stylist("Sean");
    testStylist.save();
  }




}
