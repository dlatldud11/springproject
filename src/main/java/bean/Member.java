package bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Member {
	// 상수 값들은 반드시 final 키워드를 사용해야 합니다.
	private final String MUST_INPUT = "필수 입력 사항입니다." ;	

	@NotEmpty(message= "아이디는(은) " + MUST_INPUT)
	@Length(min=4, max=10, message="아이디는 최소 4자리 최대 10자리 입니다.")
	private String id ;	
	@Length(min=2, max=10, message="이름은 최소 2자리 최대 10자리 입니다.")
	private String name ;
	@Length(min=4, max=10, message="이름은 최소 4자리 최대 10자리 입니다.")
	private String password ;
	@Min(value=100, message="최소 급여는 100원 이상입니다.")
	private int salary ;
	@Pattern(regexp = "\\d{4}[-/]\\d{2}[-/]\\d{2}", message = "입사 일자는 yyyy/MM/dd 또는 yyyy-MM-dd 형식으로 입력해 주세요.")
	private String hiredate ;
	@NotNull(message="성별은 반드시 체크가 되어야 합니다.")
	private String gender ;

	//체크 박스라서 배열로 처리하도록 합니다.
	//?? "취미는 최소 2개이상이어야 합니다."
	@NotNull(message="취미는 반드시 체크가 되어야 합니다.")
	private String hobby ; 
//	private String[] hobby ;

	//"직업을 선택해 주세요."
	private String job ;
	@NotEmpty(message= "우편 번호는(은) " + MUST_INPUT)
	private String zipcode ;
	@NotEmpty(message= "주소는(은) " + MUST_INPUT)
	private String address1 ;
	private String address2 ;
	private int mpoint ;	
	
//	@Override
//	public String toString() {
//		return "Member [id=" + id + ", name=" + name + ", password=" + password + ", salary=" + salary + ", hiredate="
//				+ hiredate + ", gender=" + gender + ", hobby=" + Arrays.toString(hobby) + ", job=" + job + ", zipcode="
//				+ zipcode + ", address1=" + address1 + ", address2=" + address2 + ", mpoint=" + mpoint + "]";
//	}

	public Member() {
	
	}

	@Override
	public String toString() {
		return "Member [MUST_INPUT=" + MUST_INPUT + ", id=" + id + ", name=" + name + ", password=" + password
				+ ", salary=" + salary + ", hiredate=" + hiredate + ", gender=" + gender + ", hobby=" + hobby + ", job="
				+ job + ", zipcode=" + zipcode + ", address1=" + address1 + ", address2=" + address2 + ", mpoint="
				+ mpoint + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

//	public String getHobby() {
//		String hobbies = "" ;
//		if(this.hobby != null) {
//			for (int i = 0; i < hobby.length; i++) {
//				hobbies += hobby[i] + "," ; 
//			}
//		}
//		if(hobbies.equals("") == false) {
//			hobbies = hobbies.substring(0, hobbies.length() - 1) ; 
//		}
//		return hobbies ; 
//	}
//
//	public void setHobby(String[] hobby) {
//		this.hobby = hobby;
//	}



	public String getJob() {
		return job;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public int getMpoint() {
		return mpoint;
	}

	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}	
}