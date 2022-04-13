package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.entity.User;
import web.repository.UserRepository;
import web.repository.UserRepositoryImp;

import javax.persistence.EntityManager;
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


}
