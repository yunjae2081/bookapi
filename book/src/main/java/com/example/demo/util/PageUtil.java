package com.example.demo.util;

public class PageUtil {

	// 디폴트 페이지 수
	private final int DEFAULT_PAGE_INDEX = 5;

	// 디폴트 게시물 수
	private final int DEFAULT_VIEW_INDEX = 10;

	// 표시할 페이지 수
	private int viewIndex = DEFAULT_PAGE_INDEX;

	// 표시할 게시물 수
	private int listCount = DEFAULT_VIEW_INDEX;

	// 전체 게시물 수
	private int listAllCount;

	// 보여줄 시작 페이지 index
	private int listStartNum;

	// 보여줄 마지막 페이지 index
	private int listEndNum;

	// 마지막 페이지 index
	private int lastPageNum;

	// 시작 게시물 index
	private int detailStartNum;

	// 마지막 게시물 index
	private int detailEndNum;

	// 현재 페이지 번호
	private int currentListNum;

	// 요청 페이지 번호
	private int reqPageNum;

	// 이전
	private int previous;

	// 다음
	private int next;

	public PageUtil(int listAllcount, int reqPageNum) {
		this.listAllCount = listAllcount;
		this.reqPageNum = reqPageNum <= 0 ? 1 : reqPageNum;
		setPageInfo();
	}

	public int getListAllCount() {
		return listAllCount;
	}

	public int getListStartNum() {
		return listStartNum;
	}

	public int getListEndNum() {
		return listEndNum;
	}

	public int getLastPageNum() {
		return lastPageNum;
	}

	public int getDetailStartNum() {
		return detailStartNum;
	}

	public int getDetailEndNum() {
		return detailEndNum;
	}

	public int getCurrentListNum() {
		return currentListNum;
	}

	public int getViewIndex() {
		return viewIndex;
	}

	public int getPrevious() {
		return previous;
	}

	public int getNext() {
		return next;
	}

	public void setViewIndex(int viewIndex) {
		this.viewIndex = viewIndex;
		setPageInfo();
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
		setPageInfo();
	}

	private void setPageInfo() {
		this.currentListNum = reqPageNum <= 0 ? 1 : reqPageNum;
		this.lastPageNum = (int) Math.ceil((double) this.listAllCount / this.listCount);
		if (this.currentListNum > this.lastPageNum) {
			this.currentListNum = this.lastPageNum;
		}
		this.detailStartNum = (this.currentListNum - 1) * this.listCount + 1;
		this.detailEndNum = this.detailStartNum + this.listCount - 1;
		this.listStartNum = ((int) Math.ceil((double) this.currentListNum / this.viewIndex) - 1) * this.viewIndex + 1;
		this.listEndNum = this.listStartNum + this.viewIndex - 1;
		if (this.listEndNum > this.lastPageNum) {
			this.listEndNum = this.lastPageNum;
		}
		
		this.previous = this.listStartNum == 1 ? 1 : this.listStartNum - 1;
		this.next = (this.listEndNum + 1) > this.lastPageNum ? this.lastPageNum : (this.listEndNum + 1);
	}
}
