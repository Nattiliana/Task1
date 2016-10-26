package entity;

import java.io.Serializable;

/**
 * Created by Nataly on 13.10.2016.
 */
abstract public class User implements Serializable {

    protected String login;
    protected transient String password;
    protected String name;
    protected String surname;
}

