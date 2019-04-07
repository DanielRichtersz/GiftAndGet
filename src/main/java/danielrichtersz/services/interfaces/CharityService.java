package danielrichtersz.services.interfaces;

import danielrichtersz.models.Charity;

import java.util.List;

public interface CharityService {
    Charity submitCharityValidationRequest(String userEmail, String name, String email, String phonenumber, String description, String bankAccount);

    boolean charityNameInUse(String name);

    Charity validateCharity(String name, boolean validated);

    boolean canBeDeleted(String charityName);

    boolean deleteCharity(String charityName);

    List<Charity> getCharities();

    Charity getCharityByCharityName(String charityName);
}
