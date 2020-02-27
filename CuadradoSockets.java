package Sockets;

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
	Gato gato = new Gato();

	final int PORT = 50001;
	final String ADDRESS = "192.168.2.149";

	DataInputStream in;
	DataOutputStream out;
	Socket sc = null;
	
	public MiMarco()
	{
		setBounds(40, 40, 500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
			
		getContentPane().add(panel);
		
		gato.setOpaque(true);
		gato.setBounds(10, 10, 64, 64);
		
		panel.add(gato);
		
		setVisible(true);

		try
		{
			sc = new Socket(ADDRESS, PORT);

			in = new DataInputStream(sc.getInputStream());
			out = new DataOutputStream(sc.getOutputStream());

			out.writeUTF("Hola desde el pocho");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		while(true)
		{
			try
			{
				String recieved = in.readUTF();
				String[] coord = recieved.split(" ");

				gato.setLocation(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
			}
			catch(IOException el)
			{
				el.printStackTrace();
			}
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
		ImageIcon imagenGato = new ImageIcon("/home/acelgaletal/Java/Sockets/gatete.png");
		setIcon(imagenGato);
		setLocation(300, 40);
	}
}
