package bean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class Board {
	private int no ;
	
	@Length(min=3, max=10, message="제목은 3글자 이상 10글자 이하이어야 합니다.")
	private String subject ;
	private String writer ;	
	@Length(min=4, max=10, message="비밀 번호는 4자리 이상 10자리 이하이어야 합니다.")
	private String password ;	
	@Length(min=4, max=10, message="글 내용은 5자리 이상 30자리 이하이어야 합니다.")
	private String content ;
	private int readhit ;

	@Pattern(regexp = "\\d{4}[-/]\\d{2}[-/]\\d{2}", message = "날짜는 yyyy/MM/dd 또는 yyyy-MM-dd 형식으로 입력해 주세요.")
	private String regdate ;	
	
	private String remark ;
	private int groupno ;
	private int orderno ;
	private int depth ;		
	
	@Override
	public String toString() {
		return "Board [no=" + no + ", subject=" + subject + ", writer=" + writer + ", password=" + password
				+ ", content=" + content + ", readhit=" + readhit + ", regdate=" + regdate + ", remark=" + remark
				+ ", groupno=" + groupno + ", orderno=" + orderno + ", depth=" + depth + "]";
	}
	
	public Board() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadhit() {
		return readhit;
	}

	public void setReadhit(int readhit) {
		this.readhit = readhit;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getGroupno() {
		return groupno;
	}

	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
}    