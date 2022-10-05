//package com.cg.repository;
//
//import com.cg.model.User;
//import com.cg.model.dto.UserDTO;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//public interface UserRepository extends JpaRepository<User, Long> {
//
//    @Query("SELECT NEW com.cg.model.dto.UserDTO(u.id, u.username, u.password, u.fullname, u.phone)FROM User AS u WHERE u.deleted = false")
//    List<UserDTO> findAllUserDTOByDeletedIsFalse();
//
//}
