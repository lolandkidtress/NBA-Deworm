package ui.team;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ui.config.PanelConfig;
import ui.config.SystemConfig;
import ui.home.HomeUI;
import ui.util.MyButton;
import ui.util.MyLabel;
import ui.util.MyTab;

public class TeamPanel extends JPanel{

	private PanelConfig pcfg ;
	private HomeUI frame;
	
	public TeamFilter teamfilter;
	
	private MyTab stat;
	
	private MyLabel settingbg;
	
	private MyButton setting;
	private MyButton menu;
	
	public TeamPanel(HomeUI frame){
		this.pcfg = SystemConfig.getHOME_CONFIG().getConfigMap()
				.get(this.getClass().getName());
		this.frame = frame;
		// 设置布局管理器为自由布局
		this.setOpaque(false);
		this.setLayout(null);
		this.setSize(pcfg.getW(), pcfg.getH());
		this.setLocation(pcfg.getX(), pcfg.getY());
		// 初始化组件
		this.initComponent();
		this.repaint();
		
	}
	
	private void initComponent(){
		initPanel();
		initButtons();
		initLabels();
		initTab();
	}
	
	private void initPanel(){
		teamfilter = new TeamFilter(frame);
		teamfilter.setVisible(false);
		add(teamfilter);
	}
	
	private void initLabels(){
		settingbg = new MyLabel(pcfg.getLabels().element("settingbg"));
		add(settingbg);
	}
	
	private void initTab(){
		stat = new MyTab(pcfg.getTab().element("stat"));
		add(stat);
	}
	
	private void initButtons(){
		menu = new MyButton(pcfg.getButtons().element("menu"),true);
		add(menu);
		
		setting = new MyButton(pcfg.getButtons().element("setting"));
		setting.addMouseListener(new MouseAdapter(){
			String[] temp = pcfg.getButtons().element("setting").attributeValue("path").split("\\.");
			String path = temp[0];
			String fix = temp[1];
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(teamfilter.isVisible() == false){
					teamfilter.setVisible(true);
					}else{
						teamfilter.setVisible(false);
					}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(teamfilter.isVisible()==false)
				setting.setIcon(new ImageIcon(path+"_point."+fix));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(teamfilter.isVisible() == false)
					setting.setIcon(new ImageIcon(path+"."+fix));
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				setting.setIcon(new ImageIcon(path+"_click."+fix));
				
			}

		});
		add(setting);
	}

	public void filter(){
		setting.setIcon(new ImageIcon(pcfg.getButtons().element("setting").attributeValue("path")));
		teamfilter.setVisible(false);
	}
}