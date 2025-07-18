package dto;

public class PagingDTO {

	private int page; // 현재 페이지 번호
	private int pageSize; // 한 페이지에 보여줄 게시물 수
	private int totalCount; // 전체 게시물 수

	private int startPage; // 화면에 보여줄 시작 페이지 번호
	private int endPage; // 화면에 보여줄 마지막 페이지 번호
	private int totalPage; // 전체 페이지 수
	private boolean prev; // 이전 버튼 표시 여부
	private boolean next; // 다음 버튼 표시 여부

	public PagingDTO(int page, int pageSize, int totalCount) {
		this.page = page;
		this.pageSize = pageSize;
		this.totalCount = totalCount;

		// 전체 페이지 수 계산
		totalPage = (int) Math.ceil((double) totalCount / pageSize);

		// 화면에 보여줄 페이지네이션 번호 계산 (예: [1][2]...[10])
		// 현재 페이지를 기준으로 끝 페이지 번호를 먼저 계산
		endPage = (int) (Math.ceil((double) page / 10.0)) * 10;
		startPage = endPage - 9;

		// 실제 마지막 페이지 번호보다 endPage가 크면 조정
		if (endPage > totalPage) {
			endPage = totalPage;
		}

		// 이전, 다음 버튼 표시 여부
		prev = startPage > 1;
		next = endPage < totalPage;
	}

	// Getter/Setter
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}
}
