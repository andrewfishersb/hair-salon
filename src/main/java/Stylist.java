//  equals  getClient
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
      this.id = (int) con.createQuery(sql,true).addParameter("name",this.name).executeUpdate().getKey();
    }
  }

  public static Stylist find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM stylists WHERE id = :id";
      return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Stylist.class);
    }
  }

  public void update(String name){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE stylists SET name = :name WHERE id =:id";
      con.createQuery(sql).addParameter("name",name).addParameter("id",id).executeUpdate();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM stylists WHERE id = :id";
      con.createQuery(sql).addParameter("id",this.id).executeUpdate();
    }
  }

  public List<Client> getClients(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM clients WHERE stylistId = :id ORDER BY name ASC";
      return con.createQuery(sql).addParameter("id",this.id).executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals(Object otherStylist){
    if(!(otherStylist instanceof Stylist)){
      return false;
    }else{
      Stylist aStylist = (Stylist) otherStylist;
      return this.getName().equals(aStylist.getName()) && this.getId() == aStylist.getId();
    }
  }

}
