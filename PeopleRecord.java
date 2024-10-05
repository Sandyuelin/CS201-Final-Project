class PeopleRecord {
    private String givenName;
    private String familyName;
    private String companyName;
    private String address;
    private String city;
    private String county;
    private String state;
    private String zip;
    private String phone1;
    private String phone2;
    private String email;
    private String web;
    private String birthday;

    // Constructor
    public PeopleRecord(String givenName, String familyName, String companyName, String address, String city,
                        String county, String state, String zip, String phone1, String phone2, String email,
                        String web, String birthday) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.companyName = companyName;
        this.address = address;
        this.city = city;
        this.county = county;
        this.state = state;
        this.zip = zip;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.web = web;
        this.birthday = birthday;
    }

    // Getters for all attributes
    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getEmail() {
        return email;
    }

    public String getWeb() {
        return web;
    }

    public String getBirthday() {
        return birthday;
    }

    // Override toString for easy printing
    @Override
    public String toString() {
        return givenName + " " + familyName + ", " + companyName + ", " + address + ", " + city + ", " + county + ", "
                + state + " " + zip + ", Phone: " + phone1 + ", Email: " + email + ", Web: " + web + ", Birthday: " + birthday;
    }
}

