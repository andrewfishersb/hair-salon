//save find equals  all delete update
import org.sql2o.*;
import java.util.List;

public class Client{
  private String name;
  private int id;

  public Client(String name){
    this.name=name;
  }

  public String getName(){
    return name;
  }

  public int getId(){
    return id;
  }

  public static List<Client> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = SELECT * FROM clients;
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

}
