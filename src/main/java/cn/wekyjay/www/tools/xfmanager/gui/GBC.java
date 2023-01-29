package cn.wekyjay.www.tools.xfmanager.gui;


import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GBC extends GridBagConstraints {
	
	private static final long serialVersionUID = 2742314297371795512L;
	/**
	 * 构造新的布局位置
	 * @param gridx
	 * @param gridy
	 */
	public GBC(int gridx, int gridy) {
		this.gridx = gridx;
		this.gridy = gridy;
	}
	/**
	 * 构造新的布局位置及占用行列大小
	 * @param gridx
	 * @param gridy
	 * @param gridwidth - 列
	 * @param gridheight - 行
	 */
	public GBC(int gridx, int gridy, int gridwidth, int gridheight) {
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}
	/**
	 * 设置组件在单元格内的位置排列
	 * @param anchor
	 * @return
	 */
	public GBC setAnchor(int anchor) {
		this.anchor = anchor;
		return this;
	}
	/**
	 * 设置组件填充方式
	 * @param fill
	 * @return
	 */
	public GBC setFill(int fill) {
		this.fill = fill;
		return this;
	}
	/**
	 * 设置组件的增量字段
	 * @param weightx
	 * @param weighty
	 * @return
	 */
	public GBC setWeight(double weightx,double weighty) {
		this.weightx = weightx;
		this.weighty = weighty;
		return this;
	}
	/**
	 * 设置外部填充
	 * @param distance
	 * @return
	 */
	public GBC setInsets(int distance) {
		this.insets = new Insets(distance, distance, distance, distance);
		return this;
	}
	/**
	 * 设置外部填充
	 * @param top
	 * @param left
	 * @param bottom
	 * @param right
	 * @return
	 */
	public GBC setInsets(int top, int left, int bottom, int right) {
		this.insets = new Insets(top, left, bottom, right);
		return this;
	}
	/**
	 * 设置内部填充
	 * @param ipadx
	 * @param ipady
	 * @return
	 */
	public GBC setIpad(int ipadx, int ipady) {
		this.ipadx = ipadx;
		this.ipady = ipady;
		return this;
	}
	
}
