class Employee {

    protected String name;
    protected String email;
    protected int experience;

    public Employee(String name, String email, int experience) {
        this.name = name;
        this.email = email;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getExperience() {
        return experience;
    }

    public void setName() {
        this.name = name;
    }

    public void setEmail() {
        this.email = email;
    }

    public void setExperience() {
        this.experience = experience;
    }
}

class Developer extends Employee {

    String[] skills;
    String mainLanguage;

    public Developer(String name, String email, int experience, String mainLanguage, String[] skills) {
        super(name, email, experience);
        this.mainLanguage = mainLanguage;
        this.skills = skills;
    }

    public String[] getSkills() {
        return skills;
    }

    public String getMainLanguage() {
        return mainLanguage;
    }
}

class DataAnalyst extends Employee {

    String[] methods;
    boolean phd;
    
    public DataAnalyst(String name, String email, int experience, boolean phd, String[] methods) {
        super(name, email, experience);
        this.phd = phd;
        this.methods = methods;
    }

    public String[] getMethods() {
        return methods;
    }

    public boolean isPhd() {
        return phd;
    }
}
