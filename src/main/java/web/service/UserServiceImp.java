package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.entity.User;
import web.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserRepository userRepository;

   @Transactional
   @Override
   public void add(User user) {
      this.userRepository.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return this.userRepository.listUsers();
   }

   @Override
   public User userById(long id) {
      return this.userRepository.userById(id);
   }

   @Override
   public void updateUser(long id, User user) {
      user.setId(id);
      this.userRepository.updateUser(user);
   }

   @Override
   public void deleteUser(long id) {
      this.userRepository.deleteUser(id);
   }


}
