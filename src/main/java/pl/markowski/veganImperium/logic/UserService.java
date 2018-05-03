package pl.markowski.veganImperium.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.markowski.veganImperium.storage.User;
import pl.markowski.veganImperium.storage.UserRepository;

@Service
public class UserService{
    
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user, boolean ecryptPass) {
    	if (ecryptPass)
    		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    
    public void update(User user) {
        userRepository.save(user);
    }
    
    public List<User> findAll(){
    	return (List<User>)userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User findById(int id) {
        return userRepository.findById(id);
    }
    
    public void deleteById(int id) {
    	userRepository.deleteById(id);
    }
}
