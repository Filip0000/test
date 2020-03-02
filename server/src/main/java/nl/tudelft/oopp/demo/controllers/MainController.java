package nl.tudelft.oopp.demo.controllers;

import nl.tudelft.oopp.demo.entities.User;
import nl.tudelft.oopp.demo.errorhandlers.UserNotFoundException;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private UserRepository userRepository;

    /** Endpoint for login page
     * @return the page.
     */
    @GetMapping("/loginpage")
    @ResponseBody
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /** Endpoint for a user on the login page, if the userid (maybe hashed) were to be given in URL
     * @param userId id of user
     * @return a user object
     * @throws ResponseStatusException when user is not found
     */
    @GetMapping("/loginpage/{userId}")
    @ResponseBody
    public User getUser(@PathVariable long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        /*
        WIP RESTful SERVICES
        return new EntityModel<>(user,
                linkTo(methodOn(MainController.class).getUser(userId).withSelfRel(),
                        linkTo(methodOn(MainController.class).getAllUsers().withRel("users"));

         */
        return user;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public void returnUser(@RequestBody String jsonString) {
        JSONObject obj = new JSONObject(jsonString);
        String netid = obj.getString("netId");
        String email = obj.getString("email");
        String password = obj.getString("password");
        int level = obj.getInt("accountLevel");
        userRepository.insertUser(netid, email, password, level);
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }
}
