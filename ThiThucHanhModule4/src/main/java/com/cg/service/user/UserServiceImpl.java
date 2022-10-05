//package com.cg.service.user;
//
//import com.cg.model.User;
//import com.cg.model.dto.UserDTO;
//import com.cg.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserServiceImpl implements IUserService{
//
//
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public List<UserDTO> findAllUserDTOByDeletedIsFalse() {
//        return userRepository.findAllUserDTOByDeletedIsFalse();
//    }
//    @Override
//    public List<User> findALl() {
//        return null;
//    }
//
//    @Override
//    public Optional<User> findById(Long id) {
//        return userRepository.findById(id);
//    }
//
//    @Override
//    public User getById(Long id) {
//        return userRepository.getById(id);
//    }
//
//    @Override
//    public User save(User user) {
//        return userRepository.save(user);
//    }
//
//    @Override
//    public void remove(Long id) {
//        userRepository.deleteById(id);
//    }
//
//}
