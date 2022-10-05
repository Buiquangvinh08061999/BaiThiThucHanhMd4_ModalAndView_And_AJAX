//package com.cg.model;
//
//
//import com.cg.model.dto.UserDTO;
//import lombok.*;
//import lombok.experimental.Accessors;
//
//import javax.persistence.*;
//import java.util.Set;
//
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "users")
//@Accessors(chain = true)
//
//public class User{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true, nullable = false)
//    private String username;
//
//    @Column(nullable = false)
//    private String password;
//
//    @Column(name = "full_name", nullable = false)
//    private String fullname;
//
//    private String phone;
//
//    @Column(columnDefinition = "boolean default false")
//    private boolean deleted;
//
//
//    public UserDTO toUserDTO() {
//        return new UserDTO().setId(id)
//                .setUsername(username)
//                .setPassword(password)
//                .setFullname(fullname)
//                .setPhone(phone);
//    }
//
//
//
//
//}
