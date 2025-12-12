// (c) 2006-2007 NextremSoft

package nq.sales;

public class User extends Person {

  private String login;
  private String password;
  private boolean superUser;

  public User() {
    this.login = "";
    this.password = "";
  }

  public String getLogin() {
    return login;
  }
  public String getPassword() {
    return password;
  }
  public boolean isSuperUser() {
    return superUser;
  }
  public void setLogin(String login) {
    this.login = login;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public void setSuperUser(boolean superUser) {
    this.superUser = superUser;
  }

}
