import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists",Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    post("/", (request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Stylist newStylist = new Stylist(name);
      newStylist.save();
      model.put("stylists",Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

//get for clients
    get("/stylist/:id", (request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist newStylist = Stylist.find(Integer.parseInt(request.params("id")));
      newStylist.save();
      // String name = request.queryParams("name");
      // Client newClient = new Client(name,newStylist.getId());
      model.put("stylist",newStylist);
      model.put("clients",Client.all()); // change to getClients
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());
//adds a client
    post("/stylist/:id", (request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist newStylist = Stylist.find(Integer.parseInt(request.params("id")));
      newStylist.save();
      String name = request.queryParams("name");
      Client newClient = new Client(name,newStylist.getId());
      newClient.save();
      model.put("stylist",newStylist);
      model.put("clients",Client.all()); // change to getClients
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    //displays updated client
    get("/stylist/:id/upgrade", (request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist updateStylist = Stylist.find(Integer.parseInt(request.params("id")));
      updateStylist.save();
      model.put("stylist",updateStylist);
      model.put("clients",Client.all()); // change to getClients
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    //updates stylist information
    post("/stylis/:id/update", (request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist updateStylist = Stylist.find(Integer.parseInt(request.params("id")));
      updateStylist.save();
      String updatedName = request.queryParams("update");
      Client updatedClient = new Client(updatedName,updateStylist.getId());
      model.put("stylist",updatedName);
      model.put("clients",Client.all()); // change to getClients
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());
  }
}
