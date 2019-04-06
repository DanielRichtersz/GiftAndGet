package danielrichtersz.services.impl;

import danielrichtersz.models.Charity;
import danielrichtersz.models.UserAccount;
import danielrichtersz.repositories.ActionGroupRepository;
import danielrichtersz.repositories.CharityRepository;
import danielrichtersz.repositories.UserRepository;
import danielrichtersz.services.interfaces.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharityServiceImpl implements CharityService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CharityRepository charityRepository;

    @Autowired
    private ActionGroupRepository actionGroupRepository;

    @Override
    public Charity submitCharityValidationRequest(String userEmail, String name, String email, String phonenumber, String description, String bankAccount) {
        UserAccount userAccount = userRepository.findByEmail(userEmail);

        if (userAccount == null) {
            return null;
        }

        if (charityRepository.existsByName(name)) {
            return null;
        }

        return charityRepository.save(new Charity(userAccount, name, email, phonenumber, description, bankAccount));
    }

    @Override
    public boolean charityNameInUse(String name) {
        return charityRepository.existsByName(name);
    }

    @Override
    public Charity validateCharity(String name, boolean validated) {
        Charity charity = charityRepository.getByName(name);

        if (charity == null) {
            return null;
        }

        charity.setVerifiedCharity(validated);

        return charityRepository.save(charity);
    }

    @Override
    public boolean canBeDeleted(String charityName) {
        Charity charity = charityRepository.getByName(charityName);

        //Cannot be deleted if
        //- Charity does not exist
        //- Charity has open action groups dedicated to it
        //- TODO: Asynchrone deletion of charity, proper handling of open action groups
        if (charity == null || actionGroupRepository.existsByCharity_NameAndIsClosedFalse(charityName)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteCharity(String charityName) {
        Charity charity = charityRepository.getByName(charityName);

        if (charity == null) {
            return false;
        }

        charityRepository.delete(charity);
        charity = charityRepository.getByName(charityName);
        return charity == null;
    }
}
