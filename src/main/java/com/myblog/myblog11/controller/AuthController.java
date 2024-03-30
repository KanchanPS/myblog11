package com.myblog.myblog11.controller;

import com.myblog.myblog11.entity.Role;
import com.myblog.myblog11.entity.UserEntity;
import com.myblog.myblog11.payload.LoginDto;
import com.myblog.myblog11.payload.SignUpDto;
import com.myblog.myblog11.repository.RoleRepository;
import com.myblog.myblog11.repository.UserRepository;
import com.myblog.myblog11.security.JWTAuthResponse;
import com.myblog.myblog11.security.JWtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController<logindto> {

@Autowired
    private UserRepository userRepository;
@Autowired
    private PasswordEncoder passwordEncoder;

//    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
@Autowired
private RoleRepository roleRepository;

    @Autowired
    private JWtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private  JWtTokenProvider tokenProvider;




    //    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        // Authenticate user and get username
//        String username = authenticate(loginRequest);
//
//        // Generate JWT token
//        String token = jwtTokenProvider.generateToken(username);
//
//        // Return token to client
//        return ResponseEntity.ok(token);
//    }


//    @PostMapping("/signIn")
//    public  ResponseEntity<String> authenticateUser( @RequestBody LoginDto loginDto){
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword());
//         Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);// whatever username and password is present in db it's compare the username &password i.e present is here
//        // if the user paasd is valid it will give me 1 token authenticate
//        SecurityContextHolder.getContext().setAuthentication(authenticate);//Session variable Created
//
//
//        return new ResponseEntity<>("UserSignIn in Successful",HttpStatus.OK);
//    }


    // verify the Due to the JWT Token

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> RegisterUser(@RequestBody SignUpDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role role = roleRepository.findByName(signUpDto.getRole()).get();

        Set<Role> convertRoleToSet = new HashSet<>();
        convertRoleToSet.add(role);

        user.setRoles(convertRoleToSet);
        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
