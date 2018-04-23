package pl.markowski.veganImperium.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.markowski.veganImperium.security.SecurityService;
import pl.markowski.veganImperium.security.UserService;
import pl.markowski.veganImperium.security.UserValidator;
import pl.markowski.veganImperium.storage.User;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping(value = "/users")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("userList", userService.findAll());
        return "users";
    }

    @PostMapping(value = "/newUser")
    public String addNewUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "users";
        }

        userService.save(userForm);

        return "redirect:/users";
    }
    
    @PostMapping(value = "/editUser")
    public String editUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
    	System.out.println(userForm.getUsername());
    	System.out.println(userForm.getId());
        return "redirect:/users";
    }

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
}
