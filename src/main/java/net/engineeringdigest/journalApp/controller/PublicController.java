package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/public")

public class PublicController {

//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserService userService;
//

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }

    @PostMapping("/signup")
    public void signup(@RequestBody User user) {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        userService.saveEntry(newUser);
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User user) {
//        try{
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
//            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
//            String jwt = jwtUtil.generateToken(userDetails.getUsername());
//            return new ResponseEntity<>(jwt, HttpStatus.OK);
//        }catch (Exception e){
//            log.error("Exception occurred while createAuthenticationToken ", e);
//            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
//        }
//    }
}