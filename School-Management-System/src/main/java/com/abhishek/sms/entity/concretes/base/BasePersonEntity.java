package com.abhishek.sms.entity.concretes.base;

import com.abhishek.sms.entity.enums.Gender;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BasePersonEntity extends BaseEntityWithIdLong{

    private String firstName;
    private String lastName;
    private int age;
    private String email;
    @Enumerated
    private Gender gender;

    //Constructors of abstract classes can only be called in constructors of their subclasses.
    //So there is no point in making them public. The protected modifier should be enough.
    protected BasePersonEntity(){

    }
    protected BasePersonEntity(String firstName, String lastName, int age, String email, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "BasePersonEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }
}
