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


    get("/stylist/:id", (request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist newStylist = Stylist.find(Integer.parseInt(request.params("id")));
      model.put("stylist",newStylist);
      model.put("clients",newStylist.getClients());
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    get("/stylist/:stylist_id/client/:id",(request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist theStylist = Stylist.find(Integer.parseInt(request.params("stylist_id")));
      Client theClient = Client.find(Integer.parseInt(request.params("id")));
      model.put("stylist",theStylist);
      model.put("client",theClient);
      model.put("template","templates/client.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:id", (request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist newStylist = Stylist.find(Integer.parseInt(request.params("id")));
      String name = request.queryParams("client");
      Client newClient = new Client(name,newStylist.getId());
      newClient.save();
      model.put("stylist",newStylist);
      model.put("clients",newStylist.getClients());
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:id/updated", (request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist updateStylist = Stylist.find(Integer.parseInt(request.params("id")));
      String updatedName = request.queryParams("update");
      updateStylist.update(updatedName);
      String url = String.format("/stylist/%d", updateStylist.getId());
      response.redirect(url);
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:stylist_id/client/:id/updated", (request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist theStylist = Stylist.find(Integer.parseInt(request.params("stylist_id")));
      Client updatedClient = Client.find(Integer.parseInt(request.params("id")));
      String updatedName = request.queryParams("update-client");
      updatedClient.update(updatedName);
      String url = String.format("/stylist/%d/client/%d", theStylist.getId(), updatedClient.getId());
      response.redirect(url);
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:id/delete", (request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist deleteStylist = Stylist.find(Integer.parseInt(request.params("id")));
      for(Client client : deleteStylist.getClients()){
        client.delete();
      }
      deleteStylist.delete();
      model.put("stylists",Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:stylist_id/client/:id/delete", (request,response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist theStylist = Stylist.find(Integer.parseInt(request.params("stylist_id")));
      Client deletedClient = Client.find(Integer.parseInt(request.params("id")));
      deletedClient.delete();
      model.put("stylist",theStylist);
      model.put("clients",theStylist.getClients());
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());
  }
}
