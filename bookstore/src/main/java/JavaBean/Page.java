package JavaBean;

import java.io.Serializable;
import java.util.List;

//业务bean  ---Page类,对象用于显示每一个分页的内容
public class Page<T> implements Serializable{

	// 5/19
	private int pageNo;   //当前页码  ---> 用户提供
	private int totalPageNo; //总页数 = 总条数/ pageSize  --->计算
	//总页数要考虑到会不会有remainder的情况
	private int totalRecord; //表中总记录条数  ---> DAO,sql:select count(*) 需要修改baseDAO
	public static final int PAGE_SIZE = 4; 
	private List<T> list;  //当前页的数据集合
	
	
	public Page(int pageNo, int totalPageNo, int totalRecord, List<T> list) {
		super();
		this.pageNo = pageNo;
		this.totalPageNo = totalPageNo;
		this.totalRecord = totalRecord;
		this.list = list;
	}
	
	public Page() {
		super();
	}
	
	public int getPageNo() {
		if(pageNo < 1)
			return 1;
		if(pageNo > getTotalPageNo()) {
	//因为totalPageNo是通过getTotalPageNo中的计算得来的,所以不能直接写pageNo > totalPageNo
			return getTotalPageNo();
		}
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	//计算总页数
	public int getTotalPageNo() {
		return (totalRecord % PAGE_SIZE == 0)? totalRecord/PAGE_SIZE: totalRecord/PAGE_SIZE + 1;
	}
	public void setTotalPageNo(int totalPageNo) {
		this.totalPageNo = totalPageNo;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public int getPAGE_SIZE() {
		return PAGE_SIZE;
	}

	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", totalPageNo=" + totalPageNo + ", totalRecord=" + totalRecord + ", list="
				+ list + "]";
	}
	
	

}
