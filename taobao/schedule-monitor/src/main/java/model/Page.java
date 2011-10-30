package model;

import java.io.Serializable;

/**
 * ��ҳ����.
 * 
 */
public class Page implements Serializable {

	
	private static final long serialVersionUID = 3927459723286328872L;
	private static int DEFAULT_PAGE_SIZE = 20;
	private int pageSize = DEFAULT_PAGE_SIZE; // ÿҳ����ʾ��¼��
	private Object data; // ��ǰҳ�д�ŵļ�
	private long totalCount; // �ܼ�¼��
	private int currentPageNo; // ��ǰҳ�� ��1��ʼ
	private long totalPageCount; // ��ҳ��
	private int blockSize = 9; // �������С Ĭ��Ϊ9

	/**
	 * ���캯��
	 * 
	 * @param currentPageNo
	 *            ��ǰҳ��
	 * @param totalSize
	 *            ���ݿ����ܼ�¼��
	 * @param pageSize
	 *            ��ҳ����
	 * @param data
	 *            ��ҳ����������
	 */
	public Page(int currentPageNo, long totalSize, int pageSize, Object data) {
		this.pageSize = pageSize;
		this.currentPageNo = currentPageNo;
		this.totalCount = totalSize;
		this.data = data;
	}


	/**
	 * ���캯��
	 * 
	 * @param data
	 */
	public Page(Object data) {
		this.data = data;
	}

	public Page() {
	}

	/**
	 * �ܼ�¼��
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * ��ҳ��
	 */
	public long getTotalPageCount() {
		if (totalCount % pageSize == 0)
			totalPageCount = totalCount / pageSize;
		else
			totalPageCount = totalCount / pageSize + 1;
		return totalPageCount;
	}

	/**
	 * ȡÿҳ��ʾ��������
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * ȡ����
	 * 
	 * @return
	 */
	public Object getData() {
		return data;
	}

	/**
	 * ��ǰҳ��
	 */
	public int getCurrentPageNo() {
		if (totalCount == 0)
			currentPageNo = 0;
		return currentPageNo;
	}

	/**
	 * ��ҳ�Ƿ�����ҳ
	 */
	public boolean hasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount();
	}

	/**
	 * ��ҳ�Ƿ�����ҳ
	 */
	public boolean hasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}

	/**
	 * ���ص�ǰ�������
	 * 
	 * @return
	 */
	public int getBlockNo() {
		if (currentPageNo % blockSize == 0)
			return currentPageNo / blockSize;
		else
			return currentPageNo / blockSize + 1;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public static int getStartOfPage(int curPageNum, int pageSize) {
		if (curPageNum < 1) {
			curPageNum = 1;
		}
		return (curPageNum - 1) * pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public void setTotalPageCount(long totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
}