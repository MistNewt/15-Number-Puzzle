 package swingPrograms;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
public class exp101 implements ActionListener {
	JFrame frame;
	JPanel pnl,pnl1,pnl2;
	CardLayout cardLO;
	JButton [] btn=new JButton[16];
	public final int n=4;
	int [] indexArray=new int[15];
	exp101() {
		frame=new JFrame("Base");
		pnl=new JPanel();
		pnl2=new JPanel();
		pnl1=new JPanel();
		cardLO=new CardLayout();
		pnl1.setLayout(new GridLayout(n,n));
		pnl.setLayout(cardLO);
		pnl1.setFont(new Font("TimesNewRoman",Font.BOLD,24));
		frame.setSize(400, 400);
		resetIndexArray();
		int k=0,index=16;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(k>0){
					btn[k-1]=new JButton(""+getRandom(index));
					pnl1.add(btn[k-1]);
				}
				k++;
				index--;
			}
		}
		btn[15]=new JButton("Demo");
		pnl1.add(btn[15]);
		btn[15].setVisible(false);
		pnl.add(pnl1,"Buttons");
		pnl.add(pnl2,"GameOver");
		frame.add(pnl);
		cardLO.show(pnl,"Buttons");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public int getIndex(JButton b) {
		int index=0;
		for(int i=0;i<16;i++) {
			if(btn[i]==b)
				index=i;
		}
		return index;
	}
	public void addButtonListener() {
		for(int i=0;i<btn.length;i++) {
			btn[i].addActionListener(this);
		}
	}
	public void actionPerformed(ActionEvent ae) {
		//System.out.println("Action Source: "+ae.getSource());
		JButton b=(JButton) ae.getSource();

		swapButtons(b);
	}
	public void swapButtons(JButton b) {
		int index=getIndex(b);
		int []arr={index-n,index+n,index-1,index+1};
		for(int i=0;i<arr.length;i++){
			try{
				if(btn[arr[i]].isVisible()==false){
					String n=b.getText();
					b.setVisible(false);
					btn[arr[i]].setText(n);
					btn[arr[i]].setVisible(true);
				}
			}catch(ArrayIndexOutOfBoundsException e){
				continue;
			}
		}
	}
	private void resetIndexArray() {
		for(int i=0;i<indexArray.length;i++)
			indexArray[i]=i+1;
	}
	private int getRandom(int index) {
		int rand=ThreadLocalRandom.current().nextInt(0,index);
		int num=indexArray[rand];
		for(int i=rand;i<indexArray.length-1;i++){
			indexArray[i]=indexArray[i+1];
		}
		return num;
	}
	public static void main(String[]args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				exp101 ob=new exp101();
				ob.addButtonListener();
			}
		});
	}
}
