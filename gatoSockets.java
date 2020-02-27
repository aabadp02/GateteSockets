package cuadradosockets;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

import javax.swing.*;

public class CuadradoSockets 
{
	public static void main(String[] args)
	{
		MiMarco escenario = new MiMarco();
	}
}

class MiMarco extends JFrame
{
	Panel panel = new Panel();
	SacarCoordenadas point = new SacarCoordenadas();
	Gato gato = new Gato();
	
	public MiMarco()
	{
		setBounds(500, 500, 500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
			
		getContentPane().add(panel);
		
		gato.setOpaque(true);
		gato.setBounds(10, 10, 64, 64);
		
		panel.add(gato);
		
		panel.addMouseListener(point);
		
		setVisible(true);
	}
	class SacarCoordenadas implements MouseListener
	{
		Socket sc;
		DataOutputStream out;
		DataInputStream in;
		
		@Override
		public void mouseClicked(MouseEvent e)
		{
			final String ADDRESS = "192.168.2.149";
			final int PORT = 50001;
			
			Point coordenadas = e.getPoint();
			
			try 
			{
				sc = new Socket(ADDRESS, PORT);
				
				out = new DataOutputStream(sc.getOutputStream());
				in = new DataInputStream(sc.getInputStream());
				
				String coord = Integer.toString(coordenadas.x - 32) + " " +  Integer.toString(coordenadas.y - 32);
				
				out.writeUTF(coord);
				
				String message = in.readUTF();
				
				String[] coordVuelta = message.split(" ");
				
				gato.setLocation(Integer.parseInt(coordVuelta[0]), Integer.parseInt(coordVuelta[1]));
				
				sc.close();
			} 
			catch (UnknownHostException e1) 
			{
				e1.printStackTrace();
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}

class Panel extends JLayeredPane
{
	public Panel()
	{
		setPreferredSize(new Dimension(500, 500));
	}
}

class Gato extends JLabel
{
	public Gato()
	{
		ImageIcon imagenGato = new ImageIcon("C:\\Users\\Alex\\Pictures\\Iconos\\gatete.png");
		setIcon(imagenGato);
		setLocation(300, 40);
	}
}
