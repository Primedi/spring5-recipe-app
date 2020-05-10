package guru.springframework.repositories;


import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
