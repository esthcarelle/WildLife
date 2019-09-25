
import spark.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        get("/add/new", (req, res) -> {
            Map<String, Object> model = new HashMap< >();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "welcome.hbs");
        }, new HandlebarsTemplateEngine());

        get("/form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();


            List<EndangeredAnimal> endangeredAnimals=EndangeredAnimal.all();
            model.put("endangeredAnimals",endangeredAnimals);
            List<NormalAnimal> normalAnimals = NormalAnimal.getAll();
            model.put("normalAnimals", normalAnimals);

            return new ModelAndView(model, "index2.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animals/new", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<String, Object>();
            String content = request.queryParams("content");
            NormalAnimal newAnimal= new NormalAnimal(content);
            newAnimal.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        post("/animalsEndangered/new", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            EndangeredAnimal newAnimal= new EndangeredAnimal(name,health,age);
            newAnimal.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        post("/sighting/newNormal", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<String, Object>();
            int IdNormalAnimalSelected= Integer.parseInt(request.queryParams("normalAnimalSelected"));


            String location = request.queryParams("location");
            String ranger = request.queryParams("ranger");
           Sighting newSighting=new Sighting(IdNormalAnimalSelected,location,ranger);
            newSighting.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        post("/sighting/new", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<String, Object>();
            int IdEndangeredAnimalSelected= Integer.parseInt(request.queryParams("endangeredAnimalSelected"));


            String location = request.queryParams("location");
            String ranger = request.queryParams("ranger");
            Sighting newSighting=new Sighting(IdEndangeredAnimalSelected,location,ranger);
            newSighting.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());



        post("/animals/new", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<String, Object>();
            String content = request.queryParams("content");
            NormalAnimal newAnimal= new NormalAnimal(content);
            newAnimal.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();


            List<EndangeredAnimal> endangeredAnimals=EndangeredAnimal.all();
            model.put("endangeredAnimals",endangeredAnimals);
            List<NormalAnimal> normalAnimals = NormalAnimal.getAll();
            model.put("normalAnimals", normalAnimals);

            return new ModelAndView(model, "index3.hbs");
        }, new HandlebarsTemplateEngine());


        get("/normal/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            NormalAnimal normalAnimal = NormalAnimal.find(Integer.parseInt(request.params("id")));

            List<Sighting> sightings= null;
            if (normalAnimal != null) {
                sightings = normalAnimal.getSightings();
            }
            model.put("sightings", sightings);

            return new ModelAndView(model, "index4.hbs");
        }, new HandlebarsTemplateEngine());


        get("endangered/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
           EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(Integer.parseInt(request.params("id")));

            List<Sighting> sightings= endangeredAnimal.getSightings();

            model.put("sightings", sightings);

            return new ModelAndView(model, "index4.hbs");
        }, new HandlebarsTemplateEngine());




    }



}
