package bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class Product {
	private final String MUST_INPUT = "필수 입력 사항입니다." ;
	
	private int num ;	
	
	@Length(min=3, max=15, message="상품 이름은 3자리 이상 15자리 이하이어야 합니다.")
	private String name ;
	
	@Length(min=3, max=30, message="제조 회사 이름은 3자리 이상 30자리 이하이어야 합니다.")
	private String company ;

	@NotEmpty(message= "이미지 이름은 " + MUST_INPUT )
	private String image ; // 이미지 이름

	private MultipartFile abcd ;	
	
	public MultipartFile getAbcd() {
		return abcd;
	}
	public void setAbcd(MultipartFile abcd) {
		this.abcd = abcd;
		
		if (this.abcd != null) {
			this.image = this.abcd.getOriginalFilename();
		}
	}

	// 숫자 형식은 보통 @Min과 @Max을 사용하면 된다. 
	@Min(value=10, message="재고 수량은 최소 10개 이상이어야 합니다")
	private int stock ;
	
	private int price ;
	
//	지정한 범위의 숫자인지 확인합니다.
//	@Range(min=1) 이라고 함은 콤보 박스를 선택하지 않았을 경우를 의미합니다.
//	개발자는 view 페이지에서 첫번째 콤보 박스에 다음과 같은 형식으로 입력해 주어야 합니다.
//	     <option value="0">---선택해 주세요 ==> 선택된 값이 0이므로 min=1에 위배 됩니다.
//	@Range(min=1, message="직업을 선택해 주셔야 합니다.")
	
//	"상품 카테고리를 선택해 주셔야 합니다."
	private String category ;
	
	@Length(min=5, max=255, message="상품에 대한 설명은 5자리 이상 255자리 이하이어야 합니다.")
	private String contents ;
	
	
	@Min(value=5, message="포인트는 최소 5 이상이어야 합니다.")
	@Max(value=10, message="포인트는 최대 10 이하이어야 합니다.")
	private int point ;
	
	@Pattern(regexp = "\\d{4}[-/]\\d{2}[-/]\\d{2}", message = "입고 일자는 yyyy/MM/dd 또는 yyyy-MM-dd 형식으로 입력해 주세요.")
	private String inputdate ;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getInputdate() {
		return inputdate;
	}
	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}
	@Override
	public String toString() {
		return "Product [num=" + num + ", name=" + name + ", company=" + company + ", image=" + image + ", stock="
				+ stock + ", price=" + price + ", category=" + category + ", contents=" + contents + ", point=" + point
				+ ", inputdate=" + inputdate + "]";
	}
	
	public Product() {
	
	}
}