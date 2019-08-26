package com.devox.app.db;

import com.devox.app.model.Entities.District;
import com.devox.app.model.Entities.Language;
import com.devox.app.model.Entities.ReadingCost;
import com.devox.app.model.security.Authority;
import com.devox.app.model.security.AuthorityName;
import com.devox.app.model.security.User;
import com.devox.app.repositories.DistrictRepository;
import com.devox.app.repositories.LanguageRepository;
import com.devox.app.repositories.ReadingCostRepository;
import com.devox.app.security.repository.AuthorityRepository;
import com.devox.app.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This component will only execute (and get instantiated) if the
 * property noteit.db.recreate is set to true in the
 * application.properties file
 */

@Component
@ConditionalOnProperty(name = "noteit.db.recreate", havingValue = "true")
public class DbSeeder implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    LanguageRepository languageRepository;
    @Autowired
    ReadingCostRepository readingCostRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Remove all existing Auhority , users
        readingCostRepository.deleteAll();
        authorityRepository.deleteAll();
        userRepository.deleteAll();
        districtRepository.deleteAll();
        languageRepository.deleteAll();


        // create authorities
        // admin
        Authority authority1 = new Authority();
        authority1.setName(AuthorityName.ROLE_ADMIN);
        // user employee
        Authority authority2 = new Authority();
        authority2.setName(AuthorityName.ROLE_USER);
        // mekawel
        Authority authority3 = new Authority();
        authority3.setName(AuthorityName.ROLE_MEKAWEL);

        // create Reading Cost
        // admin - > normal
        ReadingCost readingCostNormalAdmin = new ReadingCost();
        readingCostNormalAdmin.setCost(5);
        readingCostNormalAdmin.setType("normal");
        readingCostNormalAdmin.setAuthority(authority1);
        // admin - > fasl
        ReadingCost readingCostFaslAdmin = new ReadingCost();
        readingCostFaslAdmin.setCost(6);
        readingCostFaslAdmin.setType("fasl");
        readingCostFaslAdmin.setAuthority(authority1);

        // mekawel - > normal
        ReadingCost readingCostNormalMekawel = new ReadingCost();
        readingCostNormalMekawel.setCost(4);
        readingCostNormalMekawel.setType("normal");
        readingCostNormalMekawel.setAuthority(authority3);


        // mekawel - > fasl
        ReadingCost readingCostFaslMekawel = new ReadingCost();
        readingCostFaslMekawel.setCost(4);
        readingCostFaslMekawel.setType("fasl");
        readingCostFaslMekawel.setAuthority(authority3);

        // user -> normal
        ReadingCost readingCostNormaluser = new ReadingCost();
        readingCostNormaluser.setCost(3);
        readingCostNormaluser.setType("normal");
        readingCostNormaluser.setAuthority(authority2);

        // user -> fasl
        ReadingCost readingCostFasluser = new ReadingCost();
        readingCostFasluser.setCost(3);
        readingCostFasluser.setType("fasl");
        readingCostFasluser.setAuthority(authority2);


        // create District
        District district1 = new District();
        district1.setName("حى الزهراء");
        District district2 = new District();
        district2.setName("حى التيسير");

        // create Lang
        Language language1 = new Language();
        language1.setLanguage("ar");
        Language language2 = new Language();
        language2.setLanguage("en");

        // save language
        languageRepository.save(language1);
        languageRepository.save(language2);
        // save authority
        authorityRepository.save(authority1);
        authorityRepository.save(authority2);
        authorityRepository.save(authority3);

        // save on db
        // admin
        readingCostRepository.save(readingCostNormalAdmin);
        readingCostRepository.save(readingCostFaslAdmin);

        // mekawel
        readingCostRepository.save(readingCostNormalMekawel);
        readingCostRepository.save(readingCostFaslMekawel);

        // user
        readingCostRepository.save(readingCostNormaluser);
        readingCostRepository.save(readingCostFasluser);

        // save district
        districtRepository.save(district1);
        districtRepository.save(district2);

        // create users
        User user = new User("mekawel", passwordEncoder.encode("123456"),
                "ahmed", "marey", "ahmed@gmail.com",
                true, new Date(), "01201288779", null, convertArrayToSet(new Authority[]{authority3}), district1, language1);
        authority2.setName(AuthorityName.ROLE_USER);
        User user2 = new User("user", passwordEncoder.encode("123456"),
                "user", "user", "user@gmail.com",
                true, new Date(), "01201288779", String.valueOf(user.getId()), convertArrayToSet(new Authority[]{authority2}), district1, language2);
        User user3 = new User("admin", passwordEncoder.encode("123456"),
                "admin", "admin", "admin@gmail.com",
                true, new Date(), "01201288779", null, convertArrayToSet(new Authority[]{authority1}), district2, language1);

        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);


        System.out.println("Initialized database");
    }

    // Generic function to convert an Array to Set
    public static <T> Set<T> convertArrayToSet(T array[]) {

        // Create an empty Set
        Set<T> set = new HashSet<>();

        // Iterate through the array
        for (T t : array) {
            // Add each element into the set
            set.add(t);
        }

        // Return the converted Set
        return set;
    }
}
