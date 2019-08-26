package com.devox.app.webservice;

import com.devox.app.security.JwtAuthenticationRequest;
import com.devox.app.security.JwtTokenUtil;
import com.devox.app.security.JwtUser;
import com.devox.app.security.controller.AuthenticationException;
import com.devox.app.security.service.JwtAuthenticationResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
//@RequestMapping("/rest")
@CrossOrigin
public class AuthenticationRestController {

    org.slf4j.Logger logger = LoggerFactory.getLogger(AuthenticationRestController.class);

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @ApiOperation(value = "create Authentication Token ", response = JwtAuthenticationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Done "),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 204, message = "Not Countent Found"),
            @ApiResponse(code = 400, message = "bad Request please Fill All parameters ")
    }
    )
    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) {

        logger.info("start Auth Service ");

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // Reload password post-security so we can generate the token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        logger.info("User Authorized : " + userDetails.toString());

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @ApiOperation(value = "refresh And Get Authentication Token ", response = JwtAuthenticationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Done "),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 204, message = "Not Countent Found"),
            @ApiResponse(code = 400, message = "bad Request please Fill All parameters ")
    }
    )
    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        logger.info("start Refresh Service");

        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            logger.info("Complete  Refresh Service : " + refreshedToken);

            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            logger.error("Error Refresh Service");

            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    /**
     * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be thrown
     */
    public void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Bad credentials!", e);
        }
    }


}
