package com.shsxt.model;

import java.util.List;

/**
 * 保存分页信息
 * @author Administrator
 *
 */
public class PageInfo {
	
	public static  int MsgCount = 5;
	
	public int whopage;
	
	private int pageNum ; //当前页码
	
	private int totalPageNum ;//总页数
	
	private long totalMsg;  //全部记录数
	
	private int startIndexPage;  //起始索引页
		
	private List pageMsg;  //当前页码的信息
	
	private int prvlPage;  //上一页
	
	private int nextPage;  //下一页
	
	private int firstPage;  //首页
	
	private int lastPage ;  //末页
	
	private int startdaohang;  //开始导航页
	
	private int enddaohang;  //结束导航页
	
	public PageInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public PageInfo(long totalMsg,List pageMsg,int pageNum) {
		this.pageNum = pageNum;
		this.totalMsg = totalMsg;
		this.pageMsg = pageMsg;
		
		//总页数
		this.totalPageNum = (int) Math.ceil(totalMsg*1.0/MsgCount);
		//起始索引页
		this.startIndexPage = (pageNum-1)*MsgCount;
		//上一页
		this.prvlPage = pageNum - 1;
		//下一页
		this.nextPage = pageNum + 1;
		//首页
		this.firstPage = 1;
		//末页
		this.lastPage = totalPageNum;
		//开始导航页
		this.startdaohang = (pageNum-2)<=0 ? ((pageNum-1) <=0 ? pageNum : (pageNum-1)) : (pageNum-2);
		//结尾导航页
		this.enddaohang = (pageNum+2)>totalPageNum ? ((pageNum+1)>totalPageNum ? totalPageNum : (pageNum+1)) : (pageNum+2);
		//保证每次5条数据
		if(pageNum - startdaohang < 2){
			if(enddaohang + (2 - (pageNum - startdaohang)) <= totalPageNum){
				enddaohang = enddaohang + (2 - (pageNum - startdaohang));
			}else if (enddaohang + (1 - (pageNum - startdaohang)) <= totalPageNum){
				enddaohang = enddaohang + (1 - (pageNum - startdaohang));
			}
		}
		if(enddaohang - pageNum < 2){
			if(startdaohang - (2 - (enddaohang - pageNum)) >= 1){
				startdaohang = startdaohang - (2 - (enddaohang - pageNum));
			}else if (startdaohang - (1 - (enddaohang - pageNum)) >= 1){
				enddaohang = enddaohang + (1 - (enddaohang - pageNum));
			}
		}
	}

	
	
	public int getWhopage() {
		return whopage;
	}

	public void setWhopage(int whopage) {
		this.whopage = whopage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public long getTotalMsg() {
		return totalMsg;
	}

	public void setTotalMsg(long totalMsg) {
		this.totalMsg = totalMsg;
	}

	public int getStartIndexPage() {
		return startIndexPage;
	}

	public void setStartIndexPage(int startIndexPage) {
		this.startIndexPage = startIndexPage;
	}

	public int getMsgCount() {
		return MsgCount;
	}

	public void setMsgCount(int msgCount) {
		MsgCount = msgCount;
	}

	public List getPageMsg() {
		return pageMsg;
	}

	public void setPageMsg(List pageMsg) {
		this.pageMsg = pageMsg;
	}

	public int getPrvlPage() {
		return prvlPage;
	}

	public void setPrvlPage(int prvlPage) {
		this.prvlPage = prvlPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getStartdaohang() {
		return startdaohang;
	}

	public void setStartdaohang(int startdaohang) {
		this.startdaohang = startdaohang;
	}

	public int getEnddaohang() {
		return enddaohang;
	}

	public void setEnddaohang(int enddaohang) {
		this.enddaohang = enddaohang;
	}

	@Override
	public String toString() {
		return "PageInfo [pageNum=" + pageNum + ", totalPageNum="
				+ totalPageNum + ", totalMsg=" + totalMsg + ", startIndexPage="
				+ startIndexPage + ", pageMsg=" + pageMsg + ", prvlPage="
				+ prvlPage + ", nextPage=" + nextPage + ", firstPage="
				+ firstPage + ", lastPage=" + lastPage + ", startdaohang="
				+ startdaohang + ", enddaohang=" + enddaohang + "]";
	}
	
	
}
