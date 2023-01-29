package cn.wekyjay.www.tools.xfmanager.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

import cn.wekyjay.www.tools.xfmanager.manager.user.UserAvatar;

public class UserListRender extends DefaultListCellRenderer  {
	private static final long serialVersionUID = -2489676954666028957L;
	@Override 
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,boolean isSelected, boolean cellHasFocus) 
    { 
        String name = value.toString();
		setText(name);	//设置文字（获取每个元素文字信息将其显示）
        Image img = UserAvatar.getAvatar(name); //实例化Image对象获取ico对象的内容 
        if(img == null) System.out.println("检测到null图片");
        img = img.getScaledInstance (20,20,Image.SCALE_DEFAULT);		//把图片全部缩放为25x25
        setIcon(new ImageIcon(img));		//设置图标

        if(isSelected) {		//当某个元素被选中时
            setForeground(Color.WHITE);		//设置前景色（文字颜色）为白色
            setBackground(Color.BLUE);		//设置背景色为蓝色
        } else {		//某个元素未被选中时（取消选中）
            setForeground(Color.BLACK);		//设置前景色（文字颜色）为黑色
            setBackground(Color.WHITE);		//设置背景色为白色
        }
        return this;
    } 
}
