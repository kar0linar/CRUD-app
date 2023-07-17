package pl.pierwszastrona.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    //    @GetMapping("/test")
//    public String test() {
//    return "test";
//    }
    @GetMapping("/") //lista flimów
    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    @GetMapping("/{id}") //pojedyńczy film
    public Movie getById(@PathVariable("id") int id) {

        return movieRepository.getById(id);
    }

    @PostMapping("/")
    public int add(@RequestBody List<Movie> movies) {
        return movieRepository.save(movies);
    }

    @PutMapping("/{id}") //zastępujemy cały obiekt
    public int update(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        Movie movie = movieRepository.getById(id);
        if (movie != null) {
            movie.setName(updatedMovie.getName());
            movie.setRating(updatedMovie.getRating());

            movieRepository.update(movie);

            return 1; //wszystko ok

        } else {

            return -1; //bład
        }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        Movie movie = movieRepository.getById(id);

            if (movie != null) {
                if (updatedMovie.getName() != null) movie.setName(updatedMovie.getName());
                if (updatedMovie.getRating() > 0) movie.setRating(updatedMovie.getRating());

                movieRepository.update(movie);
                return 1; //wszystko ok

            } else {

                return -1; //bład
            }
        }
        @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id){
        return movieRepository.delete(id);
        }
    }

