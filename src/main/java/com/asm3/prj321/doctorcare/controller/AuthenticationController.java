package com.asm3.prj321.doctorcare.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import com.asm3.prj321.doctorcare.dto.ResetPasswordDTO;
import com.asm3.prj321.doctorcare.entities.User;
import com.asm3.prj321.doctorcare.exception.DoctorCareErrorResponse;
import com.asm3.prj321.doctorcare.exception.DoctorCareNotFoundException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asm3.prj321.doctorcare.jwt.AuthenticationRequest;
import com.asm3.prj321.doctorcare.jwt.AuthenticationResponse;
import com.asm3.prj321.doctorcare.jwt.JwtUtil;
import com.asm3.prj321.doctorcare.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/oauth2")
@Tag(name="Authentication Controller", description = "APIs for user authentication")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Operation(summary = "Create authentication token")
	@PostMapping("/token")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		 if (!"password".equals(authenticationRequest.getGrantType())) {
	            return ResponseEntity.badRequest().body("Unsupported grant type: " + authenticationRequest.getGrantType());
	        }
		// Xác thực thông tin đăng nhập của user
		try {
			UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword());
			authenticationManager.authenticate(upToken);
		}
		catch (AuthenticationException e) {
			e.printStackTrace();
			throw new RuntimeException("Incorrect email or password ",e);
		}
		final UserDetails user = userService.loadUserByUsername(authenticationRequest.getEmail());
		// Tạo mã JWT
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("roles", user.getAuthorities());
		final String jwt = jwtUtil.createToken(claims,user.getUsername());
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@Operation(summary = "Register new user")
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) 
			throw new DoctorCareNotFoundException("Registry error!!!");
		if(!user.getPassword().equals(user.getConfirmPassword()))
			return ResponseEntity.badRequest().body("Password do not match");
		if(userService.findByEmail(user.getEmail()).isPresent()) 
			throw new DoctorCareNotFoundException("Email existed!");
		userService.saveUser(user);
		return ResponseEntity.ok("Registry successful");
	}
	
	@Operation(summary = "Verify Email")
	@PostMapping("/verifyEmail")
	public ResponseEntity<?> verifyEmail(@RequestBody Map<String, String> request){
		String email = request.get("email");
		if(!userService.findByEmail(email).isPresent()) {
			throw new DoctorCareNotFoundException("Email doesn't exist");}
		return ResponseEntity.ok("Verify Email successful");
	}
	
	@Operation(summary = "Reset password")
	@PutMapping("/resetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDTO request){
		if(!request.getPassword().equals(request.getConfirmPassword()))
			throw new DoctorCareNotFoundException("Password do not match");
		Optional<User> userOptional = userService.findByEmail(request.getEmail());
		if(!userOptional.isPresent())
			throw new DoctorCareNotFoundException("Email doesn't exist");
		User user = userOptional.get();
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		userService.saveUser(user);
		return ResponseEntity.ok("Reset password successful");
	}
	
}
