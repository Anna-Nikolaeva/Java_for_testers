package jft.addressbook.model;

public class ContactData {
    private final int id;
    private final String firstName;
    private final String middleName;
    private final String lastname;
    private final String nickname;
    private final String companyName;
    private final String homePhone;
    private final String mobilePhone;
    private final String email;
    private final String bYear;

    private String group;

    public ContactData(int id,String firstName, String middleName, String lastname, String nickname, String companyName, String homePhone, String mobilePhone, String email, String bYear, String group) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastname = lastname;
        this.nickname = nickname;
        this.companyName = companyName;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.bYear = bYear;
        this.group = group;
    }
    public ContactData(String firstName, String middleName, String lastname, String nickname, String companyName, String homePhone, String mobilePhone, String email, String bYear, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastname = lastname;
        this.nickname = nickname;
        this.companyName = companyName;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.bYear = bYear;
        this.group = group;
    }

    public int getId() {
        return id;
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

    public String getGroup() {
        return group;
    }
    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
