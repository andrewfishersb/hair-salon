//save find equals  all delete update getClient
import org.sql2o.*;
import java.util.List;

public class Stylist{
  private String name;
  private int id;

  public Stylist(String name){
    this.name=name;
  }

  public String getName(){
    return name;
  }

  public int getId(){
    return id;
  }

  public static List<Stylist> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM stylists";
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO stylists (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql,true).addParameter("name",this.name).executeUpload().getKey()
    }
  }

  

}
