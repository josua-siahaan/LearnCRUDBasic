package com.example.demo.service;

import com.example.demo.config.JwtConfig;
import com.example.demo.dto.validation.ValLoginDTO;
import com.example.demo.dto.validation.ValRegisDTO;
import com.example.demo.dto.validation.ValVerifyOTPRegisDTO;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.security.BcryptImpl;
import com.example.demo.security.Crypto;
import com.example.demo.security.JwtUtility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class AppUserDetailService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private ModelMapper modelMapper;

    private Random random = new Random();

    public ResponseEntity<Object> login (User user, HttpServletRequest request){
        Optional<User> optUser = userRepo.findByUsername(user.getUsername());
        Map<String, Object> m = new HashMap<>();
        if (!optUser.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username / Password Salah - AUT00FV001");
        }

        User userNext = optUser.get();

        if (!userNext.getRegistered()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username / Password Salah - AUT00FV002");
        }
        if (!BcryptImpl.verifyHash(user.getUsername()+user.getPassword(), userNext.getPassword())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username / Password Salah - AUT00FV003");
        }

        Map<String, Object> m1 = new HashMap<>();
        m1.put("id", userNext.getId());
        m1.put("phn", userNext.getNoHp());
        m1.put("ml", userNext.getEmail());

        String token = jwtUtility.doGenerateToken(m1, userNext.getUsername());
        if (JwtConfig.getTokenEncryptEnable().equals("y")){
            token = Crypto.performEncrypt(token);
        }
        m.put("token", token);
        m.put("menu", new ArrayList<>());
        return ResponseEntity.ok().body(m);

    }

    public ResponseEntity<Object> regis (User user, HttpServletRequest request){
        Map<String, Object> m = new HashMap<>();
        int intOtp = random.nextInt(111111,999999);
        m.put("otp", intOtp);
        m.put("email", user.getEmail());
        user.setPassword(BcryptImpl.hash(user.getUsername()+user.getPassword()));
        user.setOtp(BcryptImpl.hash(String.valueOf(intOtp)));
        userRepo.save(user);
        return ResponseEntity.ok().body(m);
    }
    public ResponseEntity<Object> verifyRegis (User user, HttpServletRequest request){
        Optional<User> optUser = userRepo.findByEmail(user.getEmail());
        if (!optUser.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data tidak Valid");
        }
        User userNext = optUser.get();
        if (!BcryptImpl.verifyHash(user.getOtp(), userNext.getOtp())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP tidak valid, cek email anda");
        }
        userNext.setRegistered(true);
        userNext.setModifiedBy(userNext.getId());
        userRepo.save(userNext);
        return ResponseEntity.status(HttpStatus.OK).body("Registrasi Berhasil");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User>  opUser = userRepo.findByUsername(username);
        if(!opUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        User user = opUser.get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),user.getAuthorities());
    }

    public User convertToEntity(ValRegisDTO valRegisDTO){
        return modelMapper.map(valRegisDTO, User.class);
    }

    public User ConvertToEntity(ValVerifyOTPRegisDTO valVerifyOTPRegisDTO){
        return modelMapper.map(valVerifyOTPRegisDTO, User.class);
    }
    public User ConvertToEntity(ValLoginDTO valLoginDTO){
        return modelMapper.map(valLoginDTO, User.class);
    }
}
