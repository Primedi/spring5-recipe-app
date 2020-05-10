package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private  RecipeRepository recipeRepository;
    private  CategoryRepository categoryRepository;
    private  UnitOfMeasureRepository unitOfMeasureRepository;

    public Bootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        // UnitOfMeasures
        Optional<UnitOfMeasure> eachOpt = unitOfMeasureRepository.findByDescription("each");
        if(!eachOpt.isPresent()){
            throw new RuntimeException("Each muss vorhanden sein");
        }
        Optional<UnitOfMeasure> tablespoonOpt = unitOfMeasureRepository.findByDescription("tablespoon");
        if(!tablespoonOpt.isPresent()){
            throw new RuntimeException("Tablespoon muss vorhanden sein");
        }
        Optional<UnitOfMeasure> teaspoonOpt = unitOfMeasureRepository.findByDescription("teaspoon");
        if(!teaspoonOpt.isPresent()){
            throw new RuntimeException("Teaspoon muss vorhanden sein");
        }
        Optional<UnitOfMeasure> gramOpt = unitOfMeasureRepository.findByDescription("g");
        if(!gramOpt.isPresent()){
            throw new RuntimeException("Gram muss vorhanden sein");
        }
        Optional<UnitOfMeasure> pinchOpt = unitOfMeasureRepository.findByDescription("pinch");
        if(!pinchOpt.isPresent()){
            throw new RuntimeException("Pinch muss vorhanden sein");
        }
        Optional<UnitOfMeasure> cupOpt = unitOfMeasureRepository.findByDescription("cup");
        if(!cupOpt.isPresent()){
            throw new RuntimeException("Cup muss vorhanden sein");
        }
        Optional<UnitOfMeasure> dashOpt = unitOfMeasureRepository.findByDescription("dash");
        if(!dashOpt.isPresent()){
            throw new RuntimeException("Dash muss vorhanden sein");
        }

        Optional<UnitOfMeasure> piecesOpt = unitOfMeasureRepository.findByDescription("pieces");
        if(!piecesOpt.isPresent()){
            throw new RuntimeException("Pieces muss vorhanden sein");
        }

        UnitOfMeasure dash = dashOpt.get();
        UnitOfMeasure cup = cupOpt.get();
        UnitOfMeasure pinch = pinchOpt.get();
        UnitOfMeasure gram = gramOpt.get();
        UnitOfMeasure teaspoon = teaspoonOpt.get();
        UnitOfMeasure tablespoon= tablespoonOpt.get();
        UnitOfMeasure each = eachOpt.get();
        UnitOfMeasure pieces = piecesOpt.get();

        Optional<Category> germanOpt = categoryRepository.findByDescription("german");
        if(!germanOpt.isPresent())
            throw new RuntimeException("German ist nicht da");

        Optional<Category> spaishOpt = categoryRepository.findByDescription("spanish");
        if(!spaishOpt.isPresent())
            throw new RuntimeException("Spanish ist nicht da");
        Optional<Category> britishOpt = categoryRepository.findByDescription("british");
        if(!britishOpt.isPresent())
            throw new RuntimeException("British ist nicht da");
        Optional<Category> frenchOpt = categoryRepository.findByDescription("french");
        if(!frenchOpt.isPresent())
            throw new RuntimeException("French ist nicht da");
        Optional<Category> americanOpt = categoryRepository.findByDescription("american");
        if(!americanOpt.isPresent())
            throw new RuntimeException("American ist nicht da");

        Category german = germanOpt.get();
        Category spanish = spaishOpt.get();
        Category british = britishOpt.get();
        Category french = frenchOpt.get();
        Category american = americanOpt.get();

        Recipe gua = new Recipe();
        gua.setCookTime(0);
        gua.setPrepTime(10);
        gua.setDescription("Perfect Guacamole");
        gua.setDifficulty(Difficulty.EASY);
        gua.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. \n Remove the pit. \n Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. \n"
        + "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving." + "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

        Notes guaNotes = new Notes();
        guaNotes.setRecipeNotes("Once you have basic guacamole down, feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with homemade guacamole!");
        guaNotes.setRecipe(gua);
        gua.setNotes(guaNotes);

        gua.getIngredients().add(new Ingredient("ripe Avocados", BigDecimal.valueOf(2), each));
        gua.getIngredients().add(new Ingredient("salt", BigDecimal.valueOf(.5), teaspoon));
        gua.getIngredients().add(new Ingredient("fresh lime juice", BigDecimal.valueOf(1), tablespoon));
        gua.getIngredients().add(new Ingredient("minced red onion", BigDecimal.valueOf(2), tablespoon));
        gua.getIngredients().add(new Ingredient("serrano chiles", BigDecimal.valueOf(2), pieces));
        gua.getIngredients().add(new Ingredient("cilantro", BigDecimal.valueOf(2), tablespoon));
        gua.getIngredients().add(new Ingredient("black pepper", BigDecimal.valueOf(1), dash));
        gua.getIngredients().add(new Ingredient("red radishes", BigDecimal.valueOf(5), pieces));
        gua.getIngredients().add(new Ingredient("tortillas", BigDecimal.valueOf(50), pieces));

        gua.getCategories().add(american);
        recipes.add(gua);

        return recipes;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }
}
