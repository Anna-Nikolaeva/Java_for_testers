package jft.addressbook;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastname;
    private final String nickname;
    private final String companyName;
    private final String homePhone;
    private final String mobilePhone;
    private final String email;
    private final String bYear;

    public ContactData(String firstName, String middleName, String lastname, String nickname, String companyName, String homePhone, String mobilePhone, String email, String bYear) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastname = lastname;
        this.nickname = nickname;
        this.companyName = companyName;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.bYear = bYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getbYear() {
        return bYear;
    }
}
