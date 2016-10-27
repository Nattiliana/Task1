package by.courses.nattiliana.entities;

import by.courses.nattiliana.interfaces.Loginable;

import java.io.Serializable;

/**
 * Created by Nataly on 13.10.2016.
 */
abstract class User implements Serializable, Loginable {

    String login;
    transient String password;
    String name;
    String surname;
}

