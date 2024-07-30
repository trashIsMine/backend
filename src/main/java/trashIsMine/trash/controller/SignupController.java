//package trashIsMine.trash.controller;
//
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import trashIsMine.trash.dto.AddUserRequest;
//import trashIsMine.trash.dto.UserDto;
//import trashIsMine.trash.service.UserService;
//
//import java.io.IOException;
//
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/user")
//public class SignupController {
//
//
//
//    //회원가입 페이지
////    @PostMapping("/signup")
////    public String signup(AddUserRequest request){
////        userService.save(request);
////        return "success";
////    }
////
////    @PostMapping("/login")
////    public String longin(AddUserRequest request){
////        userService
////    }
//
//    private final UserService userService;
//
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/hello")
//    public ResponseEntity<String> hello() {
//        return ResponseEntity.ok("hello");
//    }
//
//    @PostMapping("/test-redirect")
//    public void testRedirect(HttpServletResponse response) throws IOException {
//        response.sendRedirect("/api/user");
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<UserDto> signup(
//            @Valid @RequestBody UserDto userDto
//    ) {
//        return ResponseEntity.ok(userService.signup(userDto));
//    }
//
//    @GetMapping("/users")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    public ResponseEntity<UserDto> getMyUserInfo(HttpServletRequest request) {
//        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
//    }
//
//    @GetMapping("/users/{username}")
//    @PreAuthorize("hasAnyRole('ADMIN')")
//    public ResponseEntity<UserDto> getUserInfo(@PathVariable String username) {
//        return ResponseEntity.ok(userService.getUserWithAuthorities(username));
//    }
//
//}
