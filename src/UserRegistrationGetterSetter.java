
public class UserRegistrationGetterSetter {
	private String name="";
	private int age=-0;
	private String email="";
	private String password="";
	private String passwordConfirm="";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(!name.equals("")){
			this.name = name;
		}
		
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if(age!=-0){
			this.age = age;
		}
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if(!email.equals("")){
			this.email = email;
		}
		
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(!password.equals("")){
			this.password = password;
		}
		
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		if(!passwordConfirm.equals("")){
			this.passwordConfirm = passwordConfirm;
		}
		
	}
	
	public boolean valueConsistency(){
		if(!passwordConfirm.equals("") && !email.equals("") && age!=-0 && !password.equals("") && !name.equals("")){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean passwordMatching(){
		if(this.getPassword().equals(this.getPasswordConfirm())){
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "SignUp " + name + " " + age + " " + email + " "
				+ password + " " + passwordConfirm;
	}

}
