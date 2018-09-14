
/*********************************************
 *  Agent.java 
 *  Sample Agent for Text-Based Adventure Game
 *  COMP3411 Artificial Intelligence
 *  UNSW Session 1, 2016
*/

import java.util.*;
import java.io.*;
import java.net.*;

import classes.*;

public class Agent {

	static Context context;

	public char get_action(View v) {

		// REPLACE THIS CODE WITH AI TO CHOOSE ACTION

		int ch = 0;

		System.out.print("Enter Action(s): ");

		/*
		 * try { while (ch != -1) { // read character from keyboard ch =
		 * System.in.read();
		 * 
		 * switch (ch) { // if character is a valid action, return it case 'f':
		 * context.moveForward(); return ((char) ch); case 'l': context.turnLeft();
		 * return ((char) ch); case 'r': context.turnRight(); return ((char) ch); case
		 * 'c': case 'u': return ((char) ch); } } } catch (IOException e) {
		 * System.out.println("IO error:" + e); }
		 * 
		 */
		
		// Implement AI
		
		return this.getNextMove();
		
		
		// return 0;
	}
	
	
	protected char getNextMove() {
		
		
		
		return ' ';
	}
	
	

	void print_view(View view) {
		System.out.println(view.toString());
	}

	public static void main(String[] args) {
		InputStream in = null;
		OutputStream out = null;
		Socket socket = null;
		Agent agent = new Agent();

		char tempView[][] = new char[5][5];
		View view;

		char action = 'F';
		int port;
		int ch;
		int i, j;

		context = new Context();

		if (args.length < 2) {
			System.out.println("Usage: java Agent -p <port>\n");
			System.exit(-1);
		}

		port = Integer.parseInt(args[1]);

		try { // open socket to Game Engine
			socket = new Socket("localhost", port);
			in = socket.getInputStream();
			out = socket.getOutputStream();
		} catch (IOException e) {
			System.out.println("Could not bind to port: " + port);
			System.exit(-1);
		}

		try { // scan 5-by-5 wintow around current location
			while (true) {
				for (i = 0; i < 5; i++) {
					for (j = 0; j < 5; j++) {
						if (!((i == 2) && (j == 2))) {
							ch = in.read();
							if (ch == -1) {
								System.exit(-1);
							}

							tempView[i][j] = (char) ch;
						}
					}
				}
				view = new View(tempView);
				// agent.print_view(view); // COMMENT THIS OUT BEFORE SUBMISSION

				context.improveMap(tempView);
				context.printMap();
				action = agent.get_action(view);
				out.write(action);
			}
		} catch (IOException e) {
			System.out.println("Lost connection to port: " + port);
			System.exit(-1);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}
}