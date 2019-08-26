package com.devox.app.webservice;


import com.devox.app.model.security.User;
import com.devox.app.security.JwtTokenUtil;
import com.devox.app.security.JwtUser;
import com.devox.app.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest/user")
@CrossOrigin
public class UserWebService {

    org.slf4j.Logger logger = LoggerFactory.getLogger(UserWebService.class);

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;


    // get user information
    @ApiOperation(value = "get user Information by token ", response = JwtUser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Done "),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 204, message = "Not Countent Found"),
            @ApiResponse(code = 400, message = "bad Request please Fill All parameters ")
    }
    )
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        logger.info("start get user service ");
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        logger.info("User Response : " + user);
        return user;
    }

    //    **************************** Mobile App - > With swagger Documentation *******************************
    // edit profile
    @ApiOperation(value = "User Edit On his information", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully Added "),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 204, message = "Not Countent Found"),
            @ApiResponse(code = 400, message = "bad Request please Fill All parameters ")
    }
    )
    @RequestMapping(value = "edit", method = RequestMethod.POST)
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> editInformations(@RequestBody JwtUser jwtUser) {

        logger.info("start edit User Informations");

        // check User If exists OR Not
        String username = jwtUser.getUsername();
        if (username == null || username.equals("")) {
            logger.error("Error Username parameter Not Found");
            return ResponseEntity.badRequest().build();
        }

        Boolean aBoolean = userService.existsByUsername(username);
        if (!aBoolean) {
            logger.error("Error User Not Found");
            return ResponseEntity.notFound().build();
        }
        // convert JWTUser to User
        // if exists update database
        User saveUser = userService.save(new User());

        logger.info("saveUser User : " + saveUser);


        return ResponseEntity.ok(saveUser);
    }


//    ******************************** Web App ********************************

}
