//package com.cg.controller.rest;
//
//import com.cg.exception.ResourceNotFoundException;
//import com.cg.model.User;
//import com.cg.model.dto.UserDTO;
//import com.cg.service.user.IUserService;
//import com.cg.service.user.UserServiceImpl;
//import com.cg.util.AppUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserRestController {
//
//    @Autowired
//    private AppUtil appUtils;
//
//    @Autowired
//    private IUserService userService;
//
//    @GetMapping
//    public ResponseEntity<?> findAllUserDTO(){
//        List<UserDTO> findAllUserDTO = userService.findAllUserDTOByDeletedIsFalse();
//        return new ResponseEntity<>(findAllUserDTO, HttpStatus.OK);
//    }
//
//
//    @PostMapping("/create")
//    public ResponseEntity<?> doCreate(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult){
//        if (bindingResult.hasFieldErrors()) {
//            return appUtils.mapErrorToResponse(bindingResult);
//        }
//        userDTO.setId(0L);
//
//        User createUser = userService.save(userDTO.toUser());
//        return new ResponseEntity<>(createUser.toUserDTO(), HttpStatus.CREATED);
//    }
//
//    //Ham tim kiem theo id, de truyen du lieu vao hien thi Edit
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getUserById(@PathVariable long id) {
//        Optional<User> userById = userService.findById(id);
//        if (!userById.isPresent()) {
//            throw new ResourceNotFoundException("Invalid user ID");
//        }
//
//        return new ResponseEntity<>(userById.get().toUserDTO(),  HttpStatus.OK);
//    }
//}
