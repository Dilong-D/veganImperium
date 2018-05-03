package pl.markowski.veganImperium.controler;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.markowski.veganImperium.logic.EditUserPasswordValidator;
import pl.markowski.veganImperium.logic.EditUserValidator;
import pl.markowski.veganImperium.logic.NewUserValidator;
import pl.markowski.veganImperium.logic.UserService;
import pl.markowski.veganImperium.security.SecurityService;
import pl.markowski.veganImperium.storage.User;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private NewUserValidator newUserValidator;
    
    @Autowired
    private EditUserValidator editUserValidator;
    
    @Autowired 
    private EditUserPasswordValidator editUserPasswordValidator;
    
    @GetMapping(value = "/users")
    public String registration(Model model) {
    	model.addAttribute("currentUser",securityService.findLoggedInUsername());
        model.addAttribute("userForm", new User());
        model.addAttribute("newUserForm", new User());
        model.addAttribute("userPasswordForm", new User());
        model.addAttribute("userList", userService.findAll());
        return "users";
    }

    @PostMapping(value = "/newUser")
    public String addNewUser(@ModelAttribute("newUserForm") User userForm, BindingResult bindingResult, Model model, RedirectAttributes redir) {
        newUserValidator.validate(userForm, bindingResult);
        
        if (bindingResult.hasErrors()) {
        	String errorMsq = bindingResult.getAllErrors().stream().map( n -> n.getDefaultMessage()).collect( Collectors.joining( "," ) );
        	redir.addFlashAttribute("errorMessage",errorMsq);
            return "redirect:/users";
        }
        userService.save(userForm, true);
    	redir.addFlashAttribute("message","Użytkownik "+ userForm.getUsername() +" dodany.");
        return "redirect:/users";
    }
    
    @PostMapping(value = "/editUser")
    public String editUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, RedirectAttributes redir) {
    	editUserValidator.validate(userForm, bindingResult);

    	if (bindingResult.hasErrors()) {
        	String errorMsq = bindingResult.getAllErrors().stream().map( n -> n.getDefaultMessage()).collect( Collectors.joining( "," ) );
        	redir.addFlashAttribute("errorMessage",errorMsq);
            return "redirect:/users";
        }
    	userService.save(userForm, false);
    	redir.addFlashAttribute("message","Użytkownik "+ userForm.getUsername() +" zmodyfikowany.");
        return "redirect:/users";
    }
    
    @PostMapping(value = "/editUserPassword")
    public String editUserPass(@ModelAttribute("userPasswordForm") User userForm, BindingResult bindingResult, Model model,  RedirectAttributes redir) {
    	editUserPasswordValidator.validate(userForm, bindingResult);

    	if (bindingResult.hasErrors()) {
        	String errorMsq = bindingResult.getAllErrors().stream().map( n -> n.getDefaultMessage()).collect( Collectors.joining( "," ) );
        	redir.addFlashAttribute("errorMessage",errorMsq);
            return "redirect:/users";
        }
    	userService.save(userForm, true);
    	redir.addFlashAttribute("message","Użytkownik "+ userForm.getUsername() +" zmodyfikowany.");
        return "redirect:/users";
    }
    
    @PostMapping(value = "/users/{id}/delete")
    public String deleteUser(@PathVariable("id") int id, final RedirectAttributes redir) {
    	String username = userService.findById(id).getUsername();
    	userService.deleteById(id);
    	redir.addFlashAttribute("message","Użytkownik "+ username +" usunięty.");
        return "redirect:/users";
    }
    
    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Twój login lub hasło są niepoprawne.");

        if (logout != null)
            model.addAttribute("message", "Zostałeś wylogowany.");

        return "login";
    }
}
