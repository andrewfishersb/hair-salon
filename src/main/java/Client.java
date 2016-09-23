//save find equals  all delete update
import org.sql2o.*;
import java.util.List;

public class Client{
  private String name;
  private int id;
  private int stylistId;

  public Client(String name, int stylistId){
    this.name=name;
    this.stylistId = stylistId;
  }

  public String getName(){
    return name;
  }

  public int getId(){
    return id;
  }

  public int getStylistId(){
    return stylistId;
  }

  public static List<Client> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM clients";
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO clients (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql,true).addParameter("name",name).executeUpdate().getKey();
    }
  }
  /// NEED TO BE EDITED

}
