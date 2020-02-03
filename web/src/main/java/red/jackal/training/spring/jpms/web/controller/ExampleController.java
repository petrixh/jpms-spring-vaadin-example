package red.jackal.training.spring.jpms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import red.jackal.training.spring.jpms.entity.ExampleEntity;
import red.jackal.training.spring.jpms.service.ExampleService;

import java.util.List;

//@RestController
//@RequestMapping("/example")
public class ExampleController {

    private final ExampleService exampleService;

  //  @Autowired
    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

//    @GetMapping("/{id}")
    public ExampleEntity getExample(@PathVariable Long id) {
        return exampleService.getExample(id).orElse(null);
    }

//    @GetMapping("/all")
    public List<ExampleEntity> getAllExamples() {
        System.out.println("Ex entity from module: " + ExampleEntity.class.getModule().getName()); 
        return exampleService.getAllExamples();
    }

//    @PostMapping("/add/{name}")
    public ExampleEntity addExample(@PathVariable String name) {
        return exampleService.addExample(name);
    }
}
